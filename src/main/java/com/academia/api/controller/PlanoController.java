/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;

import com.academia.api.dto.PlanoDTO;
import com.academia.api.service.PlanoService;
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
@RequestMapping("/api/v1/planos")
@Tag(name = "Planos", description = "Endpoints para gerenciamento dos planos da academia")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar novo plano",
            description = "Cria um novo plano de assinatura para os alunos da academia."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Plano criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO planoDTO) {
        PlanoDTO novoPlano = planoService.criarPlano(planoDTO);
        return new ResponseEntity<>(novoPlano, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar plano por ID",
            description = "Retorna os detalhes de um plano específico através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plano encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planoService.buscarPlanoPorId(id));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os planos",
            description = "Retorna uma lista com todos os planos cadastrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        return ResponseEntity.ok(planoService.listarPlanos());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar plano",
            description = "Atualiza as informações de um plano existente no sistema através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    public ResponseEntity<PlanoDTO> atualizarPlano(@PathVariable Long id, @RequestBody PlanoDTO planoDTO) {
        return ResponseEntity.ok(planoService.atualizarPlano(id, planoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar plano",
            description = "Remove um plano do sistema de forma permanente através do seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Plano deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
