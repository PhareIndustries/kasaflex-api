package com.kasaflex.api.Repositories.utilisateur;

import com.kasaflex.api.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
}
