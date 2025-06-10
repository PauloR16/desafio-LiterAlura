package br.com.alura.challenger.SpringBoot.projeto.springBoot.DTO;


public class LivroDto {

    private String titulo;
    private String autorNome;
    private Integer autorNascimento;
    private Integer autorFalecimento;
    private String idioma;
    private int downloads;

    public LivroDto(String titulo, String autorNome, Integer autorNascimento, Integer autorFalecimento, String idioma, int downloads) {
        this.titulo = titulo;
        this.autorNome = autorNome;
        this.autorNascimento = autorNascimento;
        this.autorFalecimento = autorFalecimento;
        this.idioma = idioma;
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "LivroDto{" +
                "titulo='" + titulo + '\'' +
                ", autorNome='" + autorNome + '\'' +
                ", autorNascimento=" + autorNascimento +
                ", autorFalecimento=" + autorFalecimento +
                ", idioma='" + idioma + '\'' +
                ", downloads=" + downloads +
                '}';

        // Getters


    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public Integer getAutorNascimento() {
        return autorNascimento;
    }

    public void setAutorNascimento(Integer autorNascimento) {
        this.autorNascimento = autorNascimento;
    }

    public Integer getAutorFalecimento() {
        return autorFalecimento;
    }

    public void setAutorFalecimento(Integer autorFalecimento) {
        this.autorFalecimento = autorFalecimento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
}