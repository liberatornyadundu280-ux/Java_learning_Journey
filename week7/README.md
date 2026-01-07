# Week 7 — Multithreading & Concurrency

This week’s coursework explores multithreading and concurrency in Java. The primary example demonstrates a simple thread pool implementation and a demo program that submits tasks to the pool.

## What’s included

- `ThreadPoolDemo.java` — a compact implementation of a simple thread pool and a test program that submits tasks.

## Summary

The project shows:

- How to create worker threads that pull tasks from a shared `BlockingQueue`.
- Reusing threads via a thread pool instead of creating/destroying threads per task.
- Graceful shutdown using a poison-pill pattern.

## How to run

Open a terminal, change into the `week7` folder, then compile and run:

```bash
cd week7
javac ThreadPoolDemo.java
java ThreadPoolDemo
```

You should see output indicating tasks being executed by worker threads.

## Learning outcomes

- Understand thread creation and lifecycle in Java.
- Learn how `BlockingQueue` helps coordinate producer/consumer between threads.
- See a simple thread pool design and how to shut it down cleanly.

## Notes

- The demo uses the default package (no `package` statement). Run from the `week7` folder as shown above.
- Feel free to extend the pool (e.g., add task rejection policies, dynamic resizing, or futures).

---

_Created as Week 7 coursework: Multithreading & Concurrency._
