import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent }      from './login/login.component';
import {SelectorComponent} from './selector/selector.component';
import {TestAjaxCallerComponent} from './test-ajax-caller/test-ajax-caller.component';
import { ReadComponent } from './read/read.component';
import { PostComponent } from './post/post.component';
import { WallComponent } from './wall/wall.component';
import { FollowComponent } from './follow/follow.component';
import { AuthGuardService } from './auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'caller', component: TestAjaxCallerComponent , canActivate: [AuthGuardService]},
  { path: 'selector', component: SelectorComponent , canActivate: [AuthGuardService]},
  { path: 'read', component: ReadComponent , canActivate: [AuthGuardService]},
  { path: 'post', component: PostComponent , canActivate: [AuthGuardService]},
  { path: 'follow', component: FollowComponent , canActivate: [AuthGuardService]},
  { path: 'wall', component: WallComponent , canActivate: [AuthGuardService]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
