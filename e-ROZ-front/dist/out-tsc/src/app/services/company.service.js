import * as tslib_1 from "tslib";
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
let CompanyService = class CompanyService {
    constructor(http) {
        this.http = http;
        this.url = "http://localhost:8080/company";
    }
    exist() {
        return this.http.get(this.url + "/exist").pipe(map(data => data));
    }
    create(company) {
        const headers = new HttpHeaders().set('Content-type', 'application/json');
        return this.http.post(this.url, company, { headers });
    }
    find() {
        return this.http.get(this.url);
    }
};
CompanyService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    })
], CompanyService);
export { CompanyService };
//# sourceMappingURL=company.service.js.map