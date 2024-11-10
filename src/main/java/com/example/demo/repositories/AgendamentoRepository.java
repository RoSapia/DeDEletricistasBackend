package com.example.demo.repositories;

import com.example.demo.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query("SELECT a FROM Agendamento a WHERE a.cliente.cpf = :cpf")
    List<Agendamento> findAgendamentosByCpf(@Param("cpf") String cpf);

    List<Agendamento> findByDataAgendamentoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
