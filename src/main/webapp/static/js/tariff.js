var myInputName = document.getElementById("tariff");
var description = document.getElementById("description");
var price = document.getElementById("price");
var buttonSubmit = document.getElementById("button-submit")


myInputName.oninput = function () {
    if (!((myInputName.value.length >= 0) && (myInputName.value.length < 20))  ) {
        buttonSubmit.disabled = true;
        myInputName.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        myInputName.style.borderColor = "#ccc";
    }
}

description.oninput = function () {
    if (!((description.value.length >= 0) && (description.value.length < 255 ))) {
        buttonSubmit.disabled = true;
        description.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        description.style.borderColor = "#ccc";
    }
}

price.oninput = function () {
    if (!((price.value >= 0) && (price.value < 51))) {
        buttonSubmit.disabled = true;
        price.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        price.style.borderColor = "#ccc";
    }
}