'use strict'

let username;
let password;
let form;

function loginBtnClicked() {

    console.log("loginBtn clicked");

    form = document.getElementById("login_form");
    username = form.elements["username"].value.trim();
    password = form.elements["login_password"].value.trim();

    if (username === "")
        alert("Username can't be empty");

   else if (password === "")
       alert("Password can't be empty");

   else
       form.submit();
}

function signupBtnClicked() {

   console.log("signup clicked");
   document.location.href = 'signup/signup.html'

}