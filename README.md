# SDUI Android Demo

A demonstration of Server-Driven UI implementation in Android using Jetpack Compose and Firebase Realtime Database. This project showcases how to create dynamic UI components that can be controlled and updated from the server side.

## Features

- 🎨 Dynamic UI components (Header, Card, Banner)
- 🔄 Real-time updates from Firebase
- 📱 Modern UI with Jetpack Compose
- 🎯 MVVM Architecture
- 🌓 Material 3 Design

## Tech Stack

- Kotlin
- Jetpack Compose
- Firebase Realtime Database
- Material 3
- ViewModel
- Kotlin Flow

## Project Structure

The project follows a clean architecture approach with the following key components:

- **UI Components**: Modular and reusable Compose components
- **ViewModel**: Manages UI state and business logic
- **Firebase Integration**: Real-time data synchronization
- **Data Models**: Type-safe data classes for UI components

## Getting Started

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 11 or higher
- Android SDK with minimum API level 24

### Setup

1. Clone the repository:
https://github.com/Dhiraj-op/SDUI-Android-Demo

2. Open the project in Android Studio

3. Create a Firebase project and add your `google-services.json` file to the app directory

4. Set up your Firebase Realtime Database with the following structure:

json
{
"featured_content": {
"layout": [
{
"type": "header",
"properties": {
"title": "Your Title",
"textColor": "RED"
}
},
// Add more components as needed
]
}
}

5. Build and run the project

## Firebase Database Structure

The app expects a specific structure in Firebase Realtime Database. Each UI component should have:
- `type`: Identifies the component type (header, card, banner)
- `properties`: Contains component-specific properties

## Supported Components

1. **Header**
   - Properties: title, textColor
   - Customizable text color (RED, BLUE, or default)

2. **Card**
   - Properties: title, description

3. **Banner**
   - Properties: message

## Acknowledgments

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Material 3 Design](https://m3.material.io/)
