package com.example.demo.mappers;

import com.example.demo.dtos.EnderecoDTO;
import com.example.demo.entities.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep());
        endereco.setComplemento(dto.getComplemento());
        endereco.setNumero(dto.getNumero());
        return endereco;
    }

    public static EnderecoDTO toDto(Endereco entity) {
        return new EnderecoDTO(entity.getCep(), entity.getNumero(), entity.getComplemento());
    }
}
