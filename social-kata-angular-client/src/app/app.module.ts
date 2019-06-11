import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestAjaxCallerComponent } from './test-ajax-caller/test-ajax-caller.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { SelectorComponent } from './selector/selector.component';
import { ReadComponent } from './read/read.component';
import { PostComponent } from './post/post.component';
import { WallComponent } from './wall/wall.component';
import { FollowComponent } from './follow/follow.component';
import { HeaderComponent } from './header/header.component';
import { ShowUsersComponent } from './show-users/show-users.component';


@NgModule({
  declarations: [
    AppComponent,
    TestAjaxCallerComponent,
    LoginComponent,
    SelectorComponent,
    ReadComponent,
    PostComponent,
    WallComponent,
    FollowComponent,
    HeaderComponent,
    ShowUsersComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
