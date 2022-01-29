import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablesLayoutComponent } from './tables-layout.component';

describe('TablesLayoutComponent', () => {
  let component: TablesLayoutComponent;
  let fixture: ComponentFixture<TablesLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablesLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablesLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
