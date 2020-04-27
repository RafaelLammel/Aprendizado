import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './book-list/book-list.component';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    BookListComponent
  ],
  imports: [
    CommonModule,
    MatTableModule
  ],
  exports: [
    BookListComponent
  ]
})
export class BookModule { }
