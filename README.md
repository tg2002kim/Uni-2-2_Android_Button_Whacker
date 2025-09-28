Dankook University, 2nd Year 2nd Semester (Fall 2024)

# Button Whacker (2024)

**Mobile Programming Term Project – Android Studio (Java)**  
Department of Mobile Systems Engineering, Dankook University  
Second-year (Sophomore), 2nd Semester (Fall 2024)

Button Whacker is a casual mobile game where players tap randomly activated buttons within a 5x5 grid under time constraints. The project was designed to practice key Android development concepts such as real-time interaction, scoring systems, UI feedback, and activity transitions.

---

## 🕹️ Game Overview

- **Gameplay**: Tap the yellow-highlighted button before the time runs out.
- **Scoring**: Starts at 100. +10 points for a successful hit, -10 for a miss or timeout.
- **Game Over**: Triggered when the score reaches 0 or the player fails to tap in time.
- **Difficulty Levels**:
  - Easy: 2 seconds
  - Normal: 1 second
  - Hard: 0.5 seconds

---

## 🎮 Key Features

| Feature             | Description |
|---------------------|-------------|
| 5x5 Button Grid     | Touch-enabled grid using GridLayout |
| Real-time Scoring   | Dynamic score updates via TextView |
| Difficulty Selection| PopupMenu on the main screen |
| Game Over Handling  | Triggers GameOverActivity on failure |
| Color Feedback      | Buttons change color based on result (Yellow / Blue / Red) |
| Restart/Exit Options| Available on the game over screen |

---

## 🧩 App Structure

- `MainActivity`: Handles difficulty selection and game start
- `GameActivity`: Manages button logic, scoring, and timing
- `GameOverActivity`: Displays final score and handles restart/exit actions

---

## 🛠️ Tech Stack

- **Language**: Java
- **IDE**: Android Studio (ver. 2024.1.2)
- **API Level**: 30 (Android 11)
- **Layout**: XML with GridLayout
- **Threading**: Asynchronous UI control using `Handler`

---

## 🧪 Test Scenarios

- Main Screen: Set difficulty → Start game → Reach game over
- Game Screen: Verify color changes, score tracking, and time limits
- Game Over: Confirm score display and navigation to restart/main screen

---
 
## 📄 Documentation

Project documentation is included in the `docs/` directory:

- `mobile_app_plan.pdf` (original: 모바일프로그램_앱개발_기획서.pdf)
- `mobile_app_report.pdf` (original: 모바일프로그램_앱개발_보고서.pdf)

---

## 📸 Screenshots (Optional)

Place app screenshots in the `screenshots/` directory:
- Main Menu
- Gameplay Screen
- Game Over Screen

---

## 💬 Reflection

Throughout this project, I experienced various challenges including emulator issues, UI responsiveness, and difficulty timing. However, building a complete mobile application from scratch greatly enhanced my understanding of Android development fundamentals.

Future improvements may include a leaderboard system, design theming, and additional gameplay features.

---

## 👨‍💻 Developer

- **Taekyung Kim** (Dept. of Mobile Systems Engineering, Dankook University)  
  Student ID: 32211203
