package br.com.carbigdata.teste.domain.address;

import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "endereco")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long codEndereco;

    @Column(name = "nme_logradouro", nullable = false)
    private String nmeLogradouro;

    @Column(name = "nme_bairro", nullable = false)
    private String nmeBairro;

    @Column(name = "nro_cep", nullable = false)
    private String nroCep;

    @Column(name = "nme_cidade", nullable = false)
    private String nmeCidade;

    @Column(name = "nme_estado", nullable = false)
    private String nmeEstado;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Occurrence> occurrenceList;

}
