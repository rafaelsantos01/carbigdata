package br.com.carbigdata.teste.domain.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "Cliente")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codCliente;

    @Column(name = "nme_cliente", nullable = false)
    private String nmeCliente;

    @Column(name = "dta_nascimento", nullable = false)
    private Date dtaNascimento;

    @Column(name = "nro_cpf", nullable = false)
    private String nro_cpf;

    @Column(name = "dta_criacao", nullable = false)
    private Timestamp dtaCriacao;

}
