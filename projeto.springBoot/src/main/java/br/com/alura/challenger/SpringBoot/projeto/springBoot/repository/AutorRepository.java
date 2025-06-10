package br.com.alura.challenger.SpringBoot.projeto.springBoot.repository;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int ano, int ano1);
}