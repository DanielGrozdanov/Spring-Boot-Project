package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.CategoryRepository;
import online.store.onlineBookStore.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private CategoryService categoryService;

    private Category category;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
     void setUp(){
        categoryService = new CategoryService(categoryRepository);
        category = new Category();
        category.setName(CategoryEnum.Fantasy);
        category.setDescription("SomeDescription");

    }
    @Test
    public void found(){
        when(categoryService.getCategory(category.getName())).thenReturn(category);
        Category currentCat = this.categoryService.getCategory(this.category.getName());

        Assertions.assertEquals(currentCat.getName(),category.getName());
    }
}
