export class Command {

  
    sender:string
    target:string

    constructor(private s:string, private t:string)
    {
        this.sender = s;
        this.target = t;
    }

}
