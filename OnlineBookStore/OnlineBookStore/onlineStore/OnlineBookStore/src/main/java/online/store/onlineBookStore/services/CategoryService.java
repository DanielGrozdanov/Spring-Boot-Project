package online.store.onlineBookStore.services;

import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.repositories.CategoryRepository;
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
                    case Romance -> category.setDescription("Romance novels are perhaps the most popular genre in terms of book sales." +
                            "Romance novels are sold in grocery store checkout lines, in monthly shipments from publishers to readers, and online, as well as via self-publishing services.");
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

    public Category getCategory(CategoryEnum category) {
     return this.categoryRepository.findByName(category);
    }
}
