# [5.0 Battles & Biomes] - 9/15/26
## New Features
### Player-Tailored World Generation
- Added the Tailored World Generation system
- During world creation, a "Default" world type can now be customized
- Choosing the "Customize" option allows the player to modify sliders corresponding to world generation factors
    - Current customizable options:
        - Continent Scale
        - Biome Scale
        - Erosion Scale
        - Master World Generation Scale

### Cauldron Stews
- Stews can now be made in cauldrons using their crafting ingredients
- Once an ingredient is added, the cauldron's stew becomes a Light Stew
    - These can also be eaten, though have half the nutrition as a full crafted stew
- One cauldron can hold 3 bowls of stew
- Light Stews made with flowers that would be used to make a suspicious stew do not grant status effects

### Items
- Added Light Stew
- Added Daggers
    - Deals 4 attack damage by default
    - Can deal up to an extra 4 damage depending on how discrete the user is being
    - Can be crafted with one stick and one tool material
    - Can be found as loot in any chest that may give a sword and in most chests that can give spears
- Added Quiver
    - Can hold up to 4 stacks of arrows
    - Automatically cycles through each arrow stack
- Added Dyed Quivers
    - Can be crafted by combining a quiver with any dye
- Added Heavy Shield
    - Has a Shield Strength of 10
    - Has a blocking delay of 5 ticks
    - Can become disabled for 1.6 seconds, instead of 5 seconds

### Combat
- Added Blade Clash
    - When two attackers attack each other simultaneously (within 10 ticks), their weapons may clash, and do no damage to either of them
    - This currently only applies to swords and daggers
- Added Shield Strength
- Changed Iron Grates to have the `dragon_immune` block tag
    - Determines how much damage and knockback a shield can absorb
    - By default, Shields have a Shield Strength of 5
- Changed Shields to no longer have a blocking delay

### Enchantments
- Added Enervation enchantment
    - Can be applied to daggers
    - Applies up to 4 seconds of Weakness to victims at Level III
- Added Cleaving enchantment
    - This is to match the Combat Test Snapshots

### Blocks
- Added Perse Wildflowers
    - Can be crafted into Purple Dye
    - Can be found in Birch Forests, Meadows, Swamps, and the Dappled Forest

      Developer Notes:
        - These are inspired by Forget-Me-Not flowers
        - "Perse" is an archaic term for "blue-ish"
- Added Wood Stairs
- Added Wood Slabs
- Added Stripped Wood Stairs
- Added Stripped Wood Slabs
- Added Terracotta Stairs
- Added Terracotta Slabs
- Added Dyed Terracotta Stairs
- Added Dyed Terracotta Slabs
- Added snowlogging, like on Bedrock Edition
    - Only some blocks are now snow loggable:
        - Plants
        - Fences
        - Fence gates
        - Walls
        - Metal bars
        - Glass panes
    - Snowlogged blocks may generate during world generation
    - Blocks may become snowlogged during snowfall
    - Some snowlogged blocks have snowy texture variants
- Changed Powder Snow to decrease mining speed on Hard difficulty

### Biomes
- Added Gravel Beach biome
- Added Warm River biome

### Recipes
- Adding Block of Raw Copper Blasting recipe
- Adding Block of Raw Iron Blasting recipe
- Adding Block of Raw Gold Blasting recipe

### Sounds
- Added ambient Leaves block sounds
- Added ambient Tall Grass block sounds
- Added ambient Red Shrub sounds
- Added Guardian Beam attack sounds

### Music
- Added Door by C418
    - Plays on the main menu
- Added Equinoxe by C418
    - Plays on the main menu and in Creative Mode
- Added Chris by C418
    - Plays in Creative Mode

### Settings
- Added Fancy Entities Video Settings option
    - Toggles advanced entity animations (e.g. Arthropods moving horizontally up walls)
- Added Shield Animation Accessibility Settings option
    - Toggles a blocking shield's visibility
- Added Entity Culling option
    - Determines the entity culling method
    - Frustum
        - The default culling method. Hides entities that are outside the player's FOV
    - Occlusion
        - Hides entities that are behind blocks or fog
        - Performs after frustum culling

### Game Rules
- Added Advanced Mobs game rule
    - Toggles advanced mob behavior (e.g. flock panicking, shelter seeking)
    - Does not include wall climbing hitbox adjustments
- Added Blade Clash Window game rule
    - Sets the window of time (in ticks) that two entities must attack each other within, to trigger a blade clash
    - A value of 0 disables this feature

### Advancements
- Added Marking Territory advancement
    - Is granted by using a map on a banner, to create a banner marker
- Added Gather 'Round advancement
    - Is granted by to light a campfire with a stick
- Added Soup-er! advancement
    - Is granted by adding stew ingredients to a cauldron
- Added Maelstrom Advancement
    - Granted by activating a Conduit
- Added Moskstraumen
    - Granted by bringing a Conduit to full power

