import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  loggedInuser: User = new User();
  constructor(
    private auth: AuthService,
    private router:Router
  ){}
logout() {
  this.auth.logout();
  console.log('loggin out');
  this.router.navigateByUrl('index');


}
status(){
  this.auth.checkLogin();
  if(this.auth.checkLogin()){
  //  console.log('your are signed in');}else{
  //   console.log("your are signed out")
   }


}

}
