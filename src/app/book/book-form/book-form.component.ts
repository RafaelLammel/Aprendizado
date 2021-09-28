import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Book } from '../book';
import { Category } from '../category';
import { CategoryService } from '../category.service';
import { BookRequest } from '../book-request';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  book: Book = new Book();
  id: number;
  categories: Category[] = [];

  public form: FormGroup;

  constructor(private bookService: BookService,
              private categoryService: CategoryService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.buildForm();

    this.getCategories();

    this.getBookInfo();
  }

  buildForm(): void {
    this.form = this.formBuilder.group({
      code: [ null, [
        Validators.required
      ]],
      name: [ null, [
        Validators.required
      ]],
      price: [ null, [
        Validators.required
      ]],
      pages: [ null, [
        Validators.required
      ]],
      category: [ null, [
        Validators.required
      ]]
    })
  }

  getCategories(): void {
    this.categoryService.findByOrderByName().subscribe({
      next: categories => {
        this.categories = categories;
      },
      error: err => {
        console.log('Erro ', err);
      }
    })
  }

  getBookInfo(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id');
    if(this.id > 0) {
      this.bookService.findById(this.id).subscribe({
        next: book => {
          this.book = book;
          this.form.setValue({
            code: book.code,
            name: book.name,
            price: book.price,
            pages: book.pages,
            category: book.category
          })
        },
        error: err => {
          console.log('Error', err);
        }
      });
    }
  }

  handleSubmit(): void {
    if(this.form.valid) {

      let data = this.form.value;
      if(!data.category.id) {

        let foundCategory = false;
        this.categories.forEach(element => {
          if(element.name === data.category) {
            data.category = element;
            foundCategory = true;
          }
        })

        if(!foundCategory) {
          let category: Category = {
            name: data.category
          }
          this.categoryService.save(category).subscribe({
            next: category => {
              data.category = category;
            },
            error: err => {
              console.log('Error ', err);
            }
          })
        }
      }

      let bookRequest: BookRequest = {
        name: data.name,
        id_category: data.category.id,
        price: data.price,
        pages: data.pages,
        code: data.code
      }

      if(this.book) {
        this.bookService.save(bookRequest, this.book.id).subscribe({
          next: book => {
            this.router.navigate(['/books']);
          },
          error: err => {
            console.log('Error ', err);
          }
        });
      } else {
        this.bookService.save(bookRequest).subscribe({
          next: book => {
            this.router.navigate(['/books']);
          },
          error: err => {
            console.log('Error ', err);
          }
        });
      }

    }
  }

  setCategory(category: Category): string {
    return category === null ? '' : category.name;
  }

}
