import {Component, OnInit} from '@angular/core';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';
import {TokenStorageService} from '../auth/token-storage.service';
import { CarsService } from '../cars/cars.service';
import { Car } from '../cars/car';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { InfoMassageComponent } from '../info-massage/info-massage.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: User = new User(null, null, null, null, null, null);
  username: String;
  today = new Date().toISOString().slice(0, 10)
  fileToUpload: File = null;
  httpStatusCode: number;
  error: ErrorEvent;
  avatars: String [];
  avatarUploaded: boolean = true;

  
  constructor(private userService: UsersService, private tokenStorage: TokenStorageService, private carService: CarsService, private router: Router, private dialog: MatDialog) {
  }

  ngOnInit() {
  this.userService.getUserByUsername(this.tokenStorage.getUsername())
  .subscribe(data => this.userProfile = data);

  this.getAvatar();
}

handleFileInput(event) {
  this.fileToUpload = <File>event.target.files[0];
  console.log(event.target.files)
}

uploadFile() {
  var formData = new FormData();
  formData.append('file', this.fileToUpload);
  console.log("file to upload" + formData);

   this.userService.postAvatar(formData, this.tokenStorage.getUsername())
   .subscribe(data => {

     //this.httpStatusCode = error.status,
     error => this.error = error;
     console.log(this.error);
     
 //    this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;

      //  if(this.httpStatusCode === 202){
      //    console.log(this.httpStatusCode);
      //    this.router.navigate(['ui/userprofile']);
      //  } else {
      //    console.log(this.error);
      //    this.showWarning();
      // }
     }     
     );
     window.location.reload();
   }

   getAvatar(){
     this.userService.getAvatar(this.tokenStorage.getUsername())
     .subscribe(data => 
      {this.avatars = data,
      error => this.error = error;
    
    if(this.avatars.length === 0){
      this.avatarUploaded = false;
    } else {
      this.avatarUploaded = true;
    }
    }
      )
      console.log(this.avatars)
   }


   showWarning(): void {
     const dialogRef = this.dialog.open(InfoMassageComponent, {
       height: '150px',
       width: '400px',        
     });
 
     dialogRef.afterClosed().subscribe(result => {
     });
   }

closeProfile(){
  this.router.navigate(['ui/home']);
  }

}
