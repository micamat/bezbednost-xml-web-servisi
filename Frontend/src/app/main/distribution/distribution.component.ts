import { Component, OnInit } from '@angular/core';
import { CertificateService } from '../services/certificate.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-distribution',
  templateUrl: './distribution.component.html',
  styleUrls: ['./distribution.component.css']
})
export class DistributionComponent implements OnInit {

  constructor(private _certificateService : CertificateService) { }

  certForm:FormGroup;
  certificates: any;
  headElements = ['ID', 'Common Name'];
  keystores:any;
  keystore:any;

  ngOnInit() {
    this._certificateService.getAllCertificateNotCA().subscribe(
      data => {
        this.certificates = data;
    });
    this._certificateService.getAllTransferStore().subscribe(
      data => {
        this.keystores = data;
    });
  }

  distribution(alias:any){
    this._certificateService.transfer(alias,this.keystore).subscribe(
      data => {
        this.certificates = data;
    });
  }

  transfer(e){
    this.keystore = e.target.value;
  }
}
