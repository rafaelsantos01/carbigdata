package br.com.carbigdata.teste.controller.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank
    private String nme_cliente;

    private Date dta_nascimento;

    @CPF
    private String nro_cpf;
}
