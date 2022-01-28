import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogInputPriceComponent } from 'src/app/drinks/components/dialog-input-price/dialog-input-price.component';

@Component({
  selector: 'app-price-dialog',
  templateUrl: './price-dialog.component.html',
  styleUrls: ['./price-dialog.component.css']
})
export class PriceDialogComponent implements OnInit {
  public cenaForma: FormGroup;

  constructor(public dialogRef: MatDialogRef<DialogInputPriceComponent>,@Inject(MAT_DIALOG_DATA) public data: string,
      private formBuilder: FormBuilder,) {
    let splitovano = data.split("||");
    this.data = splitovano[0];
    this.cenaForma = this.formBuilder.group({
      price: [splitovano[1], [Validators.required, Validators.min(1)]]
    });
  }

  public requiredError = (errorName: string) =>{
    return this.cenaForma.controls['price'].hasError(errorName);
  }
  
  public mindError = (errorName: string) =>{
    return this.cenaForma.controls['price'].hasError(errorName);
  }
  
  sacuvaj(): void {
    this.dialogRef.close(this.cenaForma.value.price);
  }

  ngOnInit(): void {
  }
}
