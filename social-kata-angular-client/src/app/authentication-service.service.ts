import { Injectable } from '@angular/core';
import { AjaxService } from './ajax.service';
import {Response} from './response';
import { Observable, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{

  constructor(private ajax:AjaxService) { }

  login( user:string, password:string) : Observable<Response>
  {
    return this.ajax.callAuth(user, password);
  }
}
