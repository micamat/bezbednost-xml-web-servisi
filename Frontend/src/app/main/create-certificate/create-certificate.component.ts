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
  submitted = false;
  cert: any;

  constructor(private _certificateService : CertificateService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.certForm = this.formBuilder.group({
        alias:['',Validators.required],
        selfSigned:['',Validators.required],
        comonName:['',Validators.required],
        surname:['',Validators.required],
        givenName:['',Validators.required],
        organizationName:['',Validators.required],
        organizationUnitName:['',Validators.required],
        countryName:['',Validators.required],
        email:['',Validators.required],
        startDate:['',Validators.required],
        endDate:['',Validators.required],
      });
  }

  get f() { return this.certForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.cert = this.certForm.getRawValue();
    console.log(this.cert);
    if(this.cert.selfSigned == true){
      this.cert.alias = null;
    }
    console.log(this.cert);
    this._certificateService.createCertificate(this.cert).subscribe(
      data => 
              console.log("Uspesno sam zavrsio cuvanje sertifikata")
    );
  }

}
