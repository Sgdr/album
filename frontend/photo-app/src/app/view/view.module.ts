import {NgModule} from "@angular/core";
import {ViewPageComponent} from "./view-page.component";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";

const route: Routes = [
    {
        path: '',
        component: ViewPageComponent
    }
];

@NgModule({
    declarations: [
        ViewPageComponent
    ],
    imports: [
        FormsModule,
        CommonModule,
        RouterModule.forChild(route)
    ]
})
export class ViewModule {
}
