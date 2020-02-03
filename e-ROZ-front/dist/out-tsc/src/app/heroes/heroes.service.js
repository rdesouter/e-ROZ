import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { DataService } from '../services/data.service';
let HeroesService = class HeroesService extends DataService {
    constructor(http) {
        super(http, "http://localhost:8080/heroes");
    }
};
HeroesService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    })
], HeroesService);
export { HeroesService };
//# sourceMappingURL=heroes.service.js.map