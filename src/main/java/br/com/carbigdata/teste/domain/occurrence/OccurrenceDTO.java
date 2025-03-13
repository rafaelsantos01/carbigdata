package br.com.carbigdata.teste.domain.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class OccurrenceDTO {
    private Long codOcorrencia;

    private Timestamp dtaOcorrencia;

    private SITUATION_INCIDENT staOcorrencia;

    private CustomerDTO customer;

    private AddressDTO address;
}