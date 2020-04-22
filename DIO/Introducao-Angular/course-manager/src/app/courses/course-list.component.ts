import { Component, OnInit } from '@angular/core';
import { Course } from './course';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html'
})
export class CourseListComponent implements OnInit {
  
  courses:Course[] = [];

  ngOnInit():void {
    this.courses = [
      {
        id: 1,
        name: 'Angular Forms',
        imageUrl: '/assets/images/forms.png',
        price: 99.99,
        code: 'XPS-8796',
        duration: 128,
        rating: 4.5,
        releaseDate: 'December, 2, 2019'
      },
      {
        id: 2,
        name: 'Angular HTTP',
        imageUrl: '/assets/images/http.png',
        price: 99.99,
        code: 'XPS-8797',
        duration: 78,
        rating: 4,
        releaseDate: 'November, 2, 2019'
      }
    ]
  }

}