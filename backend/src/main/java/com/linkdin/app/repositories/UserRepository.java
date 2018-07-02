package com.linkdin.app.repositories;

import com.linkdin.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByuserName(String name);
    User findByuserEmail(String userEmail);
}