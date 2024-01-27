import { AuthService } from './services/auth.service';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavigationComponent } from './components/navigation/navigation.component';
import { FormsModule } from '@angular/forms';
import { IndexComponent } from "./components/index/index.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [CommonModule, RouterOutlet, NavigationComponent, FormsModule, IndexComponent]
})
export class AppComponent implements OnInit {
  title = 'ngQuickFix';

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    // this.tempTestDeleteMeLater(); // DELETE LATER!!!
  }

  tempTestDeleteMeLater() {
    this.auth.login('admin', 'test').subscribe({
      // change username to match DB
      next: (data) => {
        console.log('Logged in:');
        console.log(data);
      },
      error: (fail) => {
        console.error('Error authenticating:');
        console.error(fail);
      },
    });
  }
}
