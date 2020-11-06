package br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.services;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Pauta;
import br.com.cwi.sicredi.pautas.shared.databases.entities.Votacao;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.PautaRepository;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.VotacaoRepository;
import br.com.cwi.sicredi.pautas.shared.exceptions.ServiceApiException;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.mappers.AbrirSessaoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AbrirSessaoService {

    private final VotacaoRepository votacaoRepository;
    private final PautaRepository pautaRepository;
    private final AbrirSessaoMapper mapper;

    public AbrirSessaoService(
            VotacaoRepository votacaoRepository,
            PautaRepository pautaRepository,
            AbrirSessaoMapper mapper) {
        this.votacaoRepository = votacaoRepository;
        this.pautaRepository = pautaRepository;
        this.mapper = mapper;
    }

    public AbrirSessaoResponseDTO execute(AbrirSessaoRequestDTO dto) {
        Optional<Pauta> optionalPauta = pautaRepository.findById(dto.getPautaId());
        if (optionalPauta.isPresent()) {
            Votacao votacao = new Votacao();
            votacao.setPauta(optionalPauta.get());
            votacao.setDataEncerramento(verificarDataEncerramento(dto.getEncerraEm()));
            Votacao entity = votacaoRepository.saveAndFlush(votacao);
            return mapper.createResponseDtoWith(entity);
        }
        throw new ServiceApiException("Pauta não encontrada!");
    }

    private Date verificarDataEncerramento(Date encerraEm) {
        if (null == encerraEm) {
            LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(1);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return encerraEm;
    }
}
