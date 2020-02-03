import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';


@Pipe({
  name: 'erozDate'
})
export class ErozDatePipe extends DatePipe implements PipeTransform {

  transform(value: Date, ...args: any[]): any {
    // expect something like Years/Month/Day
    return super.transform(value, 'dd.MM.yyyy AT HH:mm:ss');
  }

}
