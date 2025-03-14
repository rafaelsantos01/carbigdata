package br.com.carbigdata.teste.domain.address.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long codEndereco;
    private String nmeLogradouro;
    private String nmeBairro;
    private String nroCep;
    private String nmeCidade;
    private String nmeEstado;
}