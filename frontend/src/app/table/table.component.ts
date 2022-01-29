import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Table } from '../tables/table.model';
import { Input } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Renderer2 } from '@angular/core';
import { TableWrapper } from '../tables/table-wrapper.model';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent {
  @Input() tableWrapper: TableWrapper;

  divElement: any;

  constructor(public element: ElementRef) {
    this.divElement = this.element.nativeElement;
  }

  getStyle() {
    let parentWidth = this.divElement.parentNode.offsetWidth;
    let parentHeight = this.divElement.parentNode.offsetHeight;

    let x = this.tableWrapper.table.x * parentWidth;
    let y = this.tableWrapper.table.y * parentHeight;
    let width = this.tableWrapper.table.width * parentWidth;
    let height = this.tableWrapper.table.height * parentHeight;

    return `top: ${y}px; left: ${x}px; width: ${width}px; height: ${height}px;`
  }

  onTableClicked() {

  }
}
