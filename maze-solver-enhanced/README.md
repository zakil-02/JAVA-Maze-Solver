# 🎮 Maze Solver - Modern Edition

A beautiful, modern hexagonal maze solver with an intuitive UI, built with Java 21 and FlatLaf.

![Version](https://img.shields.io/badge/version-2.0.0-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

## ✨ Features

- 🎨 **Modern Dark/Light UI** - Beautiful FlatLaf theme with smooth animations
- 🔷 **Hexagonal Grid** - Unique hexagonal maze layout
- 🧠 **Dijkstra's Algorithm** - Find the shortest path efficiently
- 🖱️ **Interactive Drawing** - Click and drag to create your maze
- 📊 **Real-time Statistics** - See path distance, nodes explored, and execution time
- 💾 **Import/Export** - Save and load mazes from files
- ⌨️ **Keyboard Shortcuts** - Quick access to common actions
- 🎯 **Hover Effects** - Visual feedback when hovering over cells

## 🚀 Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Build & Run

```bash
# Build the project
mvn clean package

# Run the application
java -jar target/maze-solver-2.0.0.jar

# Or use Maven
mvn exec:java
```

## 🎮 How to Use

1. **Set Maze Dimensions**
   - Adjust width and height (5-30)
   - Click "Apply" to create new maze

2. **Draw Your Maze**
   - Select a tool: Start (🟩), End (🟥), Wall (⬛), or Empty (⬜)
   - Click on hexagons to place them
   - Drag to draw multiple cells

3. **Find the Path**
   - Click "🎯 Find Shortest Path"
   - Watch the algorithm find the optimal route
   - View statistics in the status bar

4. **Save & Load**
   - File → Export Maze to save
   - File → Import Maze to load

## ⌨️ Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `Ctrl+O` | Import maze |
| `Ctrl+S` | Export maze |
| `Ctrl+L` | Clear solution |
| `Ctrl+R` | Reset maze |
| `F1` | Show help |
| `Ctrl+Q` | Exit |

## 🏗️ Project Structure

```
maze-solver-enhanced/
├── src/main/java/com/zakaria/mazesolver/
│   ├── algorithm/          # Path finding algorithms
│   │   ├── DijkstraPathFinder.java
│   │   ├── PathFinder.java
│   │   └── ...
│   ├── model/              # Data models
│   │   ├── Maze.java
│   │   ├── MazeBox.java
│   │   └── BoxType.java
│   └── ui/                 # User interface
│       ├── MazeSolverApp.java
│       ├── MainFrame.java
│       ├── MazePanel.java
│       └── ...
├── pom.xml                 # Maven configuration
└── README.md
```

## 🎨 Technologies

- **Java 21** - Modern Java with records and pattern matching
- **FlatLaf 3.5.4** - Modern Swing look and feel
- **MigLayout** - Powerful layout manager
- **Maven** - Build and dependency management

## 📦 Dependencies

- `com.formdev:flatlaf` - Modern UI theme
- `com.formdev:flatlaf-extras` - Additional UI components
- `com.formdev:flatlaf-intellij-themes` - IntelliJ themes
- `com.miglayout:miglayout-swing` - Layout manager

## 🔧 Development

### Build Commands

```bash
# Compile
mvn compile

# Run tests
mvn test

# Package JAR
mvn package

# Clean build
mvn clean install
```

### IDE Setup

Import as Maven project in your favorite IDE:
- IntelliJ IDEA
- Eclipse
- VS Code with Java extensions

## 🎯 Algorithm Details

The application uses **Dijkstra's shortest path algorithm**:

- **Time Complexity**: O((V + E) log V)
- **Space Complexity**: O(V)
- **Optimal**: Always finds the shortest path
- **Complete**: Finds a path if one exists

## 🌟 Improvements from Original

### Code Quality
- ✅ Modern Java 21 features (records, pattern matching)
- ✅ Proper package structure
- ✅ Maven build system
- ✅ Better naming conventions
- ✅ Comprehensive documentation

### UI/UX
- ✅ Modern FlatLaf dark/light themes
- ✅ Smooth animations and transitions
- ✅ Hover effects and visual feedback
- ✅ Better color scheme
- ✅ Intuitive controls
- ✅ Keyboard shortcuts
- ✅ Status bar with real-time info

### Features
- ✅ Theme toggle (dark/light)
- ✅ Performance statistics
- ✅ Better error handling
- ✅ Improved file I/O
- ✅ Help system

## 📝 License

MIT License - feel free to use and modify!

## 👤 Author

**Zakaria AKIL**
- GitHub: [@zakil-02](https://github.com/zakil-02)
- Email: zakaria.akil.2002@gmail.com

## 🙏 Acknowledgments

- Original project concept and algorithm implementation
- FlatLaf for the amazing UI framework
- Java Swing community

---

**Enjoy solving mazes! 🎮**
