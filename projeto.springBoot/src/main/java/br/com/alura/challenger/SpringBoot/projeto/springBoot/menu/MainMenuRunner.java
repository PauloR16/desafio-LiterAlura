package br.com.alura.challenger.SpringBoot.projeto.springBoot.menu;

import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Autor;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.model.Livro;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.service.ConsultaApi;
import br.com.alura.challenger.SpringBoot.projeto.springBoot.service.DadosService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MainMenuRunner implements ApplicationRunner {

    private final ConsultaApi consultaApi;
    private final DadosService dadosService;

    public MainMenuRunner(ConsultaApi consultaApi, DadosService dadosService) {
        this.consultaApi = consultaApi;
        this.dadosService = dadosService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Buscar e salvar livros");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título ou autor: ");
                    String termo = scanner.nextLine();

                    List<Livro> livros = consultaApi.buscarLivros(termo);

                    if (livros.isEmpty()) {
                        System.out.println("⚠️ Nenhum livro encontrado.");
                        break;
                    }

                    System.out.println("\n📚 Livros encontrados:");
                    for (Livro livro : livros) {
                        System.out.printf("Título: %s | Autor: %s | Idioma: %s | Downloads: %d%n",
                                livro.getTitulo(), livro.getAutor(), livro.getIdioma(), livro.getNumeroDownloads());
                    }

                    System.out.print("\n💾 Deseja salvar estes livros no banco? (S/N): ");
                    String resposta = scanner.nextLine().trim().toUpperCase();

                    if ("S".equals(resposta)) {
                        dadosService.salvarLivros(livros);
                        System.out.println("✅ Livros salvos com sucesso!");
                    } else {
                        System.out.println("❌ Livros não salvos.");
                    }
                    break;

                case 2:
                    List<Livro> todosLivros = dadosService.listarTodosLivros();
                    if (todosLivros.isEmpty()) {
                        System.out.println("🚫 Nenhum livro registrado.");
                    } else {
                        System.out.println("\n📚 Livros Registrados:");
                        for (Livro l : todosLivros) {
                            System.out.printf("Título: %s | Autor: %s | Idioma: %s | Downloads: %d%n",
                                    l.getTitulo(), l.getAutor(), l.getIdioma(), l.getNumeroDownloads());
                        }
                    }
                    break;

                case 3:
                    List<String> autores = dadosService.listarAutoresUnicos();
                    if (autores.isEmpty()) {
                        System.out.println("🚫 Nenhum autor registrado.");
                    } else {
                        System.out.println("\n✍️ Autores Registrados:");
                        autores.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    List<Autor> autoresVivos = dadosService.listarAutoresVivosEmAno(ano);
                    if (autoresVivos.isEmpty()) {
                        System.out.println("🚫 Nenhum autor vivo nesse ano.");
                    } else {
                        System.out.println("\n👨‍🦳 Autores vivos em " + ano + ":");
                        autoresVivos.forEach(a -> System.out.println(a.getNome()));
                    }
                    break;

                case 5:
                    System.out.print("Digite o idioma (ex: pt, en, es): ");
                    String idioma = scanner.nextLine();
                    List<Livro> livrosPorIdioma = dadosService.listarLivrosPorIdioma(idioma);
                    if (livrosPorIdioma.isEmpty()) {
                        System.out.println("🚫 Nenhum livro encontrado nesse idioma.");
                    } else {
                        System.out.println("\n📚 Livros em '" + idioma + "':");
                        for (Livro l : livrosPorIdioma) {
                            System.out.printf("Título: %s | Autor: %s | Idioma: %s | Downloads: %d%n",
                                    l.getTitulo(), l.getAutor(), l.getIdioma(), l.getNumeroDownloads());
                        }
                    }
                    break;

                case 0:
                    System.out.println("👋 Saindo...");
                    return;

                default:
                    System.out.println("❌ Opção inválida.");
            }
        }
    }
}