## Changes
### Combat
- Changed natural regeneration to be twice as fast
- Changed starvation to be twice as fast
- Changed natural regeneration to continue to 7 food points
- Changed natural regeneration to be a 1:1 transaction with food points
- Changed Arrows to not trigger invincibility frames
- Changed Arrows to not inherit the Y-axis inertia of their shooter
- Changed Tipped Arrows to scale consistently with their potion effect
- Changed fast weapons to trigger less invincibility frame time
- Changed saturation to no longer be related to fast healing
- Changed saturation to drain before hunger
- Changed consuming to be interrupted by attacks
- Changed Bow and Crossbow uncertainty to be lower
- Changed critical hits to be possible while sprinting
- Changed critical hits to be impossible for arrows that have been notched for longer than 3 seconds
- Changed Axes to accept Sweeping Edge, Looting, and Fire Aspect
- Changed Mace to accept Sweeping Edge, Knockback, and Looting
- Changed Swords to require Sweeping Edge for sweeping attacks
- Changed Axes to no longer take increased durability damage when attacking
- Changed attacking with weapons to prioritize entities over blocks, such as grass

### Discrete Actions
- Discrete actions, like sneaking, crawling, being invisible, or hiding in foliage, now conceal a player's location better
    - Being discrete now allows you to get close behind creatures without them detecting you, so long as you are not within their line of sight
- Having the glowing effect negates discrete actions
- Attacking a creature triggers a 5-second cooldown, in which you are no longer considered discrete
- Crawling now also affects the Locator Bar waypoint transmit range, like crouching

### Command Macros
- Added Command Macros
- Can be accessed from the Key Binds options screen
- Up to 10 commands can be saved (with a maximum of 1024 characters each) and activated by pressing Left Alt + A Number Key from 0-9
- The secondary key can be re-bound

### World Generation
- Changed all biomes to be larger
- Changed all continents to be larger
- Changed Oak Tree height
- Changed Birch Tree height
- Changed Short Grass placement chance
- Changed Bush placement chance
- Changed Snow to generate under trees in snowy biomes
- Changed Super Birch Trees to have Shelf Mushrooms generate on their trunks

#### Oceans
- Changed oceans to be potentially up to 50% deeper
- Changed oceans to become darker depending on depth

#### Forest
- Changed understory to have sparse rocks
- Changed understory to have more grass
- Changed mob spawning to include rabbits
- Changed fallen logs to potentially have moss carpet generate on top
- Changed mushroom generation rate

  Developer's Note: I didn't even know that mushroom patches could spawn

#### Birch Forest
- Changed fallen logs to potentially have moss carpet generate on top

#### Dark Forest
- Changed understory to have sparse rocks
- Changed canopies to be larger
- Changed dark oak trees to be taller
- Changed the sky to be darker
- Changed the ratio of Dark Oak trees to other vegetation and huge mushrooms
- Changed red mushroom caps to sometimes be slightly shorter
- Added small mushroom rings around huge mushrooms
- Changed mushroom generation rate
- Changed ambient fog distance in Dark Forest biomes

#### Pale Garden
- Changed ambient fog distance in Pale Garden biomes

#### Dappled Forest
- Changed understory to have Perse Wildflowers

#### Bamboo Forest
- Changed ambient fog distance in Bamboo Jungle biomes

#### Jungle
- Changed ambient fog distance in Jungle biomes

#### Swamp
- Added Perse Wildflowers
- Changed frog spawn rates in Swamp biomes to be higher
- Changed mushroom generation rate
- Changed ambient fog distance in Swamp biomes

#### Mangrove Swamp
- Changed ambient fog distance in Mangrove Swamp biomes

#### Taiga
- Changed vegetation to be more common

#### Savanna
- Added Baobab trees
    - Similarly to Pine trees, these trees do not have a unique wood type, but are a special type of Acacia tree
    - Can be grown from a 2x2 of Acacia saplings

#### Plains
- Changed mushroom generation rate

#### The End
- Changed Chorus Flowers to have an 85% chance of being alive
- Increased Chorus Flower growth sound effect volume
- Increased Chorus Flower death sound effect volume

### Structures
#### End Spikes
- Changed End Spikes to be more triangular

### Mobs
- Added wall climbing to Silverfish AI
- Added wall climbing to Endermite AI

### Items
- Changed Pottage to accept any type of edible mushroom

### Blocks
- Changed doors to be water loggable
- Changed Brown Mushroom Blocks to have a light level of 1, to match the small brown mushroom
- Changed Shelf Mushroom to be edible
- Changed Powder Snow to decrease mining speed
- Changed Iron Grates to have the `dragon_immune` block tag

### Tents
- Tents now continuously check to see if there are sturdy blocks below it
    - The check for this includes the 4 corners of the hitbox.

### Wither
- Wither Skull explosions can now only convert `dirt` block tag blocks to Soul Soil

### Recipes
- Changed map crafting recipe
    - Changed map crafting recipe to be a 9x9 of paper
        - This matches Bedrock Edition

