'use strict'

let form,name,username,password,confirmPassword;

function registerUser() {

    form = document.getElementById("signup_form");
    name = form.elements["name"].value.trim();
    username = form.elements["username"].value.trim();
    password = form.elements["signup_password"].value.trim();
    confirmPassword = form.elements["confirm_password"].value.trim();

    if (name ==="")
        alert("Name can not be empty");
    else if(username ==="")
        alert("Username can not be empty");
    else if(password ==="")
        alert("Password can not be empty");
    else if(confirmPassword !== password)
        alert("Confirm password did not match")
    else
        form.submit();

}