package br.com.cwi.sicredi.pautas.shared.http.controllers.advices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MethodArgumentNotValidExceptionResponseDTO {

    private String target;
    private String message;
}
