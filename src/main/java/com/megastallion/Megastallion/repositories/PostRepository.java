package com.megastallion.Megastallion.repositories;

import com.megastallion.Megastallion.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
