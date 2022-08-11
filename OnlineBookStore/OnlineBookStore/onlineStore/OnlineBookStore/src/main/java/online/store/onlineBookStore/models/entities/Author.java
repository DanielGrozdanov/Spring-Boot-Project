package online.store.onlineBookStore.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{

    @Column(nullable = false)
    @Size(min = 3,message = "Author name must be at least 3 characters!")
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> books;

    public Author() {

    }

    public String getAuthorName() {
        return authorName;
    }

    public Author setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Author setBooks(Set<Book> books) {
        this.books = books;
        return this;
    }

    @Override
    public String toString() {
        return authorName;
    }
}
