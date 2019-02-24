import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private roleUrl = 'http://localhost:9501/api/role';

  constructor(private http: HttpClient) {
  }

  getAllRoles(): Observable<String[]> {
    return this.http.get<String[]>(this.roleUrl, httpOptions);
  }
}
