import { Component, OnInit } from '@angular/core';
import {CertificateService} from '../services/certificate.service';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';

@Component({
  selector: 'app-show-certificates',
  templateUrl: './show-certificates.component.html',
  styleUrls: ['./show-certificates.component.css']
})
export class ShowCertificatesComponent implements OnInit {
  
  constructor(private _certificateService : CertificateService, private formBuilder: FormBuilder){
    
  }

  public hideDetails: boolean = false;
  //categoriesSelected=[];
  certificates: any;
  certificate: any;
  certificatesF : any;
  certificateF : any;
  ispis1 : boolean = false;
  ispis2 : boolean = false;

  ngOnInit() {

    this._certificateService.getCertificateDetails().subscribe(
      data => {
              this.certificates = data;
              //this.categoriesSelected.length = this.certificates.length;
              //this.categoriesSelected.fill(false);
    });
    this._certificateService.getCertificateDetailsR().subscribe(
      data => {
              this.certificatesF = data;
    });
        

  }

  formGroup: FormGroup;
  

  headElements = ['ID', 'Common Name'];
  public showAddTrustedCertificates: boolean = false;

  showDetails(id: any){
      this.hideDetails = true;
      this.ispis1 = true;
      this.ispis2 = false;
      this.showAddTrustedCertificates = false;
      this.certificate = this.certificates[id];
  }

  showDetailsF(id: any){
    this.hideDetails = true;
    this.ispis1 = false;
    this.ispis2 = true;
    this.showAddTrustedCertificates = false;
    this.certificateF = this.certificatesF[id];
}

  trustedElements = ['Common name', 'Organization', 'Add certificate'];
  
  addTrustedCertificates(){
    this.showAddTrustedCertificates = true;
    this.hideDetails = false;
  }

  sendTrustedCertificates(){
  }

  revoke(id:any){
    this._certificateService.revoke(id).subscribe(
      data => {
        this._certificateService.getCertificateDetails().subscribe(
          data => {
                  this.certificates = data;
                  //this.categoriesSelected.length = this.certificates.length;
                  //this.categoriesSelected.fill(false);
        });
        this._certificateService.getCertificateDetailsR().subscribe(
          data => {
                  this.certificatesF = data;
        });
    });
  }

  /*onChange(e){
    if(e.target.checked == true){
      this.categoriesSelected[e.target.id] = true;
    }
  }*/
}