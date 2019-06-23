import { Component, OnInit } from '@angular/core';
import { addClass } from '@syncfusion/ej2-base';
import { Router } from '@angular/router';
import { SearchService } from '../services/search.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public minDate: Date = new Date("25/07/2018");
  public maxDate: Date = new Date("02/27/2028");
  //public placeholder: string = "Choose a Date";
  
  searchForm:FormGroup

  constructor(private router:Router,
              private searchService:SearchService,
              private formBuilder:FormBuilder) { }


  ngOnInit() {
    


    this.searchForm = this.formBuilder.group({
      destination:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],  
      dateFrom:[null,Validators.required],
      dateTo:[null,Validators.required],
      type:[null],
      category:[null],
      service:[null],
      distance:['',Validators.pattern('[0-9]*')],
      person:['',Validators.compose([Validators.required, Validators.pattern('[0-9]*')])]  
    });
  }

  search(){
    const accommodations= this.searchForm.getRawValue();
    this.searchService.getAllAvailableAccommodations(accommodations).subscribe(
      (data:any)=>{
          this.router.navigateByUrl('/accommodation-list');      
      } 
    );
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
