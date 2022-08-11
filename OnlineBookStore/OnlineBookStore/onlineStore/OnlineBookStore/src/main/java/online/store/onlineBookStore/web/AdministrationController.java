package online.store.onlineBookStore.web;

import online.store.onlineBookStore.models.entities.dtos.AuthorDTO;
import online.store.onlineBookStore.models.entities.dtos.BookDTO;
import online.store.onlineBookStore.services.BookService;
import online.store.onlineBookStore.services.UserService;
import online.store.onlineBookStore.viewModel.BookViewModel;
import online.store.onlineBookStore.viewModel.UserViewModel;
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
    private final BookService bookService;

    @Autowired
    public AdministrationController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }


    @GetMapping("/book-edit/{id}")
    public String getEditBookStockAndPrice(@PathVariable("id") Long id, Model model) {
        BookViewModel book = this.bookService.findById(id);
        model.addAttribute("bookViewModel", book);
        return "books-edit";
    }


   @PostMapping("/book-edit/{id}")
    public String editBookStockAndPrice(@Valid BookViewModel bookViewModel, BindingResult bindingResul, RedirectAttributes redirectAttributes
            , @PathVariable("id") Long id) {
        if (bindingResul.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookViewModel", bookViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookViewModel", bindingResul);
            return "redirect:/admin/book-edit";
        }

        boolean bookExists = this.bookService.findById(bookViewModel.getId()) != null;
        if (!bookExists){
            return "redirect:/books-edit";
        }

        this.bookService.saveEditsToDB(bookViewModel,id);
        return "redirect:/books/view/all";
    }

    @GetMapping("/book-edit/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books/view/all";
    }

    @GetMapping("/book-add")
    public String getAddBook() {
        return "books-add";
    }

    @PostMapping("/book-add")
    public String addBook(@Valid BookDTO bookDTO,BindingResult bindingResult,RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookDTO", bookDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookDTO", bindingResult);
            return "redirect:/admin/book-add";
        }
        boolean bookAlreadyExists = this.bookService.findByBookTitle(bookDTO.getTitle());
        boolean bookIsbnExists = this.bookService.findByBookIsbn(bookDTO.getIsbn());

        if (bookAlreadyExists || bookIsbnExists) {
            redirectAttributes.addFlashAttribute("bookDTO",bookDTO);
            redirectAttributes.addFlashAttribute("bookAlreadyExists",true);
            return "redirect:/admin/book-add";
        }

        this.bookService.saveToDB(bookDTO);
        return "redirect:/books/view/all";
    }

    @ModelAttribute("bookAlreadyExists")
    public boolean bookAlreadyExists() {
        return false;
    }

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        List<UserViewModel> users = this.userService.findAll();
        model.addAttribute("allUsers", users);
        return "all-users";
    }

    //TODO : must create view all orders made by user through admin POV.

    @GetMapping("/all-users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUserBy(id);
        return "all-users";
    }

    @GetMapping("/all-users/change/{id}")
    public String changeUserRole(@PathVariable("id") Long id) {
        this.userService.changeUserRole(id);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/all-users/search")
    public String searchUser(@Param("keyword") String keyword, Model model) {
        List<UserViewModel> users = this.userService.findByQuery(keyword);

        model.addAttribute("allUsers", users);
        model.addAttribute("keyword", keyword);
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

    @ModelAttribute("bookViewModel")
    public BookViewModel bookViewModel() {
        return new BookViewModel();
    }
}
