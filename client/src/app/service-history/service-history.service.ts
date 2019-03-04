import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ReportDto} from "./report/report-dto";

@Injectable()
export class ServiceHistoryService {

  private URL: string = '/api/report';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    params: {}
  };

  constructor(private http: HttpClient) {
  }


  public getPdfFromDB(reportId: number) {

    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
      //'responseType'  : 'blob' as 'json'        //This also worked
    };
    
    return this.http.get<any>(`${this.URL}/pdf/${reportId}`, httpOptions);

  }


  public getAllReportsByCarId(carId: number): Observable<Array<ReportDto>> {
    return this.http.get<Array<ReportDto>>(`${this.URL}/car/${carId}`)
  }

}
