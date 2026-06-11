package com.kasaflex.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;


@Entity
@Table(name = "modele")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modele {
    @Id
    @Column(name = "idmodele", length = 50, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private String idModele;

    @Column(name = "nommodele", length = 50, nullable = false)
    private String nomModele;

    @Column(name = "numeromodele", length = 50, nullable = false, unique = true)
    private String numeroModele;
}
