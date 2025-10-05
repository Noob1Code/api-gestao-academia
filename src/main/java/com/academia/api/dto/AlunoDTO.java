/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.dto;
import com.academia.api.model.StatusAluno;
import java.time.LocalDate;
/**
 *
 * @author Kayqu
 */
public record AlunoDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        StatusAluno status,
        Long planoId,
        String nomePlano
) {}