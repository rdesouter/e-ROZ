import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
let DataService = class DataService {
    constructor(http, url) {
        this.http = http;
        this.url = url;
        this._refreshNeeded$ = new Subject();
    }
    // GETTER AND SETTER
    get refreshNeeded$() { return this._refreshNeeded$; }
    // si type en number indique que le httpHeader 
    get(id) {
        let params = new HttpParams().set('id', id);
        return this.http.get(this.url + id, { params: params });
    }
    getData() { return this.data || []; }
    hasData() { return this.data && this.data.length; }
    setData(data) {
        this.data = data;
    }
    // CRUD method
    getAll() { return this.http.get(this.url); }
    create(object) {
        const headers = new HttpHeaders().set('Content-type', 'application/json');
        return this.http.post(this.url, object, { headers });
    }
    update(object) {
        const headers = new HttpHeaders().set('Content-type', 'application/json');
        return this.http
            .put(this.url, object, { headers })
            .pipe(tap(() => {
            this.refreshNeeded$.next();
        }));
    }
};
DataService = tslib_1.__decorate([
    Injectable({ providedIn: 'root' })
], DataService);
export { DataService };
//# sourceMappingURL=data.service.js.map