import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveMenuDialogComponent } from './save-menu-dialog.component';

describe('SaveMenuDialogComponent', () => {
  let component: SaveMenuDialogComponent;
  let fixture: ComponentFixture<SaveMenuDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveMenuDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveMenuDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
