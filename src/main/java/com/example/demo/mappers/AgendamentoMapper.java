package com.example.demo.mappers;

import com.example.demo.dtos.AgendamentoDTO;
import com.example.demo.entities.Agendamento;

public class AgendamentoMapper {

    public static Agendamento toEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setDataAgendamento(dto.getDataAgendamento());
        agendamento.setDescricao(dto.getDescricao());
        agendamento.setTituloDoServico(dto.getTituloDoServico());
        agendamento.setHorario(dto.getHorario());
        return agendamento;
    }

    public static AgendamentoDTO toDto(Agendamento entity) {
        return new AgendamentoDTO(entity.getId(),
                entity.getTituloDoServico(),
                entity.getDescricao(),
                entity.getDataAgendamento(),
                entity.getHorario(),
                entity.getCliente().getCpf());
    }

    public static Agendamento toEntity(Agendamento entity, AgendamentoDTO dto) {
        if (dto.getDataAgendamento() != null) entity.setDataAgendamento(dto.getDataAgendamento());
        if (dto.getDescricao() != null) entity.setDescricao(dto.getDescricao());
        if (dto.getHorario() != null) entity.setHorario(dto.getHorario());
        if (dto.getTituloDoServico() != null) entity.setTituloDoServico(dto.getTituloDoServico());
        return entity;
    }
}
