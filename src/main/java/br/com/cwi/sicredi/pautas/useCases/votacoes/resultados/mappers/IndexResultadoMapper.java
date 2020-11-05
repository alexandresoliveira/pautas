package br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.mappers;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Votacao;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.dtos.IndexResultadoResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos.AbrirSessaoResponseDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexResultadoMapper {

    private final MapperFactory mapperFactory;
    private final MapperFacade mapperFacade;

    public IndexResultadoMapper() {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();

        this.mapperFactory
                .classMap(IndexResultadoResponseDTO.class, Votacao.class)
                .field("pauta", "pauta.name")
                .field("dataEncerramento", "dataEncerramento")
                .byDefault()
                .register();

        this.mapperFacade = this.mapperFactory.getMapperFacade();
    }

    public IndexResultadoResponseDTO convertDtoWithEntity(Votacao votacao) {
        return mapperFacade.map(votacao, IndexResultadoResponseDTO.class);
    }
}
