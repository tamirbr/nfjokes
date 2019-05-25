import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './Navigation/navigation.component';
import { JokesComponent } from './jokes/jokes.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SettingsComponent } from './settings/settings.component';
import { MyJokesComponent } from './my-jokes/my-jokes.component';
import { AddJokeComponent } from './add-joke/add-joke.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    MyJokesComponent,
    AddJokeComponent,
    JokesComponent,
    NotFoundComponent,
    RegisterComponent,
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
