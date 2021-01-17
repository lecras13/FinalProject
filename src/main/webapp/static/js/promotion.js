var promotionName = document.getElementById("promotionName");
var startDate = document.getElementById("startDate");
var endDate = document.getElementById("endDate");
var description = document.getElementById("description");
var newPrice = document.getElementById("newPrice");
var buttonSubmit = document.getElementById("button-submit")
var today = Date.now();
var yesterday = Date.now() - 86400000;

promotionName.oninput = function () {
    if (!((promotionName.value.length > 0) && (promotionName.value.length < 50))) {
        buttonSubmit.disabled = true;
        promotionName.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        promotionName.style.borderColor = "#ccc";
    }
}

startDate.oninput = function () {
    if ((Date.parse(startDate.value) > yesterday)) {
        buttonSubmit.disabled = false;
        startDate.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        startDate.style.borderColor = "red";
    }
}

endDate.oninput = function () {
    if ((Date.parse(endDate.value) > today)) {
        buttonSubmit.disabled = false;
        endDate.style.borderColor = "#ccc";
    } else {
        buttonSubmit.disabled = true;
        endDate.style.borderColor = "red";
    }
}

description.oninput = function () {
    if (!((description.value.length > 0) && (description.value.length < 255))) {
        buttonSubmit.disabled = true;
        description.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        description.style.borderColor = "#ccc";
    }
}

newPrice.oninput = function () {
    if (!((newPrice.value > 0) && (newPrice.value < 51))) {
        buttonSubmit.disabled = true;
        newPrice.style.borderColor = "red";
    } else {
        buttonSubmit.disabled = false;
        newPrice.style.borderColor = "#ccc";
    }
}