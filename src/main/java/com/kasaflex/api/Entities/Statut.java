package com.kasaflex.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "statut")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statut {
    @Id
    @Column(name = "idstatut", length = 50, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private String idStatut;

    @Column(name = "nomstatut",length = 50,nullable = false,unique = true)
    private String nomStatut;


}
