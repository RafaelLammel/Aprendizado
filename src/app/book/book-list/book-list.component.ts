import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit{

  books: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit():void {
    this.index();
  }

  index(): void {
    this.bookService.index().subscribe({
      next: books => {
        this.books = books.content;
      },
      error: err => {
        console.log('Error', err);
      }
    });
  }

}
