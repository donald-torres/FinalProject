import { JobPost } from "./job-post";
import { Provider } from "./provider";

export class Appointment {
    id: number;
    description: string;
    appointmentDate: string;
    provider: Provider;
    jobPost: JobPost;
    
    constructor(
        id: number = 0,
        description: string = '',
        appointmentDate: string = '',
        provider: Provider = new Provider(),
        jobPost: JobPost = new JobPost()
    ){
        this.id = id;
        this.description = description;
        this.appointmentDate = appointmentDate;
        this.provider = provider;
        this.jobPost = jobPost;
    }
}
