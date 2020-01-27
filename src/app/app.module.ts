import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddBookingComponent } from './components/add-booking/add-booking.component';
import { EditBookingComponent } from './components/edit-booking/edit-booking.component';
import { BookingsListComponent } from './components/bookings-list/bookings-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
/* Angular material */
import { AngularMaterialModule } from './material.module';
/* Date time picker */
import { MatMomentDatetimeModule } from '@mat-datetimepicker/moment';
import { MatDatetimepickerModule } from '@mat-datetimepicker/core';




import { MatDialogModule } from '@angular/material/dialog';
import {DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS,
MatDatepickerModule, MatInputModule,MatFormFieldModule} from '@angular/material';
import {MatMomentDateModule, MomentDateAdapter, MAT_MOMENT_DATE_FORMATS, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';



/* http service */
import { HttpClientModule } from '@angular/common/http';
/* CRUD services */
import { ApiService } from './shared/api.service';
/* Reactive form services */
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AddBookingComponent,
    EditBookingComponent,
    BookingsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatMomentDatetimeModule,
    MatDatetimepickerModule,
    MatFormFieldModule,
    MatInputModule,
    // DemoMaterialModule,
    MatDatepickerModule,
    MatDialogModule,
    MatMomentDateModule,
    ReactiveFormsModule,
  ],
  providers: [
    ApiService,
    { provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
    // Comment out the line below to turn off UTC:
    // actually it does not work with the DatetimepickerModule since it uses the MatMomentDateModule
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}},
    // These should be provided by MatMomentDateModule, but it has never worked in stackblitz for some reason:
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS}
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
