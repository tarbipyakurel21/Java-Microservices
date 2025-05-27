Quiz Microservices
This project is a Spring Boot app all about quizzes, built with a bunch of smaller services that talk to each other. Think of it like a team where everyone has a specific job!

The Team:
Service Registry (Eureka Server): This is the team manager. Everyone (all the other services) tells it where they are, so they can find each other easily.

Runs on port 8761.
Question Service: This guy knows all the questions. Need a Java question? It's got 'em. It also helps score quizzes.

Runs on port 8080.
Quiz Service: This is the quiz master. It asks the Question Service for questions, puts quizzes together, and handles submissions.

Runs on port 8090.
API Gateway: This is the front door. Everyone comes here first, and it sends them to the right service. Super handy for security and making things simple.

Runs on port 8765.
How to Get It Running:
You need to start 'em up in order, like setting up a domino chain:

Start the Service Registry (Eureka Server) first. It's the boss!
Then fire up the Question Service.
Next, get the Quiz Service going.
Finally, launch the API Gateway.
Try It Out (with Postman):
Once everything's running, hit the API Gateway to interact with your quiz system!

Create a Quiz:

POST to http://localhost:8765/QUIZ-SERVICE/quiz/create
Send some JSON like: {"categoryName": "Java", "numQuestions": 5, "title": "My Super Quiz"}
Get Quiz Questions:

GET to http://localhost:8765/QUIZ-SERVICE/quiz/get/{quizId} (use the ID you got after creating a quiz)
