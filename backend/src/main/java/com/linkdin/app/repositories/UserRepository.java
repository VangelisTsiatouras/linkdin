package com.linkdin.app.repositories;

import com.linkdin.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TODO add limit to 30-50 results
    List<User> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String userName, String userSurname);
    User findByEmail(String userEmail);
    User findById(Integer userID);
}