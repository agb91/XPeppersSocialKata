import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthenticationService } from '../authentication-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  name:string;
  password:string;

  constructor(private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    localStorage.clear;
  }

  onSubmit()
  {
    console.log(this.name + " - " + this.password);
    //alert(this.name);
    if( this.authenticationService.login(this.name, this.password) )
    {
      //alert("so go to selector");
      this.router.navigate(['/selector']);
    }
    else{
      //alert("so back to login");
      this.router.navigate(['/']);
    }
  }

}
