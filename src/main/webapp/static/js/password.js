var myInput = document.getElementById("psw");
var myInputR = document.getElementById("pswr");
var validness = document.getElementById("validness");
var buttonPassSubmit = document.getElementById("button-submit")

myInputR.onfocus = function () {
    document.getElementById("message").style.display = "block";
}

myInputR.onblur = function () {
    document.getElementById("message").style.display = "none";
}

myInputR.oninput = function () {
    if (myInput.value == myInputR.value) {
        validness.classList.remove("invalid");
        validness.classList.add("valid");
        buttonPassSubmit.disabled = false;
    } else {
        validness.classList.remove("valid");
        validness.classList.add("invalid");
        buttonPassSubmit.disabled = true;
    }
}