import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/sigup-info';
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
  
 
  constructor(private authService: AuthService) { }
 
  ngOnInit() { }
 
  onSubmit() {
    console.log(this.registerForm);
 
    this.signupInfo = new SignUpInfo(
      this.registerForm.username,
      this.registerForm.password,
      this.registerForm.fullName,
      this.registerForm.email,
      this.registerForm.numberPhone,
      this.registerForm.checkRole);
 
      console.log(this.signupInfo);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
