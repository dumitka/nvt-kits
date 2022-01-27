import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DeleteDialogComponent } from '../../components/delete-dialog/delete-dialog.component';
import { PriceDialogComponent } from '../../components/price-dialog/price-dialog.component';
import { DrinkCardService } from '../../services/drink-card-service/drink-card.service';

@Component({
  selector: 'app-drink-card-page',
  templateUrl: './drink-card-page.component.html',
  styleUrls: ['./drink-card-page.component.css']
})
export class DrinkCardPageComponent implements OnInit {
  kartaPica: any;
  kolone = ['naziv', 'opis', 'cena', 'dugmiciIzmena', 'dugmiciIzbrisi'];
  izmenjeno: boolean = false;

  constructor(private servisKP: DrinkCardService, private ruter: Router,private snackBar: MatSnackBar, public dialog: MatDialog,) {
    this.kartaPica = history.state.data?.kartaPica;
    if (this.kartaPica == null) {
      this.kartaPica = this.servisKP.trenutnaKartaPica().subscribe(
        response => {
          this.izmenjeno = false;
          this.kartaPica = response;
        }
      );
    }
    else { if (this.kartaPica.drinkPriceDTOs.length != 0) this.izmenjeno = true; }
  }

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(['/ServerFirstPage']);
  }

  pronadjiPice(id: string) {
    for (let cenaPica of this.kartaPica.drinkPriceDTOs) {
      if (cenaPica.drinkDTO.id == id) return cenaPica;
    }
  }

  izbrisi(e) {
    let izabranoPice = this.pronadjiPice(e.srcElement.id);
    
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '500px',
      data: izabranoPice.drinkDTO.name,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ispisPoruke("Uspesno ste izbacili " + izabranoPice.drinkDTO.name);
        if (this.kartaPica.drinkPriceDTOs.length != 0) this.izmenjeno = true;
        else this.izmenjeno = false;
        //izbrisi iz liste za liste karte pica
        let lista = [];
        for (let elem of this.kartaPica.drinkPriceDTOs) {
          if (elem.drinkDTO.id != izabranoPice.drinkDTO.id) lista.push(elem);
        }
        this.kartaPica.drinkPriceDTOs = lista;
      }
    });
  }

  ispisPoruke(poruka: string) {
    this.snackBar.open(poruka, "x", {
      duration: 2000,
      verticalPosition: "top",
      panelClass:"back-green"
    });
  }

  izmeni(e) {
    let izabranoPice = this.pronadjiPice(e.srcElement.id);
    
    const dialogRef = this.dialog.open(PriceDialogComponent, {
      width: '500px',
      data: izabranoPice.drinkDTO.name + "||" + izabranoPice.price,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ispisPoruke("Uspesno ste uneli izmenu cene " + izabranoPice.drinkDTO.name);
        if (this.kartaPica.drinkPriceDTOs.length != 0) this.izmenjeno = true;
        else this.izmenjeno = false;
        //izmeni u listi za prikaz
        for (let elem of this.kartaPica.drinkPriceDTOs) {
          if (elem.drinkDTO.id == izabranoPice.drinkDTO.id) {
            elem.price = result;
          }
        }
      }
    });
  }

  sacuvaj() {
    this.servisKP.dodajKartuPica(this.kartaPica).subscribe(
      response => {
        this.ispisPoruke("Uspesno ste sačuvali kartu pića.");
        this.ruter.navigate(["/ServerFirstPage"]);
    },
      error => {
        this.ispisPoruke("Neuspešno ste sačuvali kartu pića.");
    });
  }

  dodaj() {
    this.ruter.navigate(["/ChooseDrinks"], {state: {data: {kartaPica: this.kartaPica}}});
  }
}
