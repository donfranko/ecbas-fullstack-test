import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, NgZone } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material';
import { ApiService } from './../../shared/api.service';
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";

export interface Subject {
  name: string;
}

@Component({
  selector: 'app-edit-booking',
  templateUrl: './edit-booking.component.html',
  styleUrls: ['./edit-booking.component.css']
})

export class EditBookingComponent implements OnInit {
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  @ViewChild('chipList', {static: true}) chipList;
  @ViewChild('resetBookingForm', {static: true}) myNgForm;
  bookingForm: FormGroup;

  ngOnInit() {
    this.updateBookForm();
  }

  constructor(
    public fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private actRoute: ActivatedRoute,
    private bookingApi: ApiService
  ) { 
    var id = this.actRoute.snapshot.paramMap.get('id');
    this.bookingApi.GetBooking(id).subscribe(data => {
      this.bookingForm = this.fb.group({
        passengerName: [data.passengerName, [Validators.required]],
        passengerContactNumber: [data.passengerContactNumber, [Validators.required]],
        pickupTime: [data.pickupTime, [Validators.required]],
        waitingTime: [data.waitingTime, [Validators.required]],
        noOfPassengers: [data.noOfPassengers, [Validators.required]],
        price: [data.price, [Validators.required]],
        rating: [data.rating, [Validators.required]],
        asap: [data.asap],
        tripWaypointDtos: this.fb.array([])
      })
      this.getFormArrayTrip(data.tripWayPoints);
    })    
  }

  /*Get FormArray of TripWayPoint*/
  getFormArrayTrip(tripWayPoints){
    if(tripWayPoints != null){
      tripWayPoints.forEach(element => {
        (<FormArray>this.bookingForm.controls['tripWaypointDtos']).push(this.fb.group({
          tripWayPointId: element.tripWayPointId,
          lastStop: element.lastStop,
          locality: element.locality,
          lat: element.lat,
          lng: element.lng,
          tripWayPointTimestamp: element.tripWayPointTimestamp
        }));
      });
    }
  }

  /* Reactive book form */
  updateBookForm() {
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

  /* Date */
  formatDate(e) {
    var convertDate = new Date(e.target.value).toISOString().substring(0, 10);
    this.bookingForm.get('pickupTime').setValue(convertDate, {
      onlyself: true
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

  /* Get errors */
  public handleError = (controlName: string, errorName: string) => {
    return this.bookingForm.controls[controlName].hasError(errorName);
  }

  public handleErrorTripWayPoint = (controlName: string, errorName: string) => {
    //return this.tripWaypointDtos.controls[controlName].hasError(errorName);
  }  

  /* Update book */
  updateBookingForm() {
    console.log(this.bookingForm.value)
    var id = this.actRoute.snapshot.paramMap.get('id');
    if (window.confirm('Are you sure you want to update?')) {
      this.bookingApi.UpdateBooking(id, this.bookingForm.value).subscribe( res => {
        this.ngZone.run(() => this.router.navigateByUrl('/bookings-list'))
      });
    }
  }
  
}
