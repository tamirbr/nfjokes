import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JokesComponent } from './jokes/jokes.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from "@angular/forms";

const routes: Routes = [{
  path:'jokes',
  component:JokesComponent
  }, {
  path:'register',
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
