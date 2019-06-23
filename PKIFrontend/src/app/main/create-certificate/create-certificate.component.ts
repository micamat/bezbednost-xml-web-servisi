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
  keystores: any;
  organizations: any;
  temp: any;
  sertificates: any; 
  node:any;
  trustStore:any;
  keystore: any;

  constructor(private _certificateService : CertificateService,
    private _certificateCertificate : CertificateService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.certForm = this.formBuilder.group({
        who:['',Validators.required],
        selfSigned:['',Validators.required],
        ca:['',Validators.required],
        toWhom:['',Validators.required],
        keystore:['',Validators.required],
        password:['',Validators.required],
        privatePassword:['',Validators.required],
        startDate:['',Validators.required],
        endDate:['',Validators.required],
      });
      this._certificateService.getAllAdminKeystores().subscribe(
        data => {
                this.keystores = data;
      });
      this._certificateService.getAllCertificate().subscribe(
        data => {
                this.sertificates = data;
      });
      
      
  }

  get f() { return this.certForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.cert = this.certForm.getRawValue();
    this.trustStore = this.certForm.get('keystore').value;
    console.log(this.cert);
    if(this.cert.selfSigned == true){
      this.cert.who = null;
    }else{
      let toArray =  this.cert.who.split(",");
      this.temp = toArray[0];
      this.sertificates.forEach(obj => {
        if(obj.alias == this.temp){
          this.cert.who = obj;
        }
      });
    }
    this.temp = this.cert.toWhom;
    console.log(this.organizations)
    this.organizations.forEach(obj => {
      if(obj.organizationName == this.temp){
        this.cert.toWhom = obj;
      }
    });


    console.log(this.cert);
    this._certificateService.createCertificate(this.cert, this.trustStore).subscribe(
      data => {
              console.log("Uspesno sam zavrsio cuvanje sertifikata")
              this.router.navigateByUrl("adminPage");
    });
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

  onChange(e){
    if(e.target.checked == true){
      this.certForm.controls["who"].disable();
    }else{
      this.certForm.controls["who"].enable();
    }

    this._certificateService.getAllNodes().subscribe(
      data => {
              this.organizations = data;
              console.log(this.organizations);
    });
   
  }
  organizacija(e){
    console.log(e.target.value)
    let toArray =  e.target.value.split(",");
    this.temp = toArray[1];

    console.log(this.temp)
    this._certificateService.getAllNodes().subscribe(
      data => {
        this.organizations = data;
        this.organizations.forEach(obj => {
          if(obj.organizationName == this.temp){
            this.node = obj;
          }
        });

        this._certificateService.getAllChildernNodes(this.node.id).subscribe(
          data => {
                  this.organizations = data;
    
        }); 
    });
    
   
  }
  
  /*zabrani(event:boolean){
    console.log(event);
    
    (change)="zabrani($event.target.value)"
  }*/

}
