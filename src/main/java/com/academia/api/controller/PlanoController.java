/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.controller;
import com.academia.api.dto.PlanoDTO;
import com.academia.api.service.PlanoService;
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
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO planoDTO) {
        PlanoDTO novoPlano = planoService.criarPlano(planoDTO);
        return new ResponseEntity<>(novoPlano, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planoService.buscarPlanoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        return ResponseEntity.ok(planoService.listarPlanos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@PathVariable Long id, @RequestBody PlanoDTO planoDTO) {
        return ResponseEntity.ok(planoService.atualizarPlano(id, planoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
