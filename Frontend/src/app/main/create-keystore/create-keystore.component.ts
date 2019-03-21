import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CertificateService } from '../services/certificate.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-keystore',
  templateUrl: './create-keystore.component.html',
  styleUrls: ['./create-keystore.component.css']
})
export class CreateKeystoreComponent implements OnInit {

  keystoreForm:FormGroup;
  submitted = false;
  keystore: any;

  constructor(private _certificateCertificate : CertificateService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.keystoreForm = this.formBuilder.group({
        keystoreName:['',Validators.required],
        password:['',Validators.required],
        role:['',Validators.required],
      });
  }

  get f() { return this.keystoreForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.keystore = this.keystoreForm.getRawValue();
    console.log(this.keystore);
    
    this._certificateCertificate.createKeystore(this.keystore).subscribe(
      data => {
              console.log("Uspesno sam zavrsio kreiranje keystora.")
              this.router.navigateByUrl("adminPage");
            });
  }

}
