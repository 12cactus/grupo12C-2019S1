import { Component } from '@angular/core';
import {ApiService} from './services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Arma tu evento!';

    constructor(private apiService: ApiService) {}

    ping() {
        this.apiService.get('ping').subscribe(response => console.log(response));
    }
}
