import { Component, OnInit, OnChanges } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  proposition:string = "Social Kata SpringBoot-Angular experiment";

  constructor() { }

  ngOnInit() {
    
  }


}
