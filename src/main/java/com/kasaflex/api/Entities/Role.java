package com.kasaflex.api.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "idrole")
    private String idRole;

    @Column(name = "nomrole", nullable = false, unique = true)
    private String nomRole;
}
