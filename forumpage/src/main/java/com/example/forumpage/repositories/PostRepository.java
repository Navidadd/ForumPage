package com.example.forumpage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forumpage.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

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
    
}
