/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.service;

import com.academia.api.dto.TreinoDTO;
import com.academia.api.exception.RecursoNaoEncontradoException;
import com.academia.api.exception.RegraDeNegocioException;
import com.academia.api.model.Treino;
import com.academia.api.repository.TreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kayqu
 */
@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    @Transactional
    public TreinoDTO criarTreino(TreinoDTO treinoDTO) {
        Treino treino = new Treino();
        treino.setNome(treinoDTO.nome());
        treino.setDescricao(treinoDTO.descricao());
        treino.setNivel(treinoDTO.nivel());

        Treino treinoSalvo = treinoRepository.save(treino);
        return toDTO(treinoSalvo);
    }

    @Transactional(readOnly = true)
    public TreinoDTO buscarTreinoPorId(Long id) {
        Treino treino = findTreinoById(id);
        return toDTO(treino);
    }

    @Transactional(readOnly = true)
    public List<TreinoDTO> listarTreinos() {
        return treinoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TreinoDTO atualizarTreino(Long id, TreinoDTO treinoDTO) {
        Treino treino = findTreinoById(id);
        treino.setNome(treinoDTO.nome());
        treino.setDescricao(treinoDTO.descricao());
        treino.setNivel(treinoDTO.nivel());

        Treino treinoAtualizado = treinoRepository.save(treino);
        return toDTO(treinoAtualizado);
    }

    @Transactional
    public void deletarTreino(Long id) {
        Treino treino = findTreinoById(id);

        if (!treino.getAlunos().isEmpty()) {
            throw new RegraDeNegocioException("Não é possível excluir o treino, pois ele está associado a " + treino.getAlunos().size() + " aluno(s).");
        }

        treinoRepository.delete(treino);
    }

    private Treino findTreinoById(Long id) {
        return treinoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Treino não encontrado com id: " + id));
    }

    private TreinoDTO toDTO(Treino treino) {
        return new TreinoDTO(treino.getId(), treino.getNome(), treino.getDescricao(), treino.getNivel());
    }
}
