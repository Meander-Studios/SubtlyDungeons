# [6.0] - UNRELEASED
## New Features
### Chocolate Bottle
- Added Chocolate Bottles, which can be crafted with Sugar, Cocao Beans, and Water Bottles
- Will reset the drinker's Freezing ticks to 0

### UI
- Added new experimental Multiplayer UI

#### Monster Variant Spawning
- Biome specific monster variants (Husk, Parched, Frostbite, Strays) will now start to spawn in place of their Zombie variants
  - This only happens on Hard difficulty
  - The variant to temperate monster ratio will grow with Local Difficulty
    - Variants will take up 100% of spawns when Local Difficulty is greater than 3.00

### Zombie Leaders
- Added unique Frostbite Leader texture

## Changes
### Combat
- Reverted change to projectile y-axis momentum

### Monsters
#### Stray
- Changed Strays to shoot and drop Freezing Tipped Arrows
- Changed Strays to spawn in Snowy Beaches, Snowy Taigas, and Groves

#### Frostbite
- Changed Frostbites to spawn in Snowy Beaches, Snowy Taigas, and Groves

#### Parched
- Changed Strays to spawn in Badlands and Eroded Badlands

#### Husk
- Changed Frostbites to spawn in Badlands and Eroded Badlands

### Blocks
- Changed Ice to no longer cause cold wind noises

### Crafting
- Golden Apples and Enchanted Golden Apples will no longer unlock the Apple Pie recipe
- Red Mushrooms and Shelf Mushrooms will now unlock the Pottage recipe

### Splash Text
- Added "Save the world — go outside!"
- Added "Also try Minecraft Dungeons 2!"

### Credits
- Added Mushinx, Rale, SK4RDY, James Robinett, Artemis Ribeiro, and Cameron Fisher!
  - Big thanks to those who were able to volunteer and/or join the Subtly Dungeons team!

## Technical Changes
### Data Tags
- Created `dyed_terracotta_stairs` block item tag
- Created `dyed_terracotta_slabs` block item tag
- Created `spawns_cold_variant_monsters` biome tag
- Created `spawns_warm_variant_monsters` biome tag

### Bug Fixes
- Fixed bug causing all skins to appear slim on the Title Screen
- Fixed bug causing the Experimental GUI settings option to display twice
- Fixed bug causing flaming arrows to destroy non-flammable blocks
- Fixed [[Bug #81]](https://github.com/Meander-Studios/SubtlyDungeons/issues/81) - Multiplayer Server fails with custom icon
- Fixed bug causing to terracotta blocks not having a specified tool