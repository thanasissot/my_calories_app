import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MydiaryComponent } from './mydiary.component';

describe('MydiaryComponent', () => {
  let component: MydiaryComponent;
  let fixture: ComponentFixture<MydiaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MydiaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MydiaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
