package br.com.alura.challenger.SpringBoot.projeto.springBoot.repository;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);
    List<Livro> findByIdioma(String idioma);
}
