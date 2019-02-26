import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent }      from './login/login.component';
import {SelectorComponent} from './selector/selector.component';
import {TestAjaxCallerComponent} from './test-ajax-caller/test-ajax-caller.component';
import { ReadComponent } from './read/read.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'caller', component: TestAjaxCallerComponent },
  { path: 'selector', component: SelectorComponent },
  { path: 'read', component: ReadComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
