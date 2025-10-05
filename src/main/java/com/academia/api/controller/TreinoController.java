/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;
import com.academia.api.dto.TreinoDTO;
import com.academia.api.service.TreinoService;
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
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<TreinoDTO> criarTreino(@RequestBody TreinoDTO treinoDTO) {
        TreinoDTO novoTreino = treinoService.criarTreino(treinoDTO);
        return new ResponseEntity<>(novoTreino, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDTO> buscarTreinoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(treinoService.buscarTreinoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        return ResponseEntity.ok(treinoService.listarTreinos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(@PathVariable Long id, @RequestBody TreinoDTO treinoDTO) {
        return ResponseEntity.ok(treinoService.atualizarTreino(id, treinoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }
}
