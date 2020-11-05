package br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.controllers;

import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.dtos.IndexResultadoRequestDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.dtos.IndexResultadoResponseDTO;
import br.com.cwi.sicredi.pautas.useCases.votacoes.resultados.services.IndexResultadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "votacoes/resultado")
public class IndexResultadoController {

    private final IndexResultadoService service;

    public IndexResultadoController(IndexResultadoService service) {
        this.service = service;
    }

    @GetMapping(
            value = "{idVotacao}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public IndexResultadoResponseDTO handle(@PathVariable("idVotacao") UUID idVotacao) {
        return service.execute(idVotacao);
    }
}
