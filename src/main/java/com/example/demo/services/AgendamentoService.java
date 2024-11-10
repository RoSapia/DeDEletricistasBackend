package com.example.demo.services;

import com.example.demo.dtos.AgendamentoDTO;
import com.example.demo.entities.Agendamento;
import com.example.demo.entities.Cliente;
import com.example.demo.exception.AgendamentoNaoEncontradoException;
import com.example.demo.exception.ClienteNaoEncontradoException;
import com.example.demo.mappers.AgendamentoMapper;
import com.example.demo.repositories.AgendamentoRepository;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public AgendamentoDTO registrarAgendamento(AgendamentoDTO dto){
        Agendamento agendamento = AgendamentoMapper.toEntity(dto);
        Cliente cliente = clienteRepository.findById(dto.getClienteCpf()).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com o cpf informado não existe"));
        agendamento.setCliente(cliente);

        agendamento = agendamentoRepository.save(agendamento);
        return AgendamentoMapper.toDto(agendamento);
    }

    @Transactional(readOnly = true)
    public List<AgendamentoDTO> listarAgendamentosPorUsuario(String clienteCpf) {
        List<Agendamento> agendamentosByCpf = agendamentoRepository.findAgendamentosByCpf(clienteCpf);
        return agendamentosByCpf.stream().map(AgendamentoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<AgendamentoDTO> listarAgendamentosPorData(LocalDate dataInicial, LocalDate dataFinal) {
        List<Agendamento> agendamentosByCpf = agendamentoRepository.findByDataAgendamentoBetween(dataInicial, dataFinal);
        return agendamentosByCpf.stream().map(AgendamentoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<AgendamentoDTO> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(AgendamentoMapper::toDto).toList();
    }

    @Transactional
    public AgendamentoDTO editarAgendamento(AgendamentoDTO dto){
        Agendamento agendamento = agendamentoRepository.findById(dto.getId()).orElseThrow(() -> new AgendamentoNaoEncontradoException("Agendamento não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteCpf()).orElseThrow(() -> new ClienteNaoEncontradoException("O CPF informado não foi encontrado"));
        agendamento.setCliente(cliente);
        agendamento = AgendamentoMapper.toEntity(agendamento, dto);
        agendamento = agendamentoRepository.save(agendamento);
        return AgendamentoMapper.toDto(agendamento);
    }

    @Transactional
    public void deletarAgendamento(Integer id) {
        if(agendamentoRepository.existsById(id)){
            agendamentoRepository.deleteById(id);
        } else {
            throw new AgendamentoNaoEncontradoException("Agendamento não encontrado");
        }
    }
}
