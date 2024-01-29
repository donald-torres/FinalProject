import { Address } from "./address";
import { BidComment } from "./bid-comment";
import { JobPost } from "./job-post";
import { Provider } from "./provider";

export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  biography: string;
  imageUrl: string;
  createDate: string;
  updateDate: string;
  bidComments: BidComment[];
  address: Address;
  provider: Provider[];
  jobPosts: JobPost[];

  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role: string = '',
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phone: string = '',
    biography: string = '',
    imageUrl: string = '',
    createDate: string = '',
    updateDate: string = '',
    bidComments: Array<BidComment> = new Array(),
    address: Address = new Address(),
    provider: Array<Provider> = new Array(),
    jobPosts: Array<JobPost> = new Array()

  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.biography = biography;
    this.imageUrl = imageUrl;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.bidComments = bidComments;
    this.address = address;
    this.provider = provider;
    this.jobPosts = jobPosts;
  }



}
