import * as tslib_1 from "tslib";
import { Component, Input, ViewChild } from '@angular/core';
import { Validators, FormGroupDirective } from '@angular/forms';
import { moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
let HeroesComponent = class HeroesComponent {
    constructor(heroService, formBuilder) {
        this.heroService = heroService;
        this.formBuilder = formBuilder;
    }
    ngOnInit() {
        this.newHeroForm = this.formBuilder.group({
            name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(35)]],
            ability: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
            description: ['', [Validators.required, Validators.minLength(50), Validators.maxLength(500)]]
        });
        this.heroService.refreshNeeded$.subscribe(() => {
            this.getHeroes();
            console.log("refresh method called");
        });
        this.getHeroes();
        //console.log("List ids :");
        //console.log(this.LIST_IDS);
    }
    get f() {
        return this.newHeroForm.controls;
    }
    getHero(id) {
        this.heroService.get(id).subscribe(result => {
            //console.log(result);
            return result;
        });
    }
    getHeroes() {
        this.heroService.getAll().subscribe(result => {
            console.log("list of heroes", result);
            this.heroes = result;
        });
    }
    sendNewHero() {
        this.hero = this.newHeroForm.value;
        this.heroService.create(this.hero).subscribe(() => {
            console.log('new hero send ' + this.hero.name);
            setTimeout(() => this.formGroupDirective.resetForm(), 0);
            this.ngOnInit();
        });
    }
    onDrop(event) {
        //console.log(event.container);
        //console.log(transferArrayItem);
        if (event.previousContainer.id == event.container.id) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
            console.log("update order of heroes list", this.heroes);
        }
        else {
            transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
        }
    }
};
tslib_1.__decorate([
    Input()
], HeroesComponent.prototype, "LIST_IDS", void 0);
tslib_1.__decorate([
    ViewChild(FormGroupDirective, { static: true })
], HeroesComponent.prototype, "formGroupDirective", void 0);
HeroesComponent = tslib_1.__decorate([
    Component({
        selector: 'heroes',
        templateUrl: './heroes.component.html',
        styleUrls: ['./heroes.component.scss']
    })
], HeroesComponent);
export { HeroesComponent };
//# sourceMappingURL=heroes.component.js.map