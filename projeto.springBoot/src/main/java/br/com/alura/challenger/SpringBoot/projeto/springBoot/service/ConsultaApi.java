package br.com.alura.challenger.SpringBoot.projeto.springBoot.service;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Livro;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaApi {

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    public List<Livro> buscarLivros(String termo) {
        try {
            // Configura HttpClient para seguir redirecionamentos
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS) // <-- Aqui está a mágica!
                    .build();

            String urlFinal = URL_BASE + URLEncoder.encode(termo, StandardCharsets.UTF_8);
            System.out.println("🔗 Buscando na URL: " + urlFinal);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFinal))
                    .header("Accept", "application/json")
                    .timeout(java.time.Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: Código HTTP " + response.statusCode());
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = json.getAsJsonArray("results");

            List<Livro> livros = new ArrayList<>();

            for (JsonElement element : results) {
                JsonObject obj = element.getAsJsonObject();

                String titulo = obj.get("title").getAsString();

                JsonArray autoresArray = obj.getAsJsonArray("authors");
                String autor = autoresArray.size() > 0 ? autoresArray.get(0).getAsJsonObject().get("name").getAsString() : "Desconhecido";

                JsonArray linguasArray = obj.getAsJsonArray("languages");
                String idioma = linguasArray.size() > 0 ? linguasArray.get(0).getAsString() : "N/A";

                int downloads = obj.has("download_count") ? obj.get("download_count").getAsInt() : 0;

                livros.add(new Livro(titulo, autor, idioma, downloads));
            }

            return livros;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao fazer requisição à API: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar livros: " + e.getMessage(), e);
        }
    }
}
