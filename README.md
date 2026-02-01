# Pixin

[Pixie](https://github.com/pixiemc) + [Nil](https://git.sleeping.town/Nil/NilLoader) = Nixie

It makes Pixie work via a NilLoader transformer, for Minecraft 1.8.9.

## Usage

Currently only compatible with 1.8.9, using [NilLoader](https://git.sleeping.town/Nil/NilLoader).

1. Install Essential and [NilLoader](https://git.sleeping.town/Nil/NilLoader)
2. Install [Nixie](https://github.com/AnotherPillow/Nixie) into the `nilmods` folder
3. Run the game once with Essential
4. Remove the original Essential jar from your mods folder
5. Copy the actual Essential mod from `<minecraft dir>/essential` (it'll be a .jar and be something like `Essential (forge_1.8.9).jar`) to your mods folder.
6. Run the game
7. If needed, adjust the [config](#Config)

## Config

The config can be modified in `<minecraft dir>/config/nixie.conf`.

### Fields

- `pixie-url`: Modifies the connection URI. Default `wss://connect.pixie.rip/v1` (which is currently non-functional).