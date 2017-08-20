import { Injectable }           from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';

import { Observable }           from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class MapsService {
  
  /*
  private url = 'http://integraweb.ddns.net/api/query?radius=2&limit=1000';
  private queryUrl = 'http://integraweb.ddns.net/api/dquery';
*/
  
  private url = 'http://localhost:8080/api/query?radius=2&limit=10000';
  private queryUrl = 'http://localhost:8080/api/dquery';
  

  constructor(
    private http: Http
  ) {}

  getMarkers (dataSend: any): Promise<any> {
    return this.http.post(this.url, dataSend)
              .toPromise()
              .then(this.extractData)
              .catch(this.handleError);
  }

  getQuery (dataSend: any): Promise<any> {
    return this.http.post(this.queryUrl,dataSend)
              .toPromise()
              .then(this.extractData)
              .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }

  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Promise.reject(errMsg);
  }

  

}

