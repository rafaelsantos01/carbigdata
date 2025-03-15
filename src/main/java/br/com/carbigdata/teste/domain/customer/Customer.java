package br.com.carbigdata.teste.domain.customer;

import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Occurrence> occurrenceList;

    @PrePersist
    protected void onCreate() {
        this.dtaCriacao = new Timestamp(System.currentTimeMillis());
    }
}
