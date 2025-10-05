/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;
import com.academia.api.dto.AlunoDTO;
import com.academia.api.dto.VincularTreinoDTO;
import com.academia.api.service.AlunoService;
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
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO novoAluno = alunoService.criarAluno(alunoDTO);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarAlunoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, alunoDTO));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id) {
        alunoService.inativarAluno(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/vincular-treino")
    public ResponseEntity<Void> vincularTreino(@RequestBody VincularTreinoDTO dto) {
        alunoService.vincularTreino(dto);
        return ResponseEntity.ok().build();
    }
}
