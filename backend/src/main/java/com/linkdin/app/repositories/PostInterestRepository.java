package com.linkdin.app.repositories;

import com.linkdin.app.model.PostInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostInterestRepository extends JpaRepository<PostInterest, Long> {
    List<PostInterest> findAllByUserId(int userID);
    PostInterest findByPostIdAndUserId(int postID, int userID);
    List<PostInterest> findAllByPostId(int postID);
    int countAllByPostId(int postID);
}
