import { Component, NgModule, OnInit } from '@angular/core';
import {DrinkService} from '../../services/drink-service/drink.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/first-pages/services/user-service/user.service';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { CDK_CONNECTED_OVERLAY_SCROLL_STRATEGY_PROVIDER_FACTORY } from '@angular/cdk/overlay/overlay-directives';

@Component({
  selector: 'app-all-drinks',
  templateUrl: './all-drinks.component.html',
  styleUrls: ['./all-drinks.component.css']
})
export class AllDrinksComponent implements OnInit {
  svaDostupnaPica: any;
  picaZaPrikaz: any;

  constructor(private drinkService: DrinkService, private ruter: Router, private service:UserService,) {
    this.drinkService.svaPica().subscribe(response => {
      this.svaDostupnaPica = response;
      this.picaZaPrikaz = this.svaDostupnaPica;
      this.prikaziPica();
    });
  }

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(['/ServerFirstPage']);
  }
  
  promenaKategorije(e) {
    let kategorija = e.target.innerHTML;
    if (kategorija === "Sve kategorije") this.picaZaPrikaz = this.svaDostupnaPica;
    else if (kategorija === "Gazirana pica") this.filterPoKategoriji("CARBONATED_DRINK");
    else if (kategorija === "Negazirana pica") this.filterPoKategoriji("NOCARBONATED_DRINK");
    else if (kategorija === "Alkoholna pica") this.filterPoKategoriji("ALCOHOL");
    else this.filterPoKategoriji("HOT_DRINK");
    this.prikaziPica();
  }

  filterPoKategoriji(kategorija: string) {
    this.picaZaPrikaz = [];
    this.svaDostupnaPica.forEach(pice => {
      if (pice.type === kategorija) {
        this.picaZaPrikaz.push(pice);
      }
    });
  }

  prikaziPica() {
    let divZaPica = document.getElementById("divZaPica");
    // brisemo sve postojece
    while (divZaPica.firstChild) {
      divZaPica.removeChild(divZaPica.lastChild);
    }
    // dodajemo za svako pice koje odgovara
    divZaPica.setAttribute("style", "overflow-y: scroll;height:500px;");
    let redniBr = 0;
    this.picaZaPrikaz.forEach(pice => {
      let div = document.createElement("div");
      div.className = "mat-card";
      div.setAttribute("style", "margin-left: 31px; margin-top: 15px;width:300px;float: left;");
      div.setAttribute("style", "margin-left: 31px; margin-top: 15px;width:300px;float: left;");
      div.setAttribute("name", pice.id);

      let div2 = document.createElement("div");
      div2.className = "mat-card-title-group";
      let ime = document.createElement("div");
      ime.className = "mat-card-title";
      ime.appendChild(document.createTextNode(pice.name));
      div2.appendChild(ime);
      let slika = document.createElement("img");
      slika.setAttribute("class", "mat-card-lg-image");
      slika.setAttribute("src", "assets\\" + pice.image);
      slika.setAttribute("name", pice.id);
      slika.ondblclick = (e: any) => {this.dupliKlikNaPice(e);};
      div2.appendChild(slika);
      div.appendChild(div2);
      // ----------
      let div3 = document.createElement("div");
      div3.className = "mat-card-content";
      let i = document.createElement("i");
      i.setAttribute("class", "sayings");
      i.appendChild(document.createTextNode(pice.description));
      div3.appendChild(i);
      div.appendChild(div3);
      // ----------
      let div4 = document.createElement("div");
      div4.className = "mat-card-actions";
      let dugme = document.createElement("button");
      dugme.setAttribute("class", "mat-raised-button");
      dugme.setAttribute("name", pice.id);
      dugme.setAttribute("style", "background-color: #5d7c77;width: 100px;text-align: center;color: whitesmoke; " 
        + "font-family: 'Trocchi', serif;font-size: 20px; margin-left: 100px;");
      dugme.appendChild(document.createTextNode("IZBRISI"));
      dugme.onclick = (e:any) => {console.log(e.srcElement.name); this.izbrisi(e);};
      div4.appendChild(dugme);
      div.appendChild(div4);

      divZaPica.appendChild(div);
      redniBr++;
    });
  }

  izbrisi(e) {
    // iskace prozor sa pitanjem
    console.log(e.srcElement.name);
    let odabranoPice;
    for (let elem of this.svaDostupnaPica) {
      if (elem.id == e.srcElement.name) {
        odabranoPice = elem;
        break;
      }
    }
    console.log(odabranoPice);
  }

  dupliKlikNaPice(e) {
    let odabranoPice;
    for (let elem of this.svaDostupnaPica) {
      if (elem.id == e.srcElement.name) {
        odabranoPice = elem;
        break;
      }
    }
    this.ruter.navigate(["/Drink"], {state: {data: {'pice': odabranoPice}}});
  }
}
