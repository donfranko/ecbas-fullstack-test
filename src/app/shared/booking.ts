import {TripWaypoint} from "./tripWaypoint";

export class Booking {
    bookingId: String;
    passengerName: String;
    passengerContactNumber: String;
    pickupTime: Date;
    asap: Boolean;
    waitingTime: number;
    noOfPassengers: number;
    price: BigInteger;
    rating: number;
    tripWayPoints: Array<TripWaypoint>;
 }