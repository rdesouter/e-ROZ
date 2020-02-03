import { TestBed } from '@angular/core/testing';
import { CompanyService } from './company.service';
describe('CompanyService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));
    it('should be created', () => {
        const service = TestBed.get(CompanyService);
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=company.service.spec.js.map