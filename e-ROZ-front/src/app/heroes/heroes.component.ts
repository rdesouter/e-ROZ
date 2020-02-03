import { Component, OnInit, Input, ViewChild, AfterViewInit, AfterViewChecked, AfterContentInit, AfterContentChecked } from '@angular/core';
import { Hero } from '../models/hero';
import { HeroesService } from './heroes.service';
import { FormGroup, FormControl, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss']
})
export class HeroesComponent implements OnInit {
  heroes : Array<Hero>;
  hero : Hero;
  heroId ;
  newHeroForm: FormGroup;
  @Input() LIST_IDS : Array<String>;
  @ViewChild(FormGroupDirective, {static: true}) formGroupDirective: FormGroupDirective;

  constructor( private heroService: HeroesService, private formBuilder : FormBuilder) { }

  ngOnInit() {
    this.newHeroForm = this.formBuilder.group({
      name: ['', [Validators.required,Validators.minLength(3), Validators.maxLength(15)]],
      ability: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
      description: ['', [Validators.required,Validators.minLength(50), Validators.maxLength(500)]]
    });
    this.heroService.refreshNeeded$.subscribe(()=>{
      this.getHeroes();
      console.log("refresh method called"); 
    });
    this.getHeroes();
     //console.log("List ids :");
     //console.log(this.LIST_IDS);   
  }

  get f(){
    return this.newHeroForm.controls;
  }

  getHero(id: number){
    this.heroService.get(id).subscribe(result => {
      //console.log(result);
      return result;
    });
  }

  getHeroes(){
    this.heroService.getAll().subscribe(result => {
      console.log("list of heroes", result);

      this.heroes = result;
      this.heroes.forEach(hero => {
        // console.log("each hero id " + hero.id)
        //document.getElementById("list_of_hero");
      })
    })
  }

  sendNewHero(){
    this.hero = this.newHeroForm.value;
    this.heroService.create(this.hero).subscribe( ()=>{
      console.log('new hero send ' + this.hero.name);
      setTimeout(() => this.formGroupDirective.resetForm(), 0)
      this.ngOnInit();
    });
  }

  test(){
    let heroId = document.getElementById("hero17");
    console.log("each div for hero id", heroId);    
  }


  getBgColor(status : string){
    switch(status) {
      case 'ACTIV':
        return 'linear-gradient(45deg, rgb(38, 135, 218), rgb(11, 85, 220))';
      case 'IN_DUTY':
        return 'linear-gradient(45deg, rgb(235, 150, 17), rgb(206, 94, 14))';
      case 'DEAD':
        return 'linear-gradient(45deg, rgb(0, 0, 0), rgb(35, 35, 55))';
      case 'FIRED':
        return 'linear-gradient(45deg, rgb(220, 20, 20), rgb(155, 15, 15))';
    } 
  }

  onDrop(event : CdkDragDrop<string[]>){
    //console.log(event.container);
    //console.log(transferArrayItem);
    if(event.previousContainer.id == event.container.id){
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      console.log("update order of heroes list" , this.heroes);
    }
    else {
      transferArrayItem(event.previousContainer.data, event.container.data,event.previousIndex,event.currentIndex);
    }
  }
}
