package com.kasaflex.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @Column(name = "idutilisateur", length = 50, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private String idUtilisateur;

    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "mail", length = 100, nullable = false, unique = true)
    private String mail;

    @Column(name = "motdepasse", length = 255, nullable = false)
    private String motDePasse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idrole", nullable = false)
    private Role role;
}
