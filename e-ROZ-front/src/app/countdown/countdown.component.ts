import { Component, OnInit, Renderer2, ElementRef, Input, AfterViewChecked, AfterContentInit, AfterContentChecked, AfterViewInit, ViewChild } from '@angular/core';
import { MissionService } from '../mission/mission.service';
import { Mission } from '../models/mission';
import { take } from 'rxjs/operators';

@Component({
  selector: 'countdown',
  templateUrl: './countdown.component.html',
  styleUrls: ['./countdown.component.scss']
})
export class CountdownComponent implements OnInit, AfterContentInit, AfterViewInit {

  @Input() child: Mission;
  @ViewChild('divSelected', { static: false }) divRef: ElementRef;
  missions: Array<Mission>;
  array: [];
  id;
  missionForEnd;
  constructor(private missionService: MissionService) {

  }

  ngOnInit() {
    // const div = this.renderer.createElement('div');
    // const text = this.renderer.createText('Hello World');
    // this.renderer.appendChild(div, text);
    // this.renderer.appendChild(this.el.nativeElement, div);
  }

  ngAfterViewInit() {
    this.id = this.child.id;
    let mission = this.child;
    let cd = this.divRef.nativeElement;
    cd.classList.add('cd' + this.id);
    //console.log("div ref", this.divRef.nativeElement);

    if (mission.isLaunch && !mission.isTry) {
      //console.log(mission.title + " is started ? " + mission.isLaunch);
      this.missionCountDown(this.child.id, mission.accomplished);
    }
    else {
      //console.log("mission not started", cd);
      cd.classList.add('hide');
    }

    // console.log("each mission", this.child);

    // if(this.child.isLaunch == true && this.child.isDone == true){
    //   console.log("alors la mission " + mission.title +  " est accomplie et finie", );
    // }
  }

  ngAfterContentInit() {

    // if(this.child.isDone){
    //   this.missionService.endOfMission(this.missionForEnd).subscribe(res => {
    //     console.log("result of endOfMission", res);
    //   });
    // }
    // else {
    // console.log("is done for", this.child.title, " :", this.child.isDone);
    // console.log("child", this.child);
    // }


    // this.missionService.getAll().subscribe(res => {
    //   this.missions = res;
    //   console.log("on onInit", res);
    //     //console.log(this.missions.length);
    //     for (let i = 0; i < this.missions.length; i++) {
    //       if (this.missions[i].isLaunch) {
    //         // console.log("id:"+ mission.id + " +title+ " +  mission.title); 
    //         // console.log(this.missions[i].accomplished);
    //         // console.log(i+1);
    //         this.missionCountDown(i + 1, this.missions[i].accomplished);
    //       }
    //       else {
    //         let cdSelected = document.getElementsByClassName("container_countdown")[i];
    //         cdSelected.classList.add('hide');
    //         //console.log("mission not started",cdSelected);             
    //       }
    //     }
    // });
  }

  getDiv() {
    //console.log(divSelected);
    let cdNumber = 'cd' + this.id;
    let cdSelect = document.getElementsByClassName(cdNumber)[0];
    console.log("cdSelect", cdSelect);
  }

  // filter array only with true value and map only two propreties and put into Map ES2015
  addArray() {
    let map = new Map();
    let array = [];
    let one = { id: 1, name: "sauvez willy I", isDone: true };
    let two = { id: 2, name: "sauve willy II", isDone: false };
    let tree = { id: 3, name: "Le retour du Jedi", isDone: true };
    let four = { id: 4, name: "Le reveil de la Force", isDone: false };
    array.push(one);
    array.push(two);
    array.push(tree);
    array.push(four);
    console.log("start array", array);
    let newArray = array.filter(e => e.isDone == true)
      .map(function (e) {
        return {
          id: e.id,
          isDone: e.isDone
        };
      })
      .forEach(el => map.set(el.id, el.isDone));
    // a la place du forEach()
    // for (const it of newArray) {
    //   map.set(it.id,it.isDone);
    // }
    // console.log("map array", map);


    //si besoin de l array filter avec id et isDone faire foreach apres
    //console.log("after filter and map", newArray);
    //newArray.forEach(el => map.set(el.id,el.isDone));
    console.log("mapArray with filter only isDone true and map with id isDone propreties", map);


    // for (let i = 0; i < newArray.length; i++) {
    //   const values =  Object.values(newArray[i]);
    //   const keys =  Object.keys(newArray[i]);
    //   console.log("each value", values);
    //   console.log("each key", keys);

    //   keys.forEach(key => {
    //     if(key == "id"){
    //       console.log(key);

    //     }
    //   });
  }

