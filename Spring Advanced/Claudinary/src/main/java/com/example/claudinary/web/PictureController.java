package com.example.claudinary.web;

import com.example.claudinary.model.binding.PictureBindingModel;
import com.example.claudinary.model.entity.Picture;
import com.example.claudinary.model.view.PictureViewModel;
import com.example.claudinary.service.CloudinaryImage;
import com.example.claudinary.service.CloudinaryService;
import com.example.claudinary.service.PictureService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PictureController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;

    public PictureController(CloudinaryService cloudinaryService, PictureService pictureService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/all")
    public String allPicture(Model model) {

        List<PictureViewModel> pictures = pictureService.findAllPictures();

        model.addAttribute("pictures", pictures);

        return "all";
    }

    @GetMapping("/pictures/add")
    public String addPicture() {

        return "add";
    }

    @PostMapping("/pictures/add")
    public String addPicture(PictureBindingModel pictureBindingModel) throws IOException {

        var picture = createPicture(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());

        pictureService.savePicture(picture);

        return "redirect:/pictures/all";
    }

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String delete(@RequestParam("public_id") String publicId) {

        if (cloudinaryService.delete(publicId)) {
            pictureService.deleteAllByPublicId(publicId);
        }

        return "redirect:/pictures/all";
    }

    private Picture createPicture(MultipartFile file, String title) throws IOException {

        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new Picture()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
    }
}
