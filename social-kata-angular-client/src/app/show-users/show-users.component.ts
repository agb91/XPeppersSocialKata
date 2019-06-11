import { Component, OnInit, EventEmitter, Output  } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.scss']
})
export class ShowUsersComponent implements OnInit {

  usersArr: string[] = [];
  
  constructor(private ajax: AjaxService) { }

  @Output() hereSelected = new EventEmitter<string>();

  ngOnInit() {
    this.ajax.callGetAllusers().subscribe(r => {
      let receiverUsers = this.formatUsers(r.response);
      receiverUsers.forEach(u => this.usersArr.push(u));
    })

    console.log(this.usersArr);
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

  selected(user: string) {
    //console.log("emitted; " + user);
    this.hereSelected.emit(user);
  }

}
