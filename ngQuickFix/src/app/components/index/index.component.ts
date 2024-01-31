import { Component, OnInit } from '@angular/core';
import { NavigationComponent } from '../navigation/navigation.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [NavigationComponent,FormsModule,CommonModule,NgbModule,RouterLink],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent implements OnInit {
  account: boolean = false;

  public isCollapsed: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
    ){}

  ngOnInit(): void { }

  loggedIn(){
    this.account = this.authService.checkLogin();
   return this.account;
  }
  quotes: string[] = [
    "Turn your dreams of a perfect home into reality, one project at a time.",
  "Every nail, every screw, every effort brings you closer to a home you'll love.",
  "Home improvement is a journey of love for your space. Enjoy every step!",
  "A home is a canvas; your DIY projects are the strokes that make it a masterpiece.",
  "Replace the old, welcome the new – transforming your home is an art of renewal.",
  "Building your dream home is not just about bricks and mortar; it's about creating memories.",
  "The sound of a hammer is the symphony of progress in your home.",
  "Small projects, big impact. Every improvement enhances the beauty of your sanctuary.",
  "Don't just build a house; create a haven where memories are made and dreams are nurtured.",
  "Your home is your masterpiece. Let every improvement be a stroke of genius.",
  ];

  getRandomQuote(): string {
    return this.quotes[Math.floor(Math.random() * this.quotes.length)];
  }
  images: string[] = [
    "assets/randomarray1.webp",
    "assets/randomarray2.jpeg",
    "assets/randomarray3.webp",
    "assets/randomarray4.webp",
    // Add more image paths as needed
  ];

  getRandomImage(): string {
    return this.images[Math.floor(Math.random() * this.images.length)];
  }

}
