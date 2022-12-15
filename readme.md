# CS611-3

## Legends of Heroes and Monsters

## Files

---------------------------------------------------------------------------

Armour.java
This class represents an armour in the game which can be equipped by the hero and increases the defence of the hero so that the attack to the monster is reduced. It extends the Item class and implements the equitable interface.

Battleground.java
This is the class where the actually battle between the heroes and the monsters take place.

BattleObserver.java
Observer abstract class which is extended in class which care used to perform certain actions at the end of each battle or each attack of the hero.

Blacksmith.java
Class that defines the operations to be performed at the blacksmith workshop like repair items.

Board.java
This contains the logic for getting the size of the board and print the board. The board size cannot be less than 8, and it cannot be greater than 30.

Cell.java
This class describes a single unit of which the board is composed of. For different board games we can have different Board Units with their own specifications and properties.

CellBush.java
A concrete class that extends the cell class and represent a Bush space on the board which increase the defence of both heroes and monsters.

CellCave.java
A concrete class that extends the cell class and represent a cave space on the board which increase the agility of hero and doge chance of monsters.

CellHero.java
A concrete class that extends the cell class and represent a cell which is occupied by a hero on the board.

CelHeroNexus.java
A concrete class that extends the cell class and represent the hero nexus space on the board.

CellInaccessible.java
A concrete class that extends the cell class and represent an inaccessible space on the board.

CellKoulou.java
A concrete class that extends the cell class and represent a koulou space on the board which increases the strength of the heroes and attack of the monsters.

CellMonster.java
A concrete class that extends the cell class and represent a cell which is occupied by a monster on the board.

CellMonsterNexus.java
A concrete class that extends the cell class and represent the monster nexus space on the board.

CellPlain.java
A concrete class that extends the cell class and represent a plain space on the board.

Combatible.java
An interface implemented by classes which represents objects that can participate in a battle like heroes and monsters.

PrintColor.java
Class containing all the colours that can be used in the game.

Consumable.java
Represents an interface to be implemented in items that are consumable (removed once used) like potions and spells.

Creature.java
Abstract Creature class that represents creatures in the game. It is extended by hero and monster class.

CreatureFactory.java
Interface that is implemented by any factory which produces creatures like MonsterFactory.

DataPool.java
Class to read the data from the csv files and create pools of various objects read from the file.

DurabilityDeductionArmour.java
Class that extends battle observer and performs an update of durability of the armour after each time hero is attacked.

DurabilityDeductionWeapon.java
Class that extends battle observer and performs an update of durability of the weapon after each attack.

Equipable.java
Interface implemented by items that can be equipped by the hero like armour and weapons.

Game.java
This is an interface that describes the basic methods that every game should have.

GameController.java
Singleton class that is used to start and play the game.

GameLHM.java
Class responsible for initializing the game and start the game play.

GameRoom.java
This class is responsible for creating the objects of all the games in the program and initializing the players and creating the teams of these players which will play the game selected later in the program.

Hero.java
Class that handles all the operations related to a hero like combat, equip item, consume potion, cast spell etc.

HeroPaladin.java
Class that extends the hero abstract class and defines the functionality specific to Hero of type Paladin

HeroSorcerer.java
Class that extends the hero abstract class and defines the functionality specific to Hero of type Sorcerer

HeroWarrior.java
Class that extends the hero abstract class and defines the functionality specific to Hero of type Warrior

Item.java
Item abstract class contains all the common attributes shared by all the items. It implements a tradable interface which enables the trade functionality (buy and sell) in all the items.

ItemFactory.java
Interface that enables creation of items to be displayed in the market for buying by the hero.

ItemFactory1.java
This class defines a factory that produces items of level 1 only.

ItemFactory2.java
This class defines a factory that produces items of level 2 only.

ItemFactory3.java
This class defines a factory that produces items of level 3 only.

ItemFactory4.java
This class defines a factory that produces items of level 4 only.

ItemFactory5.java
This class defines a factory that produces items of level 5 only.

ItemFactory6.java
This class defines a factory that produces items of level 6 only.

ItemFactory7.java
This class defines a factory that produces items of level 7 only.

ItemFactory8.java
This class defines a factory that produces items of level 8 only.

