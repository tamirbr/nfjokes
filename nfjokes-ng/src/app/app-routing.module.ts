import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JokesComponent } from './jokes/jokes.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { MyJokesComponent } from './my-jokes/my-jokes.component';
import { AddJokeComponent } from './add-joke/add-joke.component';
import { SettingsComponent } from './settings/settings.component';

const routes: Routes = [{
  path:'jokes',
  component:JokesComponent
  }, {
  path:'my-jokes',
  component:MyJokesComponent
  }, {
  path:'tell-a-joke',
  component:AddJokeComponent
  }, {
  path:'settings',
  component:SettingsComponent
  }, {
  path:'register',
  component:RegisterComponent
  },{
  path:'login',
  component:RegisterComponent
  }, {
  path:'',
  component:JokesComponent,
  pathMatch:'full'
  }, {
  path:'**',
  component:NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
