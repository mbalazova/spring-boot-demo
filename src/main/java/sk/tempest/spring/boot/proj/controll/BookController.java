/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.controll;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        model.addAttribute("books", booksService.findAllBooks());
        return booksService.findAllBooks();
    }
    
    @GetMapping("/books/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        List foundBooks = booksService.findBookByTitle(bookTitle);
        if(foundBooks.isEmpty()){
            throw new BookNotFoundException();
        }
        return foundBooks;
        //return booksService.findByTitle(bookTitle);
    }
    
    @GetMapping("/books/{id}")
    public Book findOne(@PathVariable Long id) {
        Book book = booksService.findOneBook(id);
        if (book == null){
            throw new BookNotFoundException();
        }
        return book; 
    } 
    
    
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return booksService.saveBook(book);
    }
 
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        Book book = booksService.findOneBook(id);
        if (book == null)
            throw new BookNotFoundException();
        booksService.deleteBook(id);
    }
 
    @PutMapping(value = "/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        Book old = booksService.findOneBook(id);
        if (old == null) {
            throw new BookNotFoundException();
        }
        return booksService.saveBook(book);
    }

}
