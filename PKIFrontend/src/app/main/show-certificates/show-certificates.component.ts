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
  validation : any;
  prikaz : boolean = false;
  show : boolean = true;

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

  validate(id:any){
    this._certificateService.validate(id).subscribe(
      data => {
        if(data){
          this._certificateService.getCertificateDetails().subscribe(
            data => {
                    this.certificates = data;
          });
          this._certificateService.getCertificateDetailsR().subscribe(
            data => {
                    this.certificatesF = data;
          });
          this.show = true;
          this.prikaz = true;
          this.validation = "Certificate is not trusted!";
          setTimeout (() => {
            this.show = false;
         }, 5000);
        }else{
          this.show = true;
          this.prikaz = false;
          this.validation = "Certificate is ok!";
          setTimeout (() => {
            this.show = false;
         }, 5000);
        }
    });
  }

  /*onChange(e){
    if(e.target.checked == true){
      this.categoriesSelected[e.target.id] = true;
    }
  }*/
}