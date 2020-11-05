package br.com.cwi.sicredi.pautas.useCases.votos.store.services;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Votacao;
import br.com.cwi.sicredi.pautas.shared.databases.entities.Voto;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.VotacaoRepository;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.VotoRepository;
import br.com.cwi.sicredi.pautas.shared.exceptions.ServiceApiException;
import br.com.cwi.sicredi.pautas.useCases.votos.store.dtos.StoreVotoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votos.store.mappers.StoreVotoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class StoreVotoService {

    private final VotoRepository votoRepository;
    private final VotacaoRepository votacaoRepository;
    private final StoreVotoMapper mapper;

    public StoreVotoService(
            VotoRepository votoRepository,
            VotacaoRepository votacaoRepository,
            StoreVotoMapper mapper) {
        this.votoRepository = votoRepository;
        this.votacaoRepository = votacaoRepository;
        this.mapper = mapper;
    }

    public void execute(StoreVotoRequestDTO dto) {
        Voto entity = mapper.createEntityWith(dto);
        entity.setDataVoto(new Date());

        if (!cpfValido(entity.getCpf())) {
            throw new ServiceApiException("O cpf informado não é valido!", HttpStatus.NOT_FOUND);
        }

        Optional<Votacao> optionalVotacao = votacaoRepository.findById(entity.getVotacao().getId());
        if (!optionalVotacao.isPresent()) {
            throw new ServiceApiException("A sessão de votação não foi encontrada!", HttpStatus.NOT_FOUND);
        }

        Votacao votacao = optionalVotacao.get();
        if (!entity.getDataVoto().before(votacao.getDataEncerramento())) {
            throw new ServiceApiException("A sessão de votação foi encerrada!", HttpStatus.PRECONDITION_FAILED);
        }

        Optional<Voto> optionalVoto = votoRepository.findByVotacaoAndCpf(votacao, entity.getCpf());
        if (optionalVoto.isPresent()) {
            throw new ServiceApiException("O seu voto já foi registrado nesta sessão de votação!");
        }

        entity.setVotacao(votacao);

        votoRepository.saveAndFlush(entity);
    }

    private boolean cpfValido(String cpf) {
        return true;
    }
}
