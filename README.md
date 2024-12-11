# COMP2042 COURSEWORK

--------------------------------------------------------------------------------

## Details :raising_hand_man:

--------------------------------------------------------------------------------
<ins>**Code by: Khoo Chung Ling 20208524**</ins>

2024 coursework for module COMP2042 Developing Maintainable Software

## Folder Structure :open_file_folder:

--------------------------------------------------------------------------------

### GitHub Repository Link
[COMP2042 - Coursework Repository](https://github.com/RiseUpReiz/COMP2042)

This repository contains the coursework project for the COMP2042 module, focused on maintaining and extending a Java-based plane fighter game.

## Pre-requisites

--------------------------------------------------------------------------------
- Java JDK (21 and above)
- JavaFX SDK (21 and above)
- Machine equipped with Windows 10 and above


## Compilation Instructions

--------------------------------------------------------------------------------
1. Clone the project repository to local device. ---  https://github.com/RiseUpReiz/COMP2042
2. Set up javaFX
3. Import the Project into IntelliJ IDEA or other IDE
4. Compile and Run the Project


# Features

--------------------------------------------------------------------------------
## Implemented and Working Properly

--------------------------------------------------------------------------------
###  Main Menu
- The initial screen of the game where the player can start the game, view instructions, or exit.
- This allows the player to choose what they want to do instead of being forced into the game.

###  Instructions
- A screen that displays the game's controls and objectives.
- Improves user experience by providing information on how to play the game.
### Level Two 
- The second level of the game, which includes new challenges and enemies.
- Induces a sense of challenge into the player to pique interest and keep them engaged.

###  Advanced planes
- New planes with stronger abilities and appearances.
- An addition of LevelTwo.java to implement new planes

### Level Endless 
- A game mode where the player can continue playing indefinitely, facing increasingly difficult enemies.
- Provides a challenge for players who have completed the game and want to continue playing.

###  Endless Instructions
- A screen that displays the objectives of the endless mode.
- Improves user experience by providing information on how to play EndlessMode.

###  High Score 
- A feature that tracks and displays the highest score achieved by the player.
- Induces a sense of challenge and competition among players.

###  Pause 
- A menu that allows the player to pause the game and access other options.
- Improves user experience by providing a way to pause the game and take a breather.

###  Music 
- Background music that plays throughout the game.

###  Game Over Screen 
- A screen that displays the player's score (IF in Endless Mode) and allows them to return to the main menu.
- Improves user experience by providing feedback on the player's performance and allowing them to exit to main menu instead of being forced to close the application.

###  Win Screen 
- A screen that appears when the player completes the game,and options to restart or exit.
- Gives the user a sense of euphoria for completing the game

###  Kill counter 
- A feature that tracks the number of enemies the player has destroyed.
- Improves user experience by allowing the player to track their progress and performance.

###  Destroy projectiles when out of bounds 
- A feature that removes projectiles from the game when they leave the screen.
- Keeps the game clean and prevents memory leaks.

## Implemented but Not Working Properly

--------------------------------------------------------------------------------
### Timer in Endless Mode
- Timer was created in Endless Mode but regardless of attempts to display the timer on screen, it does not show up.
- Due to time constraints, the feature was not fully implemented and fixed.

## Features Not Implemented

--------------------------------------------------------------------------------
### Using JavaFX own timeline for main instead of hard code
- Currently, the project runs at 1000/50 (millisecondDelay) which is 20 FPS. This is implemented into the timeline and is hardcoded into the LevelParent.java class. When memory is used up and/or too much resources are loaded and not destroyed, the game will slow down.

### Refactoring of LevelParent.java
- The LevelParent.java class was too long and had too many responsibilities, making it difficult to maintain and extend. Attempts to refactor responsibilities into other classes have failed. Actor logic, Timeline logic and Event logic were attempted to be refactored into separate classes but failed to communicate with other classes properly.

--------------------------------------------------------------------------------
# Java Classes

--------------------------------------------------------------------------------
## New Java Classes


| New Classes                | Description                                        | Package                                                                                  |
|----------------------------|----------------------------------------------------|------------------------------------------------------------------------------------------|
| `LevelEndless.java`        | The Endless mode of the game                       | [com.example.demo.levels](src/main/java/com/example/demo/levels/LevelEndless.java)       |
| `LevelViewEndless.java`    | The view of the Endless Mode                       | [com.example.demo.levels](src/main/java/com/example/demo/levels/LevelViewEndless.java)   |
| `LevelTwo.java`            | The second level of the game                       | [com.example.demo.levels](src/main/java/com/example/demo/levels/LevelTwo.java)           |
| `AdvancedPlane.java`       | New planes with stronger abilities and appearances | [com.example.demo.planes](src/main/java/com/example/demo/planes/AdvancedPlane.java)      |
| `HighScoreManager.java`    | Manages the high score of the game                 | [com.example.demo.manager](src/main/java/com/example/demo/manager/HighScoreManager.java) |
| `MusicManager.java`        | Manages the background music of the game           | [com.example.demo.manager](src/main/java/com/example/demo/manager/MusicManager.java)     |
| `PauseManager.java`        | Manages the pause menu of the game                 | [com.example.demo.manager](src/main/java/com/example/demo/manager/PauseManager.java)     |
| `MainMenu.java`            | The main menu of the game                          | [com.example.demo.menu](src/main/java/com/example/demo/menu/MainMenu.java)               |
| `EndlessInstructions.java` | Instructions for Endless Mode                      | [com.example.demo.menu](src/main/java/com/example/demo/menu/MenuEndlessInstruction.java) |
| `MenuGameOver.java`        | The game over screen of the game                   | [com.example.demo.menu](src/main/java/com/example/demo/menu/MenuGameOver.java)           |
| `MenuInstructions.java`    | The instructions screen of the game                | [com.example.demo.menu](src/main/java/com/example/demo/menu/MenuInstructions.java)       |
| `MenuPause.java`           | The pause menu of the game                         | [com.example.demo.menu](src/main/java/com/example/demo/menu/MenuPause.java)              |
| `MenuWin.java`             | The win screen of the game                         | [com.example.demo.menu](src/main/java/com/example/demo/menu/MenuWin.java)                |

## Deleted Java Classes
- `GameOverImage.java` - Deleted as it was not used in the project.
- `WinImage.java` - Deleted as it was not used in the project.

## Modified Java Classes
All java classes added or clarified method and field comments for better code readability and maintainability.

### ActiveActor.java
- Standardized the method of calling image for better maintainability.
- Updated the setImage logic to dynamically resolve the image path using getClass().getResource.
- Refactored to package 'controller' to better organize the classes.

### ActiveActorDestructible.java
- Introduced the isDestroyed field and associated setDestroyed and isDestroyed methods for tracking and managing the destruction of actors.
- Implemented destroy() method to standardize the destruction process.
- Added an abstract updateActor method to enforce consistency in updating actor states across other classes.
- Refactored to package 'controller' to better organize the classes.

### Controller.java
- Added the startEndlessMode() method to initiate the Endless Mode.
- Introduced the showEndlessInstructions() method to display the Endless Mode instructions.
- Added the backToMainMenu() method to return to the main menu, improving navigation between game states.
- Modified the goToLevel() method to support dynamic instantiation of levels using reflection, allowing more flexibility in level management.
- Updated the update() method to handle transitions based on observable state changes, improving responsiveness to game events.
- Refactored to package 'controller' to better organize the classes.

### Main.java
- Modified start() to show MainMenu.java instead of going straight to LevelOne.java.
- Added integration with MusicManager to play background music (bgm.mp3) during the game's main menu, enhancing the user experience.
- Configured the stage's dimensions and title in the start() method to provide a standardized window setup.
- Refactored to package 'controller' to better organize the classes.

### HeartDisplay.java and ShieldImage.java
- Refactored to package 'displays' to better organize the classes.

### LevelTwo.java(LevelBoss.java)
- Renamed the file and class from LevelTwo to LevelBoss for clarity and accurate representation of its purpose.
- Updated 'BACKGROUND_IMAGE_NAME' to use background4.jfif.
- Refactored to package 'levels' to better organize the classes.

### LevelOne.java
- Updated BACKGROUND_IMAGE_NAME to use background5.jfif as the background image.
- Adjusted spawnEnemyUnits to calculate and spawn enemies dynamically based on Y_UPPER_BOUND and Y_LOWER_BOUND.
- Added a kill counter to keep track of the number of enemies destroyed by the player.
- Refactored to package 'levels' to better organize the classes.

### LevelParent.java
- Added highScoreManager Field: Introduced HighScoreManager integration for managing high scores in game-over scenarios.
- Modified loseGame Method: Integrated HighScoreManager to enable high score tracking and pass it to the MenuGameOver class.
- Added ESC functionality to call MenuPause.
- Disable spacebar functionality when the game is paused.
- Updated lose() method to handle game-over scenarios and display the game-over screen.
- Updated win() method to handle win scenarios and display the win screen.
- Refactored to package 'levels' to better organize the classes.

### LevelView.java
- Enhanced Pause Menu Display: Added functionality to show and hide the pause menu using MenuPause. The pause menu is brought to the front when displayed.
- Modified the updateKillCounter method to update the display with the current kills and target kills. It ensures the kill counter is added to the screen if not already present.
- Refactored to package 'levels' to better organize the classes.

### LevelViewTwo.java(LevelViewBoss.java)
- Renamed the file and class from LevelViewTwo to LevelViewBoss for clarity and accurate representation of its purpose.
- Refactored to package 'levels' to better organize the classes.

### Boss.java
- Modified Y_UPPER_BOUND and Y_LOWER_BOUND to adjust boss positions to keep them in screen.
- SHIELD_X_OFFSET: Offset for shield positioning to keep shield infront of plane.
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Modified PROJECTILE_Y_OFFSET to ensure projectile spawn at center of boss.
- Refactored to package 'planes' to better organize the classes.

### EnemyPlane.java
- Modified PROJECTILE_Y_OFFSET to ensure projectile spawn at center of plane.
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Refactored to package 'planes' to better organize the classes.

### UserPlane.java
- Adjust Y_UPPER_BOUND and Y_LOWER_BOUND to keep the plane within the screen.
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Refactored to package 'planes' to better organize the classes.

### BossProjectile.java
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Refactored to package 'projectiles' to better organize the classes.

### EnemyProjectile.java
- Added a destroy method to destroy projectiles out of screen.
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Refactored to package 'projectiles' to better organize the classes.

### Projectile.java
- Added a destroy method to destroy projectiles out of screen.
- Refactored to package 'projectiles' to better organize the classes.

### UserProjectile.java
- Added a destroy method to destroy projectiles out of screen.
- Modified IMAGE_HEIGHT to ensure accurate hitbox detection.
- Refactored to package 'projectiles' to better organize the classes.


## Unexpected Problems

--------------------------------------------------------------------------------
### Screen switching bug
Problem: When in main menu and the user clicks into "Endless Mode" or "Instructions" then going back to main menu will cause the screen to be misaligned.

Solution: Attempts to fix the bug by forcing alignment and position centering of screen did not fix the bug. 
Suggestions from ChatGPT said to update JavaFX version hence, I updated JavaFX version to 21.0.2 in pom.xml and in "File > Project Structure > SDKs" changed the JavaFX SDK to 21.0.2.

### User can shoot when timeline is stopped
Problem: When the game is paused, the user can still shoot projectiles.

Solution: Fixed the bug by catching spacebar input when the game is paused, preventing the user from shooting projectiles.
### Refactoring LevelParent.java
Problem: The LevelParent.java class was too long and had too many responsibilities, making it difficult to maintain and extend.

Solution: Attempts to refactor LevelParent.java by splitting responsibilities into separate classes such as "ActorManager, EventHandler and TimelineManager" did not work as intended. The refactored classes were not able to communicate with the other java classes properly, causing the refactoring attempt to fail.

### Hitbox of actors and projectiles
Problem: The hitboxes of actors and projectiles were not accurate, causing collisions to be detected incorrectly.

Solution: Updated the hitboxes of actors and projectiles to be more accurate by cropping and editing their image size and height, ensuring that collisions are detected correctly.

### Attempt to increase game FPS
Problem: The game sometimes lags and runs slowly when too many entities are on screen.

Solution: Attempts to increase the game's FPS by decreasing millisecondDelay in LevelParent.java did not work as intended. The game still lags when too many entities are on screen. Changing the millisecondDelay caused other entities speed to be changed respectively.