import { Booking } from './../../shared/booking';
import { ApiService } from './../../shared/api.service';
import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-bookings-list',
  templateUrl: './bookings-list.component.html',
  styleUrls: ['./bookings-list.component.css']
})

export class BookingsListComponent implements OnInit {
  BookingData: any = [];
  dataSource: MatTableDataSource<Booking>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  displayedColumns: string[] = ['_id', 'passenger_name', 'passenger_contact_number', 'pickup_time', 'no_of_passengers', 'price', 'action'];

  constructor(private bookingApi: ApiService) {
    this.bookingApi.GetBookings().subscribe(data => {
      this.BookingData = data;
      this.dataSource = new MatTableDataSource<Booking>(this.BookingData);
      setTimeout(() => {
        this.dataSource.paginator = this.paginator;
      }, 0);
    })    
  }

  ngOnInit() { }

  deleteBooking(index: number, e){
    if(window.confirm('Are you sure')) {
      const data = this.dataSource.data;
      this.dataSource.data = data.filter(function(item){
          return item['bookingId'] != e.bookingId;
      });
      this.bookingApi.DeleteBooking(e.bookingId).subscribe()
    }
  }

}