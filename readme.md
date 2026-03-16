# Chess Console – v1.0.0

## Description

Chess Console is a base implementation of a chess game developed in **Java** that runs entirely in a **console environment**.  
The project implements the **fundamental logic of chess**, including board management, pieces, and the main rules of the game.

This version (**v1.0.0**) represents a **functional base of the game engine**, intended as a starting point for future expansions such as a browser-based game.

## Features

- Functional chess game in the console
- Implementation of the board, the pieces, and their rules
- Turn system between players
- Move validation according to chess rules
- Modular and object-oriented architecture to facilitate future expansions

## Project Status

Initial stable version that includes the main game logic.  
The project is prepared to evolve with new features.

## Requirements

- **Java 17** or higher (recommended)
- Terminal or console to run the application

The application uses **Unicode chess characters** to represent the pieces on the board (♔ ♕ ♖ ♗ ♘ ♙).

For this reason, it is necessary to run the program in a **terminal that correctly supports Unicode**.

## Usage

```
git clone https://github.com/SoyAlguien0/Chess
cd ./dist
java -jar Chess-{version}.jar
```