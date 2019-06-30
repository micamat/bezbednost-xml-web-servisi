import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommentService } from '../services/comment.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comments : any;

  constructor(
    private agentService: CommentService,
    private router:Router) { }

    refresh(){
      this.agentService.getAll().subscribe(
        data => {
          this.comments = data;
        }
      );
    }
  
    ngOnInit() {
      this.refresh();
    }

    hideComment(id: any){
      this.agentService.hideComment(id).subscribe(
        data => {
          this.refresh();
        }
      );
    }
}
