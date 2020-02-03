import * as tslib_1 from "tslib";
import { Component, Input, ViewChild } from '@angular/core';
import { moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { CountdownComponent } from '../countdown/countdown.component';
import { of, Subject } from 'rxjs';
let MissionComponent = class MissionComponent {
    //@ViewChild(HeroesComponent, {static: true}) heroesComp : HeroesComponent;
    constructor(missionService, heroesService, companyService, _erozFormatDate, 
    //public heroComp: HeroesComponent,
    cd, dialog) {
        this.missionService = missionService;
        this.heroesService = heroesService;
        this.companyService = companyService;
        this._erozFormatDate = _erozFormatDate;
        this.cd = cd;
        this.dialog = dialog;
        this._refreshNeeded$ = new Subject();
    }
    get refreshNeeded$() {
        return this._refreshNeeded$;
    }
    ngOnInit() {
        // console.log(this.LIST_IDS);
        // this.parent = this.missions;
        this.heroesService.refreshNeeded$.subscribe(() => {
            this.getHeroes();
            console.log("refresh method");
        });
        this.getMissions();
        this.getHeroes();
    }
    ngAfterContentInit() {
        console.log("after content init");
    }
    ngAfterViewChecked() {
        // this.parent = this.missions;
        this.cd.detectChanges();
        //console.log("after view check");
    }
    launch(missionId) {
        let isActiv = true;
        let getId = missionId.getAttribute('data-mission-id');
        let getDataId = missionId.getAttribute('data-mission');
        console.log("data mission", getDataId);
        console.log("data mission id", getId);
        //console.log("from mission", attributeCD1);
        //console.log("id from mission is:", getId);
        //console.log("from filter method", mission);
        let mission = this.findById(getId);
        mission.heroes.forEach(hero => {
            if (hero.status == 'IN_DUTY') {
                isActiv = false;
            }
        });
        if (mission.heroes.length == 0) {
            this.openDialog();
            // alert("Mission '" + mission.title + "' has no hero. \nPlease add at least one !!!");
        }
        else if (!isActiv) {
            this.openDialogInDuty();
            //console.log(hero.name, " is in", hero.status, " cannot be send to this mission!");
        }
        else {
            // let begin = this._erozFormatDate.transform(value.date);
            // let finish = this._erozFormatDate.transform(value.accomplished);
            // console.log("object from function findById :", value);
            // console.log("mission start the", begin);
            // console.log("mission end the", finish);
            let end = mission.accomplished;
            //console.log('countdown' + getId);
            //console.log('data id' + getDataId);
            this.countdown.missionCountDown(getId, end);
            mission.isLaunch = true;
            console.log("after change value of isLaunch", mission.isLaunch);
            console.log(mission);
            this.missionService.update(mission).subscribe(() => {
                this.heroesService.getAll().subscribe((res) => {
                    this.heroesService.refreshNeeded$.next();
                    console.log("inside subscription", res);
                });
            });
            let hideEl = document.getElementsByClassName('hide')[0];
            hideEl.classList.remove('hide');
        }
        //window.location.reload();
        //}
        // with classic Date Time (GMT included)
        //let end = new Date(parseInt(el.getAttribute('data-mission-id')));
        // with custom eroz Date
        //let end1 = this._erozFormatDate.transform(el.getAttribute('data-mission-id'));
        //alert('la mission prend fin le :' + end1);
    }
    testHeroes() {
        this.heroesService.getAll().subscribe((res) => {
            console.log("update heroes", res);
            //this.heroesComp.ngOnInit();
            this.heroesService.refreshNeeded$.next();
        });
    }
    setIndex(i, missionId) {
        this.index = i + 1;
        let getId = missionId.getAttribute('data-mission-id');
        console.log("index of mission fron ngFor: ", i + 1);
        console.log("attribute id in mission: ", getId);
        this.countdown.setIdForMission(getId, i);
    }
    findById(id) {
        return this.missions.find(m => m.id == id);
    }
    // query method
    // get late of one step ???
    getMission(id) {
        this.missionService.get(id).subscribe(res => {
            this.mission = res;
            //console.log(res);
        });
        return this.mission;
    }
    getHeroes() {
        this.heroesService.getAll().subscribe((heroesResult) => {
            this.heroes = heroesResult;
            console.log("getHeroes in mission", heroesResult);
        });
    }
    getMissions() {
        let allMissions = this.missionService.getAll().subscribe(response => {
            console.log("list of missions", response);
            this.missions = response;
            response.forEach(a => {
                console.log("is misssion launch ? ", a.isLaunch);
            });
        });
        return of(allMissions);
    }
    getCompany() {
        this.companyService.find().subscribe(res => {
            this.company = res;
            console.log("company", res);
        });
    }
    // DRAG DROP
    getHeroesId(title) {
        return "el" + title;
    }
    onDrop(event) {
        if (event.previousContainer.id == event.container.id) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
            console.log("update order of mission list", this.missions);
        }
        else {
            transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
        }
    }
    //MODAL AND DIALOG
    openDialog() {
        const dialogRef = this.dialog.open(DialogDataTemplate);
        dialogRef.afterClosed().subscribe(result => {
            console.log('Dialog box closed');
        });
    }
    openDialogInDuty() {
        const dialogRefForDuty = this.dialog.open(DialogForDuty);
        dialogRefForDuty.afterClosed().subscribe(result => {
            console.log('Dialog box closed');
        });
    }
    //For view or hide countdown
    addHide(missionId) {
        missionId.getAttribute('data-mission');
        let cdSelected = missionId.querySelector('[data-countdown]');
        cdSelected.classList.add('hide');
    }
    removeHide() {
        let cdSelected = document.getElementsByClassName('hide')[0];
        console.log(cdSelected);
        cdSelected.classList.remove('hide');
    }
    //For testing
    test(missionId) {
        let getId = missionId.getAttribute('data-mission-id');
        let mission = this.findById(getId);
        //console.log(typeof mission.difficulty);
        if (mission.heroes.length == 0) {
            console.log("Alors la liste est bien vide");
        }
        console.log(mission.heroes);
    }
    statusHero(missionId) {
        let getId = missionId.getAttribute('data-mission-id');
        let mission = this.findById(getId);
        console.log(mission.heroes[0].status);
    }
};
tslib_1.__decorate([
    Input()
], MissionComponent.prototype, "LIST_IDS", void 0);
tslib_1.__decorate([
    ViewChild(CountdownComponent, { static: false })
], MissionComponent.prototype, "countdown", void 0);
MissionComponent = tslib_1.__decorate([
    Component({
        selector: 'mission',
        templateUrl: './mission.component.html',
        styleUrls: ['./mission.component.scss']
    })
], MissionComponent);
export { MissionComponent };
//FOR DIALOG
let DialogDataTemplate = class DialogDataTemplate {
    constructor(dialogRef) {
        this.dialogRef = dialogRef;
    }
    onNoClick() {
        this.dialogRef.close();
    }
};
DialogDataTemplate = tslib_1.__decorate([
    Component({
        selector: 'dialog-data-template',
        templateUrl: 'dialog-data-template.html',
    })
], DialogDataTemplate);
export { DialogDataTemplate };
let DialogForDuty = class DialogForDuty {
    constructor(dialogDuty) {
        this.dialogDuty = dialogDuty;
    }
    onNoClick() {
        this.dialogDuty.close();
    }
};
DialogForDuty = tslib_1.__decorate([
    Component({
        selector: 'dialog-for-duty',
        templateUrl: 'dialog-for-duty.html',
    })
], DialogForDuty);
export { DialogForDuty };
//for progress bar
// startTimer(seconds: number){
//const time = seconds;
//   const timer$ = interval(1000);
//   const sub = timer$.subscribe((sec) => {
//     this.progressValue = 100 - (sec * 100) / seconds;
//     console.log(this.progressValue);
//     console.log("sec", sec);
//     console.log("curSec", this.curSec);
//     this.curSec = sec;
//     if(this.curSec == seconds){
//       sub.unsubscribe();
//     }
//   });
// }
//# sourceMappingURL=mission.component.js.map