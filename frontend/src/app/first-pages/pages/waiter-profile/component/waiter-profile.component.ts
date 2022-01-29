import { MatButton } from '@angular/material/button';
import { AuthService } from 'src/app/login/auth.service';
import { Table } from 'src/app/tables/table.model';
import { WaiterProfileService } from '../service/waiter-profile.service';
import { ElementRef, Component,
  ViewChildren,
  AfterViewInit,
  OnInit,
  QueryList, 
  ViewChild} from '@angular/core';
  import { TableComponent } from 'src/app/table/table.component';
import { map } from 'rxjs';
import { TableWrapper } from 'src/app/tables/table-wrapper.model';

@Component({
  selector: 'app-waiter-profile',
  templateUrl: './waiter-profile.component.html',
  styleUrls: ['./waiter-profile.component.css']
})
export class WaiterProfileComponent implements OnInit {

  name = "Screen"
  foods : any[] = [{num:1, value: "Banana"}, {num:2, value: "ANanas"}]

  tables: Table[] = []
  tableWrappers: TableWrapper[] = []

  constructor(private authService: AuthService, private service: WaiterProfileService) { }

  ngOnInit(): void {
    this.service.getAllDesks().subscribe((data: any) => {
      this.tables = data;
      console.log("OVO SMO PRIMILI KAO SVE STOLOVE")
      console.log(data)

      this.calculateAndUpdateTableNumbers();

    })
  }

  calculateAndUpdateTableNumbers() {
    const map1 = new Map();

    for( let table of this.tables) {
      let score = Math.sqrt(Math.pow(table.x, 2) + Math.pow(table.y, 2) );

      map1.set(table, score);
    } 
    const mapSort1 = new Map([...map1.entries()].sort((a, b) => a[1] - b[1]));
    console.log(mapSort1);
    let i = 1;
    for (let [key, value] of mapSort1) {

      this.tableWrappers.push({
        table: key,
        tableNum: i
      })
      i = i+1;
    }
    console.log(this.tableWrappers)
  }

  logout(){
    this.authService.logout();
  }

}
