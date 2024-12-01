# Static Factory

## Overview
Managing a group with 4 team members using Object-Oriented Principles and Design Patterns to design
and create a modular and extensible text-based roguelike game using an engine.

## Workflow
Features were first planned and designed using UML diagrams and then implemented with Java. The 
design rationale explains the design choices of each feature implemented and justified with the 
principles and design patterns. Interaction diagrams were created to show the flow of complex 
features. Code and documents were regularly reviewed by members and committed to the GitLab 
repository.

The design documents can be found in `docs/design` separated into stages, where each stage
consists of 4 implemented features. Each member was responsible for implementing one feature per
stage along with the design documents on a separate branch. The final implementation was then merged
into the main branch. 

## Features
- Movable player that can perform a list of actions based on their inventory and surroundings, which
  can be selected with a key through the console
- Walls and trees that block entities from moving through, with floors preventing certain enemies 
  from entering
- Puddles that can be drank from to heal the player
- Trees that can grow through multiple stages and produce fruit over time
- Different types of enemy spawners to spawn different enemies over time
- Enemies that can be attacked by the player
  - Huntsman Spider that can attack the player and wander around
  - Alien Bug which follows the player and steals items off the ground, which can be killed to
    retrieve the items
  - Suspicious Astronaut which instantly kills the player if they are within range
- Items that can be picked up, dropped and used by the player
  - Consumable Items such as fruits and pots of gold that can heal, damage or grant credits
  - Weapons with different damage and accuracy to fight enemies
  - Theseus, a portable teleporter that can be placed down to randomly teleport between different 
    locations on the same map
  - Astley, a device which provides monologue options to the player while deducting credits over time as
    a subscription service
- Computer Terminal to purchase items and travel between maps
- Humanoid Figure to sell items to and receive credits

## Instructions
1. Clone the repository 
2. Run `src/game/Application.java` to start the game
3. Pick actions from the selection menu by entering the associated key into the console
