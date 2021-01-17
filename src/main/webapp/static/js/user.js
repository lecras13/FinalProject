var login = document.getElementById("login");
var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");
var discount = document.getElementById("discount");
var buttonSubmit = document.getElementById("button-submit")

login.oninput = function () {
    if ((login.value.length > 0) && (login.value.length < 25)) {
        buttonSubmit.disabled = false;
        login.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        login.style.borderColor = "red";
    }
}

firstName.oninput = function () {
    if ((firstName.value.length > 0) && (login.value.length < 25)) {
        buttonSubmit.disabled = false;
        firstName.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        firstName.style.borderColor = "red";
    }
}

lastName.oninput= function () {
    if ((lastName.value.length > 0) && (lastName.value.length < 25)) {
        buttonSubmit.disabled = false;
        lastName.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        lastName.style.borderColor = "red";
    }
}

discount.oninput = function () {
    if ((discount.value > 0) && (discount.value < 25)) {
        buttonSubmit.disabled = false;
        discount.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        discount.style.borderColor = "red";
    }
}
