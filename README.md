Quiz Microservices
This project is a Spring Boot-based microservices application designed for a quiz system. It demonstrates inter-service communication using Eureka for service discovery and OpenFeign for declarative REST client calls.

Services Included:
Service Registry (Eureka Server): The central hub for all microservices to register themselves, allowing them to find each other.

Port: 8761
Question Service: Manages all quiz questions. It provides endpoints to generate questions for a quiz, fetch question details, and calculate scores based on user responses.

Port: 8080
Quiz Service: Handles the creation, retrieval, and submission of quizzes. It communicates with the Question Service to get questions and calculate results.

Port: 8090
API Gateway: The single entry point for all client requests. It dynamically routes requests to the correct microservice using Eureka for discovery.

Port: 8765
How to Run:
Start the Service Registry:

Navigate to the service-registry project.
Run the main application class.
Verify it's up by visiting http://localhost:8761/ in your browser.
Start the Question Service:

Navigate to the QuestionService project.
Run the main application class.
Start the Quiz Service:

Navigate to the QuizServices project.
Run the main application class.
Start the API Gateway:

Navigate to the Api-gateway project.
Run the main application class.
Order Matters! Make sure you start the Service Registry first, then the other services. They need Eureka to be running to register themselves.

Endpoints (Example with Postman):
Once all services are running, you can interact with them via the API Gateway.

Create a Quiz:

Method: POST
URL: http://localhost:8765/QUIZ-SERVICE/quiz/create
Headers: Content-Type: application/json
Body (raw JSON):
JSON

{
    "categoryName": "Java",
    "numQuestions": 5,
    "title": "My Awesome Java Quiz"
}
Get Quiz Questions:

Method: GET
URL: http://localhost:8765/QUIZ-SERVICE/quiz/get/{id} (Replace {id} with the quiz ID from the creation response)
Submit Quiz Answers:

Method: POST
URL: http://localhost:8765/QUIZ-SERVICE/quiz/submit/{id} (Replace {id} with the quiz ID)
Headers: Content-Type: application/json
Body (raw JSON - example structure):
JSON

[
    {
        "id": 101,
        "response": "Option A"
    },
    {
        "id": 102,
        "response": "Option C"
    }
]
(Make sure id and response match your Response model and actual questions.)
