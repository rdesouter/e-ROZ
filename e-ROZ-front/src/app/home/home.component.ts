import { MissionService } from '../mission/mission.service';
import { Observable } from 'rxjs';
import { CompanyService } from './../services/company.service';
import { Component, OnInit, Input, EventEmitter, ViewChild, AfterContentInit, AfterViewChecked } from '@angular/core';
import { MissionComponent } from '../mission/mission.component';
import { Company } from '../models/company';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  title = 'e-ROZ Corp.';
  companyDoesntExist: boolean;
  company: Company;

  LIST_IDS: Array<String> = new Array();

  constructor(
    private companyService: CompanyService,
    private missionService: MissionService,
  ) { }

  ngOnInit() {
    this.companyService.refreshNeeded$.subscribe(()=>{
      this.exist();
    });
    this.exist();
  }

  getCompany() {
    this.companyService.find().subscribe(res => {
      this.company = res;
      console.log("from home component", res);
      if (this.company.assets <= 0) {
        console.log(this.company.name + " should be destroy ! Use id " + this.company.id);
        this.companyService.delete(this.company.id).subscribe(response => {
          console.log("réponse du back", response);
        });

      }
    })
  }

  exist() {
    let bool = this.companyService.exist();
    bool.subscribe(data => {
      this.companyDoesntExist = !data;
      console.log("companyDoenstExist from Home Component =" + data);
      if (data) {
        console.log("La company existe déjà" + this.companyDoesntExist);        
        this.getCompany();
        this.createListIds(); 
      }
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



}
