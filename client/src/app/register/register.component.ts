import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { ROLES } from '../roles/mock-roles';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  roleArray = ROLES;
  registerForm: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  unchecked = true;
 
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  choseRole(){
    if(this.registerForm.checkRole != null){
      this.unchecked = false;
    }
  }

  onSubmit() {
    console.log(this.registerForm);
 
    this.signupInfo = new SignUpInfo(
      this.registerForm.username,
      this.registerForm.password,
      this.registerForm.fullName,
      this.registerForm.email,
      this.registerForm.numberPhone,
      this.registerForm.checkRole);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.href='ui/auth/login';
  }
}
