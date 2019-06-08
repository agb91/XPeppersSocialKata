import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-selector',
  templateUrl: './selector.component.html',
  styleUrls: ['./selector.component.scss']
})
export class SelectorComponent implements OnInit {

  name:string; 

  constructor() { }

  ngOnInit() {


    this.name = localStorage.getItem('user');


    //alert(this.name);
  }

  read()
  {

  }

  post()
  {

  }

  follow()
  {

  }

  wall()
  {

  }

}
