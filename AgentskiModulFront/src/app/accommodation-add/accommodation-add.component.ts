import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AccommodationService } from '../services/accommodation.service';

@Component({
  selector: 'app-accommodation-add',
  templateUrl: './accommodation-add.component.html',
  styleUrls: ['./accommodation-add.component.css']
})
export class AccommodationAddComponent implements OnInit {
  
  accommodationAdd:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;
  slika : String;
  categoryL:any;
  typeL : any;
  temp:any;
  accommodation:any;
  kategorijaID : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.accommodationAdd = this.formBuilder.group({
        naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idKategorijaSmestaja:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
        opis:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idTipSmestaja:['',Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
        idAgent:['',Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
        slika:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
        kapacitet:['',Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
        drzava:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        grad:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        ulica:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        broj:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],  
      });
      this._accommodationService.getAllCategoryAccommodation().subscribe(
        data => {
          this.categoryL = data;
      });
      this._accommodationService.getAllTypeAccommodation().subscribe(
        data => {
          this.typeL = data;
      });
      

  }

  get f() { return this.accommodationAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.accommodationAdd.getRawValue();
    this.accommodation = this.temp;

    this.image.forEach(element => {
      this.slika += element + " ";
    });
    console.log(this.slika)
    this.accommodation.slika = this.slika;
    this.accommodation.idAgent = 1;
    console.log(this.temp);

    this._accommodationService.addAccommmodation(this.accommodation).subscribe(
      data => {
              this.router.navigateByUrl("accommodation");
    });
  }

  image:Array<any> = new Array();

  changeListener($event) : void {
    this.image = new Array();
    for (var i = 0; i < $event.target.files.length; i++) {
        this.readThis($event.target.files[i]);
    }
  }

  readThis(inputValue: any): void {
    
      var myReader:FileReader = new FileReader();
      var file: File = inputValue
      myReader.onloadend = (e) => {
        this.image.push(myReader.result);
      }
      myReader.readAsDataURL(file);
  }
  
}
