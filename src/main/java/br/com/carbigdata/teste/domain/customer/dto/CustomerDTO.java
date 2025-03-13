package br.com.carbigdata.teste.domain.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class CustomerDTO {
    private Long codCliente;
    private String nmeCliente;
    private Date dtaNascimento;
    private String nroCpf;
    private Timestamp dtaCriacao;

}