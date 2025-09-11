# 📱 Online Confernce- Android Live Streaming App

**Online Conference** is an Android-based live streaming application that allows users to host and join live streaming sessions. Built with **Firebase authentication** and **ZEGOCLOUD's streaming capabilities**, it provides a seamless live streaming experience.

---

## 🌟 Features

- **User Authentication**: Secure login and registration using Firebase.
- **Live Streaming**: Host and join live streams with ZEGOCLOUD.
- **Host/Viewer Roles**: Switch between hosting and viewing modes.
- **Profile Management**: Manage user profiles and settings.
- **Real-time Interaction**: Engage with viewers or hosts in real-time.

---


## 🛠️ Tech Stack

- **Java**: Primary programming language.
- **Firebase Authentication**: For secure user authentication.
- **ZEGOCLOUD UI Kit**: For live streaming capabilities.
- **Android SDK**: Core framework for Android development.
- **Gradle**: Build automation tool.

---

## 🚀 Prerequisites

- **Android Studio Arctic Fox** or later.
- **JDK 8** or higher.
- **Android device/emulator** running Android 5.0 (API 21) or higher.
- **Active Firebase account**.
- **ZEGOCLOUD account**.

---

## 🛠️ Setup Instructions

### 1. Clone the Repository
```bash


2. Firebase Configuration
Create a new Firebase project at Firebase Console.
Add an Android app with the package name com.example.myapplication.
Download the google-services.json file.
Place the google-services.json file in the app directory.
3. ZEGOCLOUD Setup
Sign up for a ZEGOCLOUD account.
Obtain your AppID and AppSign.
Update these credentials in your app.
4. Build and Run
Open the project in Android Studio.
Sync Gradle files.
Build the project.
Run on an emulator or physical device.
📂 Project Structure
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/myapplication/
│   │   │   ├── LiveActivity.java
│   │   │   ├── MainActivity.java
│   │   │   ├── MainUI.java
│   │   │   └── ...
│   │   └── res/
│   └── androidTest/
└── build.gradle
📦 Dependencies
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'com.google.firebase:firebase-auth:22.3.1'
    implementation 'com.github.ZEGOCLOUD:zego_uikit_prebuilt_live_streaming_android'
    implementation 'com.google.code.gson:gson:2.8.8'
}
⚙️ Configuration
The app uses the following configuration from google-services.json:

Project ID: login-register-project-b9528
Package Name: com.example.myapplication
