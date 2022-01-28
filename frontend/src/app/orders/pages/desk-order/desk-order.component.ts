import { Component, OnInit } from '@angular/core';
import { DeskOrderService } from '../../services/desk-order.service';
import { Router } from '@angular/router';
import { Desk } from 'src/app/models/desk';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-desk-order',
  templateUrl: './desk-order.component.html',
  styleUrls: ['./desk-order.component.css']
})
export class DeskOrderComponent implements OnInit {
  desk : Desk = new Desk();
  order : Order = new Order();
  ordered : boolean = false;
  deliveredDrinks : boolean = false;
  deliveredMeals : boolean = false;
  delivered : boolean = false;

  constructor(private service: DeskOrderService, private router: Router) {
    this.service.getDesk().subscribe((data : Desk | null) => {
      this.desk = data as Desk;

      let deskStatus : string = this.desk.deskStatus.toString();
      if (deskStatus === 'NOT_ORDERED') {
        this.ordered = false;
      } else {
        this.ordered = true;
      }

      switch (deskStatus) {
        case 'DELIVERED':
          this.deliveredDrinks = true;
          this.deliveredMeals = true;
          this.delivered = true;
          break;
        case 'DELIVERED_DRINKS':
          this.deliveredDrinks = true;
          break;
        case 'DELIVERED_MEALS':
          this.deliveredMeals = true;
          break;
        default:
          this.deliveredDrinks = false;
          this.deliveredMeals = false;
          this.delivered = false;
          break;
      }
    });

    this.service.getOrder().subscribe((data: any) => {
      this.order = data;
    });
  }

  ngOnInit() : void {

  }

  back() : void {
    this.router.navigate(['/login'])
  }
}
