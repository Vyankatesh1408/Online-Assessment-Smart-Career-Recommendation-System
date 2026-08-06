const registerForm = document.getElementById("registerForm");

registerForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    const fullName = document.getElementById("fullName").value;

    const email = document.getElementById("email").value;

    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/api/users", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            fullName: fullName,
            email: email,
            password: password
        })

    });

    const data = await response.json();

    alert("Registration Successful");

    window.location.href = "login.html";

});