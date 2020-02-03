import * as tslib_1 from "tslib";
import { Component, Output, EventEmitter } from '@angular/core';
import { Validators } from '@angular/forms';
let CompanyCreateComponent = class CompanyCreateComponent {
    constructor(formBuilder, companyService, router) {
        this.formBuilder = formBuilder;
        this.companyService = companyService;
        this.router = router;
        this.companyCreated = new EventEmitter();
    }
    ngOnInit() {
        this.form = this.formBuilder.group({
            name: ['', Validators.required],
            // assets: ['', Validators.required],
            town: ['', Validators.required]
        });
    }
    submitForm() {
        this.company = this.form.value;
        this.companyService.create(this.company).subscribe((data) => {
            console.log("test redirect");
            this.companyCreated.emit();
        });
    }
};
tslib_1.__decorate([
    Output()
], CompanyCreateComponent.prototype, "companyCreated", void 0);
CompanyCreateComponent = tslib_1.__decorate([
    Component({
        selector: 'app-company',
        templateUrl: './company-create.component.html',
        styleUrls: ['./company-create.component.scss']
    })
], CompanyCreateComponent);
export { CompanyCreateComponent };
//# sourceMappingURL=company-create.component.js.map