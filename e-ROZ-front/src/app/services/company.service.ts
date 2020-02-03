import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Company } from '../models/company';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyService extends DataService<Company>{
  
  constructor(http: HttpClient) { 
    super(http, "http://localhost:8080/company/");
  }

  apiUrl = "http://localhost:8080/company";

  exist() : Observable<boolean>{
    return this.http.get(this.apiUrl + "/exist").pipe(map(data => data as boolean));
  }

  create(company : Company) {
    const headers = new HttpHeaders().set('Content-type','application/json');
    return this.http.post(this.apiUrl,company,{headers});
  }

  find(){
    return this.http.get(this.apiUrl);
  }

  
}
