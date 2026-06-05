AI RAG System — Current Architecture
Overview
This project is a Java/Spring-based AI Retrieval-Augmented Generation (RAG) system focused on building a scalable and extensible documentRequest ingestion pipeline.
The current focus is on:
•	clean backend architecture
•	parser extensibility
•	separation of concerns
•	SOLID design principles
•	ingestion pipeline evolution
The goal is to deeply understand backend and AI system design rather than relying entirely on framework abstractions.

Current Ingestion Flow
Client Upload
->
Multipart REST API
->
Controller Layer
->
Document Resolver / Decider Service
->
Parser Strategy Resolution
->
Specific Parser Implementation
- PDF Parser
- TXT Parser
- JSON Parser

Current Architectural Decisions
1. Multipart Upload API
   The system currently accepts documentRequest uploads through a multipart REST endpoint.
   Reasoning
   •	standard ingestion entrypoint
   •	supports multiple documentRequest formats
   •	aligns with future async ingestion evolution
