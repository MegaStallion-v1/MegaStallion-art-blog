package com.megastallion.Megastallion.repositories;

import com.megastallion.Megastallion.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
