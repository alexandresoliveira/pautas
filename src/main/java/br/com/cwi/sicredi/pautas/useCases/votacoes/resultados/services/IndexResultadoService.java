package br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.services;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Votacao;
import br.com.cwi.sicredi.pautas.shared.databases.enums.VotoResposta;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.VotacaoRepository;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.VotoRepository;
import br.com.cwi.sicredi.pautas.shared.exceptions.ServiceApiException;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.dtos.IndexResultadoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.dtos.IndexResultadoResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.mappers.IndexResultadoMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class IndexResultadoService {

    private static String EM_ANDAMENTO = "Em andamento";
    private static String ENCERRADO = "Encerrado";

    private final VotacaoRepository votacaoRepository;
    private final VotoRepository votoRepository;
    private final IndexResultadoMapper mapper;

    public IndexResultadoService(
            VotacaoRepository votacaoRepository,
            VotoRepository votoRepository,
            IndexResultadoMapper mapper) {
        this.votacaoRepository = votacaoRepository;
        this.votoRepository = votoRepository;
        this.mapper = mapper;
    }

    public IndexResultadoResponseDTO execute(UUID idVotacao) {
        Optional<Votacao> optionalVotacao = votacaoRepository.findById(idVotacao);

        if (!optionalVotacao.isPresent()) {
            throw new ServiceApiException("Sessão de votação não encontrada!");
        }

        List<Map<String, Object>> votosByVotacao = votoRepository.countVotosByVotacao(optionalVotacao.get().getId());

        IndexResultadoResponseDTO responseDTO = mapper.convertDtoWithEntity(optionalVotacao.get());

        votosByVotacao.forEach(voto -> {
            if (voto.get("RESPOSTA").equals(VotoResposta.SIM.name())) {
                responseDTO.setNumVotosSim((BigInteger) voto.get("NUM_VOTOS"));
            } else if (voto.get("RESPOSTA").equals(VotoResposta.NAO.name())) {
                responseDTO.setNumVotosNao((BigInteger) voto.get("NUM_VOTOS"));
            }
        });

        responseDTO.setStatus(new Date().before(responseDTO.getDataEncerramento()) ? EM_ANDAMENTO : ENCERRADO);

        return responseDTO;
    }
}
