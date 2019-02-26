import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-read',
  templateUrl: './read.component.html',
  styleUrls: ['./read.component.scss']
})
export class ReadComponent implements OnInit {

  sender:string;
  target:string;

  constructor(private ajax:AjaxService) { }

  ngOnInit() {
    this.sender = localStorage.getItem("name");
  }

  onSubmit()
  {
    console.log( this.sender + "--->" + this.target );
    this.ajax.callRead(this.sender, this.target).subscribe( r => console.log( r.response) );

  }

}
