import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './user';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:9501/users');
  }

  deleteUserById(id: number) {
    return this.http.delete('http://localhost:9501/user/' + id);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>('http://localhost:9501/userbyid/' + id);
  }

  getUserByUsername(username: String): Observable<User> {
    return this.http.get<User>('http://localhost:9501/userbyname/' + username);
  }
}
