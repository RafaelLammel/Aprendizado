import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from './category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryUrl: string = 'http://localhost:8080/categorias';
  private httOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic dHJpbGhhLWphdmE6RGVhbEAyMDIw'
    })
  }

  constructor(private httpClient: HttpClient) { }

  findByOrderByName(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(`${this.categoryUrl}?ordenarPor=nome`, this.httOptions);
  }

  save(category: Category): Observable<Category> {
    return this.httpClient.post<Category>(this.categoryUrl, category, this.httOptions);
  }

}