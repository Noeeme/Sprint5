package com.sprint.sprint5.handler;

import com.sprint.sprint5.exceptions.item.*;
import com.sprint.sprint5.exceptions.oferta.*;
import com.sprint.sprint5.exceptions.pedido.CpfInvalido;
import com.sprint.sprint5.exceptions.pedido.PedidoNotFound;
import com.sprint.sprint5.exceptions.pedido.TotalInvalido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    private static final String PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado.";
    private static final String CPF_INVALIDO = "Cpf inválido";
    private static final String TOTAL_INVALIDO = "Total inválido";

    private static final String NOME_ITEM_INVALIDO = "Nome do item inválido";
    private static final String DATA_CRIACAO_ITEM_INVALIDO = "Data de criação do item inválido";
    private static final String DATA_VALIDADE_ITEM_INVALIDA = "Data de validade do item inválido";
    private static final String VALOR_ITEM_INVALIDO = "Valor do item inválido";
    private static final String DESCRICAO_ITEM_INVALIDO = "Descrição do item inválido";

    private static final String NOME_OFERTA_INVALIDO = "Nome da oferta inválido";
    private static final String DATA_CRIACAO_OFERTA_INVALIDA = "Data de criação da oferta inválida";
    private static final String DATA_VALIDADE_OFERTA_INVALIDA = "Data de validade da oferta inválida";
    private static final String DESCONTO_INVALIDO = "Desconto da oferta inválido";
    private static final String DESCRICAO_OFERTA_INVALIDO = "Descrição da oferta inválida";


    @ExceptionHandler(value = {PedidoNotFound.class})
    protected ResponseEntity<ErroMensagem> handlerPedidoNaoEncontrado(PedidoNotFound e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(PEDIDO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = {CpfInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerCpfInvalido(CpfInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(CPF_INVALIDO));
    }

    @ExceptionHandler(value = {TotalInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerTotalInvalido(TotalInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(TOTAL_INVALIDO));
    }
    @ExceptionHandler(value = {NomeItemInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerNomeItemInvalido(NomeItemInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(NOME_ITEM_INVALIDO));
    }
    @ExceptionHandler(value = {DataCriacaoItemInvalida.class})
    protected ResponseEntity<ErroMensagem> handlerDataCriacaoItemInvalido(DataCriacaoItemInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DATA_CRIACAO_ITEM_INVALIDO));
    }
    @ExceptionHandler(value = {DataValidadeItemInvalida.class})
    protected ResponseEntity<ErroMensagem> handlerDataValidadeItemInvalido(DataValidadeItemInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DATA_VALIDADE_ITEM_INVALIDA));
    }
    @ExceptionHandler(value = {ValorItemInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerValorItemInvalido(ValorItemInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(VALOR_ITEM_INVALIDO));
    }
    @ExceptionHandler(value = {DescricaoItemInvalida.class})
    protected ResponseEntity<ErroMensagem> handlerDescricaoItemInvalido(DescricaoItemInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DESCRICAO_ITEM_INVALIDO));
    }
    @ExceptionHandler(value = {NomeOfertaInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerNomeOfertaInvalido(NomeOfertaInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(NOME_OFERTA_INVALIDO));
    }
    @ExceptionHandler(value = {DataCriacaoOfertaInvalida.class})
    protected ResponseEntity<ErroMensagem> handlerDataCriacaoOfertaInvalido(DataCriacaoOfertaInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DATA_CRIACAO_OFERTA_INVALIDA));
    }
    @ExceptionHandler(value = {DataDeValidadeDaOfertaInvalidaException.class})
    protected ResponseEntity<ErroMensagem> handlerDataValidadeOfertaInvalido(DataDeValidadeDaOfertaInvalidaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DATA_VALIDADE_OFERTA_INVALIDA));
    }
    @ExceptionHandler(value = {DescontoInvalido.class})
    protected ResponseEntity<ErroMensagem> handlerDescontoInvalido(DescontoInvalido e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DESCONTO_INVALIDO));
    }
    @ExceptionHandler(value = {DescricaoOfertaInvalida.class})
    protected ResponseEntity<ErroMensagem> handlerDescricaoOfertaInvalido(DescricaoOfertaInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(DESCRICAO_OFERTA_INVALIDO));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroMensagem(validationList));
    }
}
