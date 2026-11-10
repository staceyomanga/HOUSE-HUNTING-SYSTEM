# Student Accommodation & Hostel Finder Platform

**Group 5 - Software Engineering Project**

A web-based application designed to help students find, compare, and book accommodation near their campus. Landlords can list properties, and administrators can manage approvals and reports.

## 🚀 Tech Stack
*   **Backend:** Java (Spring Boot 4.x)
*   **Frontend:** HTML, CSS, JavaScript (Thymeleaf)
*   **Database:** MySQL (via XAMPP)
*   **Build Tool:** Maven
*   **IDE:** IntelliJ IDEA / NetBeans / VS Code

## 👥 Group Members & Modules
*   **Module 1 (Requirements):** Ibrahim Abdullahi, Praise Baraka
*   **Module 2 (Auth):** Claudia Jebet, Mbiko Valentine
*   **Module 3 (Profile):** Stacey Moraa, Ounga Raeli Ayieko
*   **Module 4 (Listings):** Candy Wanjiru, Abdulmalik Bashir
*   **Module 5 (Search):** Ibrahim Abdullahi, Claudia Jebet
*   **Module 6 (Details):** Praise Baraka, Stacey Moraa
*   **Module 7 (Inquiries):** Mbiko Valentine, Candy Wanjiru
*   **Module 8 (Reviews):** Ounga Raeli Ayieko, Abdulmalik Bashir
*   **Module 9 (Admin):** Ibrahim Abdullahi, Stacey Moraa
*   **Module 10 (Integration):** Mbiko Valentine, Praise Baraka
*   **Module 11 (Testing):** Claudia Jebet, Candy Wanjiru
*   **Module 12 (Deployment):** Praise Baraka, Ounga Raeli Ayieko

## 💻 How to Run the Project Locally

### Prerequisites
1.  **Java JDK 17 or higher** installed.
2.  **XAMPP** installed (for MySQL).
3.  **IntelliJ IDEA, NetBeans, or VS Code**.
4.  **Git** installed.

### 1. Set up the Database
1. Open **XAMPP Control Panel** and click **Start** next to MySQL.
2. Open phpMyAdmin (or your IDE's database tool).
3. Create a database named exactly: `hostel_finder_db`.

### 2. Configure the Application
1. Open `src/main/resources/application.properties`.
2. Ensure it matches your local XAMPP setup:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hostel_finder_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
