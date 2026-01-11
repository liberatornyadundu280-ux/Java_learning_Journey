🎬 Online Movie Ticket Booking System (Java)

1. Project Overview

The Online Movie Ticket Booking System is a console-based Java application that simulates how real-world cinema booking platforms operate.
It supports administrative configuration, customer ticket booking, seat-level management, cancellation rules, file-based persistence, and safe concurrent access.

The system is designed with clean architecture principles, ensuring separation of concerns, extensibility, and reliability.

2. Key Features
   👨‍💼 Admin Features

Add movies

Add theaters

Add halls to theaters

Schedule showtimes for movies

View system summary (movies, theaters, halls, showtimes)

All admin actions persist across restarts

👤 Customer Features

View movies

View theaters, halls, and showtimes

Book seats for a showtime

Cancel tickets (only before a defined cutoff time)

Seat availability updates correctly

🔒 System Guarantees

No double booking of seats

Thread-safe booking and cancellation

Persistent data using files

Centralized input validation

Clean role separation (Admin vs Customer)

3. High-Level Architecture

The system follows a layered architecture:

```cmd
UI Layer
├── MovieSystemApp (menus & flow)
│
Service Layer
├── AdminService
├── BookingService
│
Domain Layer
├── Movie
├── Theater
├── Hall
├── Showtime
├── Seat
├── Ticket
│
Persistence Layer
├── MovieRepository
├── TheaterRepository
├── ShowtimeRepository
├── TicketRepository
│
Utility Layer
├── InputValidator
```

Each layer has one responsibility only.

4. Domain Model Explanation
   Movie

Immutable

Fields: movieId, name, genre, duration

Represents content, not scheduling

Theater

Fields: theaterId, name, location

Owns multiple halls

Hall

Identified by hall number per theater

Has a fixed seat capacity

Owns multiple showtimes

Showtime

Fields: showtimeId, movie, hall, startTime

Owns seats

Seat list derived from hall capacity

Seat

Fields: seatNumber, booked

No business logic

Booking state controlled by service layer

Ticket

Fields: ticketId, showtime, seats, bookingTime, cancelled

Immutable except for cancellation flag

Acts as booking proof

5. Service Layer Responsibilities
   AdminService

Handles system configuration:

Add movies

Add theaters

Add halls

Schedule showtimes

Generate system summary

AdminService replaces all hard-coded initialization logic.

BookingService

Handles transactional operations:

Book seats

Cancel tickets

Enforce cancellation cutoff

Prevent double booking

Synchronize on Showtime for thread safety

Synchronization is applied at the business-operation level, not at collections.

6. Persistence Design
   File-Based Storage

The system uses plain text files for persistence:

```cmd
data/
├── movies.txt
├── theaters.txt
├── halls.txt
├── showtimes.txt
├── seats.txt
└── tickets.txt
```

Key Properties

Human-readable

Restart-safe

No external libraries

Load order strictly enforced:

Movies

Theaters & halls

Showtimes & seats

Tickets

Repositories contain only file logic, no business rules.

7. Input Handling Strategy
   InputValidator

Uses a shared BufferedReader for fast input

Centralizes all input parsing and validation

Supports:

Integers with range constraints

Mandatory strings

Optional strings

Pattern-constrained strings

Comma-separated integer lists

Prevents invalid states before they reach the system

The UI never parses input directly.

8. UI Design

```cmd
Main Menu
1. Customer
1. Admin
1. Exit

Admin Menu

1. Add Movie
2. Add Theater
3. Add Hall to Theater
4. Schedule Showtime
5. View System Summary
6. Back

Customer Menu

1. List Movies
2. List Theaters & Showtimes
3. Book Ticket
4. Cancel Ticket
5. Back
```

Menu input is range-validated

Defensive default cases exist for future safety

UI only coordinates actions

9. Concurrency Handling

Multiple users can book simultaneously

Synchronization is done on Showtime objects

Ensures:

Atomic booking

No race conditions

No double booking

Collections are not relied upon for thread safety—business logic is.

10. Design Principles Applied

Single Responsibility Principle

Separation of Concerns

Fail-fast validation

Defensive programming

Composition over inheritance

Transaction-level synchronization

Persistence isolation

11. Limitations & Future Improvements

No authentication (admin PIN can be added)

No seat categories (VIP/Standard)

No pricing model

Console UI only (can be replaced with GUI/Web)

12. Conclusion

This project demonstrates:

Real-world system modeling

Clean Java OOP design

Safe concurrency

Persistent state management

Scalable architecture

It goes beyond a basic CRUD application and reflects how real booking systems are structured internall
