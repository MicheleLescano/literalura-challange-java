package br.com.michelelescano.literalura.principal;

import br.com.michelelescano.literalura.dto.AutorDTO;
import br.com.michelelescano.literalura.dto.LivroDTO;
import br.com.michelelescano.literalura.model.*;
import br.com.michelelescano.literalura.model.repository.AutorRepository;
import br.com.michelelescano.literalura.model.repository.LivroRepository;
import br.com.michelelescano.literalura.records.DadosAutor;
import br.com.michelelescano.literalura.records.DadosBusca;
import br.com.michelelescano.literalura.records.DadosLivro;
import br.com.michelelescano.literalura.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            leitura.nextLine();

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
                    return;
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

        List<LivroDTO> livrosDTO = livros.stream()
                .map(livro -> new LivroDTO(
                        livro.getTitulo(),
                        livro.getAutor().getNome(),
                        livro.getIdioma(),
                        livro.getNumeroDeDownloads()))
                .collect(Collectors.toList());

        if (livrosDTO.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("----- LIVROS CADASTRADOS -----");
            livrosDTO.forEach(livro -> System.out.printf("""
                            Título: %s
                            Autor: %s
                            Idioma: %s
                            Downloads: %d
                            --------------------------------%n""",
                    livro.titulo(),
                    livro.autor(),
                    livro.idioma(),
                    livro.numeroDeDownloads()));
        }
    }


    private void listarAutoresRegistrados() {

        List<Autor> autores = autorRepositorio.findAll();

        List<AutorDTO> autoresDTO = autores.stream()
                .map(autor -> new AutorDTO(
                        autor.getNome(),
                        autor.getAnoNascimento(),
                        autor.getAnoFalecimento(),
                        autor.getLivros().stream()
                                .map(livro -> livro.getTitulo())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());


        if (autoresDTO.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            System.out.println("----- AUTORES CADASTRADOS -----");
            autoresDTO.forEach(autor -> System.out.printf("""
                            Autor: %s
                            Ano de Nascimento: %d
                            Ano de Falecimento: %d
                            Livros: %s
                            --------------------------------%n""",
                    autor.nome(),
                    autor.anoNascimento(),
                    autor.anoFalecimento(),
                    autor.livros()));
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

            List<AutorDTO> autoresDTO = autores.stream()
                    .map(autor -> new AutorDTO(
                            autor.getNome(),
                            autor.getAnoNascimento(),
                            autor.getAnoFalecimento(),
                            autor.getLivros().stream().map(Livro::getTitulo).collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());

            autoresDTO.forEach(autor -> System.out.printf("""
                Autor: %s
                Ano de Nascimento: %d
                Ano de Falecimento: %d
                Livros: %s
                --------------------------------%n""",
                    autor.nome(), autor.anoNascimento(), autor.anoFalecimento(), autor.livros()));
        }
    }
    private void listarLivrosPorIdioma() {
        System.out.println("""
            Digite o idioma para a busca:
            en - inglês
            es - espanhol
            pt - português
            fr - francês""");
        var idioma = leitura.nextLine();

        List<Livro> livros = livroRepositorio.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'");
        } else {
            System.out.println("----- LIVROS NO IDIOMA '" + idioma.toUpperCase() + "' -----");

            List<LivroDTO> livrosDTO = livros.stream()
                    .map(livro -> new LivroDTO(
                            livro.getTitulo(),
                            livro.getAutor().getNome(),
                            livro.getIdioma(),
                            livro.getNumeroDeDownloads()))
                    .collect(Collectors.toList());

            livrosDTO.forEach(livro -> System.out.printf("""
                Título: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                --------------------------------%n""",
                    livro.titulo(), livro.autor(), livro.idioma(), livro.numeroDeDownloads()));
        }
    }

}

