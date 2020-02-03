import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { DataService } from '../services/data.service';
import { Mission } from '../models/mission';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MissionService extends DataService<Mission>{

  constructor(http: HttpClient) {
    super(http, "http://localhost:8080/missions/");
   }

   apiUrl = "http://localhost:8080/missions/"; 

   endOfMission(mission: Mission): Observable<any>{
    console.log("mission in service", mission);
    const headers = new HttpHeaders().set('Content-type','application/json');
    return this.http.put(this.apiUrl + "end", mission, {headers});
   }

   isSucceed(mission: Mission){
    const headers = new HttpHeaders().set('Content-type','application/json');
    return this.http.put(this.apiUrl + "succeed", mission, {headers});
   }
   
}
