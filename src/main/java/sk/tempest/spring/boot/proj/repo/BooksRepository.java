/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sk.tempest.spring.boot.proj.entity.Book;

public interface BooksRepository extends CrudRepository<Book, Long>{
    
    List<Book> findAll(); 
    List<Book> findByTitle(String title);
    Book findOne(Long id);
    Book save(Book book);
    void delete(Long id);
    
}
