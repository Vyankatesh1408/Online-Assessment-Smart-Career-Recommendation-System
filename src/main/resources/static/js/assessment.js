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

        options.forEach(function (option) {

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

                    <h5>

                        ${question.questionText}

                    </h5>

                    ${optionHtml}

                </div>

            </div>

        `;
    }

}

loadQuestions();
const submitBtn = document.getElementById("submitBtn");

submitBtn.addEventListener("click", async function () {

	alert("Button Clicked");
	
    const userId = localStorage.getItem("userId");

	alert(userId);

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

	alert(answers.length);

	const response = await fetch("http://localhost:8080/api/student-answers/submit", {

   	 	method: "POST",

   	 	headers: {
        	"Content-Type": "application/json"
    	},

    	body: JSON.stringify(answers)

	});

	alert("Answers Saved Successfully");