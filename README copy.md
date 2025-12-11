# Solve & Hack Platform 🚀

A competitive programming platform with a unique two-phase system: **Solve Phase** for solving problems and **Hack Phase** for challenging other participants' solutions.

[![Go Version](https://img.shields.io/badge/Go-1.21+-00ADD8?style=flat&logo=go)](https://go.dev/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Docker](https://img.shields.io/badge/Docker-Required-2496ED?style=flat&logo=docker)](https://www.docker.com/)

## Table of Contents

- [Solve \& Hack Platform 🚀](#solve--hack-platform-)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
    - [How It Works](#how-it-works)
  - [Features](#features)
    - [Core Features](#core-features)
    - [Technical Features](#technical-features)
  - [Architecture](#architecture)
    - [Components](#components)
  - [Tech Stack](#tech-stack)
    - [Backend](#backend)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Quick Start](#quick-start)
  - [Project Structure](#project-structure)
  - [Development](#development)
    - [Available Make Commands](#available-make-commands)
    - [Database Migrations](#database-migrations)
    - [Generating Code with sqlc](#generating-code-with-sqlc)
  - [Configuration](#configuration)
    - [Environment Variables](#environment-variables)
  - [Contributing](#contributing)
  - [License](#license)

## Overview

The Solve & Hack Platform is a competitive programming system that introduces an innovative **Hack Phase** after the traditional solving phase. Participants not only solve problems but can also "hack" other solutions by providing custom test cases that expose incorrect implementations.

### How It Works

1. **Solve Phase**: Participants solve competitive programming problems within a time limit
2. **Hack Phase**: After the solve phase ends, participants can attempt to break other solutions with custom test cases
3. **Scoring**: Points are awarded for correct solutions and successful hacks, with penalties for failed hacks

## Features

### Core Features
- **Contest Management**: Create and manage timed programming contests
- **Problem Repository**: Rich problem statements with public/private test cases
- **Real-time Judging**: Automated code execution and evaluation
- **Hack System**: Challenge other solutions with custom test cases
- **Live Leaderboard**: Real-time rankings based on score and submission time
- **Role-based Access**: Admin, setter, and participant roles
- **Contest Controls**: Pause/resume/end contests dynamically

### Technical Features
- **Queue-based Architecture**: Scalable submission processing using Redis/RabbitMQ
- **Secure Execution**: Sandboxed code execution with resource limits
- **Monitoring**: Health checks, metrics, and audit logging
- **Docker Support**: Complete containerized deployment

## Architecture

```
┌─────────────┐
│   Frontend  │
└──────┬──────┘
       │ REST API
       ▼
┌─────────────────────────────────────────┐
│          BFF (Backend for Frontend)     │
│              Gin + Golang               │
│  ┌─────────────────────────────────┐    │
│  │  Auth  │ Contests │ Submissions │    │
│  │Problems│ Hacks    │ Leaderboard │    │
│  └─────────────────────────────────┘    │
└──────┬──────────────────┬───────────────┘
       │                  │
       ▼                  ▼
┌─────────────┐    ┌─────────────┐
│  PostgreSQL │    │   Redis/    │
│   + sqlc    │    │  RabbitMQ   │
└─────────────┘    └──────┬──────┘
                          │
                          ▼
                   ┌─────────────┐
                   │   Worker    │
                   │   Golang    │
                   └──────┬──────┘
                          │
                          ▼
                   ┌─────────────┐
                   │  Execution  │
                   │   Engine    │
                   └─────────────┘
```

### Components

- **BFF (Backend for Frontend)**: REST API server handling all client requests
- **Worker Service**: Consumes jobs from queue and coordinates with execution engine
- **PostgreSQL**: Primary data store with type-safe queries via sqlc
- **RabbitMQ**: Message queue for async job processing
- **Execution Engine**: Sandboxed code execution service (placeholder - needs external implementation)

## Tech Stack

### Backend
- **Language**: Go 1.23.3
- **Web Framework**: Gin
- **Database**: PostgreSQL 16
- **Query Builder**: sqlc (type-safe SQL)
- **Queue**: RabbitMQ
- **Migrations**: Goose
- **Authentication**: JWT
- **Containerization**: Docker & Docker Compose
- **Logging**: Uber Zap

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Go 1.23.3 or higher (for local development)
- Make (optional, for convenience commands)
- PostgreSQL client tools (optional, for manual database operations)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/ComputerSocietyVITC/solve-and-hack-platform.git
   cd solve-and-hack-platform
   ```

2. **Set up environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with your configuration
   ```

3. **Start all services**
   ```bash
   make docker-up
   # Or use docker-compose directly:
   docker-compose up -d
   ```

4. **Verify services are running**
   ```bash
   make health-check
   ```

5. **Seed the database (optional)**
   ```bash
   make db-seed
   ```

The API will be available at `http://localhost:8080`

**Service URLs:**
- BFF API: `http://localhost:8080`
- RabbitMQ Management UI: `http://localhost:15672` (admin / rabbitmq_dev_password)
- PostgreSQL: `localhost:5432`

## Project Structure

```
solve-and-hack-platform/
├── bff/                      # Backend for Frontend service
│   ├── cmd/
│   │   └── server/          # Main application entry point
│   ├── internal/
│   │   ├── api/             # HTTP handlers and routes
│   │   ├── auth/            # Authentication & authorization
│   │   ├── db/              # Database queries (sqlc generated)
│   │   ├── middleware/      # HTTP middleware
│   │   ├── models/          # Domain models
│   │   ├── queue/           # Queue client
│   │   └── services/        # Business logic
│   ├── go.mod
│   └── go.sum
│
├── worker/                   # Worker service
│   ├── cmd/
│   │   └── worker/          # Worker entry point
│   ├── internal/
│   │   ├── execution/       # Execution engine client
│   │   ├── jobs/            # Job processors
│   │   └── queue/           # Queue consumer
│   ├── go.mod
│   └── go.sum
│
├── migrations/               # Database migrations
│   ├── 000001_init.up.sql
│   ├── 000001_init.down.sql
│   └── ...
│
├── queries/                  # SQL queries for sqlc
│   ├── users.sql
│   ├── contests.sql
│   ├── submissions.sql
│   └── ...
│
├── docs/                     # Documentation
│   ├── api/                 # API documentation
│   ├── architecture/        # Architecture diagrams
│   └── guides/              # User guides
│
├── scripts/                  # Utility scripts
│   ├── seed.sh              # Database seeding
│   └── backup.sh            # Backup scripts
│
├── docker-compose.yml        # Development environment
├── docker-compose.prod.yml   # Production environment
├── Makefile                  # Development commands
├── .env.example             # Example environment variables
├── sqlc.yaml                # sqlc configuration
└── README.md                # This file
```

## Development

### Available Make Commands

```bash
# Development
make build          # Build all services
make run-bff        # Run BFF locally
make run-worker     # Run worker locally
make install-deps   # Install Go dependencies
make tidy           # Tidy Go modules
make fmt            # Format code
make vet            # Run go vet
make lint           # Run linters (requires golangci-lint)

# Database
make migrate-up     # Apply migrations
make migrate-down   # Rollback last migration
make migrate-status # Check migration status
make migrate-create # Create new migration (NAME=migration_name)
make db-reset       # Reset database
make db-seed        # Seed test data
make sqlc           # Generate sqlc code

# Docker
make docker-up      # Start all containers
make docker-down    # Stop all containers
make docker-logs    # View logs (SERVICE=service_name)
make docker-clean   # Remove all containers and volumes
make docker-rebuild # Rebuild and restart containers

# Tools
make install-tools  # Install development tools (goose, sqlc, golangci-lint)
make health-check   # Check health of all services

# Utility
make dev            # Start development environment
make init           # Initialize project (first time setup)
make clean          # Clean build artifacts
make help           # Show all available commands
```

### Database Migrations

We use [Goose](https://github.com/pressly/goose) for database migrations.

```bash
# Create new migration
make migrate-create NAME=add_user_table

# Apply all pending migrations
make migrate-up

# Rollback last migration
make migrate-down

# Check migration status
make migrate-status

# Reset database (drop all tables and reapply migrations)
make db-reset
```

Migration files are stored in the `migrations/` directory and follow the naming convention:
- `XXXXX_description.sql` where XXXXX is a sequential number

### Generating Code with sqlc

After modifying SQL queries in `queries/`:

```bash
make sqlc
```

This generates type-safe Go code in `bff/internal/db/`

## Configuration

### Environment Variables

Create a `.env` file in the root directory:

```env
# Server Configuration
PORT=8080
ENV=development
LOG_LEVEL=info

# Database
DB_HOST=postgres
DB_PORT=5432
DB_USER=postgres
DB_PASSWORD=your_secure_password
DB_NAME=solve_hack
DB_SSLMODE=disable
DATABASE_URL=postgres://postgres:your_secure_password@postgres:5432/solve_hack?sslmode=disable

# RabbitMQ
RABBITMQ_HOST=rabbitmq
RABBITMQ_PORT=5672
RABBITMQ_USER=admin
RABBITMQ_PASSWORD=admin_password
RABBITMQ_VHOST=/
RABBITMQ_URL=amqp://admin:admin_password@rabbitmq:5672/

# JWT
JWT_SECRET=your_jwt_secret_key_change_this
JWT_EXPIRATION=15m
REFRESH_TOKEN_EXPIRATION=7d

# Execution Engine
EXECUTION_ENGINE_URL=http://execution-engine:2358
EXECUTION_CALLBACK_URL=http://bff:8080/api/v1/webhooks/execution-result

# Rate Limiting
RATE_LIMIT_REQUESTS=100
RATE_LIMIT_WINDOW=1m

# Contest Settings
DEFAULT_HACK_PHASE_DURATION=2h
MAX_SUBMISSION_SIZE=100KB

# Worker
WORKER_CONCURRENCY=5
WORKER_RETRY_ATTEMPTS=3
```

## Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes**
   - Follow Go best practices
   - Update documentation
4. **Run tests and linters**
   ```bash
   make test
   make lint
   ```
5. **Commit your changes**
   ```bash
   git commit -m "Add amazing feature"
   ```
6. **Push to your fork**
   ```bash
   git push origin feature/amazing-feature
   ```
7. **Open a Pull Request**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
