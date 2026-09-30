# 🌳 Employee Management System: Algorithms & MVC

A console-based Java application for managing a school's employee records, built to apply core algorithms and data structures (recursive merge sort, recursive binary search and a binary tree) inside a clean Model-View-Controller architecture. Built individually for the **Algorithms, Architectures and Design Patterns** module (Year 3, CCT College Dublin).

## Features

The app loads employee records from a CSV file (a sample school with 5 departments and 5 hierarchy levels is included in `School.txt`) and offers a menu:

1. **Sort employees** alphabetically by full name using a recursive **merge sort**
2. **Search for an employee** by full name using a recursive **binary search** (the list is sorted first if needed)
3. **Add an employee** with validated input: names can't be empty, salary can't be negative, the department is chosen from existing ones, the supervisor is chosen from people at a higher level, and a work email is generated automatically
4. **Build a binary tree** of 20 employees ordered by hierarchy level, displayed with a level-order traversal, plus the tree's height and node count
5. **Exit**

## Architecture

The code follows the **MVC** pattern, with an enum-driven menu:

```
src/main/java/org/example/
├── model/        Employee, TreeNode, EmployeeBinaryTree
├── view/         ConsoleView: all input and output, no business logic
├── controller/   Main (loads the file) and EmployeeController (menu actions)
└── utils/        SortingAlgorithms (merge sort), SearchAlgorithms (binary search),
                  FileHandler (CSV reading), MenuOption (menu enum)
```

**Why these algorithms?**
- **Merge sort:** O(n log n) in every case, and stable, so employees with equal names keep their order.
- **Binary search:** O(log n) on sorted data, so about 5 comparisons for 30 employees instead of up to 30 with a linear search. A hash table was not used because the dataset is small and the list also needs to stay sorted for display.

## Tech

Java · Maven · Recursion · Merge sort · Binary search · Binary tree · MVC · Enums · CSV file I/O

## How to run

**You need:** a JDK. The Maven project targets Java 24; any recent JDK can compile it directly.

From the repository's root folder:

```bash
mkdir out
javac -d out src/main/java/org/example/*/*.java
java -cp out org.example.controller.Main
```

When asked for a filename, enter `School.txt`, then choose options from the menu.

## Notes and known limitations

- The binary tree is filled **level by level** from the employees sorted by hierarchy level, so it shows the levels (1 at the top, 5 at the bottom) but a node's children aren't necessarily the people who report to that employee.
- Only the first 20 employees are added to the tree.
- New employees are kept in memory and aren't saved back to the file.
- `Main` and `ConsoleView` each open their own `Scanner` on `System.in`. This works when typing interactively, but piping all input in at once doesn't.
- `School.txt` contains fictional sample data.
