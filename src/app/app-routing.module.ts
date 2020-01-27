import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddBookingComponent } from './components/add-booking/add-booking.component';
import { EditBookingComponent } from './components/edit-booking/edit-booking.component';
import { BookingsListComponent } from './components/bookings-list/bookings-list.component';



const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'add-booking' },
  { path: 'add-booking', component: AddBookingComponent },
  { path: 'edit-booking/:id', component: EditBookingComponent },
  { path: 'bookings-list', component: BookingsListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
