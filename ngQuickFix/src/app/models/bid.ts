import { JobPost } from "./job-post";
import { Provider } from "./provider";

export class Bid {

    id: number;
    amount: number;
    provider: Provider;
    jobPost: JobPost;
    bidDate: string;
    providerNote: string;
    accepted: boolean;
    ratingByUser: number;
    userComment: string;
    ratingByProvider: number;
    providerComment: string;
    enabled: boolean;

    constructor(
        id: number = 0,
        amount: number = 0,
        provider: Provider = new Provider(),
        jobPost: JobPost = new JobPost(),
        bidDate: string = '',
        providerNote: string = '',
        accepted: boolean = false,
        ratingByUser: number = 0,
        userComment: string = '',
        ratingByProvider: number = 0,
        providerComment: string = '',
        enabled: boolean = true
    ){
        this.id = id;
        this.amount = amount;
        this.provider = provider;
        this.jobPost = jobPost;
        this.bidDate = bidDate;
        this.providerNote = providerNote;
        this.accepted = accepted;
        this.ratingByUser = ratingByUser;
        this.userComment = userComment;
        this.ratingByProvider = ratingByProvider;
        this.providerComment = providerComment;
        this.enabled = enabled;
    }

}
