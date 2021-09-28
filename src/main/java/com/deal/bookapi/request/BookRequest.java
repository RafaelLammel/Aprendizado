package com.deal.bookapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    private String name;
    private float price;
    private int pages;
    private int code;
    private int id_category;

}
