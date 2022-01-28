import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkDTO } from 'src/app/models/drinkDTO';

@Component({
  selector: 'app-drink-view',
  templateUrl: './drink-view.component.html',
  styleUrls: ['./drink-view.component.css']
})
export class DrinkViewComponent implements OnInit {
  pice: DrinkDTO;
  kategorija: any;

  constructor(private ruter: Router, public dialog: MatDialog, private snackBar: MatSnackBar) {
    this.pice = history.state.data.pice;
    if (this.pice.type === "CARBONATED_DRINK") this.kategorija = "Gazirana pića";
    else if (this.pice.type === "NOCARBONATED_DRINK") this.kategorija = "Negazirana pića";
    else if (this.pice.type === "ALCOHOL") this.kategorija = "Alkoholna pića";
    else this.kategorija = "Topli napitci";
   }

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(["/ServerFirstPage"]);
  }
}
