function togglePassword(){
    var passwordField = document.getElementById("password");
    if (passwordField.type === "password"){
    passwordField.type = "text";
    }else{
        passwordField.type = "password";
    }
}

function validateSearch() {
    var price = document.getElementById("maxPrice");
    if (price && price.value < 0) {
        alert("Price cannot be negative!");
        return false; // Stops the form from submitting
    }
    return true;
}

function toggleDarkMode(){
    var body = document.body;
    body.classList.toggle("dark-mode");

    if(body.classList.contains("dark-mode")) {
        localStorage.setItem("theme" , "dark");
    } else {
        localStorage.setItem("theme" , "light");
    }
}


document.addEventListener("DOMContentLoaded", function (){
    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark-mode");
    }
});