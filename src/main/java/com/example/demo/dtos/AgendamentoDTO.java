package com.example.demo.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDTO {

    private Integer id;
    private String tituloDoServico;
    private String descricao;
    private LocalDate dataAgendamento;
    private LocalTime horario;
    private String clienteCpf;

    public AgendamentoDTO() {
    }

    public AgendamentoDTO(Integer id, String tituloDoServico, String descricao, LocalDate dataAgendamento, LocalTime horario, String clienteCpf) {
        this.id = id;
        this.tituloDoServico = tituloDoServico;
        this.descricao = descricao;
        this.dataAgendamento = dataAgendamento;
        this.horario = horario;
        this.clienteCpf = clienteCpf;
    }

    public String getTituloDoServico() {
        return tituloDoServico;
    }

    public void setTituloDoServico(String tituloDoServico) {
        this.tituloDoServico = tituloDoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
