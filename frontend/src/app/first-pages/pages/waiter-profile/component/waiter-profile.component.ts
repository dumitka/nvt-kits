import { AuthService } from 'src/app/login/auth.service';
import { Table } from 'src/app/tables/table.model';
import { WaiterProfileService } from '../service/waiter-profile.service';
import { Component, OnInit } from '@angular/core';
import { TableWrapper } from 'src/app/tables/table-wrapper.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiter-profile',
  templateUrl: './waiter-profile.component.html',
  styleUrls: ['./waiter-profile.component.css']
})
export class WaiterProfileComponent implements OnInit {
  currentWaiter: string = window.localStorage.getItem('username');
  waiters: string[] = [];
  tables: Table[] = [];
  tableWrappers: TableWrapper[] = [];

  constructor(private authService: AuthService, private service: WaiterProfileService, private router: Router) { 
    this.service.getWaiters().subscribe((response: string[]) => {
      this.waiters = response;
    });

    this.service.getDesks().subscribe((response: Table[]) => {
      this.tables = response;
      this.calculateAndUpdateTableNumbers();
    })
  }

  ngOnInit(): void {

  }

  calculateAndUpdateTableNumbers(): void {
    const map = new Map();
    for (let table of this.tables) {
      let score = Math.sqrt(Math.pow(table.x, 2) + Math.pow(table.y, 2));
      map.set(table, score);
    }
    const mapSort = new Map([...map.entries()].sort((a, b) => a[1] - b[1]));

    let i = 1;
    for (let [key, value] of mapSort) {
      this.tableWrappers.push({
        table: key,
        tableNum: i
      })
      i = i + 1;
    }
  }

  logout(): void {
    this.authService.logout();
  }
  
  changeWaiter(username: string): void {
    this.authService.logout(username);
  }

  order(tw: TableWrapper): void {
    this.router.navigate(['/DeskOrder'], { state: { data: { "id": tw.table.id, "number": tw.tableNum } } });
  }
}