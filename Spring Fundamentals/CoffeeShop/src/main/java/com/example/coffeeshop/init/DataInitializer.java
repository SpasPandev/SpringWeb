package com.example.coffeeshop.init;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.enums.CategoryNameEnum;
import com.example.coffeeshop.repository.CategoryRepository;
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

        if (categoryRepository.count() > 0) {
            return;
        }


        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();

                    category.setName(categoryNameEnum);

                    switch (category.getName()) {

                        case Drink -> category.setNeededTime(1);
                        case Coffee -> category.setNeededTime(2);
                        case Other -> category.setNeededTime(5);
                        case Cake -> category.setNeededTime(10);
                    }

                    categoryRepository.save(category);
                });

    }
}
