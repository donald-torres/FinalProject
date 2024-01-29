import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobPost } from '../../models/job-post';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  jobPosts: JobPost[] = [];

  getPostCount(): number {
    return this.jobPosts.length;
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
