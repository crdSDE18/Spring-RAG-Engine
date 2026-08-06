# AI RAG System — Current Architecture

## Overview

This project is a Java/Spring Boot-based Retrieval-Augmented Generation (RAG) system focused on building a scalable, maintainable, and extensible document ingestion pipeline.

Rather than focusing solely on integrating AI models, the project emphasizes backend architecture and sound engineering principles. The objective is to understand how production systems are designed by building each component intentionally, allowing the architecture to evolve alongside new requirements.

Current areas of focus include:

* Clean backend architecture
* Parser extensibility
* Separation of concerns
* SOLID design principles
* Domain-driven layering
* Repository abstraction
* Evolutionary architecture
* AI document ingestion pipelines

The long-term goal is to build a production-ready RAG platform while gaining a deeper understanding of backend and AI system design instead of relying entirely on framework abstractions.

---

# Current MVP Scope

The current MVP focuses exclusively on the **document ingestion pipeline**.

### Implemented

* Multipart document upload
* Job creation
* Parser strategy resolution
* Document parsing
* Semantic chunking
* Embedding generation
* Persistence of embedded chunks
* Layered application architecture

### Deferred to Future MVPs

The following capabilities are intentionally postponed to keep the initial implementation focused:

* Asynchronous processing (`@Async`)
* Retry policies
* Comprehensive error handling
* Job failure handling
* Event publishing
* Metrics and observability
* Retrieval pipeline
* Vector database integration

These features will be introduced incrementally as the system evolves.

---

# Current Ingestion Flow

```text
Client
    │
    ▼
POST /documents
    │
    ▼
Create Job
(Status: PENDING)
    │
    ▼
Controller
    │
    ▼
DocumentOrchestrator
    │
    ├── Resolve Parser Strategy
    │
    ├── Parse Document
    │
    ├── Chunk Document
    │
    ├── Generate Embeddings
    │
    └── Persist Embedded Chunks
    │
    ▼
Update Job
(Status: COMPLETED)
```

---

# Component Responsibilities

## Controller

Responsible only for handling HTTP requests and delegating work to the application layer.

Responsibilities:

* Validate incoming requests
* Create processing jobs
* Delegate processing to the orchestrator
* Return the appropriate HTTP response

---

## DocumentOrchestrator

Coordinates the complete document processing workflow.

Responsibilities:

* Select the correct parser
* Parse the uploaded document
* Chunk parsed content
* Generate embeddings
* Persist embedded chunks
* Update job status

The orchestrator intentionally owns workflow coordination while individual services remain responsible for a single capability.

---

## Parser Strategy

Document parsing is implemented using the **Strategy Pattern**.

Current implementations include:

* PDF Parser
* TXT Parser
* JSON Parser

This design allows new document formats to be added without modifying orchestration logic, adhering to the Open/Closed Principle.

---

## Chunk Service

Transforms parsed document content into semantically meaningful chunks suitable for embedding.

This service is intentionally unaware of embedding generation or persistence.

---

## Embed Service

Generates vector embeddings from document chunks.

Responsibilities:

* Receive document chunks
* Generate embeddings
* Return embedded domain objects

Embedding generation and persistence are intentionally separated to maintain a clear separation of concerns.

---

## Repository Layer

Responsible for persisting embedded chunks.

The application depends on repository interfaces rather than concrete persistence implementations, allowing storage technologies to evolve independently of the application layer.

---

# Current Job Lifecycle

The MVP currently models a minimal job lifecycle.

```text
PENDING
    │
    ▼
COMPLETED
```

Future iterations will introduce additional states such as:

```text
PENDING
    │
    ▼
PROCESSING
    │
    ├── COMPLETED
    └── FAILED
```

This evolution supports asynchronous processing, retries, and improved operational visibility without requiring significant architectural changes.

---

# Architectural Decisions

## Multipart Upload API

The system currently accepts document uploads through a multipart REST endpoint.

### Why?

* Standard document ingestion entry point
* Supports multiple document formats
* Aligns naturally with future asynchronous processing

---

## Why an Orchestrator?

Document processing spans multiple independent capabilities:

* Parsing
* Chunking
* Embedding
* Persistence

Rather than coupling these concerns together, a dedicated orchestrator coordinates the workflow while each service remains focused on a single responsibility.

---

## Why the Strategy Pattern?

Different document types require different parsing logic.

The Strategy Pattern allows additional parsers to be introduced without modifying existing orchestration code, making the system easily extensible.

---

## Why Repository Abstractions?

The application depends on repository interfaces rather than JPA implementations.

This isolates business logic from persistence concerns and allows the storage implementation to evolve independently.

---

## Why Separate Embedding from Persistence?

Generating embeddings is a computational concern, while storing them is an infrastructure concern.

Separating these responsibilities keeps services cohesive and allows each capability to evolve independently.

---



# Future Iterations

Planned improvements include:

* Asynchronous document processing
* Retry policies for transient failures
* Refined transaction boundaries
* Comprehensive error handling
* Job status polling
* Event-driven processing
* Vector database integration
* Retrieval pipeline
* Semantic search
* Observability, metrics, and distributed tracing

---

# Project Goal

The objective of this project extends beyond building a functional RAG pipeline.

It serves as an exercise in designing production-quality backend systems by emphasizing maintainability, extensibility, and thoughtful architectural decisions. Each feature is introduced incrementally, allowing the architecture to evolve naturally while remaining clean and adaptable.
