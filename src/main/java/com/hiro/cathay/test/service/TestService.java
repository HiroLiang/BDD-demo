package com.hiro.cathay.test.service;

import com.hiro.cathay.test.annotation.UseDataSource;
import com.hiro.cathay.test.config.DatabaseSource;
import com.hiro.cathay.test.dao.entity.Book;
import com.hiro.cathay.test.dao.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("testService")
@RequiredArgsConstructor
public class TestService {

    private final BookRepository bookRepo;

    public void addBook(String bookName, String author) {
        bookRepo.save(new Book(bookName, author));
    }

    @UseDataSource(source = DatabaseSource.DB1)
    public List<Book> getDB1Books() {
        return bookRepo.findAll();
    }

    @UseDataSource(source = DatabaseSource.DB2)
    public List<Book> getDB2Books() {
        return bookRepo.findAll();
    }

}
