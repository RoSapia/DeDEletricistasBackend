package com.example.demo.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {
    public AgendamentoNaoEncontradoException(String s) {
        super(s);
    }
}
