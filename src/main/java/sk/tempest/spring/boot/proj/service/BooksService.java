package sk.tempest.spring.boot.proj.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tempest.spring.boot.proj.entity.Book;
import sk.tempest.spring.boot.proj.repo.BooksRepository;

public interface BooksService {
    
    List<Book> findAllBooks(); 
    List<Book> findBookByTitle(String title);
    Book findOneBook(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
    
}
