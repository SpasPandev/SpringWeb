package com.example.battleships.init;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.enums.CategoryNameEnum;
import com.example.battleships.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() > 0){
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();

                    category.setName(categoryNameEnum);

                    switch (category.getName()){
                        case BATTLE -> category.setDescription("This is a BATTLE category!");
                        case CARGO -> category.setDescription("This is CARGO category!");
                        case PATROL -> category.setDescription("This is PATROL category!");
                    }

                    categoryRepository.save(category);
                });
    }
}
