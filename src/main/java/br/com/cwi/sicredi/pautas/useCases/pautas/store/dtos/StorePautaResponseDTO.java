package br.com.cwi.sicredi.pautas.useCases.pautas.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StorePautaResponseDTO {

    private UUID id;
    private String nome;
    private Date dataCriacao;
}
