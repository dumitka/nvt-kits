import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-input-price',
  templateUrl: './dialog-input-price.component.html',
  styleUrls: ['./dialog-input-price.component.css']
})
export class DialogInputPriceComponent implements OnInit {
  public cenaForma: FormGroup;

  constructor(public dialogRef: MatDialogRef<DialogInputPriceComponent>,@Inject(MAT_DIALOG_DATA) public data: string,
      private formBuilder: FormBuilder,) {
    this.cenaForma = this.formBuilder.group({
      price: ['', [Validators.required, Validators.min(1)]]
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
