package br.com.alura.challenger.SpringBoot.projeto.springBoot.repository;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer ano, Integer ano2);
}