  setIdForMission(cdNumber, i) {
    let containerSelect = document.getElementsByClassName("container_countdown")[i];
    containerSelect.setAttribute("id", "countdown" + cdNumber);
    console.log("check if div well selected: ", containerSelect);

  }

  missionCountDown(cdNumber, end) {
    let mission = this.child;
    let cdNum = 'cd' + cdNumber;
    let countdownSelect = document.getElementsByClassName(cdNum)[0];
    countdownSelect.classList.remove("hide");

    const second = 1000,
      minute = second * 60,
      hour = minute * 60,
      day = hour * 24;
    let finish = new Date(end).getTime();
    
    cdNumber = setInterval(() => {

      let now = new Date().getTime();
      let distance = finish - now;
      let getDays = countdownSelect.getElementsByClassName('days')[0] as any;
      let getHours = countdownSelect.getElementsByClassName('hours')[0] as any;
      let getMinutes = countdownSelect.getElementsByClassName('minutes')[0] as any;
      let getSeconds = countdownSelect.getElementsByClassName('seconds')[0] as any;

      getDays.innerText = Math.floor(distance / (day)),
        getHours.innerText = Math.floor((distance % (day)) / (hour)),
        getMinutes.innerText = Math.floor((distance % (hour)) / (minute)),
        getSeconds.innerText = Math.floor((distance % (minute)) / second);
      //console.log(distance);
      //do something later when date is reached
      if (distance < 0 && !mission.isTry) {
        let titleH2 = countdownSelect.getElementsByTagName('h2')[0];
        titleH2.classList.add('hide');
        let replacedTitle = document.createElement("h2");
        replacedTitle.innerHTML = "Mission finished";
        countdownSelect.appendChild(replacedTitle);
        countdownSelect.getElementsByTagName('h2')[1].style.textAlign = "center";
        getDays.innerText = 0;
        getHours.innerText = 0;
        getMinutes.innerText = 0;
        getSeconds.innerText = 0;
        //isLaunched is false ???
        mission.isDone = true;
        // for debug
        console.log("Mission accomplished! between 0 - 1000");
        console.log("islaunched", mission.isLaunch, "isdone", mission.isDone);

        if (mission.isDone) {
          console.log(mission.title + " est commencé et terminée");
          this.missionService.endOfMission(mission).subscribe(response => {
            this.missionService.refreshNeeded$.next();
            console.log("result of endMission(), is ", this.child.title, " done? ", response);
          });
        }
        clearInterval(cdNumber);

      } else if (distance < 0 && mission.isTry) {
        let titleH2 = countdownSelect.getElementsByTagName('h2')[0];
        titleH2.classList.add('hide');
        let replacedTitle = document.createElement("h2");
        replacedTitle.innerHTML = "Mission finished";
        countdownSelect.appendChild(replacedTitle);
        countdownSelect.getElementsByTagName('h2')[1].style.textAlign = "center";
        //cdSelect.insertBefore(replacedTitle,cdSelect.childNodes[2]);
        //put countdown to zero
        getDays.innerText = 0;
        getHours.innerText = 0;
        getMinutes.innerText = 0;
        getSeconds.innerText = 0;
        // for debug
        //console.log("Mission accomplished!");
        clearInterval(cdNumber);
      }
    }, 500);
  }

  end() {
    console.log("mouse over?");
  }

  launch() {
    // console.log("default name:" + this.name);
    // console.log(this.child);
    //this.isLaunched = !this.isLaunched;
    // if(this.myVariable){
    //   this.myVariable = false
    // }
    // else {
    //   this.myVariable = true;
    // }
    //console.log(this.child[0]);
  }
}

        // if(this.missionService.hasData()){
        //   let data = this.missionService.getData();
        //   console.log('data store', data);
        // }
        // else {
        //   console.log("isMission done outside ? ", mission);
        //   //this.missionForEnd = mission;
        //   this.missionService.endOfMission(mission).subscribe( response => {
        //     console.log("result of endOfMission", response);
        //     this.missionService.setData(response.data);
        //   });
        // }   
