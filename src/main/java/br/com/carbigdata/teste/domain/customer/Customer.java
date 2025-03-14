package br.com.carbigdata.teste.domain.customer;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "cliente")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codCliente;

    @Column(name = "nme_cliente", nullable = false)
    private String nmeCliente;

    @Column(name = "dta_nascimento")
    private Date dtaNascimento;

    @Column(name = "nro_cpf", nullable = false)
    private String nroCpf;

    @Column(name = "dta_criacao", nullable = false)
    private Timestamp dtaCriacao;

    @PrePersist
    protected void onCreate() {
        this.dtaCriacao = new Timestamp(System.currentTimeMillis());
    }
}
