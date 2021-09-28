import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import { Book } from '../book';
import { BookService } from '../book.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit{

  columnsToDisplay = ['code', 'name', 'price', 'pages', 'category.name', 'edit', 'delete'];
  books = new MatTableDataSource();

  constructor(private bookService: BookService) { }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit():void {
    this.index();
    this.books.sort = this.sort;

    this.paginator._intl.firstPageLabel = 'Primeira página';
    this.paginator._intl.lastPageLabel = 'Última página';
    this.paginator._intl.nextPageLabel = 'Próxima página';
    this.paginator._intl.previousPageLabel = 'Página anterior';
    this.paginator._intl.itemsPerPageLabel = 'Itens por página';
    this.paginator.pageSize = 10;
    
    this.books.paginator = this.paginator;
  }

  index(): void {
    this.bookService.findAll().subscribe({
      next: books => {
        this.books.data = books;
      },
      error: err => {
        console.log('Error', err);
      }
    });
  }

  deleteById(id: number): void {
    this.bookService.deleteById(id).subscribe({
      next: res => {
        console.log(id + " Excluido com sucesso!");
        this.index();
      },
      error: err => {
        console.log("Error ", err);
      }
    });

  }

}
