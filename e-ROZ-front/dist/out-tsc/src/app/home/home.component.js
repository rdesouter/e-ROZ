import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
let HomeComponent = class HomeComponent {
    constructor(companyService, missionService) {
        this.companyService = companyService;
        this.missionService = missionService;
        this.title = 'e-ROZ Corp.';
        this.LIST_IDS = new Array();
    }
    ngOnInit() {
        this.exist();
        this.createListIds();
        this.getCompany();
    }
    exist() {
        let bool = this.companyService.exist();
        bool.subscribe(data => {
            this.companyDoesntExist = !data;
            console.log("companyDoenstExist from Home Component =" + data);
        });
    }
    onCompanyCreated() {
        this.companyDoesntExist = false;
    }
    createListIds() {
        this.LIST_IDS.push('el');
        this.missionService.getAll().subscribe(data => {
            //console.log(data);
            data.forEach(mission => this.LIST_IDS.push('el' + mission.id));
        });
        console.log("list of element: ", this.LIST_IDS);
    }
    getCompany() {
        this.companyService.find().subscribe(res => {
            this.company = res;
            console.log("from home component", res);
        });
    }
};
HomeComponent = tslib_1.__decorate([
    Component({
        selector: 'app-home',
        templateUrl: './home.component.html',
        styleUrls: ['./home.component.scss']
    })
], HomeComponent);
export { HomeComponent };
//# sourceMappingURL=home.component.js.map