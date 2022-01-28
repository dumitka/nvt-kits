import { Component, OnInit } from '@angular/core';
import {DrinkService} from '../../services/drink-service/drink.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/first-pages/services/user-service/user.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogDeleteComponent } from '../../components/dialog-delete/dialog-delete.component';
import { DrinkDTO } from 'src/app/models/drinkDTO';

@Component({
  selector: 'app-all-drinks',
  templateUrl: './all-drinks.component.html',
  styleUrls: ['./all-drinks.component.css']
})
export class AllDrinksComponent implements OnInit {
  picaZaPrikaz: DrinkDTO[];
  pretraga: string = '';
  kategorija: string = '';

  constructor(private drinkService: DrinkService, private ruter: Router, private service:UserService,
      private snackBar: MatSnackBar, public dialog: MatDialog,) {
    this.drinkService.svaPica().subscribe(response => {
      this.picaZaPrikaz = response as DrinkDTO[];
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
    else if (k === "Gazirana pića") this.kategorija = "CARBONATED_DRINK";
    else if (k === "Negazirana pića") this.kategorija = "NOCARBONATED_DRINK";
    else if (k === "Alkoholna pića") this.kategorija = "ALCOHOL";
    else this.kategorija = "HOT_DRINK";
    this.pretrazi();
  }

  filterPoKategoriji() {
    if (this.kategorija === "SVE") return;
    let novaLista = new Array<DrinkDTO>();
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
        this.picaZaPrikaz = response as DrinkDTO[];
        if (this.kategorija != '') this.filterPoKategoriji();
        this.prikaziPica();
      });
    }
    else {
      this.drinkService.pretraziPica(this.pretraga).subscribe(response => {
        this.picaZaPrikaz = response as DrinkDTO[];
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
      div.setAttribute("name", pice.id.toString());
      div.setAttribute("id", "divPica" + pice.id);

      let div2 = document.createElement("div");
      div2.className = "mat-card-title-group";
      let ime = document.createElement("div");
      ime.className = "mat-card-title";
      ime.appendChild(document.createTextNode(pice.name));
      div2.appendChild(ime);
      let slika = document.createElement("img");
      slika.setAttribute("class", "mat-card-lg-image");
      slika.setAttribute("src", "assets\\" + pice.image);
      slika.setAttribute("name", pice.id.toString());
      slika.setAttribute("id", "slikaPica" + pice.id);
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
      dugme.setAttribute("name", pice.id.toString());
      dugme.setAttribute("id", "digmePica" + pice.id);
      dugme.setAttribute("style", "background-color: #5d7c77;width: 100px;text-align: center;color: whitesmoke; " 
        + "font-family: 'Trocchi', serif;font-size: 20px; margin-left: 100px;");
      dugme.appendChild(document.createTextNode("IZBRIŠI"));
      dugme.onclick = (e:any) => { this.izbrisi(e); };
      div4.appendChild(dugme);
      div.appendChild(div4);

      divZaPica.appendChild(div);
      redniBr++;
    });
  }

  izbrisi(e) {
    let odabranoPice;
    for (let elem of this.picaZaPrikaz) {
      if (elem.id == e.srcElement.name) {
        odabranoPice = elem;
        break;
      }
    }

    const dialogRef = this.dialog.open(DialogDeleteComponent, {
      width: '500px',
      data: odabranoPice.name,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.drinkService.izbrisiPice(odabranoPice).subscribe(
          response => {
            this.ispisPoruke("Uspešno ste izbrisali " + odabranoPice.name);
            let lista: DrinkDTO[] = [];
            for (let elem of this.picaZaPrikaz) {
              if (elem.id != e.srcElement.name) lista.push(elem);
            }
            this.picaZaPrikaz = lista;
            this.prikaziPica();
          },
          error => {
            this.ispisPoruke("Niste uspešno izbrisali " + odabranoPice.name);
          });
      }
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
    this.ruter.navigate(["/Drink"], {state: {data: {'pice': odabranoPice}}});
  }

  ispisPoruke(poruka: string) {
    this.snackBar.open(poruka, "x", {
      duration: 2000,
      verticalPosition: "top",
      panelClass:"back-green"
    });
  }
}
