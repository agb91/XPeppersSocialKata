import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { Command } from '../command';

@Component({
  selector: 'app-test-ajax-caller',
  templateUrl: './test-ajax-caller.component.html',
  styleUrls: ['./test-ajax-caller.component.scss']
})
export class TestAjaxCallerComponent implements OnInit {

  constructor(private ajax:AjaxService) { }

  title:string
  sender:string
  

  ngOnInit() {

    this.sender = localStorage.getItem("name")
    console.log("sender: " + this.sender)

    this.ajax.callRead(this.sender, "luigi").subscribe( t => this.title = t );

    let com: Command = new Command();
    com.sender = this.sender
    com.target = "bbb"
    this.ajax.callPost(com).subscribe( t => console.log("messageeeee---------" + t) ) 
    
  }

  

}
