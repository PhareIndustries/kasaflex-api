package com.kasaflex.api.Repositories.user;

import com.kasaflex.api.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
