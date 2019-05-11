import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{

  constructor() { }

  login( name:string) : Boolean
  {
    if( name === 'mario' )
    {
      //alert("auth is ok");
      localStorage.setItem("name",name);
      return true;
    }
    else{
      //alert("auth is KO");
      localStorage.clear;
      return false;
    }
  }
}
