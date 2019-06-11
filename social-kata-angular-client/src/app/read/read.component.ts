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
  usersArr: string[] = [];

  constructor(private ajax: AjaxService) { }

  ngOnInit() {
    this.ajax.callGetAllusers().subscribe(r => {
      let receiverUsers = this.formatUsers(r.response);
      receiverUsers.forEach(u => this.usersArr.push(u));
    })

    console.log(this.usersArr);
    this.sender = localStorage.getItem("name");
  }

  onSubmit() {
    console.log("read: " + this.sender + "--->" + this.target);
    this.ajax.callRead(this.sender, this.target).subscribe(r => {
      this.response = this.formatPosts(r.response);
    });

  }

  selected(user: string) {
    this.target = user;
  }

  formatPosts(str: string): string {
    let formatted: string = "";
    this.resultArr = this.clean(str.split(">"));

    return formatted;
  }

  formatUsers(str: string): string[] {
    let formatted: string[] = [];

    str.split(",").forEach(s => {
      s.trim();
      s.replace(",", "");
      if (s.trim().length > 0) { formatted.push(s.trim()); }

    })
    return formatted;
  }

  clean(arr: string[]): string[] {

    //alert ( arr );
    let result: string[] = arr.filter(e => e.trim().length > 0);

    //alert(result);  
    return result;
  }

}
