import { CompanyService } from './../services/company.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Company } from '../models/company';
import { MissionService } from '../mission/mission.service';
import { HeroesService } from '../heroes/heroes.service';

@Component({
  selector: 'app-company',
  templateUrl: './company-create.component.html',
  styleUrls: ['./company-create.component.scss']
})
export class CompanyCreateComponent implements OnInit {
  company: Company;
  public form: FormGroup;
  @Output() companyCreated: EventEmitter<any> = new EventEmitter();
  
  constructor(
    private formBuilder: FormBuilder,
    private companyService : CompanyService,
    private missionService : MissionService,
    private heroService : HeroesService,

    private router : Router
  ) { }

  ngOnInit() { 
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      // assets: ['', Validators.required],
      town: ['', Validators.required]
    })
  }

  submitForm(){
    this.company = this.form.value;
    this.companyService.create(this.company).subscribe((data : Company) => {
      console.log("test redirect");
      this.companyCreated.emit();
      document.location.reload();
    }); 
  }

}
