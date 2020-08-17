package br.com.sos.patrimonio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(
    name = "marca",
    uniqueConstraints = {
            @UniqueConstraint
            (
                name = "nome_unique",
                columnNames = { "nome" }
            )
    })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marcaSequence")
    @SequenceGenerator(name = "marcaSequence", sequenceName = "marca_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

//    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Patrimonio> patrimonios;

}
