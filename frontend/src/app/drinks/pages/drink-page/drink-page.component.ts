import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkService } from '../../services/drink-service/drink.service';

@Component({
  selector: 'app-drink-page',
  templateUrl: './drink-page.component.html',
  styleUrls: ['./drink-page.component.css']
})
export class DrinkPageComponent implements OnInit {
  pice: any;
  kategorija: any;

  constructor(private ruter: Router, public dialog: MatDialog, private drinkService: DrinkService, private snackBar: MatSnackBar) {
    this.pice = history.state.data.pice;
    if (this.pice.type === "CARBONATED_DRINK") this.kategorija = "Gazirana pica";
    else if (this.pice.type === "NOCARBONATED_DRINK") this.kategorija = "Negazirana pica";
    else if (this.pice.type === "ALCOHOL") this.kategorija = "Alkoholna pica";
    else this.kategorija = "Topli napitci";
   }

  ngOnInit(): void {
  }

  izbrisi() {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '500px',
      data: this.pice.name,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.drinkService.izbrisiPice(this.pice).subscribe(
          response => {
            this.ispisPoruke("Uspesno ste izbrisali " + this.pice.name);
            this.ruter.navigate(["/AllDrinks"]);
          },
          error => {
            this.ispisPoruke("Niste uspesno izbrisali " + this.pice.name);
          });
      }
    });
  }
  
  izmeni() {
    this.ruter.navigate(["/AddDrink"], {state: {data: {'pice': this.pice}}});
  }
  
  nazadNaProfil() {
    this.ruter.navigate(["/ServerFirstPage"]);
  }
  
  ispisPoruke(poruka: string) {
    this.snackBar.open(poruka, "x", {
      duration: 2000,
      verticalPosition: "top",
      panelClass:"back-green"
    });
  }
}

@Component({
  selector: 'dialog-delete',
  templateUrl: './dialog-delete.html',
  styleUrls: ['./dialog-delete.css']
})
export class DialogOverviewExampleDialog {
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string,
  ) {}

  zatvori(): void {
    this.dialogRef.close(false);
  }
  
  izbrisi(): void {
    this.dialogRef.close(true);
  }
}
