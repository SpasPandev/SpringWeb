package com.example.claudinary.repository;

import com.example.claudinary.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    void deleteAllByPublicId(String publicId);
}
