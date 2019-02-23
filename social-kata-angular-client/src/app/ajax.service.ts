import { Injectable } from '@angular/core';
import { HttpParams, HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { Command } from './command';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private http: HttpClient) { }

  private urlBase = 'http://localhost:8080/'
  private message = 'message'
  private user = 'user'
  private relation = 'relation'

  callRead( sender, target ): Observable<string> {

    let url = this.urlBase + this.message
    let params = new HttpParams().set("sender",sender)
      .set("target", target);
    let headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
    
    let result:Observable<string> = this.http.get<string>(url,
         { headers: headers, params: params })

    return result;
  }

  callPost( command:Command ): Observable<string> {

    let url = this.urlBase + this.message
    let headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
    
    let result:Observable<string> = this.http.post<string>(url, command, 
      {headers : headers})

    return result;
  }


 


}
