import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Table } from '../tables/table.model';
import { Input } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Renderer2 } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent {
  @ViewChild("tableDiv") tableDiv: ElementRef;
  @Input() tableItem: Table;
  @Input() parentWidth: number;
  @Input() parentHeight: number;

  constructor() {
    
  }

  getStyle() {
    let x = this.tableItem.x * this.parentWidth;
    let y = this.tableItem.y * this.parentHeight;
    let width = this.tableItem.width * this.parentWidth;
    let height = this.tableItem.height * this.parentHeight;

    return `top: ${y}px; left: ${x}px; width: ${width}px; height: ${height}px;`
  }

  onTableClicked() {

  }
}
