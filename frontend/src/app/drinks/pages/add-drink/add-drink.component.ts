import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkService } from '../../services/drink-service/drink.service';

@Component({
  selector: 'app-add-drink',
  templateUrl: './add-drink.component.html',
  styleUrls: ['./add-drink.component.css']
})
export class AddDrinkComponent implements OnInit {
  public piceForma: FormGroup;

  constructor(private ruter: Router, private formBuilder: FormBuilder, private drinkService: DrinkService, 
      private snackBar: MatSnackBar) {
    let pice = history.state.data?.pice;
    if (pice == undefined) {
      this.piceForma = this.formBuilder.group({
        id: [''],
        name:['', Validators.required],
        description:['', Validators.required],
        type:['', Validators.required],
        amountNumber: ['', [Validators.required, Validators.min(0.1)]],
        amountUnit: ['', Validators.required],
        image: ['pice.png']
      });
    }
    else {
      this.piceForma = this.formBuilder.group({
        id: [pice.id],
        name:[pice.name, Validators.required],
        description:[pice.description, Validators.required],
        type:[pice.type, Validators.required],
        amountNumber: [pice.amountNumber, [Validators.required, Validators.min(0.1)]],
        amountUnit: [pice.amountUnit, Validators.required],
        image: [pice.image]
      });
    }
   }

  ngOnInit(): void {
  }

  sacuvaj() {
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
        this.ruter.navigate(["/Drink"], {state: {data: this.piceForma.value}});
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
}
