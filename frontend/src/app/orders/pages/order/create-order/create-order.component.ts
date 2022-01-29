import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkService } from 'src/app/drinks/services/drink-service/drink.service';
import { MealServiceService } from 'src/app/meal-category/service/meal-service.service';
import { DrinkDTO } from 'src/app/models/drinkDTO';

interface ItemCategory {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  deskId: number = history.state.data.deskId;
  selectedItems: DrinkDTO[] = history.state.data.selectedItems;
  options: string[] = ['Piće', 'Jelo'];
  chosenOption: string = 'Piće';
  drinks: boolean = true;
  items: DrinkDTO[];
  categories: ItemCategory[];
  drinkCategories: ItemCategory[] = [
    { value: '', viewValue: 'Sve kategorije' },
    { value: 'CARBONATED_DRINK', viewValue: 'Gazirana pića' },
    { value: 'NOCARBONATED_DRINK', viewValue: 'Negazirana pića' },
    { value: 'ALCOHOL', viewValue: 'Alkoholna pića' },
    { value: 'HOT_DRINK', viewValue: 'Topli napitci' }
  ];
  mealCategories: ItemCategory[] = [
    { value: '', viewValue: 'Sve kategorije' },
    { value: 'COLD_APPETIZER', viewValue: 'Hladna predjela' },
    { value: 'HOT_APPETIZER', viewValue: 'Topla predjela' },
    { value: 'MAIN_COURSE', viewValue: 'Glavna jela' },
    { value: 'DESERT', viewValue: 'Deserti' },
    { value: 'SALAD', viewValue: 'Salate' },
    { value: 'APPENDICES', viewValue: 'Dodaci' }
  ];

  constructor(private drinkService: DrinkService, private mealService: MealServiceService, private router: Router) {
    this.drinkService.svaPica().subscribe(response => {
      this.items = response as DrinkDTO[];
      this.categories = this.drinkCategories;
      this.showItems();
    });
  }

  ngOnInit(): void {

  }

  changeItems(option: string): void {
    if (option === 'Piće') {
      this.getDrinks();
      this.categories = this.drinkCategories;
      this.drinks = true;
    } else {
      this.getMeals();
      this.categories = this.mealCategories;
      this.drinks = false;
    }
  }

  getDrinks(): void {
    this.drinkService.svaPica().subscribe(response => {
      this.items = response as DrinkDTO[];
      this.showItems();
    });
  }

  getMeals(): void {
    this.mealService.getAllMeals().subscribe(response => {
      this.items = response as DrinkDTO[];
      this.showItems();
    });
  }

  changeCategory(category: string): void {
    if (this.drinks) {
      this.drinkService.svaPica().subscribe(response => {
        this.items = response as DrinkDTO[];
        if (category != '') {
          this.filterItems(category);
        }
        this.showItems();
      });
    } else {
      this.mealService.getAllMeals().subscribe(response => {
        this.items = response as DrinkDTO[];
        if (category != '') {
          this.filterItems(category);
        }
        this.showItems();
      });
    }
  }

  filterItems(category: string): void {
    let newList: DrinkDTO[] = [];
    this.items.forEach(item => {
      if (item.type === category) {
        newList.push(item);
      }
    });
    this.items = newList;
  }

  showItems(): void {
    let itemsSection = document.getElementById("itemsSection");
    while (itemsSection.firstChild) {
      itemsSection.removeChild(itemsSection.lastChild);
    }

    itemsSection.setAttribute("style", "overflow-y: scroll; height: 600px;");
    this.items.forEach(item => {
      let div1 = document.createElement("div");
      div1.className = "mat-card";
      div1.setAttribute("style", "margin-left: 31px; margin-top: 15px; width:300px; float: left;");
      div1.setAttribute("name", item.id.toString());
      div1.setAttribute("id", "item-" + item.id);

      let div2 = document.createElement("div");
      div2.className = "mat-card-title-group";
      let name = document.createElement("div");
      name.className = "mat-card-title";
      name.appendChild(document.createTextNode(item.name));
      div2.appendChild(name);
      let image = document.createElement("img");
      image.setAttribute("class", "mat-card-lg-image");
      image.setAttribute("src", "assets\\" + item.image);
      image.setAttribute("name", item.id.toString());
      image.setAttribute("id", "image-item-" + item.id);
      div2.appendChild(image);
      div1.appendChild(div2);

      let div3 = document.createElement("div");
      div3.className = "mat-card-content";
      let i = document.createElement("i");
      i.setAttribute("class", "sayings");
      i.appendChild(document.createTextNode(item.description));
      div3.appendChild(i);
      div1.appendChild(div3);

      let div4 = document.createElement("div");
      div4.className = "mat-card-actions";
      let button = document.createElement("button");
      button.setAttribute("class", "mat-raised-button");
      button.setAttribute("name", item.id.toString());
      button.setAttribute("id", "digmePica" + item.id);
      button.setAttribute("style", "background-color: #5d7c77; width: 100px; text-align: center; color: whitesmoke; "
        + "font-family: 'Trocchi', serif; font-size: 20px; margin-left: 100px;");
      button.appendChild(document.createTextNode("DODAJ"));
      button.onclick = (e: any) => { this.addItem(e); };
      div4.appendChild(button);
      div1.appendChild(div4);

      itemsSection.appendChild(div1);
    });
  }

  addItem(e: any): void {
    for (let item of this.items) {
      if (item.id == e.srcElement.name) {
        item.isDrink = this.drinks;
        this.selectedItems.push(item);
        break;
      }
    }
  }

  viewOrder(): void {
    this.router.navigate(['/ViewOrder'], { state: { data: { 'deskId': this.deskId, 'selectedItems': this.selectedItems } } });
  }

  back(): void {
    this.router.navigate(['/WaiterProfile']);
  }
}