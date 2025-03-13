package br.com.carbigdata.teste.utils;

import org.springframework.stereotype.Service;

@Service
public class UtilDocuments {

    public  String clearDocument(String document) {
        if(document.length() == 11){
            return document;
        }
        return document.replaceAll("\\D", "");
    }

    public String maskCpf(String cpf) {
        if(cpf == null){
            return null;
        }
        return cpf.substring(0, 3) + ".***.***-" + cpf.substring(9);
    }
}
