export class User{

    id: number;
    fullname: string;
    email: string;
    password: string;
    username: string;
    phoneNumber: string;
    role: string[];
    
     constructor(fullname: string, email: string, password: string, username: string, phoneNumber: string, role: string [] ) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = [];
    }
}