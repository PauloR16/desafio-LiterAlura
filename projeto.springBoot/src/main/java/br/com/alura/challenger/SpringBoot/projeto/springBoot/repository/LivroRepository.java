package br.com.alura.challenger.SpringBoot.projeto.springBoot.repository;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {


    @Query("SELECT l FROM Livro l JOIN FETCH l.autor")
    List<Livro> findAllWithAutor();


    // Alternativa: busca por idioma
    List<Livro> findByIdioma(String idioma);
}
