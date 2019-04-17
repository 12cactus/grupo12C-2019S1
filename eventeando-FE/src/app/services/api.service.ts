import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

    apiURL = 'http://localhost:1212/';

    constructor(private httpClient: HttpClient) {
      this.httpClient = httpClient;
    }

    get(urlContext) {
      const url = `${this.apiURL}${urlContext}`;
      return this.httpClient.get(url);
    }
}
