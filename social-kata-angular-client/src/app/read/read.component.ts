import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { isFormattedError, formattedError } from '@angular/compiler';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-read',
  templateUrl: './read.component.html',
  styleUrls: ['./read.component.scss']
})
export class ReadComponent implements OnInit {

  sender: string;
  target: string;
  response: string;
  resultArr: string[] = [];

  constructor(private ajax: AjaxService) { }

  ngOnInit() {

    this.sender = localStorage.getItem("name");
  }

  onSubmit() {
    console.log("read: " + this.sender + "--->" + this.target);
    this.ajax.callRead(this.sender, this.target).subscribe(r => {
      this.response = this.formatPosts(r.response);
    });

  }

  formatPosts(str: string): string {
    let formatted: string = "";
    this.resultArr = this.clean(str.split(">"));

    return formatted;
  }

  clean(arr: string[]): string[] {

    //alert ( arr );
    let result: string[] = arr.filter(e => e.trim().length > 0);

    //alert(result);  
    return result;
  }

  selected(user: string) {
    console.log(user);
    this.target = user;
  }

}
