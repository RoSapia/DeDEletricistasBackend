package com.example.demo.services;

import com.example.demo.dtos.ClienteDTO;
import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Endereco;
import com.example.demo.exception.ClienteNaoEncontradoException;
import com.example.demo.mappers.ClienteMapper;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO){
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Endereco endereco = cliente.getEndereco();

        endereco = enderecoRepository.save(endereco);
        cliente.setPerfil("USUARIO");
        cliente.setEndereco(endereco);

        cliente = clienteRepository.save(cliente);
        return ClienteMapper.toDto(cliente);
    }

    @Transactional(readOnly = true)
    public Map<String, String> login(LoginDTO loginDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            Map<String, Object> claims = new HashMap<>();
            claims.put("perfil", cliente.getPerfil());
            claims.put("cpf", cliente.getCpf());
            claims.put("nomeCompleto", cliente.getNomeCompleto());
            Map<String, String> token = new HashMap<>();
            token.put("token", jwtUtil.generateToken(loginDTO.getEmail(), claims));
            return token;
        }

        throw new ClienteNaoEncontradoException("Credencias inválidas");
    }

}
