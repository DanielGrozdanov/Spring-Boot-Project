package online.store.onlineBookStore.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String firstName;

    @Column(nullable = false,unique = true)
    private String lastName;

    @OneToMany
    private Set<Book> book;

    public Author() {

    }

    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<Book> getBook() {
        return book;
    }

    public Author setBook(Set<Book> book) {
        this.book = book;
        return this;
    }
}
