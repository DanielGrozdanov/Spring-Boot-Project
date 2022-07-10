package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.models.enums.CategoryEnum;
import online.store.onlineBookStore.models.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void seedCategories(){
        if (categoryRepository.count() == 0){
            Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {
                Category category = new Category();
                category.setName(categoryEnum);
                switch (categoryEnum){
                    case Art -> category.setDescription("Art is eternal, " +
                            "but life is short. You want to be reading the best books on art? Browse through our expert recommendations to find the best books on art" +
                            "for your own library – or to give as a gift to an art lover.");
                    case Psychology-> category.setDescription("The best psychology books combine scientific rigour with accessible writing. " +
                            "We turned to some of the most eminent psychologists working today for their book recommendations.");
                    case Fantasy -> category.setDescription("Fantasy is a genre that uses magic and other supernatural forms as a primary element of plot, theme, and/or setting. " +
                            "Fantasy is generally distinguished from science fiction and horror by the expectation that it steers clear of technological and macabre themes, respectively, though there is a great deal of overlap between the three (collectively known as speculative fiction or science fiction/fantasy");
                    case Horror -> category.setDescription("For some, horror is a genre founded on trope and convention: a checklist of blighted houses and monstrous secrets, men in masks and women in white nightgowns. " +
                            "For others it hinges on atmosphere and tone.");
                    default -> category.setDescription("nothing");
                }
                this.categoryRepository.save(category);
            });
        }
    }
}
