<br />
<div align="center">
  <a href="https://github.com/RajasLokhande/vbs">
    <img src="https://img.icons8.com/external-flaticons-flat-flat-icons/100/external-backend-computer-programming-flaticons-flat-flat-icons.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Virtual Banking System (VBS)</h3>

  <p align="center">
    The engine under the hood. A clean, robust REST API built with Spring Boot.
    <br />
    <br />
    <a href="#-getting-started"><strong>Let's get started »</strong></a>
    <br />
    <br />
    <a href="https://github.com/RajasLokhande/vbs/issues">Report Bug</a>
    ·
    <a href="https://github.com/RajasLokhande/vbs/issues">Request Feature</a>
  </p>
</div>

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

</div>

---

## 📋 Table of Contents

1. [About The Project](#-about-the-project)
2. [How It's Built](#-how-its-built)
3. [Getting Started](#-getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Building from Scratch](#-building-from-scratch-optional)

---

## 📖 About The Project

Welcome to the **Virtual Banking System (VBS)** backend! 

This project is the backbone of a banking application, designed to handle user data securely and efficiently. I built this using a standard **Model-View-Controller (MVC)** architecture, which means the code is organized, easy to read, and scalable.

Whether you are connecting a frontend application or just testing API endpoints, this system is ready to go.

### ✨ What makes it cool?
* **RESTful Design:** Clean, standardized endpoints that are easy to integrate with.
* **User Power:** Full control over user data (Create, Read, Update, Delete).
* **Frontend Friendly:** CORS (Cross-Origin Resource Sharing) is already configured, so you won't get those annoying browser errors when connecting your UI.
* **Spring Magic:** heavy lifting is handled by Spring's Dependency Injection.

---

## 🛠 How It's Built

This project stands on the shoulders of giants:

* **Language:** [Java 17](https://www.oracle.com/java/) (LTS)
* **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
* **Build Tool:** [Maven](https://maven.apache.org/)
* **Database:** [MySQL]

---

## 🚀 Getting Started

Ready to run this on your local machine? Here is how to get set up.

### Prerequisites

Before you begin, make sure you have these installed:
* **Java JDK 17** or higher.
* **Maven** (optional, as you can use the wrapper included in the files).
* **MySQL** (or your preferred database) running on port 3306.

### Installation

1.  **Clone the repo**
    ```sh
    git clone [https://github.com/RajasLokhande/vbs.git](https://github.com/RajasLokhande/vbs.git)
    ```
2.  **Go to the code**
    ```sh
    cd vbs
    ```
3.  **Run it!**
    Maven will automatically download all the dependencies (like Lombok, JPA, etc.) defined in the `pom.xml`.
    ```sh
    mvn spring-boot:run
    ```

---

### 🎓 Building from Scratch (Optional)

If you are learning Spring Boot and want to recreate this project structure manually instead of cloning it, I've outlined the setup steps below.

<details>
<summary><strong>Click here to see the Spring Initializr setup guide</strong></summary>

<br>

If you want to build the skeleton of this project yourself:

1.  Head over to [start.spring.io](https://start.spring.io/).
2.  **Project:** Choose **Maven**.
3.  **Language:** Choose **Java**.
4.  **Spring Boot:** Select the latest stable version (e.g., 3.x.x).
5.  **Java Version:** Select **17**.
6.  **Dependencies:** Search for and add these 4 key items:
    * **Spring Web** (for the REST API)
    * **Lombok** (to reduce boilerplate code)
    * **MySQL Driver** (to talk to the database)
    * **Spring Data JPA** (to handle SQL magic)
7.  Click **Generate** to download the ZIP file.
8.  Unzip it, open it in your IDE, and you are ready to start coding!

</details>
