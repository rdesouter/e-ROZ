import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class DataService<T> {
  private _refreshNeeded$ = new Subject<void>();
  private data: any[];

  constructor(public http: HttpClient, public apiUrl: string) { }

  // GETTER AND SETTER
  get refreshNeeded$() { return this._refreshNeeded$ }

  getData() { return this.data || []; }

  hasData() { return this.data && this.data.length; }

  setData(data: any[]) {this.data = data; }

  // CRUD method
  // si type en number indique que le httpHeader 
  get(id) {
    
    let params = new HttpParams().set('id', id);
    return this.http.get<any>(this.apiUrl + id, { params: params });
  }

  getAll(): Observable<any> { return this.http.get<Array<T>>(this.apiUrl) }

  create(object: T) {
    const headers = new HttpHeaders().set('Content-type', 'application/json');
    return this.http.post(this.apiUrl, object, { headers });
  }

  update(object: T): Observable<any> {
    const headers = new HttpHeaders().set('Content-type', 'application/json');
    return this.http
      .put(this.apiUrl, object, { headers })
      .pipe(
        tap(() => {
          this.refreshNeeded$.next();
        })
      );
  }

  delete(id: number) {
    const headers = new HttpHeaders().set('Content-type', 'application/json');
    return this.http.delete(`${this.apiUrl}/${id}`, {headers});
  }
}
