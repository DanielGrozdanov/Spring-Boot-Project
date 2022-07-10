package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.enums.RoleEnum;
import online.store.onlineBookStore.models.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void seedRoles(){
        if (this.roleRepository.count() == 0){
            Role adminRole = new Role();
            adminRole.setName(RoleEnum.ADMIN);

            Role userRole = new Role();
            userRole.setName(RoleEnum.USER);

            this.roleRepository.save(adminRole);
            this.roleRepository.save(userRole);
        }
    }

    public Role findByName(RoleEnum role) {
        return this.roleRepository.findByName(role);
    }
}
