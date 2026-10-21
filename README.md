# api-spring-ai-prompt-openai-v1

Spring Boot and Spring AI example that uses **prompt engineering** with OpenAI. It shows how to combine a system role, user instructions and a structured output format to turn a short business request into a requirements analysis. It also exposes a REST endpoint that forwards a question to the model.

This project is part of the *Java & Spring AI Examples* series (module **02 – Prompt Engineering**).

**Project page:** https://josesaid.github.io/api-spring-ai-prompt-openai-v1/

## What it does

On startup, `PromptRunner` runs two `CommandLineRunner` beans that call the model through `ChatClient`:

1. **Requirements analysis:** a system role (business analyst) with explicit rules, a business request and a required Markdown structure (objective, actors, functional and non-functional requirements, business rules and missing information).
2. **Dynamic role prompt:** a second role (application architect) analyzes a security incident-management request.

The REST endpoint sends any question to the same model through `ChatService`.

## Tech stack

| Component       | Detail                                   |
|-----------------|------------------------------------------|
| Java            | 24 (see `pom.xml`)                       |
| Spring Boot     | 4.x (Spring Boot parent in `pom.xml`)    |
| Spring AI       | 2.0.0                                    |
| Model provider  | OpenAI (chat model, profile `openai`)    |
| Build tool      | Maven (wrapper included)                 |

## Project structure

```
src/main/java/com/mx/development/jose/said/
├── ApiSpringAiDemoV1Application.java   # Spring Boot entry point
├── controller/ChatController.java      # GET /api/v1/chat-service/chat
├── service/ChatService.java            # Service contract
├── service/ChatServiceImpl.java        # Calls the model through ChatClient
└── runner/PromptRunner.java            # Startup prompt examples
src/main/resources/
├── application.yml                     # Port, active profile, logging
└── application-openai.yml              # OpenAI configuration
```

## Prerequisites

- **JDK 24**, as declared in `pom.xml`
- **Maven** (the Maven Wrapper is included, so a local installation is optional)
- An **OpenAI API key**, created at https://platform.openai.com/api-keys

## Configuration

The API key is read from the `API_KEY` environment variable. Never commit a real key to the repository.

```bash
export API_KEY=your_openai_api_key
```

In PowerShell:

```powershell
$Env:API_KEY = "your_openai_api_key"
```

## Running the application

```bash
./mvnw spring-boot:run
```

The application starts on port **8088**. The two prompt examples print their responses to the console at startup.

## REST API

| Method | Path                                  | Query parameter | Description                         |
|--------|---------------------------------------|-----------------|-------------------------------------|
| GET    | `/api/v1/chat-service/chat`           | `question`      | Sends the question to the model     |

```bash
curl "http://localhost:8088/api/v1/chat-service/chat?question=Explain%20prompt%20engineering%20in%20one%20paragraph"
```

## Key concepts

- **System role:** sets the model's behavior and rules.
- **User message:** carries the task and its input data.
- **Structured output:** a fixed set of Markdown sections makes the answer predictable.
- **Explicit constraints:** rules such as "do not invent information" reduce hallucinations.

## Author

Created by **Jose Said Olano Garcia**.
