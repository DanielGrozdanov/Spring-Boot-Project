package online.store.onlineBookStore.models.web;


import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.enums.CategoryEnum;
import online.store.onlineBookStore.models.services.BookService;
import online.store.onlineBookStore.models.services.UserService;
import online.store.onlineBookStore.models.utility.ShopCartEntity;
import online.store.onlineBookStore.models.utility.ShopCart;
import online.store.onlineBookStore.models.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final ShopCart shopCart;

    @Autowired
    public BookController(BookService bookService, UserService userService, ShopCart shopCart) {
        this.bookService = bookService;
        this.userService = userService;
        this.shopCart = shopCart;
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String homepage() {
        return "home";
    }

    @GetMapping("/view/all")
    public String viewAllBooks(Model model) {
        List<BookViewModel> bookViewModelList = this.bookService.findAll();
        model.addAttribute("viewAllBooks", bookViewModelList);
        return "books";
    }

    @PostMapping("/view/all/{id}")
    public String addBooksToCart(@PathVariable("id") Long id) {
        Book chosenBook = bookService.findBookById(id);
        ShopCartEntity cart = fillShopCartData(chosenBook);
        this.shopCart.getItems().add(cart);
        return "index"; //TODO : must finish Shopping Cart and Payments/Order contrroler
    }

    @GetMapping("/category/fantasy")
    public String getBooksByCategoryFantasy(Model model) {
        List<BookViewModel> getBooksByCategoryFantasy = this.bookService.findByCategoryName(CategoryEnum.Fantasy);
        model.addAttribute("booksByFantasy", getBooksByCategoryFantasy);
        return "books-category-fantasy";
    }

    @GetMapping("/category/horror")
    public String getBooksByCategoryHorror(Model model) {
        List<BookViewModel> getBooksByCategoryHorror = this.bookService.findByCategoryName(CategoryEnum.Horror);
        model.addAttribute("booksByHorror", getBooksByCategoryHorror);
        return "books-category-horror";
    }

    @GetMapping("/category/psychology")
    public String getBooksByCategoryPsychology(Model model) {
        List<BookViewModel> getBooksByCategoryPsychology = this.bookService.findByCategoryName(CategoryEnum.Psychology);
        model.addAttribute("booksByPsychology", getBooksByCategoryPsychology);
        return "books-category-psychology";
    }

    @GetMapping("/category/romance")
    public String getBooksByCategoryRomance(Model model) {
        List<BookViewModel> getBooksByCategoryRomance = this.bookService.findByCategoryName(CategoryEnum.Romance);
        model.addAttribute("booksByRomance", getBooksByCategoryRomance);
        return "books-category-romance";
    }

    private ShopCartEntity fillShopCartData(Book book) {
        ShopCartEntity cart = new ShopCartEntity();
        cart.setBook(book);
        cart.setCost(book.getPrice());
        return cart;
    }
}

