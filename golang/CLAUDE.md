# Go Learning TIL - Claude Code Guide

## Project Overview
Self-paced 6-week Go learning program focused on web/API server development. Each week builds on previous concepts with hands-on projects.

## Learning Path
- **Week 1:** Basic syntax and Go idioms
- **Week 2:** Go's unique thinking patterns (interfaces, errors, pointers)
- **Week 3:** Concurrency (goroutines, channels, context)
- **Week 4:** HTTP server with standard library
- **Week 5:** Database integration and testing
- **Week 6:** Production-ready deployment

Reference: `go-learning-checklist-6weeks.md`

## Using This Repository

### Start/Resume Learning
Use the `go-learning-progress` skill to:
- Check current progress
- Get next learning step
- Review checkpoint questions
- Prepare environment for next day

```
/go-learning-progress
```

### Directory Structure
```
week1/day1/    # Current progress location
week1/day2/    # To be created as you progress
...
week6/         # Final project
```

Each day contains:
- `*.go` source files for learning
- Comments explaining concepts
- Links to official resources

### Git Workflow
- Commit after each day's learning
- Include checkpoint questions answered in commit message
- Use descriptive messages: `[golang] 1주차 Day 2 - 슬라이스와 맵`

## Important Conventions

1. **Package declarations** must match directory names
   - `week1/day1/` → `package day1`
   - All files in same directory must have same package

2. **File organization**
   - One concept per file when possible
   - Use `_test.go` for test files
   - Group related functions in one file

3. **Code style**
   - Always run `go fmt` before committing
   - Use `go vet` to catch common mistakes
   - Follow https://go.dev/doc/effective_go for idioms

## Checkpoint Questions

Before moving to next day, answer these:
- Can you explain the concept in your own words?
- Can you write code demonstrating the concept?
- Can you identify when to use this in a real API?

Checkpoint questions for each day are listed in `go-learning-checklist-6weeks.md`.

## Resources

**Official (required)**
- https://go.dev/tour
- https://go.dev/doc/effective_go
- https://pkg.go.dev/std

**Learning supplements**
- https://gobyexample.com
- https://go.dev/blog
- `go doc` command (fastest reference)

**Infrastructure**
- `.gitignore`: Go project standard ignores
- `go.mod`: Module definition (created as needed)

## Next Steps

Run `/go-learning-progress` to see what's next!
