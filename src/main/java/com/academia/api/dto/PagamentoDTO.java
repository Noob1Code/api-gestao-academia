/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.dto;
import com.academia.api.model.StatusPagamento;
import java.time.LocalDate;
/**
 *
 * @author Kayqu
 */
public record PagamentoDTO(
        Long id,
        Long alunoId,
        String nomeAluno,
        LocalDate dataPagamento,
        Double valorPago,
        StatusPagamento status
) {}
