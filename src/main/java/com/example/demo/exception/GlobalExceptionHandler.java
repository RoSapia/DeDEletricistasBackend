package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> clienteNaoEncontrado(ClienteNaoEncontradoException e){
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        map.put("status", 404);
        return ResponseEntity.status(404).body(map);
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> agendamentoNaoEncontrado(AgendamentoNaoEncontradoException e){
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        map.put("status", 404);
        return ResponseEntity.status(404).body(map);
    }
}
