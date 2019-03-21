import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CertificateService } from '../services/certificate.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-certificate',
  templateUrl: './create-certificate.component.html',
  styleUrls: ['./create-certificate.component.css']
})
export class CreateCertificateComponent implements OnInit {

  certForm:FormGroup;
  submitted : boolean = false;
  cert: any;
  next : boolean =  true;

  constructor(private _certificateService : CertificateService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.certForm = this.formBuilder.group({
        who:['',Validators.required],
        selfSigned:['',Validators.required],
        CA:['',Validators.required],
        toWhom:['',Validators.required],
        keystore:['',Validators.required],
        password:['',Validators.required],
        startDate:['',Validators.required],
        endDate:['',Validators.required],
      });
  }

  get f() { return this.certForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.cert = this.certForm.getRawValue();
    console.log(this.cert);
    /*if(this.cert.selfSigned == true){
      this.cert.who = null;
    }
    console.log(this.cert);
    this._certificateService.createCertificate(this.cert).subscribe(
      data => {
              console.log("Uspesno sam zavrsio cuvanje sertifikata")
              this.router.navigateByUrl("adminPage");
            });*/
  }

  nextt(event:any) {
    this.next = false;
    this.certForm.controls["who"].disable();
    this.certForm.controls["selfSigned"].disable();
  }

  back(event:any) {
    this.next = true;
    this.certForm.controls["who"].enable();
    this.certForm.controls["selfSigned"].enable();
  }

  /*zabrani(event:boolean){
    console.log(event);
    if(event == true){
      this.certForm.controls["who"].disable();
    }else{
      this.certForm.controls["who"].enable();
    }
    (change)="zabrani($event.target.value)"
  }*/

}
