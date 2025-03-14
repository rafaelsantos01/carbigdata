package br.com.carbigdata.teste.domain.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Table(name = "ocorrencia")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Occurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ocorrencia")
    private Long codOcorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_cliente", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_endereco", nullable = false)
    private Address address;

    @Column(name = "dta_ocorrencia", nullable = false)
    private Timestamp dtaOcorrencia;

    @Column(name = "sta_ocorrencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private SITUATION_INCIDENT staOcorrencia;

    @OneToMany(mappedBy = "occurrence", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoOccurrence> photoOccurrences;

}
