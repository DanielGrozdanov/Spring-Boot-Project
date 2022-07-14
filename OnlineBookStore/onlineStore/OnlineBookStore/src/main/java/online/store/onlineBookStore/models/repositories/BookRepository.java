package online.store.onlineBookStore.models.repositories;

import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByCategoryName(CategoryEnum name);
}
