# 📱 Kartngo Demo App – Android (Java + Room + MVVM)

This project is a submission for the **Mobile Developer** position task, where I recreated a mobile app screen using Java and guided an AI assistant (**ProxyAI**) to generate and refactor code.

> ✅ The goal was to use **AI-led development**, implement proper **MVVM architecture**, and build a **fully functional screen** using real/mock data.

---

## 🎯 Objective

Rebuild the provided screen design using Android (Java), leveraging **ProxyAI** to:
- Generate Room DB structure
- Implement MVVM architecture
- Load data and manage state
- Deliver a functional, pixel-perfect UI

---

## 🧠 ProxyAI Prompts & Integration

Throughout the project, I used ProxyAI with **clear, purposeful prompts**. Here's a list of them and how I integrated their results into the codebase.

### 🧩 Prompt 1
**Prompt:**
```
hi iam using java in android development please setup my project to use room db
```
**Result:**
Set up Room DB dependencies and structure. Created:
- `ProductEntity.java`
- `ProductDao.java`
- `AppDatabase.java`

---

### 🧩 Prompt 2
**Prompt:**
```
i'm building an android app using java and room db , iwant you to generate the complete data layer . start with product entity with the following fields name image url , category price currency the generate dao interface with getall function and database class follow mvvm arch
```
**Result:**
Generated:
- Full data layer (entities, DAO, DB)
- MVVM structure foundation

---

### 🧩 Prompt 3
**Prompt:**
```
add dummy data in database initialization
```
**Result:**
Populated `AppDatabase.java` with mock product data on first launch.

---

### 🧩 Prompt 4
**Prompt:**
```
ProductDao.java ProductEntity.java AppDatabase.java and product repo refactor all of them to use live data
```
**Result:**
- Updated all data returns to use `LiveData<List<Product>>`
- Integrated with ViewModel

---

### 🧩 Prompt 5
**Prompt:**
```
make a ProductDataSource Interface
```
**Result:**
- Created abstraction for repository layer.

---

### 🧩 Prompt 6
**Prompt:**
```
make product model in models layer and make product repo and interface to map product entity to product. the product entity name is ProductEntity and Product model name is Product in com.mina.kartngo.models
```
**Result:**
- Created a clean separation between DB model (`ProductEntity`) and domain model (`Product`).
- Mapping handled in repository.

---

### 🧩 Prompt 7
**Prompt:**
```
create a single MainViewModel in java using mvvm arch should be fetch all products as a list and use live data make OrderItem that includes the product and its quantity and make view model manage it in memory and should expose the list of products and list of order item as current order please follow best practice
```
**Result:**
- Created `MainViewModel.java` with:
  - `LiveData<List<Product>>`
  - `MutableLiveData<List<OrderItem>>` as in-memory cart
- Fully managed current order logic inside ViewModel

---

## 🖼️ Screenshots

### 🔷 Original Design

![Original Screen](https://github.com/mina0maher/kartngo/blob/master/screenShots/original_screen.jpg?raw=true)

### 🔶 Recreated App

| Orders Screen | Products Screen |
|---------------|-----------------|
| ![Orders Screen](https://github.com/mina0maher/kartngo/blob/master/screenShots/ordres_screen.jpg?raw=true) | ![Products Screen](https://github.com/mina0maher/kartngo/blob/master/screenShots/products_screen.jpg?raw=true) |

---

## 🧪 Features

- ✅ **Pixel-perfect layout** reproduction
- ✅ Products loaded from **Room DB**
- ✅ Uses **MVVM architecture**
- ✅ Functional back navigation (via back arrow)
- ✅ All clickable elements show **Toast** messages
- ✅ In-memory state management for current order
- ✅ Organized clean codebase and architecture

---

## 🚀 How to Run

1. Clone this repo:
   ```bash
   git clone https://github.com/mina0maher/kartngo.git
   ```
2. Open in Android Studio (Arctic Fox or above).
3. Build and run on emulator/device.
4. App will auto-load **mock data** from Room DB.

Or simply download the APK:

📦 [Download APK](https://github.com/mina0maher/kartngo/blob/master/apk/app-debug.apk?raw=true)

---

## 📬 Submission Info

- **Track**: Android (Java)
- **GitHub Repo**: [https://github.com/mina0maher/kartngo](https://github.com/mina0maher/kartngo)
- **Video Recording**: Attached in email
- **Design Screenshot**: [Original Design](https://github.com/mina0maher/kartngo/blob/master/screenShots/original_screen.jpg?raw=true)

---

## ✅ Evaluation Checklist

| Criteria             | Status |
|----------------------|--------|
| AI‑Led Coding        | ✅ Yes |
| UI Accuracy          | ✅ Yes |
| Demo Functionality   | ✅ Yes |
| Architecture (MVVM)  | ✅ Yes |
| Code Quality         | ✅ Yes |
| Interaction          | ✅ Yes |

---

🎉 **Thanks for reviewing! Looking forward to hearing from you.**
