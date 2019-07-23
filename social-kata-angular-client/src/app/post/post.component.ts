import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { Command } from '../command';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  sender:string;
  target:string;
  response:string;


  constructor(private ajax:AjaxService) { }

  ngOnInit() {
    this.sender = localStorage.getItem("user");
  }

  onSubmit()
  {
    console.log( "post: " + this.sender + "--->" + this.target );
    let com:Command = new Command(this.sender, this.target);
    this.ajax.callPost(com).subscribe( r => this.response = r.response );

    this.target = ""; //clean the input text

  }

}
