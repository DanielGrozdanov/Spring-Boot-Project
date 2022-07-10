package online.store.onlineBookStore.models.web;


import online.store.onlineBookStore.models.services.BookService;
import online.store.onlineBookStore.models.services.UserService;
import online.store.onlineBookStore.models.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String homepage(){
        return "home"; //TODO : must create home page;
    }
    @GetMapping("/view/all")
    public String viewAllBooks(Model model){
      //  List<BookViewModel> bookViewModelList = this.bookService.findAll(); //TODO : implement method to find Books in DB.
       // model.addAttribute("viewAllBooks",bookViewModelList);
        return "books"; // TODO : create book page;
    }

    //TODO: create SQL file to fill the DB with BOOKS.
}
