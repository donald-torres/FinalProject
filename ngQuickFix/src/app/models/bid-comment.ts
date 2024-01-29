import { Bid } from "./bid";
import { User } from "./user";

export class BidComment {
    id: number;
    content: string;
    commentDate: string;
    bid: Bid;
    user: User;
    
    constructor(
    id: number = 0,
    content: string = '',
    commentDate: string = '',
    bid: Bid = new Bid(),
    user: User = new User(),
    ){
    this.id = id;
    this.content = content ;
    this.commentDate = commentDate;
    this.bid = bid;
    this.user = user;
    }


}
