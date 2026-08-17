const timerElement = document.getElementById("timer");

let timeRemaining = 0;
let timerInterval;
let isAutoSubmit = false;
let isSubmitting = false;

const userId = localStorage.getItem("userId");
const fullName = localStorage.getItem("fullName");

const assessmentId = 1;

if (!userId || !fullName) {
    window.location.href = "login.html";
}

const questionContainer = document.getElementById("questionContainer");


/* =========================
   LOAD QUESTIONS
========================= */

async function loadQuestions() {

    try {

        const questionResponse = await fetch(
            "http://localhost:8080/api/questions"
        );

        if (!questionResponse.ok) {
            throw new Error("Failed to load questions.");
        }

        const questions = await questionResponse.json();

        questionContainer.innerHTML = "";

        for (const question of questions) {

            const optionResponse = await fetch(
                `http://localhost:8080/api/options/question/${question.id}`
            );

            if (!optionResponse.ok) {
                throw new Error("Failed to load options.");
            }

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

    } catch (error) {

        console.error(error);

        questionContainer.innerHTML =
            "<p class='text-danger'>Unable to load questions.</p>";
    }
}


/* =========================
   START / GET ATTEMPT
========================= */

async function startAssessment() {

    try {

        const response = await fetch(
            `http://localhost:8080/api/attempts/start/${userId}/${assessmentId}`,
            {
                method: "POST"
            }
        );

        if (!response.ok) {
            throw new Error("Failed to start assessment.");
        }

        const attempt = await response.json();

        console.log("Assessment Attempt:", attempt);

        startTimer(attempt);

    } catch (error) {

        console.error(error);

        alert("Unable to start assessment.");

    }
}


/* =========================
   START TIMER FROM SERVER TIME
========================= */

function startTimer(attempt) {

    const startTime = new Date(attempt.startTime);

    const durationInSeconds =
        attempt.assessment.duration * 60;

    const currentTime = new Date();

    const elapsedSeconds =
        Math.floor(
            (currentTime.getTime() - startTime.getTime()) / 1000
        );

    timeRemaining =
        durationInSeconds - elapsedSeconds;

    // Assessment already expired
    if (timeRemaining <= 0) {

        timeRemaining = 0;

        updateTimerDisplay();

        isAutoSubmit = true;

        alert(
            "Your assessment time has expired. Your assessment will be submitted."
        );

        submitAssessment();

        return;
    }

    updateTimerDisplay();

    timerInterval = setInterval(() => {

        timeRemaining--;

        updateTimerDisplay();

        if (timeRemaining <= 0) {

            clearInterval(timerInterval);

            timeRemaining = 0;

            updateTimerDisplay();

            isAutoSubmit = true;

            alert(
                "Time is over. Your assessment will be submitted automatically."
            );

            submitAssessment();
        }

    }, 1000);
}


/* =========================
   UPDATE TIMER DISPLAY
========================= */

function updateTimerDisplay() {

    const minutes = Math.floor(timeRemaining / 60);

    const seconds = timeRemaining % 60;

    timerElement.textContent =
        `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(2, "0")}`;
}


/* =========================
   SUBMIT ASSESSMENT
========================= */

async function submitAssessment() {

    // Prevent duplicate submission
    if (isSubmitting) {
        return;
    }

    isSubmitting = true;

    if (timerInterval) {
        clearInterval(timerInterval);
    }

    const answers = [];

    try {

        const questions = await fetch(
            "http://localhost:8080/api/questions"
        ).then(response => {

            if (!response.ok) {
                throw new Error("Failed to load questions.");
            }

            return response.json();
        });


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


        /* =========================
           MANUAL SUBMISSION
        ========================= */

        if (answers.length === 0 && !isAutoSubmit) {

            alert("Please select at least one answer.");

            isSubmitting = false;

            return;
        }


        /* =========================
           CONFIRM MANUAL SUBMISSION
        ========================= */

        if (!isAutoSubmit) {

            const confirmSubmit = confirm(
                "Are you sure you want to submit the assessment?\n\nAfter submission, you cannot change your answers."
            );

            if (!confirmSubmit) {

                isSubmitting = false;

                return;
            }
        }


        /* =========================
           SAVE ANSWERS
        ========================= */

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


        /* =========================
           CALCULATE RESULT
        ========================= */

        const resultResponse = await fetch(
            `http://localhost:8080/api/results/calculate/${userId}/${assessmentId}`,
            {

                method: "POST"

            }
        );


        if (!resultResponse.ok) {

            throw new Error("Failed to calculate result.");

        }


        /* =========================
           REDIRECT TO RESULT
        ========================= */

        window.location.href = "result.html";


    } catch (error) {

        console.error(error);

        alert("Something went wrong. Please try again.");

        isSubmitting = false;
    }
}


/* =========================
   SUBMIT BUTTON
========================= */

const submitBtn = document.getElementById("submitBtn");

submitBtn.addEventListener("click", function () {

    submitAssessment();

});


/* =========================
   START APPLICATION
========================= */

loadQuestions();

startAssessment();