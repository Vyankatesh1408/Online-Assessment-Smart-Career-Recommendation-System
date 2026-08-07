const userId = localStorage.getItem("userId");
const fullName = localStorage.getItem("fullName");

// Redirect to login if user is not logged in
if (!userId || !fullName) {
    window.location.href = "login.html";
}

const welcomeMessage = document.getElementById("welcomeMessage");
welcomeMessage.textContent = "Welcome, " + fullName;

const assessmentBtn = document.getElementById("assessmentBtn");

const resultBtn = document.getElementById("resultBtn");
const recommendationBtn = document.getElementById("recommendationBtn");
const profileBtn = document.getElementById("profileBtn");


assessmentBtn.addEventListener("click", async function () {

    try {

        const response = await fetch(
            `http://localhost:8080/api/results/user/${userId}/assessment/1`
        );

        if (response.ok) {

 			   window.location.href = "assessment-completed.html";

		} else if (response.status === 404) {

            window.location.href = "assessment.html";

        } else {

            alert("Something went wrong. Please try again.");

        }

    } catch (error) {

        alert("Unable to connect to the server.");

    }

});

resultBtn.addEventListener("click", async function () {

    try {

        const response = await fetch(
            `http://localhost:8080/api/results/user/${userId}/assessment/1`
        );

        if (response.ok) {

            window.location.href = "result.html";

        } else {

            alert("You have not completed the assessment yet.");

        }

    } catch (error) {

        alert("Unable to connect to the server.");

    }

});

const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", function () {

    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");

    window.location.href = "login.html";

});