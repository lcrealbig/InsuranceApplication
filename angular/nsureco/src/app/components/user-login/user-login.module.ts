import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [UserLoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
  ]
})
export class UserLoginModule { }
