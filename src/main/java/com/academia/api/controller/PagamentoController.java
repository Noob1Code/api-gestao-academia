/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;
import com.academia.api.dto.PagamentoDTO;
import com.academia.api.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 *
 * @author Kayqu
 */
@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/aluno/{alunoId}")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@PathVariable Long alunoId) {
        PagamentoDTO novoPagamento = pagamentoService.registrarPagamento(alunoId);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentosPorAluno(alunoId));
    }
}
