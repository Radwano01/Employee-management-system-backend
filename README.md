Employee Management System

This project implements a comprehensive employee management system using Java and Spring Boot. It offers robust features for managing employee information, tasks, and user access, utilizing a MySQL/SQL database for persistence.

Project Structure:

The project adheres to a well-defined structure, promoting maintainability and clarity:

Entity Classes:

  Employee: Represents employee data (name, surname, email, salary, etc.)
  Field: Categorizes employee expertise (e.g., Software Development, Marketing) - CRUD operations supported
  Role: Defines employee responsibilities (e.g., Developer, Manager)
  Level: Indicates employee experience (e.g., Junior, Senior)
  Task: Tracks assigned tasks to employees - (Supports CRUD operations)
  Admin: Stores admin credentials (username, hashed password)
  
Controller Classes:

  EmployeeController: Handles employee-related API endpoints (CRUD operations)
  FieldController: Manages field-related functionalities (CRUD operations supported)
  TaskController: Handles task-related functionalities (CRUD operations supported) - (Manages task creation, reading, updating, and deletion)
  AdminController: Manages admin functionalities (authentication, etc.)
  
Service Classes:

  EmployeeService: Provides business logic for employee management
  FieldService: Handles field-specific operations (CRUD operations supported)
  TaskService: Provides business logic for creating, assigning, and managing tasks - (Handles CRUD operations for tasks)
  AdminService: Handles admin-specific operations

Features:

  Employee Management:
  
  Create, read, update, and delete employee records
  Associate employees with fields, roles, and levels
  Secured access using Spring Security
  
  Field Management:
  
  Create (add) new fields (e.g., Software Development, Marketing)
  Read (get) existing fields and their details
  Update (edit) existing fields
  Delete fields (ensure no employees are associated before deletion)
  
  Task Management:
  
  Create new tasks and assign them to employees
  Read existing tasks and their details
  Update task details (e.g., status, priority)
  Delete completed or no longer relevant tasks

Technologies:

  Java
  Spring Boot
  Spring Security
  JWT (optional)
  MySQL/SQL Database

Admin Account

The system features a dedicated admin account for comprehensive system administration. This account possesses elevated privileges to manage all aspects of the employee management system.

Enhanced Admin Capabilities:
  Employee Management (CRUD Operations):
    The admin can create new employee accounts, including setting their names, emails, salaries, roles, levels, and associating them with relevant fields.
    The admin has the authority to read all employee details, enabling them to view the entire employee database.
    Admins can update existing employee information, allowing for changes to names, emails, salaries, roles, levels, or field associations.
    The admin can delete employee accounts, ensuring proper management of the employee database.

  Field Management (CRUD Operations):
    The admin can now create new fields (e.g., Software Development, Marketing) to categorize employee expertise, fostering a more granular classification system.
    Admins can read existing fields and their details, providing an overview of the defined expertise categories.
    The admin has the ability to update existing fields, enabling adjustments to their names or descriptions as needed.
    For improved data integrity, the admin can delete fields, with the caveat that no employees should be currently associated with them before deletion.

  Secure Login:
    The admin account utilizes a secure login process with JWT (JSON Web Token) authentication, guaranteeing a robust mechanism for access control.
    The admin's password is always stored in a hashed format within the database, significantly enhancing system security by preventing plain text storage of sensitive information. 
