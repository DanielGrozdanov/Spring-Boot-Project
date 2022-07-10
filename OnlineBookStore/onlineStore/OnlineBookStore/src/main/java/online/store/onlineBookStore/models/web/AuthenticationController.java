package online.store.onlineBookStore.models.web;

import online.store.onlineBookStore.models.entities.dtos.UserRegisterDTO;
import online.store.onlineBookStore.models.viewModel.UserRegServiceModel;
import online.store.onlineBookStore.models.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @GetMapping("/login")
   public String getLogin(){
        return "login";
    }

    @ModelAttribute("passwordMustMatch")
    public boolean passwordMatch(){
        return true;
    }

    @ModelAttribute("userAlreadyExists")
    public boolean userExists(){
        return false;
    }


    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterDTO",userRegisterDTO);
            redirectAttributes.addFlashAttribute("passwordMustMatch",false);
            return "redirect:/users/register";
        }
        if (!userService.userExistsInDatabase(userRegisterDTO)){
            redirectAttributes.addFlashAttribute("userRegisterDTO",userRegisterDTO);
            redirectAttributes.addFlashAttribute("userAlreadyExists",true);
            return "redirect:/users/register";
        }

        UserRegServiceModel userRegisterModel = this.userService.registerUserMap(userRegisterDTO);
        this.userService.register(userRegisterModel);
        return "redirect:/users/login";
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }
}
