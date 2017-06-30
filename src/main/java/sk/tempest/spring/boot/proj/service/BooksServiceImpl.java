/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tempest.spring.boot.proj.entity.Book;
import sk.tempest.spring.boot.proj.repo.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService{
    
    @Autowired
    private BooksRepository booksRepo;
    
    @Override
    public List<Book> findAllBooks(){
        return this.booksRepo.findAll();
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return this.booksRepo.findByTitle(title);
    }
    
    @Override
    public Book findOneBook(Long id){
        return this.booksRepo.findOne(id);
    }
    
    @Override
    public Book saveBook(Book book){
        return this.booksRepo.save(book);
    }
    
    @Override
    public void deleteBook(Long id){
        this.booksRepo.delete(id);
    }
       
}
