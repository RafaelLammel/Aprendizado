import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookUrl = "http://localhost:8080/livros";
  private httOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic dHJpbGhhLWphdmE6RGVhbEAyMDIw'
    })
  }

  constructor(private httpClient: HttpClient) { }

  index(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.bookUrl, this.httOptions);
  }

}