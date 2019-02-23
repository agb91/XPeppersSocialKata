import { Injectable } from '@angular/core';
import { HttpParams, HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private http: HttpClient) { }

  private readUrlBase = 'http://localhost:8080/read/'


  callRead(): Observable<string> {

    let params = new HttpParams().set("sender","mario")
      .set("target", "luigi");
    let headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')
      .set('secure', 'false')

    let result:Observable<string> = this.http.get<string>(this.readUrlBase,
      { headers: headers, params: params })

    console.log( "got answer" )

    return result;
  }

  

}
