import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthenticationService } from '../authentication-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user:string;
  password:string;

  constructor(private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    localStorage.clear;
  }

  onSubmit()
  {
    //console.log("login ts: " + this.user + " - " + this.password);
    //alert(this.name);

    let loginIsOk = this.authenticationService.login(this.user, this.password).subscribe(r =>
      {
        if( r.response === "true" )
        {
          alert("so go to selector");
          this.router.navigate(['/selector']);
        }
        else{
          alert(r.response);
          this.router.navigate(['/']);
        }
      })

    
  }

}
