import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAjaxCallerComponent } from './test-ajax-caller.component';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TestAjaxCallerComponent', () => {
  let component: TestAjaxCallerComponent;
  let fixture: ComponentFixture<TestAjaxCallerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestAjaxCallerComponent],
      imports: [HttpClientTestingModule],
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
