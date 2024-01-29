import { Provider } from "./provider";
import { JobPost } from "./job-post";
import { Specialty } from "./specialty";

export class Trade {
    id: number;
    name: string;
    createDate: string;
    updateDate: string;
    imageUrl: string;
    jobPosts: JobPost[];
    providers: Provider[];
    specialties: Specialty[];


    constructor(
        id: number = 0,
        name: string = '',
        createDate: string = '',
        updateDate: string = '',
        imageUrl: string = '',
        jobPost: Array<JobPost> = new Array(),
        providers: Array<Provider> = new Array(),
        specailties: Array<Specialty> = new Array()
    ){
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.imageUrl = imageUrl;
        this.jobPosts = jobPost;
        this.providers = providers;
        this.specialties = specailties
    }
}
