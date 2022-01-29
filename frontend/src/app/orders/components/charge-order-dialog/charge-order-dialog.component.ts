import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface ChargeInterface {
  chargeMessage: string;
}

@Component({
  selector: 'app-charge-order-dialog',
  templateUrl: './charge-order-dialog.component.html',
  styleUrls: ['./charge-order-dialog.component.css']
})
export class ChargeOrderDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: ChargeInterface) { }

  ngOnInit(): void {
  }

}
