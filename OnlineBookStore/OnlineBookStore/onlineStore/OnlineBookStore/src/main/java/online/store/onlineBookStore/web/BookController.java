package online.store.onlineBookStore.web;


import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.services.BookService;
import online.store.onlineBookStore.services.CartBookService;
import online.store.onlineBookStore.services.CartService;
import online.store.onlineBookStore.services.UserService;
import online.store.onlineBookStore.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final CartService cartService;
    private final CartBookService cartBookService;


    @Autowired
    public BookController(BookService bookService, UserService userService, CartService cartService, CartBookService cartBookService) {
        this.bookService = bookService;
        this.userService = userService;
        this.cartService = cartService;
        this.cartBookService = cartBookService;
    }


    @GetMapping("/view/all")
    public String viewAllBooks(Model model) {
        List<BookViewModel> bookViewModelList = this.bookService.findAll();
        model.addAttribute("viewAllBooks", bookViewModelList);
        return "books";
    }

    @GetMapping("/cart/{id}")
    public String addBookToCart(@PathVariable("id") Long bookId, Principal principal) {  //TODO : can use @AuthenticationPrincipal OnlineBookStoreUserDetails userDetails - getID

        String user = principal.getName();
        User loggedUser = this.userService.findByUsername(user);
        Cart cart = cartService.validateCart(loggedUser);
        Book book = bookService.findBookById(bookId);

        if (cart == null || cart.getStatus().equals("closed")) {
            Cart newCart = new Cart();
            newCart.setUser(loggedUser);
            CartBooks cartBooks = new CartBooks();
            cartBooks.setCart(newCart);
            cartBooks.setBook(book);
            this.cartService.saveCart(cartBooks, newCart);
        } else {
            CartBooks bookInCart = cartBookService.findBookInCart(book, cart);
            if (bookInCart == null) {
                CartBooks cartBooks = new CartBooks();
                cartBooks.setCart(cart);
                cartBooks.setBook(book);
                this.cartService.saveCart(cartBooks, cart);
            } else {
                bookInCart.setAmount(bookInCart.getAmount() + 1);
                this.cartService.updateAmount(bookInCart);
            }
        }
        return "redirect:/books/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeBookFromCart(@PathVariable Long id) {
        List<CartBooks> cartBooksList = this.cartBookService.checkIfThereAreBooks();
        if (cartBooksList != null) {
            this.cartBookService.removeById(id);
        }
        return "redirect:/books/cart";
    }

    @GetMapping("/cart")
    public String getCart(Principal principal, Model model) {
        String name = principal.getName();
        User loggedUser = this.userService.findByUsername(name);
        Cart cart = cartService.validateCart(loggedUser);

        List<CartBooks> cartContent = this.cartService.findCartContent(cart.getId());

        if (cartContent == null) {
            return "redirect:/books/view/all";
        }

        if (this.cartService.validateCart(loggedUser) == null) {
            return "redirect:/books/view/all";
        }


        model.addAttribute("getCartContent", cartContent);
        return "cart";
    }


    @ModelAttribute("viewAllBooks")
    public BookViewModel bookById() {
        return new BookViewModel();
    }

    @ModelAttribute("getCartContent")
    public CartBooks viewCartBooks() {
        return new CartBooks();
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

}

