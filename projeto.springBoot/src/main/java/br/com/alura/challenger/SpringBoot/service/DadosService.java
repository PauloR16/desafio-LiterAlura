package br.com.alura.challenger.SpringBoot.service;

import br.com.alura.challenger.SpringBoot.DTO.LivroDto;
import br.com.alura.challenger.SpringBoot.model.Autor;
import br.com.alura.challenger.SpringBoot.model.Livro;
import br.com.alura.challenger.SpringBoot.repository.AutorRepository;
import br.com.alura.challenger.SpringBoot.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DadosService {

    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;

    public DadosService(LivroRepository livroRepo, AutorRepository autorRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public void salvarLivros(List<LivroDto> dtos) {
        for (LivroDto dto : dtos) {
            System.out.println("🔄 Processando livro: " + dto.getTitulo());
            System.out.println("Autor DTO: " + dto.getAutorNome() + " (" + dto.getAutorNascimento() + "-" + dto.getAutorFalecimento() + ")");

            // 1. Criar autor
            Autor autor = new Autor(dto.getAutorNome(), dto.getAutorNascimento(), dto.getAutorFalecimento());

            // 2. Buscar ou salvar autor
            Autor autorExistente = autorRepo.findByNome(dto.getAutorNome())
                    .orElseGet(() -> {
                        Autor salvo = autorRepo.save(autor);
                        System.out.println("💾 Autor salvo: " + salvo.getNome());
                        return salvo;
                    });

            // 3. Criar livro
            Livro livro = new Livro();
            livro.setTitulo(dto.getTitulo());
            livro.setIdioma(dto.getIdioma());
            livro.setNumeroDownloads(dto.getDownloads());
            livro.setAutor(autorExistente);

            // 4. Salvar livro
            try {
                livroRepo.save(livro);
                System.out.println("✅ Livro salvo: " + livro.getTitulo());
            } catch (Exception e) {
                System.err.println("❌ Erro ao salvar livro: " + livro.getTitulo());
                e.printStackTrace();
            }
        }

        System.out.println("✅ Todos os livros processados.");
    }

    public List<Livro> listarTodosLivros() {
        return livroRepo.findAll();
    }

    public List<Autor> listarAutoresUnicos() {
        return livroRepo.findAll().stream().map(Livro::getAutor).distinct().toList();
    }

    public List<Autor> listarAutoresVivosEmAno(int ano) {
        return autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepo.findByIdioma(idioma);
    }
}
