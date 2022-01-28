import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkDTO } from 'src/app/models/drinkDTO';
import { DrinkService } from '../../services/drink-service/drink.service';

@Component({
  selector: 'app-add-drink',
  templateUrl: './add-drink.component.html',
  styleUrls: ['./add-drink.component.css']
})
export class AddDrinkComponent implements OnInit {
  public piceForma: FormGroup;
  slika: string = 'pice.png';

  constructor(private ruter: Router, private formBuilder: FormBuilder, private drinkService: DrinkService, 
      private snackBar: MatSnackBar) {
    let pice: DrinkDTO = history.state.data?.pice;
    if (pice == undefined) {
      this.piceForma = this.formBuilder.group({
        id: [''],
        name:['', Validators.required],
        description:['', Validators.required],
        type:['', Validators.required],
        amountNumber: ['', [Validators.required, Validators.min(0.1)]],
        amountUnit: ['', Validators.required],
        image: ['pice.png'],
        available: true,
      });
    }
    else {
      this.slika = pice.image;
      this.piceForma = this.formBuilder.group({
        id: [pice.id],
        name:[pice.name, Validators.required],
        description:[pice.description, Validators.required],
        type:[pice.type, Validators.required],
        amountNumber: [pice.amountNumber, [Validators.required, Validators.min(0.1)]],
        amountUnit: [pice.amountUnit, Validators.required],
        image: [pice.image],
        available: pice.available,
      });
    }
   }

  ngOnInit(): void {
  }

  sacuvaj() {
    this.piceForma.value.image = this.slika;
    if (this.piceForma.value.id === '') this.drinkService.dodajPice(this.piceForma.value).subscribe(
      response => {
        this.ispisPoruke("Uspešno ste dodali " + this.piceForma.value.name);
        this.ruter.navigate(["/ServerFirstPage"]);
      },
      error => {
        this.ispisPoruke("Niste uspešno dodali novo piće - identicni naziv, količina i jedinica već postoje.");
      });
    else this.drinkService.izmeniPice(this.piceForma.value).subscribe(
      response => {
        this.ispisPoruke("Uspešno ste izmenili " + this.piceForma.value.name);
        this.ruter.navigate(["/Drink"], {state: {data: {'pice': response}}});
      },
      error => {
        this.ispisPoruke("Niste uspešno izmenili " + this.piceForma.value.name + "- identicni naziv, količina i jedinica već postoje.");
      });
  }
  
  nazadNaProfil() {
    this.ruter.navigate(["/ServerFirstPage"]);
  }
  
  public requiredError = (controlName: string, errorName: string) =>{
    return this.piceForma.controls[controlName].hasError(errorName);
  }
  
  public mindError = (controlName: string, errorName: string) =>{
    return this.piceForma.controls[controlName].hasError(errorName);
  }
  
  ispisPoruke(poruka: string) {
    this.snackBar.open(poruka, "x", {
      duration: 2000,
      verticalPosition: "top",
      panelClass:"back-green"
    });
  }
  
  promenaSlike(e) {
    let s = e.target.innerHTML;
    if (s === "Univerzalna")  this.slika = "pice.png";
    else if (s === "Kafa") this.slika = "kafa.jpg";
    else if (s === "Čaj") this.slika = "caj.png";
    else if (s === "Sok") this.slika = "sok.jpg";
    else if (s === "Voda") this.slika = "voda.jpg";
    else if (s === "Vino") this.slika = "vino.jpg";
    else if (s === "Pivo") this.slika = "pivo.jpg";
    else if (s === "Žestina") this.slika = "zestina.jpg";
    else this.slika = "koktel.jpg";
  }
}
