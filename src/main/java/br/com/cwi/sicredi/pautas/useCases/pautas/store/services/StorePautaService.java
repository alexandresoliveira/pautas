package br.com.cwi.sicredi.pautas.useCases.pautas.store.services;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Pauta;
import br.com.cwi.sicredi.pautas.shared.databases.repositories.PautaRepository;
import br.com.cwi.sicredi.pautas.useCases.pautas.store.dtos.StorePautaRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.pautas.store.dtos.StorePautaResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.pautas.store.mappers.StorePautaMapper;
import org.springframework.stereotype.Service;

@Service
public class StorePautaService {

    private final PautaRepository repository;
    private final StorePautaMapper mapper;

    public StorePautaService(PautaRepository repository, StorePautaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StorePautaResponseDTO execute(StorePautaRequestDTO dto) {
        Pauta entity = mapper.createEntityWith(dto);
        Pauta pauta = repository.saveAndFlush(entity);
        return mapper.createResponseDtoWith(pauta);
    }
}
