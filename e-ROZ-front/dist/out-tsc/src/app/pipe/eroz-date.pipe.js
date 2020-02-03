import * as tslib_1 from "tslib";
import { Pipe } from '@angular/core';
import { DatePipe } from '@angular/common';
let ErozDatePipe = class ErozDatePipe extends DatePipe {
    transform(value, ...args) {
        // expect something like Years/Month/Day
        return super.transform(value, 'dd.MM.yyyy AT HH:mm:ss');
    }
};
ErozDatePipe = tslib_1.__decorate([
    Pipe({
        name: 'erozDate'
    })
], ErozDatePipe);
export { ErozDatePipe };
//# sourceMappingURL=eroz-date.pipe.js.map