import { Hero } from './hero';

export class Mission {
    id : number;
    title? : string;
    difficulty? : number;
    safe_people? : number;
    town? : string;
    award? : number;
    date? : Date;
    accomplished? : Date;
    experience? : number;
    heroes? : Array<Hero>;
    isLaunch? : boolean;
    isDone? : boolean;
    isAccomplished? : boolean;
    isTry? : boolean;

}