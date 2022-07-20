package online.store.onlineBookStore.models.seed;

import online.store.onlineBookStore.models.services.CategoryService;
import online.store.onlineBookStore.models.services.RoleService;
import online.store.onlineBookStore.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseSeed implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final CategoryService categoryService;


    @Autowired
    public DataBaseSeed(RoleService roleService, UserService userService, CategoryService categoryService) {
        this.roleService = roleService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.seedRoles();
        userService.addFirstUser();
        categoryService.seedCategories();
    }
}
