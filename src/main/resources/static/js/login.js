const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    const email = document.getElementById("email").value;

    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/api/auth/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            email: email,
            password: password
        })

    });

    const data = await response.json();
  
	alert(JSON.stringify(data));

    localStorage.setItem("fullName", data.fullName);
    
  
	localStorage.setItem("userId", data.userId);

	window.location.href = "dashboard.html";
});