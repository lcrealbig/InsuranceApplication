import { NgModule } from "@angular/core";
import {Routes, RouterModule} from '@angular/router';
import { UserLoginComponent } from "./components/user-login/user-login.component";
import { UserPanelComponent } from "./components/user-panel/user-panel.component";

const routes: Routes = [
    { path: 'userlogin', component: UserLoginComponent},
    { path: 'userpanel', component: UserPanelComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class appRoutingModule {}
export const routingComponents = [UserPanelComponent]