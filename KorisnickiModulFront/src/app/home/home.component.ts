import { Component, OnInit } from '@angular/core';
import { addClass } from '@syncfusion/ej2-base';
import { UserService } from '../services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Search } from '../services/search';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public minDate: Date = new Date("25/07/2018");
  public maxDate: Date = new Date("02/27/2028");

  searchForm:FormGroup;
  submitted = false;
  temp:any;

  constructor(private _searchService : Search,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      drzava:['',Validators.compose([Validators.pattern('[a-zA-Z 0-9!]+')])],
      grad:['',Validators.compose([Validators.pattern('[a-zA-Z 0-9!]+')])],
      ulica:['',Validators.compose([Validators.pattern('[a-zA-Z 0-9!]+')])],
      datumOd:['',Validators.compose([null])],
      datumDo:['',Validators.compose([null])],
      brojOsoba:['',Validators.compose([Validators.pattern('[0-9!]+')])],
      tip:['',Validators.compose([Validators.pattern('[a-zA-Z !]+')])],
      kategorija:['',Validators.compose([Validators.pattern('[a-zA-Z 0-9!]+')])],
      usluge:['',Validators.compose([Validators.pattern('[a-zA-Z 0-9!]+')])]
    });
  }

  get f() { return this.searchForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.searchForm.getRawValue();
    console.log(this.temp);
    if(this.temp.usluge == ""){
      console.log("sildbglhdfjbgjsd;bnfojl")
      this.temp.usluge = null;
    }
    this._searchService.search(this.temp).subscribe(
      data => {
        localStorage.setItem('search', data);
        console.log(data);
        this.router.navigateByUrl("hotel");
    });
    
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
