package com.example.demo.controllers;

import com.example.demo.dtos.AgendamentoDTO;
import com.example.demo.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoDTO> registrarAgendamento(@RequestBody AgendamentoDTO dto){
        return ResponseEntity.status(201).body(agendamentoService.registrarAgendamento(dto));
    }

    @PutMapping
    public ResponseEntity<AgendamentoDTO> editarAgendamento(@RequestBody AgendamentoDTO dto){
        return ResponseEntity.ok().body(agendamentoService.editarAgendamento(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> deletarAgendamento(@PathVariable Integer id){
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> listarAgendamentosPorCpf(@RequestParam String cpf){
        List<AgendamentoDTO> agendamentoDTOS = agendamentoService.listarAgendamentosPorUsuario(cpf);
        return agendamentoDTOS.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(agendamentoDTOS);
    }

    @GetMapping("/data")
    public ResponseEntity<List<AgendamentoDTO>> listarAgendamentosPorIntervaloDeData(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<AgendamentoDTO> agendamentoDTOS = agendamentoService.listarAgendamentosPorData(dataInicial, dataFinal);
        return agendamentoDTOS.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(agendamentoDTOS);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<AgendamentoDTO>> listarTodosAgendamentos(){
        List<AgendamentoDTO> agendamentoDTOS = agendamentoService.listarTodosAgendamentos();
        return agendamentoDTOS.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(agendamentoDTOS);
    }
}
