import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { DataService } from '../services/data.service';
let MissionService = class MissionService extends DataService {
    constructor(http) {
        super(http, "http://localhost:8080/missions/");
        this.apiUrl = "http://localhost:8080/missions/";
    }
    endOfMission(mission) {
        console.log("mission in service", mission);
        const headers = new HttpHeaders().set('Content-type', 'application/json');
        return this.http.put(this.apiUrl + "end", mission, { headers });
    }
};
MissionService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    })
], MissionService);
export { MissionService };
//# sourceMappingURL=mission.service.js.map