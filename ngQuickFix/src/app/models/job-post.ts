import { Appointment } from "./appointment";
import { Bid } from "./bid";
import { ProjectArea } from "./project-area";
import { Specialty } from "./specialty";
import { Trade } from "./trade";
import { User } from "./user";

export class JobPost {
    id: number;
    title: string;
    description: string;
    createDate: string;
    updateDate: string;
    complete: boolean;
    enabled: boolean;
    startDate: string;
    specialInstructions: string;
    materialsProvided: boolean;
    imageUrl: string;
    user: User;
    budgetMax: number;
    bidBy: string;
    projectAreas: ProjectArea[];
    appointments: Appointment[];
    trades: Trade[];
    specialties: Specialty[];
    bids: Bid[];

    constructor(
        id: number = 0,
        title: string = '',
        description: string = '',
        createDate: string = '',
        updateDate: string ='', 
        complete: boolean = false,
        enabled: boolean = true,
        startDate: string = '',
        specialInstructions: string = '',
        materialsProvided: boolean = false,
        imageUrl: string = '',
        user: User = new User(),
        budgetMax: number = 0,
        bidBy: string = '',
        projectAreas: Array<ProjectArea> = new Array(),
        appointments: Array<Appointment> = new Array(),
        trades: Array<Trade> = new Array(),
        specialties: Array<Specialty> = new Array(),
        bids: Array<Bid> = new Array()
    ){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.complete = complete;
        this.enabled = enabled;
        this.startDate = startDate;
        this.specialInstructions = specialInstructions;
        this.materialsProvided = materialsProvided;
        this.imageUrl = imageUrl;
        this.user = user;
        this.budgetMax = budgetMax;
        this.bidBy = bidBy;
        this.projectAreas = projectAreas;
        this.appointments = appointments;
        this.trades = trades;
        this.specialties = specialties;
        this.bids = bids;
    }


}
