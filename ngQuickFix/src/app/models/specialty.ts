import { JobPost } from './job-post';
import { Provider } from './provider';
import { Trade } from './trade';

export class Specialty {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  jobPosts: JobPost[];
  providers: Provider[];
  trade: Trade;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string = '',
    jobPosts: Array<JobPost> = new Array(),
    providers: Array<Provider> = new Array(),
    trade: Trade = new Trade()
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.jobPosts = jobPosts;
    this.providers = providers;
    this.trade = trade;
  }
}
