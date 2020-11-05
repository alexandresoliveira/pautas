package br.com.cwi.sicredi.pautas.useCases.votos.store.mappers;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Votacao;
import br.com.cwi.sicredi.pautas.shared.databases.entities.Voto;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.votos.store.dtos.StoreVotoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votos.store.services.StoreVotoService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class StoreVotoMapper {

    private final MapperFactory mapperFactory;
    private final MapperFacade mapperFacade;

    public StoreVotoMapper() {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();

        this.mapperFactory
                .classMap(StoreVotoRequestDTO.class, Voto.class)
                .field("votacaoId", "votacao.id")
                .byDefault()
                .register();

        this.mapperFacade = this.mapperFactory.getMapperFacade();
    }

    public Voto createEntityWith(StoreVotoRequestDTO dto) {
        return mapperFacade.map(dto, Voto.class);
    }
}
