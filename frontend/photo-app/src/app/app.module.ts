import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {ViewModule} from "./view/view.module";
import {EditModule} from "./edit/edit.module";

const route: Routes = [
    {
        path: 'photos',
        children:
            [
                {
                    path: 'view',
                    loadChildren: 'app/view/view.module#ViewModule'
                },
                {
                    path: 'edit',
                    loadChildren: 'app/edit/edit.module#EditModule'
                }
            ]
    },
    {
        path: '', redirectTo: '/photos/view', pathMatch: 'full'
    }
];

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        ViewModule,
        EditModule,
        RouterModule.forRoot(route)
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
