Goals of game:

Build game where there is a clear objective, gameplay is fair, enjoyable for end user
Controls make sense and easy to learn for user
Game mechanics make aren’t confusing
Control feels like second nature
Adding Features:

As long as you follow my conventions you should be fine for most features
If you want to add a new enemy type, make sure to extend GameObject which follows the basic structure
If you want to add more levels, this will be a little more tricky.
Look how I built the boss level and follow this: Remove all undesired game objects from current level and then add the new ones. One way to determine when to level is to look at score. Another is to add a button. Just follow rules of javafx and you should be ok.
To add additional controls, simply map key bindings to new methods. As long as these methods don’t interfere with the ones I wrote (should be clear) there should be no issue. But general steps are, make new method that edits aspects of the player. Map key to method. Profit.
Major Design Choices:

I chose to use images instead of shapes. I felt that shapes were bland and almost cheating while images looked cool and felt more genuine. The only con was that you need to make sure you have the images in the src.
Initially I wanted to spawn asteroids randomly all over the map however I eventually decided to spawn asteroids at the top and have them fall down. The pros of this are that it makes the game more fair, asteroids won’t spawn super close to ship and make it impossible to avoid. It also gives the user a feel that they are navigating an asteroid field and makes the level transition feel less forced. The cons are that I had to redo this scene.
I had to decided whether or not I was going to randomize speeds of spawned asteroids. I eventually went with randomizing because it made the game less predictable and a little more fun to play IMO.
I decided to build an abstract GameObject class in order to make building new objects easier. Pros were it was easier to build objects and the structure was going to be somewhat uniform so adding new ones wouldn’t be arduous for a new user. The only possible con is that objects might be slightly harder to make unique.
The hardest design choice was deciding how to level. Building a new scene crossed my mind but I didn’t want to return a new scene to Main. Instead I chose to update the root by removing objects I didn’t want in the next level and adding the new ones in. The pros are this implementation is easy and seamless. The cons are that the scene is less unique.
The simplifications I made include how I leveled. Each level could be a new scene but I made it one scene where I just edited that once scene. The other simplification I made was with main menu and end screen transitions. I have the user press a button in order to progress into and out of the game. This made the issue of handling these transitions far simpler.