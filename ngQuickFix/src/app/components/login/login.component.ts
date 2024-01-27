import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginUser: User = new User();
  constructor(private auth: AuthService, private router: Router) {}

  login(user: User) {
    console.log(user.username);
    console.log(user.password);
    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl('index'); // change to user dashboard
      },
      error: (denied) => {
        console.error('error invalid user');
        console.error(denied);
      },
    });
  }
}
