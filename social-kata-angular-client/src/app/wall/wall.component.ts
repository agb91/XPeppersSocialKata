import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { FormatterService } from '../formatter.service';

@Component({
  selector: 'app-wall',
  templateUrl: './wall.component.html',
  styleUrls: ['./wall.component.scss']
})
export class WallComponent implements OnInit {

  sender:string;
  responseList:string[];

  constructor(private ajax:AjaxService, private formatter:FormatterService) { }

  ngOnInit() {
    this.sender = localStorage.getItem("user");
  }
  
  onSubmit()
  {
    console.log( "wall: " + this.sender + "--->");
    this.ajax.callWall(this.sender, this.sender).subscribe( r => this.responseList 
      = this.formatter.formatWall(r.response) );

  }


}
