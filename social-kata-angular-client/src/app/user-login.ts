export class UserLogin {

    user:string;
    password:string;

    constructor(private u:string, private p:string)
    {
        this.user = u;
        this.password = p;
    }
}
