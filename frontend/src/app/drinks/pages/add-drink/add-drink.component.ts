import { Component, OnInit } from '@angular/core';
import { DrinkService } from '../../services/drink-service/drink.service';

@Component({
  selector: 'app-add-drink',
  templateUrl: './add-drink.component.html',
  styleUrls: ['./add-drink.component.css']
})
export class AddDrinkComponent implements OnInit {

  constructor(private drinkService: DrinkService) { }

  ngOnInit(): void {
  }

}
