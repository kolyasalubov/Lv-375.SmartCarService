import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicesFeedbackPageComponent } from './services-feedback-page.component';

describe('ServicesFeedbackPageComponent', () => {
  let component: ServicesFeedbackPageComponent;
  let fixture: ComponentFixture<ServicesFeedbackPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServicesFeedbackPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicesFeedbackPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
