package br.com.alura.challenger.SpringBoot.utilitarios;

public class AutorUtils {

    public static String normalizarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "Desconhecido";
        }

        if (nome.contains(",")) {
            String[] partes = nome.split(",");
            String sobrenome = partes[0].trim();
            String primeiroNome = (partes.length > 1) ? partes[1].trim() : "";

            return (primeiroNome + " " + sobrenome).replaceAll("\\s+", " ").trim();
        }

        return nome.trim();
    }
}
