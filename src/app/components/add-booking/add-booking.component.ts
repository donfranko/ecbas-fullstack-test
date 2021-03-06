import { Router } from '@angular/router';
import { Component, OnInit, ViewChild, NgZone } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
//import {  MatChipInputEvent } from '@angular/material';
import { ApiService } from './../../shared/api.service';
import {FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";

@Component({
  selector: 'app-add-booking',
  templateUrl: './add-booking.component.html',
  styleUrls: ['./add-booking.component.css']
})

export class AddBookingComponent implements OnInit {
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  @ViewChild('chipList', {static: true}) chipList;
  @ViewChild('resetBookingForm', {static: true}) myNgForm;
  //readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  bookingForm: FormGroup;

  ngOnInit() {
    this.submitBookForm();
  }
  form: FormGroup;

  constructor(
    public fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private bookingApi: ApiService
  ) { }

  /* Reactive book form */
  submitBookForm() {
    this.bookingForm = this.fb.group({
      passengerName: ['', [Validators.required]],
      passengerContactNumber: ['', [Validators.required]],
      pickupTime: ['', [Validators.required]],
      waitingTime: ['', [Validators.required]],
      noOfPassengers: ['', [Validators.required]],
      price: ['', [Validators.required]],
      rating: ['', [Validators.required]],
      asap: false,
      tripWaypointDtos: this.fb.array([])
    })
  }

  get tripWaypointDtos() : FormArray {
    return this.bookingForm.get("tripWaypointDtos") as FormArray
  }

  newTripWayPoint(): FormGroup {
    return this.fb.group({
      tripWayPointId: '',
      lastStop: false,
      locality: '',
      lat: '',
      lng: '',
      tripWayPointTimestamp: '',
    })
  }
  
  addTripWayPoints() {
    this.tripWaypointDtos.push(this.newTripWayPoint());
  }
 
  removeTripWayPoint(i:number) {
    this.tripWaypointDtos.removeAt(i);
  }

  /* Date */
  formatDate(e) {
    var convertDate = new Date(e.target.value).toISOString().substring(0, 10);
    this.bookingForm.get('pickupTime').setValue(convertDate, {
      onlyself: true
    })
  }  

  /* Get errors */
  public handleError = (controlName: string, errorName: string) => {
    return this.bookingForm.controls[controlName].hasError(errorName);
  }  

  public handleErrorTripWayPoint = (controlName: string, errorName: string) => {
    //return this.tripWaypointDtos.controls[controlName].hasError(errorName);
  }  

  /* Submit book */
  submitBookingForm() {
    if (this.bookingForm.valid) {
      this.bookingApi.AddBooking(this.bookingForm.value).subscribe(res => {
        this.ngZone.run(() => this.router.navigateByUrl('/bookings-list'))
      });
    }
  }

}