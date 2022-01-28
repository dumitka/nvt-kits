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
    })
  }









  // ngAfterViewInit(): void {
      
  //   console.log("Hello", this.stolovi.toArray());
  // }


  logout(){
    this.authService.logout();
  }



}
