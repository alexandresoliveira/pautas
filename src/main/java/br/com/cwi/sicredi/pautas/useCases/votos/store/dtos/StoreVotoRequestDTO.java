package br.com.cwi.sicredi.pautas.useCases.votos.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreVotoRequestDTO {

    @NotNull
    private UUID votacaoId;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 3, max = 3)
    private String resposta;
}
