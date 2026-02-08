# 🎮 Hexagonal Maze Solver

A modern, interactive hexagonal maze solver with a beautiful UI, built with Java 21 and FlatLaf.

![Version](https://img.shields.io/badge/version-2.0.0-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

**Author:** Zakaria Akil

---

## ✨ Features

- 🎨 **Modern Dark/Light UI** - Beautiful FlatLaf theme with smooth animations
- 🔷 **Hexagonal Grid** - Unique hexagonal maze layout with perfect alignment
- 🧠 **Dijkstra's Algorithm** - Find the shortest path efficiently
- 🖱️ **Interactive Drawing** - Click and drag to create your maze
- 📊 **Real-time Statistics** - See path distance, nodes explored, and execution time
- 💾 **Import/Export** - Save and load mazes from files
- ⌨️ **Keyboard Shortcuts** - Quick access to common actions
- 🎯 **Dynamic Zoom** - Auto-scales based on maze dimensions (5-30)

---

## 🚀 Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Option 1: macOS Desktop App (Recommended for Mac users)

```bash
# Navigate to the enhanced version
cd maze-solver-enhanced

# Create the macOS application
./create-mac-app.sh

# Install the app
# Drag "Maze Solver.app" to your Applications folder
# Or double-click to run directly
```

**Note:** On first run, macOS may block the app. Right-click → Open, or go to System Settings → Privacy & Security → Allow.

### Option 2: Run from JAR

```bash
# Navigate to the enhanced version
cd maze-solver-enhanced

# Build the project
mvn clean package

# Run the application
./run.sh

# Or directly
java -jar target/maze-solver-2.0.0.jar
```

---

## 🎮 How to Use

### Creating a Maze

1. **Set Dimensions**
   - Adjust width and height (5-30)
   - Click "Apply" to create new maze

2. **Draw Your Maze**
   - Select a tool: Start, End, Wall, or Empty
   - Click on hexagons to place them
   - Drag to draw multiple cells

3. **Find the Path**
   - Click "Find Shortest Path"
   - View statistics in the status bar

4. **Save & Load**
   - File → Export Maze to save
   - File → Import Maze to load

### Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `Ctrl+O` | Import maze |
| `Ctrl+S` | Export maze |
| `Ctrl+L` | Clear solution |
| `Ctrl+R` | Reset maze |
| `F1` | Show help |
| `Ctrl+Q` | Exit |

---

## 🏗️ Project Structure

```
JAVA-Maze-Solver/
├── maze-solver-enhanced/          # Modern version (v2.0)
│   ├── src/main/java/com/zakaria/mazesolver/
│   │   ├── algorithm/            # Path finding algorithms
│   │   ├── model/                # Data models
│   │   └── ui/                   # User interface
│   ├── pom.xml                   # Maven configuration
│   ├── run.sh                    # Quick start script
│   └── README.md                 # Detailed documentation
└── tp04/                         # Original version (preserved)
```

---

## 🎨 Technologies

- **Java 21** - Modern Java with records and pattern matching
- **FlatLaf 3.5.4** - Modern Swing look and feel
- **MigLayout** - Powerful layout manager
- **Maven** - Build and dependency management
- **JUnit 5** - Testing framework

---

## 🔧 Building from Source

### Option 1: Create macOS Desktop App

```bash
# Clone the repository
git clone https://github.com/zakil-02/JAVA-Maze-Solver.git
cd JAVA-Maze-Solver/maze-solver-enhanced

# Create the macOS application
./create-mac-app.sh

# The app will be created as "Maze Solver.app"
# Drag it to your Applications folder or double-click to run
```

### Option 2: Build JAR

```bash
# Clone the repository
git clone https://github.com/zakil-02/JAVA-Maze-Solver.git
cd JAVA-Maze-Solver/maze-solver-enhanced

# Build
mvn clean package

# Run
java -jar target/maze-solver-2.0.0.jar
```

---

## 🎯 Algorithm

The application uses **Dijkstra's shortest path algorithm**:

- **Time Complexity**: O((V + E) log V)
- **Space Complexity**: O(V)
- **Optimal**: Always finds the shortest path
- **Complete**: Finds a path if one exists

---

## 🌟 Key Improvements (v2.0)

### Code Quality
- ✅ Modern Java 21 features (records, pattern matching)
- ✅ Professional package structure
- ✅ Maven build system
- ✅ Clean architecture (MVC pattern)

### UI/UX
- ✅ Modern FlatLaf dark/light themes
- ✅ Smooth animations and transitions
- ✅ Hover effects and visual feedback
- ✅ Theme-aware colors
- ✅ Dynamic zoom based on dimensions
- ✅ Perfect hexagonal grid alignment

### Features
- ✅ Theme toggle with animation
- ✅ Performance statistics display
- ✅ Keyboard shortcuts
- ✅ Import/Export functionality
- ✅ Help system

---

## 📝 License

MIT License - feel free to use and modify!

---

## 👤 Author

**Zakaria Akil**
- GitHub: [@zakil-02](https://github.com/zakil-02)
- Email: zakaria.akil.2002@gmail.com

---

## 🙏 Acknowledgments

- FlatLaf for the amazing UI framework
- Java Swing community
- Original algorithm implementation

---

**Enjoy solving mazes! 🎮**
