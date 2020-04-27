import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book';
import { BookRequest } from './book-request';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookUrl: string = "http://localhost:8080/livros";
  private httOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic dHJpbGhhLWphdmE6RGVhbEAyMDIw',
    })
  }

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.bookUrl, this.httOptions);
  }

  findById(id: number): Observable<Book> {
    return this.httpClient.get<Book>(`${this.bookUrl}/${id}`, this.httOptions);
  }

  save(book: BookRequest, bookId?: number): Observable<Book> {
    if(!bookId) {
      return this.httpClient.post<Book>(this.bookUrl, book, this.httOptions);
    }
    return this.httpClient.put<Book>(`${this.bookUrl}/${bookId}`, book, this.httOptions);
  }

  deleteById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.bookUrl}/${id}`, this.httOptions);
  }

}