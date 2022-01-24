import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerFirstPageComponent } from './server-first-page.component';

describe('ServerFirstPageComponent', () => {
  let component: ServerFirstPageComponent;
  let fixture: ComponentFixture<ServerFirstPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServerFirstPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerFirstPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
