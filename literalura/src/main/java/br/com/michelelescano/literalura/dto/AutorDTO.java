package br.com.michelelescano.literalura.dto;


import java.util.List;

public record AutorDTO(String nome,
                       Integer anoNascimento,
                       Integer anoFalecimento,
                       List<String> livros) {
}