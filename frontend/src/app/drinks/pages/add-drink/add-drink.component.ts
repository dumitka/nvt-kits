import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-drink',
  templateUrl: './add-drink.component.html',
  styleUrls: ['./add-drink.component.css']
})
export class AddDrinkComponent implements OnInit {
  pice: any;
  kategorija: string = 'toplo';

  constructor(private ruter: Router) {
    this.pice = history.state.data?.pice;
    if (this.pice == undefined) {
      this.pice = {
        'name': undefined,
        'description': undefined,
        'type': undefined,
        'amountNumber': undefined,
        'amountUnit': undefined,
        'image': undefined,
      }
    }
    else {
      if (this.pice.type === "CARBONATED_DRINK") this.kategorija = "gazirano";
      else if (this.pice.type === "NOCARBONATED_DRINK") this.kategorija = "negazirano";
      else if (this.pice.type === "ALCOHOL") this.kategorija = "alkoholno";
      else this.kategorija = "toplo";
    }
   }

  ngOnInit(): void {
  }

  sacuvaj() {
    console.log("sacuvaj ***");
  }
  
  nazadNaProfil() {
    this.ruter.navigate(["/ServerFirstPage"]);
  }
}
