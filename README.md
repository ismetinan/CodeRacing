# CodeRacing 🏎️💨

> **Multiplayer Code-Questions based racing game**

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-23-007396?style=for-the-badge&logo=java&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![Gson](https://img.shields.io/badge/Gson-Serialization-46BC99?style=for-the-badge)

## 🏁 Overview

**CodeRacing** is a high-octane multiplayer game where coding skills meet racing thrills! 
Compete against friends in real-time, solving algorithmic challenges to fuel your car and speed past the competition. 
Customize your ride, climb the global leaderboards, and prove you're the fastest coder on the track.

![Game Logo](GUI_JavaFX/src/Images/GameLogo.png)

## ✨ Key Features

- **🚦 Real-Time Multiplayer**: Race head-to-head with friends in synchronized code battles.
- **🧠 Coding Challenges**: Solve Java programming puzzles to boost your speed.
- **🏎️ Garage & Customization**: Choose from a variety of cars and colors to match your style.
- **🏆 Global Leaderboards**: Track your rankings and compete for the top spot.
- **💾 Profile System**: Save your progress, stats, and preferences.

## 🛠️ Built With

- **Language**: Java 17+
- **UI Framework**: JavaFX
- **Database**: SQLite (`coderacers.db`)
- **Serialization**: Google Gson

## 🚀 Getting Started

### Prerequisites

- **Java JDK 17** or higher.
- **JavaFX SDK** (Included in `javafx-sdk-23.0.1` folder).
- **Libraries**: `gson-2.8.2.jar`, `sqlite-jdbc-*.jar` (Included in `GUI_JavaFX/libs`).

### ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/ismetinan/CodeRacing.git
   cd CodeRacing
   ```

2. **Configure the Server**
   > ⚠️ **Important:** You must update the server IP before running!
   
   - Open `GUI_JavaFX/src/controller/Server.java`.
   - Locate line 24: `InetAddress.getByName("139.179.223.52")`.
   - Change `"139.179.223.52"` to your local machine's IPv4 address or `localhost`.

### 🎮 How to Run

#### Option 1: Using an IDE (Recommended)
1. Open the project in **IntelliJ IDEA** or **Eclipse**.
2. Add the `GUI_JavaFX/libs` jars to your project libraries / module path.
3. Add the `javafx-sdk-23.0.1/lib` to your VM options if needed.
4. Run `GUI_JavaFX/src/controller/Server.java` first.
5. Run `GUI_JavaFX/src/guicoderacers/CodeRacersGUI.java` to start the game client.

#### Option 2: Command Line
*Note: Ensure you are in the project root directory.*

**1. Compile the sources:**
```powershell
javac --module-path "javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml,javafx.media -cp "GUI_JavaFX/libs/*;GUI_JavaFX/src" -d bin GUI_JavaFX/src/controller/*.java GUI_JavaFX/src/guicoderacers/*.java GUI_JavaFX/src/questions/*.java
```

**2. Start the Server:**
```powershell
java -cp "bin;GUI_JavaFX/libs/*" controller.Server
```

**3. Start the Client:**
```powershell
java --module-path "javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml,javafx.media -cp "bin;GUI_JavaFX/libs/*" guicoderacers.CodeRacersGUI
```

## 📂 Project Structure

```
CodeRacing/
├── GUI_JavaFX/
│   ├── src/
│   │   ├── controller/      # Server and Database logic
│   │   ├── guicoderacers/   # Main Game UI & Screens
│   │   ├── questions/       # Question generation logic
│   │   └── Images/          # Game assets
│   └── libs/                # Dependencies (Gson, SQLite)
├── javafx-sdk-23.0.1/       # Local JavaFX SDK
└── README.md
```

---
*Ready to race? Start your engines!* 🏎️
