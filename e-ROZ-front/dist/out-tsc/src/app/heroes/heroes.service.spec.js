import { TestBed } from '@angular/core/testing';
import { HeroesService } from './heroes.service';
describe('HeroesService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));
    it('should be created', () => {
        const service = TestBed.get(HeroesService);
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=heroes.service.spec.js.map