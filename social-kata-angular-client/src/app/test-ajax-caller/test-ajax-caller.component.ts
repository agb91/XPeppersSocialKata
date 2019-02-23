import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-test-ajax-caller',
  templateUrl: './test-ajax-caller.component.html',
  styleUrls: ['./test-ajax-caller.component.scss']
})
export class TestAjaxCallerComponent implements OnInit {

  constructor(private ajax:AjaxService) { }

  title:string

  ngOnInit() {
    this.ajax.callRead().subscribe( t => this.title = t );
  }

  

}
