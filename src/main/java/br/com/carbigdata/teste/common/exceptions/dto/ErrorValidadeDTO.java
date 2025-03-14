package br.com.carbigdata.teste.common.exceptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorValidadeDTO {

    private String field;

    private String message;

    public ErrorValidadeDTO(){
    }

    public ErrorValidadeDTO(String field,String message){
        this.field = field;
        this.message = message;
    }
}