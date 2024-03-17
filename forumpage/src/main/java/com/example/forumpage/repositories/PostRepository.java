package com.example.forumpage.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.forumpage.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

        @Query("SELECT p from Post p WHERE p.id = ?1")
        public Post findPostById(Long id);

        @Query("SELECT p from Post p WHERE p.idUsuario = ?1")
        public List<Post> findAllByUserId(Long id);

        // Encontrar todos los posts
        public List<Post> findAll();
    
        // Encontrar posts por título
        public List<Post> findByTitleContaining(String title);
        
        // Guardar un nuevo post
        public Post save(Post post);
    
        // Eliminar un post
        public void delete(Post post);
    
        // Contar el número total de posts
        public long count();
    
        @Query("SELECT creationDate FROM Post p WHERE p.id = ?1")
        public Date getCreationDate(Long id);

}
