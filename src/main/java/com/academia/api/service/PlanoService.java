/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.service;

import com.academia.api.dto.PlanoDTO;
import com.academia.api.exception.RecursoNaoEncontradoException;
import com.academia.api.model.Plano;
import com.academia.api.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kayqu
 */
@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    @Transactional
    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        Plano plano = new Plano();
        plano.setNome(planoDTO.nome());
        plano.setValor(planoDTO.valor());

        Plano planoSalvo = planoRepository.save(plano);
        return toDTO(planoSalvo);
    }

    @Transactional(readOnly = true)
    public PlanoDTO buscarPlanoPorId(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado com id: " + id));
        return toDTO(plano);
    }

    @Transactional(readOnly = true)
    public List<PlanoDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlanoDTO atualizarPlano(Long id, PlanoDTO planoDTO) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado com id: " + id));

        plano.setNome(planoDTO.nome());
        plano.setValor(planoDTO.valor());

        Plano planoAtualizado = planoRepository.save(plano);
        return toDTO(planoAtualizado);
    }

    @Transactional
    public void deletarPlano(Long id) {
        if (!planoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Plano não encontrado com id: " + id);
        }
        planoRepository.deleteById(id);
    }

    private PlanoDTO toDTO(Plano plano) {
        return new PlanoDTO(plano.getId(), plano.getNome(), plano.getValor());
    }
}
