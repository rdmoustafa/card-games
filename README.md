# Card Games Collection

Welcome to the Card Games Collection repository! This project contains a set of three card games implemented in Java. The games included in this collection are Barsa, Go Fish, and Pontoon. Each game can be played by users through a simple and interactive command-line interface.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Games](#games)
  - [Barsa](#barsa)
  - [Go Fish](#go-fish)
  - [Pontoon](#pontoon)
- [Contributing](#contributing)
- [License](#license)

## Installation

To get started with the Card Games Collection, you need to have Java and Maven installed on your system. Follow the instructions below to clone and run the project:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/rdmoustafa/card_games.git
    cd card_games
    ```

2. **Build the project:**
    ```bash
    mvn clean install
    ```

3. **Run the application:**
    ```bash
    mvn exec:java -Dexec.mainClass="GameLaunch"
    ```

## Usage

After running the application, you will be prompted to select one of the three card games to play. Follow the on-screen instructions to play the game of your choice.

## Games

### Barsa

Barsa is a popular card game in some regions. The objective is to gather as many cards as possible. 

### Go Fish

Go Fish is a simple and fun card game suitable for all ages. The objective is to collect as many sets of four cards of the same rank as possible. Players ask each other for specific cards and draw from the deck if the requested card is not available.

### Pontoon

Pontoon is a classic casino card game, similar to Blackjack. The objective is to get a hand total as close to 21 as possible without exceeding it. Players compete against the dealer to get the highest hand total.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for new features, please open an issue on the GitHub repository. If you'd like to contribute code, follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with descriptive commit messages.
4. Push your changes to your forked repository.
5. Open a pull request against the main repository.

## License

This project is licensed under the MIT License.
