import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../services/data.service';
import { Hero } from '../models/hero';

@Injectable({
  providedIn: 'root'
})
export class HeroesService extends DataService<Hero> {

  constructor(http: HttpClient) {
    super(http, "http://localhost:8080/heroes")
   }

}
