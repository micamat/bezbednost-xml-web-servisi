import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  rooms : any;
  accommodationId : any;
  multiSelect : boolean = false;
  roomL : Array<any> = new Array();
  idSoba :any;
  submitted = false;
  reservationForm : FormGroup;
  reservation :any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationId = params.get('id');
          this._accommodationService.getByAccommodationRoom(this.accommodationId).subscribe(
            data => {
              this.rooms = data;
          });
      }
    );
    
    this.reservationForm = this.formBuilder.group({
      datumDo:["",Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
      datumOd:["",Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
      idKorisnika : ["",Validators.compose(null)],
      idSmestaj : ["",Validators.compose(null)],
      cena : ["",Validators.compose(null)],
      rezervisaneSobeDTO : ["",Validators.compose(null)],
      });
    
  }

  onChange(e:any,i:any){
        console.log(e.target.checked + "IPSIIS MI   " + i);
        if(e.target.checked){
          this.roomL.push({ 
            idSoba: i, 
            statusRezervacije: "Zauzeto" 
         });
          console.log(this.roomL);
        }else{
            for (var _i = 0; _i < this.roomL.length; _i++) {
                      if(this.roomL[_i].idSoba == i){
                         this.idSoba = _i;
                      }

            };
            this.roomL.splice(this.idSoba, 1);
            console.log(this.roomL);
        }
  }

  selectAll(event){
      this.multiSelect = event.target.checked;
      if(event.target.checked){
        this.rooms.forEach(element => {
              this.roomL.push({
                idSoba: element.id, 
                statusRezervacije: "Zauzeto" 
              })
        });
      }else{
        this.roomL = new Array();
      }
      console.log(this.roomL);
  }

  get f() { return this.reservationForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.reservation = this.reservationForm.getRawValue();
    this.reservation.idKorisnika =  1;
    this.reservation.idSmestaj = this.accommodationId;
    this.reservation.rezervisaneSobeDTO = this.roomL;
    
    if(this.roomL.length != 0){
      this._accommodationService.addReservation(this.reservation).subscribe(
        data => {
              this.router.navigateByUrl("reservation");
      });
    }else{
      alert("Odaberite sobu !");
    }
  }
}
