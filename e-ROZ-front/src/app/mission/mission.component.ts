import { Component, OnInit, Input, ViewChild, AfterContentInit, AfterViewChecked, ChangeDetectorRef, Inject } from '@angular/core';
import { MissionService } from './mission.service';
import { Mission } from '../models/mission';
import { Hero } from '../models/hero';
import { HeroesService } from '../heroes/heroes.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { CountdownComponent } from '../countdown/countdown.component';
import { ErozDatePipe } from '../pipe/eroz-date.pipe';
import { interval, Observable, of, Subject, timer } from 'rxjs';
import { delay, takeUntil } from 'rxjs/operators';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CompanyService } from '../services/company.service';
import { Status } from '../models/status';

@Component({
  selector: 'mission',
  templateUrl: './mission.component.html',
  styleUrls: ['./mission.component.scss']
})
export class MissionComponent implements OnInit, AfterViewChecked, AfterContentInit {
  // progressValue = 100;
  // curSec: number = 0;
  index;
  missions: Array<Mission>;
  heroes: Array<Hero>;
  mission: any;
  company;
  parent: any;

  @Input() LIST_IDS: Array<String>;
  @ViewChild(CountdownComponent, { static: false }) countdown: CountdownComponent;
  //@ViewChild(HeroesComponent, {static: true}) heroesComp : HeroesComponent;

  constructor(
    private missionService: MissionService,
    private heroesService: HeroesService,
    private companyService: CompanyService,
    private _erozFormatDate: ErozDatePipe,
    //public heroComp: HeroesComponent,
    private cd: ChangeDetectorRef,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    // console.log(this.LIST_IDS);
    // this.parent = this.missions;
    this.heroesService.refreshNeeded$.subscribe(() => {
      this.getHeroes();
      this.getMissions();
      console.log("refresh for heroes in missionComp called method");
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
    let isDeath = false;
    let isFired = false;
    let getId = missionId.getAttribute('data-mission-id');
    let getDataId = missionId.getAttribute('data-mission');
    console.log("data mission", getDataId);
    console.log("data mission id", getId);
    //console.log("from mission", attributeCD1);
    //console.log("id from mission is:", getId);
    //console.log("from filter method", mission);
    let mission = this.findById(getId);

    mission.heroes.forEach(hero => {
      if (hero.status ==  Status.induty ) {
        isActiv = false;
      }
      if (hero.status == Status.dead) {
         isDeath = true;
      }
      if (hero.status == Status.fired){
         isFired = true;
      };
    });

    if (mission.heroes.length == 0) {
      this.openDialog();
      // alert("Mission '" + mission.title + "' has no hero. \nPlease add at least one !!!");
    } else if (!isActiv || isFired || isDeath) {
      this.openDialogInDuty();
      //console.log(hero.name, " is in", hero.status, " cannot be send to this mission!");
    } else {
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
          this.companyService.refreshNeeded$.next();

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

  succeed(missionId){
    
    let getId = missionId.getAttribute('data-mission-id');
    let mission = this.findById(getId);
    console.log("mission for succeed", mission);
    
    if (mission.isLaunch && mission.isDone){
      console.log("Alors la mission peut être envoyé pour le succeed test");
      mission.heroes.forEach(hero => {
        hero.status = "ACTIV";
        console.log("le status de " + hero.name + " est passé à " + hero.status);   
      });
      console.log("hero in mission now " , mission.heroes);
      this.missionService.isSucceed(mission).subscribe( response => {
        console.log(mission.title, " is succeed ?", response);
        this.heroesService.refreshNeeded$.next();
        this.missionService.refreshNeeded$.next();
        this.companyService.refreshNeeded$.next();
      });
    }
  }

  public heroesSum(id) {
    let total = 0;
    let mission = this.findById(id);
    mission.heroes.forEach(hero => total += hero.strength);
    return total;
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

  findById(id: number) {
    return this.missions.find(m => m.id == id);
  }

  // query method
  // get late of one step ???
  getMission(id): Mission {
    this.missionService.get(id).subscribe(res => {
      this.mission = res;
      //console.log(res);
    });
    return this.mission;
  }

  getHeroes() {
    this.heroesService.getAll().subscribe((heroesResult: Hero[]) => {
      this.heroes = heroesResult;
      console.log("getHeroes in mission", heroesResult);

    });
  }

  getMissions(): Observable<any> {
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
  getHeroesId(title: String) {
    return "el" + title;
  }
  onDrop(event: CdkDragDrop<String[]>) {
    if (event.previousContainer.id == event.container.id) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      console.log("update order of mission list", this.missions);
    }
    else {
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    }
  }
  //MODAL AND DIALOG
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogDataTemplate);
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog box closed');
    });
  }

  openDialogInDuty(): void {
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
}

//FOR DIALOG
@Component({
  selector: 'dialog-data-template',
  templateUrl: 'dialog-data-template.html',
})
export class DialogDataTemplate {
  constructor(
    public dialogRef: MatDialogRef<DialogDataTemplate>
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: 'dialog-for-duty',
  templateUrl: 'dialog-for-duty.html',
})
export class DialogForDuty {
  constructor(
    public dialogDuty: MatDialogRef<DialogForDuty>
  ) { }

  onNoClick(): void {
    this.dialogDuty.close();
  }
}
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
