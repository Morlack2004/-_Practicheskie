package com.example.practicheskaya2.dao;

import com.example.practicheskaya2.models.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDao {
    private static int CATEGORY_COUNT;
    private List<Category> categories;

    {
        categories = new ArrayList<>();

        categories.add(new Category(++CATEGORY_COUNT, "Категория 1"));
        categories.add(new Category(++CATEGORY_COUNT, "Категория 2"));
        categories.add(new Category(++CATEGORY_COUNT, "Категория 3"));
        categories.add(new Category(++CATEGORY_COUNT, "Категория 3"));

    }

    public List<Category> index() {
        return categories;
    }

    public Category show(int id) {
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    public void save(Category newCategory) {
        newCategory.setId(++CATEGORY_COUNT);
        categories.add(newCategory);
    }

    public void update(int id, Category updatedCategory) {
        Category category = show(id);
        if (category != null) {
            category.setName(updatedCategory.getName());
        }
    }

    public void delete(int id) {
        categories.removeIf(category -> category.getId() == id);
    }
}
