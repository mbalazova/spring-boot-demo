/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.controll;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.tempest.spring.boot.proj.entity.Book;
import sk.tempest.spring.boot.proj.exhandler.exceptions.BookIdMismatchException;
import sk.tempest.spring.boot.proj.exhandler.exceptions.BookNotFoundException;
import sk.tempest.spring.boot.proj.service.BooksService;

@RestController
public class BookController {
    
    @Autowired
    private BooksService booksService;
    
    @RequestMapping("/books")
    public Iterable showAllBooks(Model model) {
        model.addAttribute("books", booksService.findAll());
        return booksService.findAll();
    }
    
    @GetMapping("/books/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        List foundBooks = booksService.findByTitle(bookTitle);
        if(foundBooks.isEmpty()){
            throw new BookNotFoundException();
        }
        return foundBooks;
        //return booksService.findByTitle(bookTitle);
    }
    
    @GetMapping("/books/{id}")
    public Book findOne(@PathVariable Long id) {
        Book book = booksService.findOne(id);
        if (book == null){
            throw new BookNotFoundException();
        }
        return book; 
    } 
    
    
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return booksService.save(book);
    }
    
    /*
    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);
 
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    */
 
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        Book book = booksService.findOne(id);
        if (book == null)
            throw new BookNotFoundException();
        booksService.delete(id);
    }
 
    @PutMapping(value = "/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        Book old = booksService.findOne(id);
        if (old == null) {
            throw new BookNotFoundException();
        }
        return booksService.save(book);
    }

}
