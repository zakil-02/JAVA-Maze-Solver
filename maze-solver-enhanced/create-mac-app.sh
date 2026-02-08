#!/bin/bash
# Script to create a macOS application bundle for Maze Solver

echo "🎮 Creating Maze Solver macOS Application..."

# Set Java path
export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"

# Build the project first
echo "📦 Building project..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed!"
    exit 1
fi

echo "✅ Build successful!"

# Create the macOS app bundle
echo "🖥️  Creating macOS application..."

jpackage \
    --input target \
    --name "Maze Solver" \
    --main-jar maze-solver-2.0.0.jar \
    --main-class com.zakaria.mazesolver.ui.MazeSolverApp \
    --type app-image \
    --app-version 2.0.0 \
    --vendor "Zakaria Akil" \
    --description "Modern hexagonal maze solver with Dijkstra's algorithm" \
    --java-options '-Xmx512m' \
    --dest .

if [ $? -eq 0 ]; then
    echo "✅ Application created successfully!"
    echo "📍 Location: $(pwd)/Maze Solver.app"
    echo ""
    echo "To install:"
    echo "  1. Drag 'Maze Solver.app' to your Applications folder"
    echo "  2. Or double-click to run directly"
    echo ""
    echo "Note: On first run, you may need to:"
    echo "  - Right-click → Open (if macOS blocks it)"
    echo "  - Or go to System Settings → Privacy & Security → Allow"
else
    echo "❌ Failed to create application"
    exit 1
fi
