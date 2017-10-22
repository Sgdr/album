import {NgModule} from "@angular/core";
import {EditPageComponent} from "./edit-page.component";
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";

const route: Routes = [
  {
    path: '',
    component: EditPageComponent
  }
];

@NgModule({
  declarations: [
    EditPageComponent
  ],
  imports: [
    FormsModule,
    RouterModule.forChild(route)
  ]
})
export class EditModule {
}
