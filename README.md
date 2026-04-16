# CS316_Project5

Created by Noah Silva-Lopez & Sinclair Nzenwata

This project simulates a call center system using Java’s Executor framework, demonstrating key concepts in multithreading.

The application uses an ExecutorService with a fixed thread pool to efficiently manage multiple customer and agent threads. Customers act as producers by entering a shared waiting queue, while agents act as consumers by servicing customers from that queue. To prevent race conditions and ensure data consistency, a mutex lock is used to allow only one thread at a time to modify the queue. When a customer enters the queue, they release a semaphore to signal that a customer is available. Agent threads acquire this semaphore before attempting to serve a customer, ensuring proper synchronization between customer and agent operations.
