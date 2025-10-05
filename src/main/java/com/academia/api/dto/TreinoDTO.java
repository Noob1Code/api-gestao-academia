/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.dto;
import com.academia.api.model.NivelTreino;
/**
 *
 * @author Kayqu
 */
public record TreinoDTO(
        Long id,
        String nome,
        String descricao,
        NivelTreino nivel
) {}
