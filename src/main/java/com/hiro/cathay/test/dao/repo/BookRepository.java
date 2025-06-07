package com.hiro.cathay.test.dao.repo;

import com.hiro.cathay.test.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
