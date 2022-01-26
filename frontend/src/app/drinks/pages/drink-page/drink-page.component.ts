import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-drink-page',
  templateUrl: './drink-page.component.html',
  styleUrls: ['./drink-page.component.css']
})
export class DrinkPageComponent implements OnInit {
  pice: any;
  kategorija: any;

  constructor(private ruter: Router) {
    this.pice = history.state.data.pice;
    if (this.pice.type === "CARBONATED_DRINK") this.kategorija = "Gazirana pica";
    else if (this.pice.type === "NOCARBONATED_DRINK") this.kategorija = "Negazirana pica";
    else if (this.pice.type === "ALCOHOL") this.kategorija = "Alkoholna pica";
    else this.kategorija = "Topli napitci";
   }

  ngOnInit(): void {
  }

  izbrisi() {
    console.log("izbrisiiii ***");
  }
  
  izmeni() {
    this.ruter.navigate(["/AddDrink"], {state: {data: {'pice': this.pice}}});
  }
  
  nazadNaProfil() {
    this.ruter.navigate(["/ServerFirstPage"]);
  }
}
