# Library Management System (Java + MySQL)

## Setup Instructions

1. Install MySQL and create the database:
   ```sql
   SOURCE library.sql;
   ```

2. Compile the project:
   ```sh
   javac -cp "lib/mysql-connector-j-8.0.xx.jar;src" src/*.java
   ```

3. Run the project:
   ```sh
   java -cp "lib/mysql-connector-j-8.0.xx.jar;src" LibraryApp
   ```

## Features
- Display all books
- Borrow a book
- Return a book
- Simple command-line interface
