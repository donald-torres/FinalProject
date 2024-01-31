import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';
import { Bid } from '../models/bid';

@Pipe({
  name: 'postBids',
  standalone: true
})
export class PostBidsPipe implements PipeTransform {



  transform(jobPost: JobPost): Bid[] {
    return jobPost.bids;
  }



}
