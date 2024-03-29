package online.store.onlineBookStore.web;

import online.store.onlineBookStore.models.entities.dtos.UserRegisterDTO;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.servicemodels.UserRegServiceModel;
import online.store.onlineBookStore.services.UserService;
import online.store.onlineBookStore.models.viewmodel.UserViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthenticationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/profile")
    public String userProfile(Principal principal, Model model) {
        String username = principal.getName();
        User user = this.userService.findByUsername(username);
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        model.addAttribute("userProfile", userViewModel);
        return "user-profile";
    }

    @ModelAttribute("passwordMustMatch")
    public boolean passwordMatch() {
        return true;
    }

    @ModelAttribute("userAlreadyExists")
    public boolean userExists() {
        return false;
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("passwordMustMatch", false);
            return "redirect:/users/register";
        }
        if (!userService.userExistsInDatabase(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            return "redirect:/users/register";
        }

        UserRegServiceModel userRegisterModel = this.userService.registerUserMap(userRegisterDTO);
        this.userService.register(userRegisterModel);
        return "redirect:/users/login";
    }

    @PostMapping("/login-failed")
    public String wrongCredentials(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("checkCredentials", true);
        return "redirect:/users/login";


    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }
}
