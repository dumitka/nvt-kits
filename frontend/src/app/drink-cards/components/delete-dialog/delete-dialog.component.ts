import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogDeleteComponent } from 'src/app/drinks/components/dialog-delete/dialog-delete.component';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogDeleteComponent>,@Inject(MAT_DIALOG_DATA) public data: string,) {}
    
  ngOnInit(): void {}

  zatvori(): void {
    this.dialogRef.close(false);
  }
  
  izbrisi(): void {
    this.dialogRef.close(true);
  }
}
