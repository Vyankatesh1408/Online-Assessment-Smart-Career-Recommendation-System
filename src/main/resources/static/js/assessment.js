const userId = localStorage.getItem("userId");
const fullName = localStorage.getItem("fullName");

if (!userId || !fullName) {
    window.location.href = "login.html";
}

const questionContainer = document.getElementById("questionContainer");

async function loadQuestions() {

    const questionResponse = await fetch("http://localhost:8080/api/questions");

    const questions = await questionResponse.json();

    questionContainer.innerHTML = "";

    for (const question of questions) {

        const optionResponse = await fetch(
            `http://localhost:8080/api/options/question/${question.id}`
        );

        const options = await optionResponse.json();

        let optionHtml = "";

        options.forEach(option => {

            optionHtml += `
                <div class="form-check">

                    <input
                        class="form-check-input"
                        type="radio"
                        name="question${question.id}"
                        value="${option.id}">

                    <label class="form-check-label">

                        ${option.optionText}

                    </label>

                </div>
            `;

        });

        questionContainer.innerHTML += `

            <div class="card mb-4">

                <div class="card-body">

                    <h5>${question.questionText}</h5>

                    ${optionHtml}

                </div>

            </div>

        `;

    }

}

loadQuestions();

const submitBtn = document.getElementById("submitBtn");

submitBtn.addEventListener("click", async function () {

    const userId = localStorage.getItem("userId");

    const answers = [];

    const questions = await fetch("http://localhost:8080/api/questions")
        .then(response => response.json());

    questions.forEach(question => {

        const selectedOption = document.querySelector(
            `input[name="question${question.id}"]:checked`
        );

        if (selectedOption) {

            answers.push({

                userId: Number(userId),
                questionId: question.id,
                optionId: Number(selectedOption.value)

            });

        }

    });

    if (answers.length === 0) {

        alert("Please select at least one answer.");

        return;

    }

    const confirmSubmit = confirm(
        "Are you sure you want to submit the assessment?\n\nAfter submission, you cannot change your answers."
    );

    if (!confirmSubmit) {
        return;
    }

    try {

        const answerResponse = await fetch(
            "http://localhost:8080/api/student-answers/submit",
            {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(answers)

            }
        );

        if (!answerResponse.ok) {

            throw new Error("Failed to save answers.");

        }

        const resultResponse = await fetch(
            `http://localhost:8080/api/results/calculate/${userId}/1`,
            {

                method: "POST"

            }
        );

        if (!resultResponse.ok) {

            throw new Error("Failed to calculate result.");

        }

        window.location.href = "result.html";

    } catch (error) {

        console.error(error);

        alert("Something went wrong. Please try again.");

    }

});