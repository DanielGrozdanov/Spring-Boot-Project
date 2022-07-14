package online.store.onlineBookStore.models.entities;

import online.store.onlineBookStore.models.enums.CategoryEnum;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private CategoryEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private Set<Book> book;

    public Category() {

    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum category) {
        this.name = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Book> getBook() {
        return book;
    }

    public Category setBook(Set<Book> book) {
        this.book = book;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(book, category.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
