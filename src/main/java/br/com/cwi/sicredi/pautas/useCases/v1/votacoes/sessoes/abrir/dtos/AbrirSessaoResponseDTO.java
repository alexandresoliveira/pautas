package br.com.cwi.sicredi.pautas.useCases.votacoes.sessoes.abrir.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AbrirSessaoResponseDTO {

    private UUID idVotacao;
    private String pauta;
    private Date encerraEm;
}