### Settings
- Changed "Advanced Entity Animations" to "Fancy Entities"
- Changed custom options ordering

### Loot
- Changed Swamp Hut cauldron potions to be a data-driven loot table
- Changed Tannery Chests to possibly generate Quivers
- Changed Pillager Outpost Chests to possibly generate Heavy Shields and Quivers
- Changed Weaponsmith House Chests to possibly generate Heavy Shields
- Changed Woodland Mansion Chests to possibly generate Heavy Shields
- Changed Leatherworkers to gift Bundles and Quivers

### Textures
- Changed spherical potion texture
- Changed the Leaf Litter texture to have fewer leaves and (hopefully) appear more natural
- Changed Illusioner texture to match Minecraft Dungeons
- Changed Map texture to match the base game
- Changed the Adventure Mode texture to use the Buried Treasure Map texture

### Particles
- Changed Guardian Beams to be emissive
- Changed Conduit particles to match Bedrock Edition

### Sounds
- Changed Bush ambient sound to be louder
- Changed Sand ambient sound to be louder
- Changed dry vegetation ambient sound to be louder
- Changed ambient leaves block ambient sounds to be louder
- Changed Dead Bush ambient sound to be louder
- Changed Bush ambient sound subtitle to match the vanilla dry grass subtitle
- Changed ambient cold wind sounds to be less common
- Changed Guardian Beam charge sounds
- Changed Guardian Beam spike sounds
- Changed Large Ferns to have ambient sounds

### Advancements
- Changed "Traveler" advancement name to "Tentative Accommodations"
- Changed the Adventure root advancement to use the Buried Treasure Map texture
- Changed Subspace Bubble advancement to use the Filled Map texture
- Changed the Voluntary Exile advancement to use the Ominous Bottle texture
- Changed the Voluntary Exile advancement description, criteria, and parent

### Splash Text
- Added "Bigger! Better!" splash text
- Added "Perse!" splash text
- Added "Any shape and size!" splash text
- Added "Read the books!" splash text
- Added "More music by C418!" splash text

### Credits
- Added Zeit
    - We'd like to thank Zeit for modeling the quiver and assisting with the texture

## Technical Changes
### Mobs
- Refactored advanced mob AI

### Camera Shake Events
- Changed `range` field to accept an integer value

### Particles
- Added `blade_clash` particle

### Statistics
- Added `damage_blocked_by_weapon` statistic

### Attributes
- Added `shield_strength` attribute

### Data Components
- Added `tent/color` data component

### Data Tags
- Added `is_foggy` biome tag
- Added `is_slightly_foggy` biome tag
- Added `is_very_foggy` biome tag
- Added `has_cespitose` biome tag
- Added `triggers_ambient_bush_block_sounds` block tag
- Added `huge_glowshroom_can_place_on` block tag
- Added `stew_ingredient` block tag
- Added `tall_plants` block tag
- Added `silent_foliage` block tag
- Added `arrow_flammable` block tag
- Added `triggers_ambient_grass_block_sounds` block tag
- Added `daggers` item tag
- Added `can_parry_swords` item tag
- Added `can_parry_daggers` item tag
- Added `sweeping_weapon` item tag
- Added `quivers` item tag
- Added `shields` item tag
- Added `scansorial` entity type tag
- Added `causes_flock_panic` damage type tag
- Changed `can_be_full` to be named `predator`
- Changed `can_be_scared` to `panics_with_flock`
- Changed `can_seek_shade` to `seeks_shade`
- Changed `can_seek_warmth` to `seeks_warmth`
- Removed `can_break_tents` damage type tag
- Removed `always_kills_tent` damage type tag
- Removed `ignites_tents` damage type tag
- Removed `burns_tents` damage type tag
- Removed `has_ambient_block_sounds` block tag
- Removed `skull_block` block tag

### Bug Fixes
- Fixed bug causing a client/server de-sync when lighting a campfire with sticks
- Fixed bug where unknown_server.png (commonly known as pack.png) was stretched when there's an issue with a world thumbnail
- Fixed bug causing Tents to display the wrong error when trying to sleep during the day
- Fixed bug preventing Redstone comparisons to work with Potion Cauldrons
- Fixed bug that caused Water Bottles to completely fill Cauldrons
- Fixed bug causing spider jockeys to suffocate if their spider reaches a ceiling
- Fixed [BUG #76] Tents Z-Fight with their pegs
- Fixed missing Blast Fungus entity translation
- Fixed bug causing Tentative Accommodations to be granted to those who have yet to sleep in beds
- Fixed bug allowing Withers to set fire when Destructive Mob Actions is set to "Off"
- Fixed bug preventing some settings from being saved
- Fixed [MC-57057](https://bugs.mojang.com/browse/MC/issues/MC-57057) - Guardian laser attack sound ignores distance
- Fixed a memory leak relating to the Select World screen
- Fixed a bug relating to Multiplayer Server icons