package br.com.alura.challenger.SpringBoot.projeto.springBoot.service;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Autor;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Livro;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.repository.AutorRepository;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosService {

    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;

    public DadosService(LivroRepository livroRepo, AutorRepository autorRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public void salvarLivros(List<Livro> livros) {
        livroRepo.saveAll(livros);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepo.findAll();
    }

    public List<String> listarAutoresUnicos() {
        return livroRepo.findAll().stream().map(Livro::getAutor).distinct().toList();
    }

    public List<Autor> listarAutoresVivosEmAno(int ano) {
        return autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepo.findByIdioma(idioma);
    }
}
