const userId = localStorage.getItem("userId");
const fullName = localStorage.getItem("fullName");

if (!userId || !fullName) {
    window.location.href = "login.html";
}

const resultContainer = document.getElementById("resultContainer");

async function loadResult() {

    const userId = localStorage.getItem("userId");

    const response = await fetch(
        `http://localhost:8080/api/results/user/${userId}/assessment/1`
    );

    const result = await response.json();

    resultContainer.innerHTML = `

<div class="text-center">

    <h2 class="text-success">
        🎉 Congratulations, ${result.fullName}!
    </h2>

    <hr>

    <h4 class="mt-3">
        📊 Score
    </h4>

    <h3 class="text-primary">
        ${result.score}
    </h3>

    <h4 class="mt-3">
        📈 Percentage
    </h4>

    <h3 class="text-info">
        ${result.percentage.toFixed(2)}%
    </h3>

    <h4 class="mt-3">
        🎓 Recommended Stream
    </h4>

    <h3 class="text-success">
        ${result.streamName}
    </h3>

</div>

`;

}

loadResult();

const homeBtn = document.getElementById("homeBtn");

homeBtn.addEventListener("click", function () {

    window.location.href = "dashboard.html";

});

const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", function () {

    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");

    window.location.href = "login.html";

});