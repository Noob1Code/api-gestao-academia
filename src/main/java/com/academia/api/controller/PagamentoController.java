/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;
import com.academia.api.dto.PagamentoDTO;
import com.academia.api.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pagamentos", description = "Endpoints para gerenciamento dos pagamentos dos alunos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/aluno/{alunoId}")
    @Operation(
            summary = "Registrar pagamento para um aluno",
            description = "Registra um novo pagamento para o aluno informado pelo ID. "
                        + "Geralmente usado para confirmar mensalidades ou outros serviços."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<PagamentoDTO> registrarPagamento(@PathVariable Long alunoId) {
        PagamentoDTO novoPagamento = pagamentoService.registrarPagamento(alunoId);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/aluno/{alunoId}")
    @Operation(
            summary = "Listar pagamentos de um aluno",
            description = "Retorna o histórico de pagamentos de um aluno específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentosPorAluno(alunoId));
    }
}