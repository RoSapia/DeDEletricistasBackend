package com.example.demo.mappers;

import com.example.demo.dtos.ClienteDTO;
import com.example.demo.entities.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setNomeCompleto(dto.getNomeCompleto());
        cliente.setSenha(dto.getSenha());
        cliente.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        return cliente;
    }

    public static ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(cliente.getCpf(),
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getSenha(),
                EnderecoMapper.toDto(cliente.getEndereco()));
    }
}
