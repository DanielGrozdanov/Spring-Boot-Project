package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(RoleEnum role);

}
