import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input()
  public name: string;

  constructor() { }

  ngOnInit(): void {
  }

  onClickButton(name): void {
    console.log('click: ' + name);
  }

}
