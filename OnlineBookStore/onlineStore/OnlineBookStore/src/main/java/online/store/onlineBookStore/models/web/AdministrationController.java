package online.store.onlineBookStore.models.web;

import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.AuthorDTO;
import online.store.onlineBookStore.models.entities.dtos.BookDTO;
import online.store.onlineBookStore.models.services.BookService;
import online.store.onlineBookStore.models.services.OrderService;
import online.store.onlineBookStore.models.services.UserService;
import online.store.onlineBookStore.models.viewModel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdministrationController {

    private final UserService userService;
    private final OrderService orderService;
    private final BookService bookService;

    @Autowired
    public AdministrationController(UserService userService, OrderService orderService, BookService bookService) {
        this.userService = userService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/book-edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book bookById = this.bookService.findBookById(id);
        model.addAttribute("");
        return null; // TODO: must finish edit book.
    }

    @GetMapping("/book-add")
    public String getAddBook() {
        return "books-add";
    }

    @PostMapping("/book-add")
    public String addBook(@Valid BookDTO bookDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookDTO", bookDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorDTO", bindingResult);
        }
        boolean bookAlreadyExists = this.bookService.findByBookTitle(bookDTO.getTitle());
        boolean bookIsbnExists = this.bookService.findByBookIsbn(bookDTO.getIsbn());

        if (bookAlreadyExists || bookIsbnExists) {
            return "redirect:/admin/book-add";
        }

        this.bookService.saveToDB(bookDTO);
        return "redirect:/books/view/all";
    }

    @GetMapping("/all-users")
    public String allUsers(Model model){
        List<UserViewModel> users = this.userService.findAll();
        model.addAttribute("allUsers",users);
        return "all-users";
    }

    @GetMapping("/all-users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUserBy(id);
        return "all-users";
    }

    @GetMapping("/all-users/change/{id}")
    public String changeUserRole(@PathVariable("id") Long id) {
        this.userService.changeUserRole(id);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/all-users/search")
    public String searchUser(@Param("keyword") String keyword , Model model ){
        List<UserViewModel> users = this.userService.findByQuery(keyword);

        model.addAttribute("allUsers",users);
        model.addAttribute("keyword",keyword);
        return "all-users";
    }


    @ModelAttribute("bookDTO")
    public BookDTO bookDTO() {
        return new BookDTO();
    }

    @ModelAttribute("authorDTO")
    public AuthorDTO authorDTO() {
        return new AuthorDTO();
    }
}
