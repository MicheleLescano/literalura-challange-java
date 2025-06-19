package br.com.michelelescano.literalura.service;

import br.com.michelelescano.literalura.model.*;
import br.com.michelelescano.literalura.model.repository.AutorRepository;
import br.com.michelelescano.literalura.model.repository.LivroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ObjectMapper conversor = new ObjectMapper();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    *************************************************
                    Escolha o número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    *************************************************
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                     listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                     listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro que você deseja buscar:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));

        try {
            DadosBusca dadosBusca = conversor.readValue(json, DadosBusca.class);
            Optional<DadosLivro> dadosLivro = dadosBusca.resultados().stream().findFirst();

            if (dadosLivro.isPresent()) {
                DadosLivro livroEncontrado = dadosLivro.get();

                Optional<Livro> livroExistente = livroRepositorio.findByTituloContainingIgnoreCase(livroEncontrado.titulo());

                if (livroExistente.isPresent()) {
                    System.out.println("O livro '" + livroExistente.get().getTitulo() + "' já está cadastrado.");
                    return; // Para a execução do método aqui
                }

                DadosAutor dadosAutor = livroEncontrado.autores().get(0);
                Optional<Autor> autorExistente = autorRepositorio.findByNomeContainingIgnoreCase(dadosAutor.nome());

                Autor autor;
                if (autorExistente.isPresent()) {

                    autor = autorExistente.get();
                    System.out.println("Autor já existente no banco: " + autor.getNome());
                } else {

                    autor = new Autor(dadosAutor);
                    System.out.println("Novo autor cadastrado: " + autor.getNome());
                }


                Livro novoLivro = new Livro(livroEncontrado);
                novoLivro.setAutor(autor);

                autor.getLivros().add(novoLivro);
                autorRepositorio.save(autor);

                System.out.println("Livro '" + novoLivro.getTitulo() + "' cadastrado com sucesso!");

            } else {
                System.out.println("Livro não encontrado na base de dados da API.");
            }

        } catch (JsonProcessingException e) {
            System.out.println("Não foi possível converter os dados recebidos da API.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepositorio.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("----- LIVROS REGISTRADOS -----");
            livros.forEach(System.out::println);
            System.out.println("-----------------------------");
        }
    }


    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            System.out.println("----- AUTORES REGISTRADOS -----");
            autores.forEach(System.out::println);
            System.out.println("-----------------------------");
        }
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para pesquisar os autores vivos:");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepositorio.autoresVivosEmDeterminadoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para o ano de " + ano);
        } else {
            System.out.println("----- AUTORES VIVOS EM " + ano + " -----");
            autores.forEach(System.out::println);
            System.out.println("-----------------------------");
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma para a busca ex: " +
                "\n en- inglês" +
                "\n es- espanhol" +
                "\n pt- português" +
                "\n fr- francês");
        var idioma = leitura.nextLine();

        List<Livro> livros = livroRepositorio.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'");
        } else {
            System.out.println("----- LIVROS NO IDIOMA '" + idioma + "' -----");
            livros.forEach(System.out::println);
            System.out.println("-----------------------------");
        }
    }
}
