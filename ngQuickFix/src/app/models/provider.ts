import { Address } from "./address";
import { Appointment } from "./appointment";
import { Bid } from "./bid";
import { Specialty } from "./specialty";
import { Trade } from "./trade";
import { User } from "./user";

export class Provider {
    id: number;
    company: string;
    email: string;
    phone: string;
    ratePerHour: number;
    createDate: string;
    updateDate: string;
    enabled: boolean;
    address: Address;
    user: User;
    description: string;
    logoUrl: string;
    trades: Trade[];
    specialties: Specialty[];
    appointments: Appointment[];
    bids: Bid[];

    constructor(
        id:number = 0,
        company: string = '',
        email:string = '',
        phone: string = '',
        ratePerHour: number = 0,
        createDate: string = '',
        updateDate: string = '',
        enabled: boolean = true,
        address: Address = new Address(),
        user: User = new User,
        description: string = '',
        logoUrl: string = '', 
        trades: Array<Trade> = new Array(),
        specialties: Array<Specialty> = new Array(),
        appointments: Array<Appointment> = new Array(),
        bids: Array<Bid> = new Array()
    ){
        this.id = id;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.ratePerHour = ratePerHour;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.enabled = enabled;
        this.address = address;
        this.user = user;
        this.description = description;
        this.logoUrl = logoUrl;
        this.trades = trades;
        this.specialties = specialties;
        this.appointments = appointments;
        this.bids = bids;
    }
}
