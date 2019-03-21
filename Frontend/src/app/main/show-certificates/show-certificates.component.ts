import { Component, OnInit } from '@angular/core';
import {CertificateService} from '../services/certificate.service';

@Component({
  selector: 'app-show-certificates',
  templateUrl: './show-certificates.component.html',
  styleUrls: ['./show-certificates.component.css']
})
export class ShowCertificatesComponent implements OnInit {

  constructor(private _certificateService : CertificateService){

  }
  ngOnInit() {
    this._certificateService.getCertificateDetails().subscribe(
      data => {
        this.certificates = data;
              console.log("Uspesno sam zavrsio cuvanje sertifikata")
              console.log(this.certificates);
              alert("asd");
    });
     }

  certificates: any;

  elements: any = [
    {id: 1, first: 'Mark', last: 'Otto', handle: '@mdo'},
    {id: 2, first: 'Jacob', last: 'Thornton', handle: '@fat'},
    {id: 3, first: 'Larry', last: 'the Bird', handle: '@twitter'},
  ];

  headElements = ['ID', 'Common Name'];

  showDetails(){
 
  }

 

}

