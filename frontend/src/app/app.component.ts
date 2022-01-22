import { Component } from '@angular/core';
// import * as Stomp from '@stomp/stompjs';
// import * as SockJS from 'sockjs-client';
import * as SockJS from 'sockjs-client';
import {Stomp} from '@stomp/stompjs';
import { Main} from '../main';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  greetings: string[] = [];
  disabled = true;
  name: string = "";
  private stompClient;

  constructor() {}

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    this.stompClient = Stomp.over(function(){
             return new SockJS(Main.PATH + 'nvt-stomp-endpoint');
          });
    // this.stompClient.reconnect_delay = 3000;

    const _this = this;
    
    this.stompClient.connect({}, function (frame: string) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/hi', function (notification) {
        console.log(notification)
        _this.showGreeting(JSON.parse(notification.body).message);
      });
    });
  }

//JSON.parse(hello.body).greeting
  disconnect() {
    if(this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send('/nvt/hello', {}, JSON.stringify(this.name));
  }

  showGreeting(message: string) {
    this.greetings.push(message);
  }

}
