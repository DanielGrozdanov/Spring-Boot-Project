package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategoryName(CategoryEnum name);

    Optional<Book> findByTitle(String title);

    Optional<Book> findBookByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.price = :price, b.stock = :stock WHERE b.id = :id")
    void updateBook(BigDecimal price,Integer stock, Long id);

    Optional<Book> findById(Long id);

}
