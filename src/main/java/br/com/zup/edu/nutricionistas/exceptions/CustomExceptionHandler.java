package br.com.zup.edu.nutricionistas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        Integer codigoHttp = badRequestStatus.value();
        String mensagemHttp = badRequestStatus.getReasonPhrase();

        List<String> mensagens = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();

            if (field.equals("dataNascimento")) {
                field = "data_nascimento";
            }

            mensagens.add(field + ": " + fieldError.getDefaultMessage());
        }

        String palavraErro = mensagens.size() == 1 ? "erro" : "erros";
        String mensagemGeral = "Validação falhou com " + mensagens.size() + " " + palavraErro + ".";

        ErroPadronizado erroPadronizado = new ErroPadronizado(
            codigoHttp, mensagemHttp, mensagemGeral, mensagens
        );

        return ResponseEntity.badRequest().body(erroPadronizado);

    }

}