ItemFactory9.java
This class defines a factory that produces items of level 9 only.

ItemFactory10.java
This class defines a factory that produces items of level 10 and above.

LevelUp.java
Class that extends battle observer and performs an update of attributes whenever a player levels up.

Market.java
Class that defines the operations to be performed in the market like buy and sell items etc.

Menu.java
This is the class responsible for Menu handling. It is also responsible for calling the initialisePlayers and initialiseTeams methods of the GameRoom class. It also sets up the game for by calling initialiseGame() method of the games and initiate the game play.

Monster.java
Monster class responsible for handling the attack of monsters

MonsterFactory.java
Class responsible for producing monsters of different levels to fight with the heroes according to the hero's level.

Player.java
This class describes the attributes of a player like 'First Name' and 'User Name'.

PlayerLevelComparator.java
This is a class which implements Comparator<Player> which takes players as input and sort them on the basis of the level of the players and create monster according to the player of highest level.

Potion.java
Class that extends item abstract class and used to represent the potion object. It implements the consumable interface as a potion can only be used once.

Repairable.java
Interface that is implemented by items which have wear and tear during the battle and requires repairing.

Rewards.java
this class extends the battle observer abstract class and this is used to provide various rewards/upgrades to players after the battle.

SelectPlayer.java
Class used to get input from the user to select a player for some specific operation like buying or selling in market.

Spell.java
Abstract class that extends item abstract class and used to represent the spells object. It implements the consumable interface as a spell can only be used once.

SpellFire.java
Class extends spell class and implements functionality specific to Fire type of spell.

SpellIce.java
Class extends spell class and implements functionality specific to Ice type of spell.

SpellLightening.java
Class extends spell class and implements functionality specific to Lightening type of spell.

StaticData.java
This class contains all the static data in the game. From here we can control all sorts of values and enums.
For example - the probablity of generating different type of cells in the game and strength of attack and defense of heroes and monsters.
Upgradation percentages after each battle and upgradation percentages after level up etc. It also contains names of monsters, armour, weapon and spells to use when create random monsters and items in their respective factories.

Repairable.java
Interface that is implemented by items which have wear and tear during the battle and requires repairing.

Tradable.java
Interface which is implemented by items that are tradable (bought or sold) in the market.

Validator.java
Class that defines various validators in the game.

Weapon.java
This class represents a weapon in the game which can be equipped by the hero and increases the strength of the hero so that his attack is increased. It extends the Item class and implements the equitable interface.


## Notes

---------------------------------------------------------------------------

1. Files to be parsed are in csv folder and there are files in asciiart folder which are printed during the game. The file read operations are relative to the src folder, so the location of the folders will not be a problem as long as they stay with the src folder

2. Bonus Done.
   a) Implemented all the requirements of the game as stated in the document along with the added functionality of the blacksmith. Every time a hero uses his weapon then his weapons durability gets reduced and he can go to the blacksmith to repair it. If he does not repair it then at 0 durability the weapon will be removed from the inventory.
   b) I have added an observer to increase the experience of the hero with every attack instead of at the end of the battle because it is fair to give the hero experience for his attacks in case he faints just before the last attack in which monster dies.
   c) I have added the defence to the hero's characteristics because when he wears an armour his defense should increase and the value of monsters attack will reduce while attacking that hero.
   Board is coloured with different colours representing different cells like for cave-yellow coloured cells, white for plain cells etc.
   d) I have used strategy pattern for heroes, cell spaces and spells.
   e) I have used observer pattern for level up, rewards after every battle, reducing the durability of weapon and armour after every attack.
   f) I have used a singleton class for gamecontroller.
   g) I have used factory pattern for creating monsters when they are encountered in the forest. There is a 50% probability that  a monster from the given monster pool is selected and 50% chances are that I will create a random monster for the battle.
   h) I have used factory pattern for creating items in the inventory. A hero when enters a market will see the items of his own level only. There are 10 factories which creates items of a particular level depending on the level of the hero. Again there is 50% probability that the given item from the item pool is selected and 50% probability that a random weapon will be generated for the market.
   i) I have added colorful output


## How to compile and run

---------------------------------------------------------------------------

1. Navigate to the directory "pa4/src" after unzipping the files
2. Run the following instructions:
   javac Main.java
   java Main