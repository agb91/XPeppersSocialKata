import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { Command } from '../command';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.scss']
})
export class FollowComponent implements OnInit {

  sender:string;
  target:string;
  response:string;

  constructor(private ajax:AjaxService) { }

  ngOnInit() {
    this.sender = localStorage.getItem("name");
  }

  onSubmit()
  {
    console.log( "follow: " + this.sender + "--->" + this.target );
    let com:Command = new Command(this.sender, this.target);
    this.ajax.callFollow(com).subscribe( r => this.response = r.response );

  }

}
