import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DrinkCardService } from '../../services/drink-card-service/drink-card.service';

@Component({
  selector: 'app-drink-card-page',
  templateUrl: './drink-card-page.component.html',
  styleUrls: ['./drink-card-page.component.css']
})
export class DrinkCardPageComponent implements OnInit {
  kartaPica: any;

  constructor(private servisKP: DrinkCardService, private ruter: Router) {
    this.kartaPica = history.state.data?.kartaPica;
    if (this.kartaPica == null) {
      this.kartaPica = this.servisKP.trenutnaKartaPica().subscribe(
        response => {
          this.kartaPica = response;
        }
      );
    }
  }

  ngOnInit(): void {
  }

  nazadNaProfil() {
    this.ruter.navigate(['/ServerFirstPage']);
  }
}
