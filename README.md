# 🛒 Buy‑Now: Android Grocery App (Jetpack Compose Practice)

**Buy‑Now** is a **pet Android project** created to practice **Jetpack Compose** and modern Android development principles. It simulates a grocery shopping app with modular design and feature-based architecture.

---

## 📌 Overview

- Browse grocery categories and products  
- Add/remove items from the shopping cart  
- Manage user profile and favorites  
- Login and signup flow  
- Notifications for offers and updates  
- Firebase is used for database and backend services  

> This project is for learning purposes and practicing **Jetpack Compose**. It is not production-ready.

---

## 🧱 Architecture

The app follows a **modular, feature-based architecture**:

- **Feature Modules:** Separate modules for Home, Cart, Product, Profile, Category, Favorites, Notifications, Login, Signup  
- **Shared Components:** `common-ui` for reusable UI elements, `common-utils` for utilities  
- **Data Layer:** `data`, `network`, and `persistence` handle models, repositories, APIs, and local storage  
- **Backend:** Firebase is used for authentication, database, and notifications  
- **Navigation:** Jetpack Navigation Compose manages screen flows  

This structure promotes **clean code, scalability, and modularity**, ideal for experimentation and learning.

---

## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM / Clean Architecture |
| Backend / Database | Firebase (Auth, Firestore, Realtime Database) |
| Dependency Injection | Hilt (if implemented) |
| Networking | Retrofit / OkHttp |
| Persistence | Room / SharedPreferences |

---

## ⚠️ Note

This is a **personal pet project** for **practicing Jetpack Compose** and modern Android development techniques. It is intended for educational purposes.

---

## 📂 Project Structure

- app  
- common-ui  
- common-utils  
- data  
- feature-cart  
- feature-category  
- feature-favorite  
- feature-home  
- feature-login  
- feature-main  
- feature-notification  
- feature-product  
- feature-profile  
- feature-signup  
- network  
- persistence  
- model

Each module encapsulates UI and business logic for a specific feature, making the project modular and maintainable.
