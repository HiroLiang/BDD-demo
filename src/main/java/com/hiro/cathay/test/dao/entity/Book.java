package com.hiro.cathay.test.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOK")
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;


    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
