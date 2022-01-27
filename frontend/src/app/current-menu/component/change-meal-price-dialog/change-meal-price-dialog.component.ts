import { Component, OnInit , Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog'


export interface MealPriceInterface {
  newPrice: number;
}


@Component({
  selector: 'app-change-meal-price-dialog',
  templateUrl: './change-meal-price-dialog.component.html',
  styleUrls: ['./change-meal-price-dialog.component.css']
})
export class ChangeMealPriceDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: MealPriceInterface,
  ) {}

  ngOnInit(): void {
  }

}
