/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;

import com.academia.api.dto.AlunoDTO;
import com.academia.api.dto.VincularTreinoDTO;
import com.academia.api.service.AlunoService;
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
@RequestMapping("/api/v1/alunos")
@Tag(name = "Alunos", description = "Endpoints para gerenciamento de alunos da academia")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar novo aluno",
            description = "Cria um novo registro de aluno no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO novoAluno = alunoService.criarAluno(alunoDTO);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar aluno por ID",
            description = "Retorna os dados de um aluno específico através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarAlunoPorId(id));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os alunos",
            description = "Retorna uma lista com todos os alunos cadastrados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar dados do aluno",
            description = "Atualiza as informações de um aluno existente pelo ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, alunoDTO));
    }

    @PatchMapping("/{id}/inativar")
    @Operation(
            summary = "Inativar aluno",
            description = "Marca um aluno como inativo no sistema, sem removê-lo do banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aluno inativado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id) {
        alunoService.inativarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/vincular-treino")
    @Operation(
            summary = "Vincular treino a um aluno",
            description = "Associa um treino existente a um aluno específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Treino vinculado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Aluno ou treino não encontrado")
    })
    public ResponseEntity<Void> vincularTreino(@RequestBody VincularTreinoDTO dto) {
        alunoService.vincularTreino(dto);
        return ResponseEntity.ok().build();
    }
}
