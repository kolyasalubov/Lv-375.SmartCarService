import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FakeDataBtnComponent } from './fake-data-btn.component';

describe('FakeDataBtnComponent', () => {
  let component: FakeDataBtnComponent;
  let fixture: ComponentFixture<FakeDataBtnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FakeDataBtnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FakeDataBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
