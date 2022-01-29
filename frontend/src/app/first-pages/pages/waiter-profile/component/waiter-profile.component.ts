import { MatButton } from '@angular/material/button';
import { AuthService } from 'src/app/login/auth.service';
import { Table } from 'src/app/tables/table.model';
import { WaiterProfileService } from '../service/waiter-profile.service';
import { ElementRef, Component,
  ViewChildren,
  AfterViewInit,
  OnInit,
  QueryList } from '@angular/core';
  import { TableComponent } from 'src/app/table/table.component';
import { map } from 'rxjs';

@Component({
  selector: 'app-waiter-profile',
  templateUrl: './waiter-profile.component.html',
  styleUrls: ['./waiter-profile.component.css']
})
export class WaiterProfileComponent implements OnInit {

  name = "Screen"

  foods : any[] = [{num:1, value: "Banana"}, {num:2, value: "ANanas"}]
  tables: Table[] = []


  constructor(private authService: AuthService, private service: WaiterProfileService) { }

  // @ViewChildren(TableComponent) stolovi: QueryList<TableComponent>;

  ngOnInit(): void {
    this.service.getAllDesks().subscribe((data: any) => {
      this.tables = data;
      console.log("OVO SMO PRIMILI KAO SVE STOLOVE")
      console.log(data)
      if(this.tables[0].tableNum === 0){ //ili Nan
        console.log("HEJ TREBA IZRACUNATI")
        this.calculateAndUpdateTableNumbers();
      }
    })
  }

  calculateAndUpdateTableNumbers() {
    const map1 = new Map();

    for( let table of this.tables) {
      let score = Math.sqrt(Math.pow(table.x, 2) + Math.pow(table.y, 2) );
      map1.set(table.id, score);
    } 

    const mapSort1 = new Map([...map1.entries()].sort((a, b) => a[1] - b[1]));
    console.log(mapSort1);
    
    let i = 1;
    let updatedTables : Table[] = [];
    for (let [key, value] of mapSort1) {
      this.service.setTableNum(key, i).subscribe((data:any) => {
        console.log("Update uradjen");
        console.log(data)
        updatedTables.push(data);
      })
      i = i+1;
    }
    console.log(updatedTables)
    this.tables = updatedTables;
  }

  logout(){
    this.authService.logout();
  }
}