package br.com.alura.challenger.SpringBoot.service;

import br.com.alura.challenger.SpringBoot.DTO.LivroDto;
import br.com.alura.challenger.SpringBoot.utilitarios.AutorUtils;
import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaApi {

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    public List<LivroDto> buscarLivros(String termo) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            String urlFinal = URL_BASE + URLEncoder.encode(termo, StandardCharsets.UTF_8);
            System.out.println("🔗 Buscando na URL: " + urlFinal);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlFinal))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: Código HTTP " + response.statusCode());
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = json.getAsJsonArray("results");

            List<LivroDto> livros = new ArrayList<>();

            for (JsonElement element : results) {
                JsonObject obj = element.getAsJsonObject();

                String titulo = obj.get("title").getAsString();

                JsonArray autoresArray = obj.getAsJsonArray("authors");
                String autorNome = "Desconhecido";
                Integer anoNascimento = null;
                Integer anoFalecimento = null;

                if (autoresArray.size() > 0) {
                    JsonObject autorJson = autoresArray.get(0).getAsJsonObject();
                    autorNome = autorJson.get("name").getAsString();
                    autorNome = AutorUtils.normalizarNome(autorNome);

                    if (autorJson.has("birth_year")) {
                        JsonElement birthYearElement = autorJson.get("birth_year");
                        if (!birthYearElement.isJsonNull() && birthYearElement.isJsonPrimitive()) {
                            try {
                                int valor = birthYearElement.getAsInt();
                                System.out.println("Valor extraído de birth_year: " + valor); // ✅ Confirmação direta
                                anoNascimento = valor;
                            } catch (Exception e) {
                                System.err.println("Erro ao converter birth_year para Integer");
                                e.printStackTrace();
                            }
                        }
                    }

                    if (autorJson.has("death_year")) {
                        JsonElement deathYearElement = autorJson.get("death_year");
                        if (!deathYearElement.isJsonNull()) {
                            try {
                                anoFalecimento = ((JsonPrimitive) deathYearElement).getAsInt();
                            } catch (Exception e) {
                                System.err.println("Erro ao converter death_year para Integer");
                            }
                        }
                    }
                }

                JsonArray linguasArray = obj.getAsJsonArray("languages");
                String idioma = linguasArray.size() > 0 ? linguasArray.get(0).getAsString() : "N/A";

                int downloads = obj.has("download_count") ? obj.get("download_count").getAsInt() : 0;


                LivroDto dto = new LivroDto(titulo, autorNome, anoNascimento, anoFalecimento, idioma, downloads);


                livros.add(dto);
            }

            return livros;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao fazer requisição à API: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar livros: " + e.getMessage(), e);
        }
    }
}
