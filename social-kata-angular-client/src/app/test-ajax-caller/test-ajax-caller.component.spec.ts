import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAjaxCallerComponent } from './test-ajax-caller.component';

describe('TestAjaxCallerComponent', () => {
  let component: TestAjaxCallerComponent;
  let fixture: ComponentFixture<TestAjaxCallerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestAjaxCallerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestAjaxCallerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
