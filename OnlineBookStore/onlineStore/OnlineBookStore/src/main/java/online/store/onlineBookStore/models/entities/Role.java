package online.store.onlineBookStore.models.entities;

import online.store.onlineBookStore.models.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @OneToMany(mappedBy = "role")
    private List<User> users;


    public Role() {

    }

    public RoleEnum getName() {
        return name;
    }

    public Role setName(RoleEnum roles) {
        this.name = roles;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Role setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
