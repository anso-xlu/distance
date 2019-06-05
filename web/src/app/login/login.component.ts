import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm = this.fb.group({
    email: [null, [Validators.required, Validators.pattern('[a-z0-9._%+_]+@[a-z0-9.-]+')]],
    password: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(18)]]
  });
  formErrorMsg = {
    email: {
      required: 'this is required',
      pattern: 'not email'
    },
    password: {
      required: 'this is required',
      minlength: 'length 8-18',
      maxlength: 'length 8-18',
    }
  };

  constructor(private fb: FormBuilder,
              private http: HttpClient,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.get();
  }

  onSubmit() {
    this.router.navigateByUrl('/home');
  }

  getErrorMsg(name) {
    const key = Object.keys(this.loginForm.get(name).errors)[0];
    return this.formErrorMsg[name][key];
  }
}
