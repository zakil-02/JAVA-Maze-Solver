# Maze Solver - Setup & Development Guide

## 🚀 Quick Start

### Prerequisites
- macOS with Homebrew installed
- Java 21 (OpenJDK)

### Installation

1. **Install Java 21** (if not already installed):
```bash
brew install openjdk@21
```

2. **Configure Java in your PATH**:
```bash
echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

3. **Verify Installation**:
```bash
java -version
# Should show: openjdk version "21.0.10"
```

### Building the Project

Navigate to the `tp04` directory and compile:

```bash
cd tp04
./compile.sh
```

Or manually:
```bash
export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"
find src -name "*.java" -type f > sources.txt
javac -d bin @sources.txt
```

### Running the Application

**GUI Application** (Recommended):
```bash
./run-gui.sh
```

Or manually:
```bash
java -cp bin graphicInterface.MainApp
```

**Console Test**:
```bash
java -cp bin mainPackage.Main
```

**File I/O Test**:
```bash
java -cp bin mainPackage.MainTest
```

## 📁 Project Structure

```
tp04/
├── src/
│   ├── graphicInterface/    # GUI components (Swing)
│   ├── Labyrinthe/          # Maze model and box types
│   ├── graph/               # Graph algorithms (Dijkstra)
│   └── mainPackage/         # Main entry points
├── bin/                     # Compiled classes
├── data/                    # Sample maze files
├── processingData/          # Runtime maze data
├── compile.sh              # Build script
└── run-gui.sh              # Run GUI script
```

## 🎮 How to Use

1. **Launch the GUI**: Run `./run-gui.sh`
2. **Create a Maze**:
   - Set dimensions in the configuration panel
   - Click boxes to set types (Departure, Arrival, Wall, Empty)
   - Or import from a text file (File → Import)
3. **Solve**: Click "Shortest Path" button
4. **Save**: File → Save to export the solution

## 🔧 Development Environment

- **Java Version**: 21 (backward compatible with Java 18)
- **Build Tool**: javac (manual compilation)
- **GUI Framework**: Java Swing
- **Algorithm**: Dijkstra's shortest path

## 📝 Notes

- The project was originally built with Java 18 but has been updated to Java 21
- Uses Eclipse project structure (.classpath, .project)
- Hexagonal maze implementation with custom graph algorithms
