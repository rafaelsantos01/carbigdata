package br.com.carbigdata.teste.domain.occurrence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Table(name = "foto_ocorrencia")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_foto_ocorrencia")
    private Long codFotoOcorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_ocorrencia", nullable = false)
    private Occurrence occurrence;

    @Column(name = "dta_criacao")
    private Timestamp dtaCriacao;

    @Column(name = "dsc_path_bucket", nullable = false)
    private String dscPathBucket;

    @Column(name = "dsc_hash", nullable = false)
    private String dscHash;

}
