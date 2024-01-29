import { JobPost } from "./job-post";

export class ProjectArea {
    id:number;
    name: string;
    jobPosts: JobPost[];

    constructor(
        id:number = 0,
        name: string = '',
        jobPosts: Array<JobPost> = new Array()
    ){
        this.id = id;
        this.name = name;
        this.jobPosts = jobPosts;
    }
}
