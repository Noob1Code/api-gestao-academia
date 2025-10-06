/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;

import com.academia.api.dto.TreinoDTO;
import com.academia.api.service.TreinoService;
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
@RequestMapping("/api/v1/treinos")
@Tag(name = "Treinos", description = "Endpoints para gerenciamento dos treinos da academia")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar novo treino",
            description = "Cria um novo treino no sistema com base nos dados fornecidos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Treino criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<TreinoDTO> criarTreino(@RequestBody TreinoDTO treinoDTO) {
        TreinoDTO novoTreino = treinoService.criarTreino(treinoDTO);
        return new ResponseEntity<>(novoTreino, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar treino por ID",
            description = "Retorna os detalhes de um treino específico através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Treino encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    public ResponseEntity<TreinoDTO> buscarTreinoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(treinoService.buscarTreinoPorId(id));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os treinos",
            description = "Retorna uma lista contendo todos os treinos cadastrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        return ResponseEntity.ok(treinoService.listarTreinos());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar treino",
            description = "Atualiza as informações de um treino existente no sistema pelo ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Treino atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    public ResponseEntity<TreinoDTO> atualizarTreino(@PathVariable Long id, @RequestBody TreinoDTO treinoDTO) {
        return ResponseEntity.ok(treinoService.atualizarTreino(id, treinoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar treino",
            description = "Remove um treino do sistema permanentemente através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Treino deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    public ResponseEntity<Void> deletarTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }
}
