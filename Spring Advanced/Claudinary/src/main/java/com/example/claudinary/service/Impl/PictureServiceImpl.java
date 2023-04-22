package com.example.claudinary.service.Impl;

import com.example.claudinary.model.entity.Picture;
import com.example.claudinary.model.view.PictureViewModel;
import com.example.claudinary.repository.PictureRepository;
import com.example.claudinary.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void savePicture(Picture picture) {

        pictureRepository.save(picture);
    }

    @Override
    public List<PictureViewModel> findAllPictures() {

        return pictureRepository
                .findAll()
                .stream()
                .map(picture -> {
                    PictureViewModel pictureViewModel =
                            new PictureViewModel()
                                    .setPublicId(picture.getPublicId())
                                    .setTitle(picture.getTitle())
                                    .setUrl(picture.getUrl());

                    return pictureViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByPublicId(String publicId) {

        pictureRepository.deleteAllByPublicId(publicId);
    }
}
