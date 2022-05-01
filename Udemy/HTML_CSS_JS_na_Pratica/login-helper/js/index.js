function showPassword() {
    const eye = document.getElementById("eye");
    const eyeSlash = document.getElementById("eye-slash")
    const passwordField = document.getElementById("password");

    if(eye.style.display === "none") {
        eye.style.display = "block";
        eyeSlash.style.display = "none";
        passwordField.type = "text";
    }
    else {
        eye.style.display = "none";
        eyeSlash.style.display = "block";
        passwordField.type = "password"; 
    }
}