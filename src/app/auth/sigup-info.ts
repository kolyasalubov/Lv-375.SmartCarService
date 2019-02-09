export class SignUpInfo {
    username: string;
    password: string;
    fullName: string;
    email: string;
    numberPhone: string;
    role: string[];
    
 
    constructor(username: string, password: string, fullName: string, email: string, numberPhone: string ) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.role = ["aaaa"];
    }
}