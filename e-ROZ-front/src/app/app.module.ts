import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeroesComponent } from './heroes/heroes.component';
import { MissionComponent, DialogDataTemplate, DialogForDuty } from './mission/mission.component';
import { CompanyCreateComponent } from './company-create/company-create.component';
import { HomeComponent } from './home/home.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule, MatButtonModule, MatCardModule, MatSelectModule, MatProgressBar} from '@angular/material';
import {MatDividerModule} from '@angular/material/divider';
import { CountdownComponent } from './countdown/countdown.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import {MatExpansionModule} from '@angular/material/expansion';
import { ErozDatePipe } from './pipe/eroz-date.pipe';
import { DatePipe } from '@angular/common';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';
import { DataService } from './services/data.service';


@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    MissionComponent,
    CompanyCreateComponent,
    HomeComponent,
    CountdownComponent,
    ErozDatePipe,
    MatProgressBar,
    DialogDataTemplate,
    DialogForDuty
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    HttpClientModule,
    MatDividerModule,
    ReactiveFormsModule,
    DragDropModule,
    MatExpansionModule,
    MatDialogModule
  ],
  entryComponents: [
    DialogDataTemplate,
    DialogForDuty
  ],
  providers: [DatePipe, ErozDatePipe, DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
