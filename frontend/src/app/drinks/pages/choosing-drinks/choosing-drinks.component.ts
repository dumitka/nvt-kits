import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/first-pages/services/user-service/user.service';
import { DialogInputPriceComponent } from '../../components/dialog-input-price/dialog-input-price.component';
import { DrinkService } from '../../services/drink-service/drink.service';

@Component({
  selector: 'app-choosing-drinks',
  templateUrl: './choosing-drinks.component.html',
  styleUrls: ['./choosing-drinks.component.css']
})
export class ChoosingDrinksComponent implements OnInit {
  picaZaPrikaz: any;
  pretraga: string = '';
  kategorija: string = '';
  kartaPica: any;
  
  constructor(private drinkService: DrinkService, private ruter: Router, private service:UserService,
      private snackBar: MatSnackBar, public dialog: MatDialog,) {
    this.kartaPica = history.state.data?.kartaPica;
    if (this.kartaPica == undefined) {
      this.kartaPica = {
        id: undefined,
        dateOfValidation: undefined,
        drinkPriceDTOs: [],
        restaurantId: undefined,
      };
    }
    this.drinkService.svaPica().subscribe((response: any) => {
      this.picaZaPrikaz = [];
      for (let pice of response) {
        let dodato = false;
        for (let elem of this.kartaPica.drinkPriceDTOs) {
          if (elem.drinkDTO.id == pice.id) {
            dodato = true;
            break;
          }
        }
        if (!dodato) this.picaZaPrikaz.push(pice);
      }
      this.prikaziPica();
  });
}

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(['/ServerFirstPage']);
  }
  
  promenaKategorije(e) {
    let k = e.target.innerHTML;
    if (k === "Sve kategorije") this.kategorija = "SVE";
    else if (k === "Gazirana pica") this.kategorija = "CARBONATED_DRINK";
    else if (k === "Negazirana pica") this.kategorija = "NOCARBONATED_DRINK";
    else if (k === "Alkoholna pica") this.kategorija = "ALCOHOL";
    else this.kategorija = "HOT_DRINK";
    this.pretrazi();
  }

  filterPoKategoriji() {
    if (this.kategorija === "SVE") return;
    let novaLista = [];
    this.picaZaPrikaz.forEach(pice => {
      if (pice.type === this.kategorija) {
        novaLista.push(pice);
      }
    });
    this.picaZaPrikaz = novaLista;
  }
  
  pretrazi() {
    if (this.pretraga === '') {
      this.drinkService.svaPica().subscribe(response => {
        this.picaZaPrikaz = response;
        if (this.kategorija != '') this.filterPoKategoriji();
        this.prikaziPica();
      });
    }
    else {
      this.drinkService.pretraziPica(this.pretraga).subscribe(response => {
        this.picaZaPrikaz = response;
        if (this.kategorija != '') this.filterPoKategoriji();
        this.prikaziPica();
      });
    }
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
      dugme.appendChild(document.createTextNode("DODAJ"));
      dugme.onclick = (e:any) => {console.log(e.srcElement.name); this.dodaj(e);};
      div4.appendChild(dugme);
      div.appendChild(div4);

      divZaPica.appendChild(div);
      redniBr++;
    });
  }

  dupliKlikNaPice(e) {
    let odabranoPice;
    for (let elem of this.picaZaPrikaz) {
      if (elem.id == e.srcElement.name) {
        odabranoPice = elem;
        break;
      }
    }
    this.ruter.navigate(["/DrinkView"], {state: {data: {'pice': odabranoPice}}});
  }

  dodaj(event) {
    let odabranoPice;
    for (let elem of this.picaZaPrikaz) {
      if (elem.id == event.srcElement.name) {
        odabranoPice = elem;
        break;
      }
    }

    const dialogRef = this.dialog.open(DialogInputPriceComponent, {
      width: '500px',
      data: odabranoPice.name,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ispisPoruke("Uspesno ste dodali " + odabranoPice.name + " u kartu pica");
        //izbrisi iz liste za prikaz
        let izbrisiPice = 0;
        for (let elem of this.picaZaPrikaz) {
          if (elem.id == odabranoPice.id) break;
          izbrisiPice++;
        }
        this.picaZaPrikaz.splice(izbrisiPice, izbrisiPice);
        this.prikaziPica();
        //dodaj u drinkCard
        let novaCena = {
          drinkDTO: odabranoPice,
          price: result,
        }
        this.kartaPica.drinkPriceDTOs.push(novaCena);
      }
    });
  }

  redirektuj() {
    this.ruter.navigate(['/DrinkCard'], {state: {data: {'kartaPica': this.kartaPica}}});
  }
    
  ispisPoruke(poruka: string) {
    this.snackBar.open(poruka, "x", {
      duration: 2000,
      verticalPosition: "top",
      panelClass:"back-green"
    });
  }
}

