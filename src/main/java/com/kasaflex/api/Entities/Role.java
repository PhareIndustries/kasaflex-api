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
    @Column(name = "idRole", length = 50, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private String idRole;

    @Column(name = "nomrole", nullable = false, unique = true)
    private String nomRole;
}
