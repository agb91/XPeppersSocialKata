import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-wall',
  templateUrl: './wall.component.html',
  styleUrls: ['./wall.component.scss']
})
export class WallComponent implements OnInit {

  sender:string;
  response:string;

  constructor(private ajax:AjaxService) { }

  ngOnInit() {
    this.sender = localStorage.getItem("name");
  }
  
  onSubmit()
  {
    console.log( "wall: " + this.sender + "--->");
    this.ajax.callWall(this.sender, this.sender).subscribe( r => this.response = r.response );

  }
}
