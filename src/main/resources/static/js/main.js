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