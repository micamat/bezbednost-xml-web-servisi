import { Component, OnInit } from '@angular/core';
import { addClass } from '@syncfusion/ej2-base';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public minDate: Date = new Date("25/07/2018");
  public maxDate: Date = new Date("02/27/2028");
  //public placeholder: string = "Choose a Date";

  constructor() { }

  ngOnInit() {
  }

  onLoad(args: any) {
    /*Date need to be customized*/
    if (args.date.getDate() === 10) {
      this.specialDate(args, "Birthday");
    }
    else if (args.date.getDate() === 15) {
        this.specialDate(args, "Farewell");
    }
    else if (args.date.getDate() === 25) {
        this.specialDate(args, "Vacation");
    }
  }
  /*Function to customize the Date*/
  public specialDate(args, name) {
    let span = document.createElement('span');
    span.setAttribute('class', 'e-icons highlight');
    args.element.firstElementChild.setAttribute('title', name + '!');
    addClass([args.element], ['e-day', 'special', name.toLowerCase()]);
    args.element.setAttribute('title', name + '!');
    args.element.appendChild(span);
  }

}
