package br.com.carbigdata.teste.domain.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class CustomerDTO {
    Long codCliente;
    String nmeCliente;
    Date dtaNascimento;
    String nroCpf;
    Timestamp dtaCriacao;
}