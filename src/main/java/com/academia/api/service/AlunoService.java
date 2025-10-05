/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.service;

import com.academia.api.dto.AlunoDTO;
import com.academia.api.dto.VincularTreinoDTO;
import com.academia.api.exception.RecursoNaoEncontradoException;
import com.academia.api.exception.RegraDeNegocioException;
import com.academia.api.model.Aluno;
import com.academia.api.model.Plano;
import com.academia.api.model.StatusAluno;
import com.academia.api.model.Treino;
import com.academia.api.repository.AlunoRepository;
import com.academia.api.repository.PlanoRepository;
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
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final TreinoRepository treinoRepository;

    public AlunoService(AlunoRepository alunoRepository, PlanoRepository planoRepository, TreinoRepository treinoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.treinoRepository = treinoRepository;
    }

    @Transactional
    public AlunoDTO criarAluno(AlunoDTO alunoDTO) {
        alunoRepository.findByCpf(alunoDTO.cpf()).ifPresent(aluno -> {
            throw new RegraDeNegocioException("O CPF " + alunoDTO.cpf() + " já está cadastrado.");
        });

        Plano plano = planoRepository.findById(alunoDTO.planoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado com id: " + alunoDTO.planoId()));

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome());
        aluno.setCpf(alunoDTO.cpf());
        aluno.setDataNascimento(alunoDTO.dataNascimento());
        aluno.setPlano(plano);
        aluno.setStatus(StatusAluno.ATIVO);

        Aluno alunoSalvo = alunoRepository.save(aluno);
        return toDTO(alunoSalvo);
    }

    @Transactional(readOnly = true)
    public AlunoDTO buscarAlunoPorId(Long id) {
        Aluno aluno = findAlunoById(id);
        return toDTO(aluno);
    }

    @Transactional(readOnly = true)
    public List<AlunoDTO> listarAlunos() {
        return alunoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDTO atualizarAluno(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = findAlunoById(id);
        Plano plano = planoRepository.findById(alunoDTO.planoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado com id: " + alunoDTO.planoId()));

        aluno.setNome(alunoDTO.nome());
        aluno.setDataNascimento(alunoDTO.dataNascimento());
        aluno.setPlano(plano);
        aluno.setStatus(alunoDTO.status());

        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return toDTO(alunoAtualizado);
    }

    @Transactional
    public void inativarAluno(Long id) {
        Aluno aluno = findAlunoById(id);
        aluno.setStatus(StatusAluno.INATIVO);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void vincularTreino(VincularTreinoDTO dto) {
        Aluno aluno = findAlunoById(dto.alunoId());
        Treino treino = treinoRepository.findById(dto.treinoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Treino não encontrado com id: " + dto.treinoId()));

        aluno.getTreinos().add(treino);
        alunoRepository.save(aluno);
    }

    private Aluno findAlunoById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado com id: " + id));
    }

    private AlunoDTO toDTO(Aluno aluno) {
        Long planoId = (aluno.getPlano() != null) ? aluno.getPlano().getId() : null;
        String nomePlano = (aluno.getPlano() != null) ? aluno.getPlano().getNome() : null;

        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getStatus(),
                planoId,
                nomePlano
        );
    }
}
