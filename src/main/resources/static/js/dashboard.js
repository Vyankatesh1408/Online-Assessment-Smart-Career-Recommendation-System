const fullName = localStorage.getItem("fullName");

const welcomeMessage = document.getElementById("welcomeMessage");

welcomeMessage.textContent = "Welcome, " + fullName;


const assessmentBtn = document.getElementById("assessmentBtn");

assessmentBtn.addEventListener("click", function () {

    window.location.href = "assessment.html";

});

const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", function () {

    localStorage.removeItem("fullName");

    window.location.href = "login.html";

});