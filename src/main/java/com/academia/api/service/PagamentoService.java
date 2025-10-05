/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.service;

import com.academia.api.dto.PagamentoDTO;
import com.academia.api.exception.RecursoNaoEncontradoException;
import com.academia.api.exception.RegraDeNegocioException;
import com.academia.api.model.Aluno;
import com.academia.api.model.Pagamento;
import com.academia.api.model.StatusPagamento;
import com.academia.api.repository.AlunoRepository;
import com.academia.api.repository.PagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kayqu
 */
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, AlunoRepository alunoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public PagamentoDTO registrarPagamento(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado com id: " + alunoId));

        if (aluno.getPlano() == null) {
            throw new RegraDeNegocioException("O aluno " + aluno.getNome() + " não possui um plano ativo para registrar o pagamento.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setValorPago(aluno.getPlano().getValor());
        pagamento.setStatus(StatusPagamento.PAGO);

        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return toDTO(pagamentoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PagamentoDTO> listarPagamentosPorAluno(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new RecursoNaoEncontradoException("Aluno não encontrado com id: " + alunoId);
        }
        return pagamentoRepository.findByAlunoId(alunoId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PagamentoDTO toDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getAluno().getId(),
                pagamento.getAluno().getNome(),
                pagamento.getDataPagamento(),
                pagamento.getValorPago(),
                pagamento.getStatus()
        );
    }
}
