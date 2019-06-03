import { Component, OnInit, OnChanges } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  proposition:string = "Spoiler: correct usernames are 'Mario' - with password 'Mario' or 'Luigi' with password 'Luigi'";

  constructor() { }

  ngOnInit() {
    
  }


}
