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
categoriesSelected=[];
certificates: any;
certificate: any;
  ngOnInit() {

    this._certificateService.getCertificateDetails().subscribe(
      data => {
        this.certificates = data;
              console.log("Uspesno sam zavrsio cuvanje sertifikata")
              console.log(this.certificates);
    });
        this.categoriesSelected.length = this.certificates.length;
        this.categoriesSelected.fill(false);

     }

  formGroup: FormGroup;
  

  headElements = ['ID', 'Common Name'];
  public showAddTrustedCertificates: boolean = false;
  showDetails(id: any){
      this.hideDetails = true;
      this.showAddTrustedCertificates = false;
      this.certificate = this.certificates[id];
  }

  trustedElements = ['Common name', 'Organization', 'Add certificate'];
  
  addTrustedCertificates(){
    this.showAddTrustedCertificates = true;
    this.hideDetails = false;
  }

  sendTrustedCertificates(){
   
  }


  onChange(e){
    if(e.target.checked == true){
      this.categoriesSelected[e.target.id] = true;
    }
  }
}