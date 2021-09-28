package com.deal.bookapi.entity;

import com.deal.bookapi.request.BookRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "nome")
    private String name;
    @Column(name = "preco")
    private float price;
    @Column(name = "paginas")
    private int pages;
    @Column(name = "codigo")
    private int code;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Category category;

    public Book(BookRequest bookRequest) {
        this.name = bookRequest.getName();
        this.price = bookRequest.getPrice();
        this.pages = bookRequest.getPages();
        this.code = bookRequest.getCode();
    }
}
