import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-menu-category',
  templateUrl: './menu-category.component.html',
  styleUrls: ['./menu-category.component.css']
})
export class MenuCategoryComponent implements OnInit {

  showMoney:boolean
  
  constructor(private router:Router) { 
    this.showMoney = history.state.data.showMoney;
    console.log(this.showMoney)
  }

  ngOnInit(): void {
  }

}





