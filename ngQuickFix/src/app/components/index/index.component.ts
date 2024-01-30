import { Component } from '@angular/core';
import { NavigationComponent } from '../navigation/navigation.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [NavigationComponent,FormsModule,CommonModule,NgbModule],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent {

}
