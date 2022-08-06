package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(CategoryEnum category);
}

