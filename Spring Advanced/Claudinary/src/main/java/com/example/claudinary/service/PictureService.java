package com.example.claudinary.service;

import com.example.claudinary.model.entity.Picture;
import com.example.claudinary.model.view.PictureViewModel;

import java.util.List;

public interface PictureService {

    void savePicture(Picture picture);

    List<PictureViewModel> findAllPictures();

    void deleteAllByPublicId(String publicId);
}
