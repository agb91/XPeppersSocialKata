import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormatterService {

  formatWall(response: string): string[] {
    let splitted:string[] = response.split(">");
    splitted.map( s => s.trim() );
    return this.removeEmpty(splitted);
    //let result = splitted.join(" \n");
    //return result;
  }

  removeEmpty(list : string[]):string[]
  {
    list = list.filter( l => l.trim().length > 0 );
    return list;
  }

  constructor() { }
}
