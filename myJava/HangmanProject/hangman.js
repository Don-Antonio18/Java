const hangmanArt = [
    `
    +---+
        |
        |
        |
        |
        |
    =========`,
    `
    +---+
    0   |
        |
        |
        |
        |
    =========`,
    `
    +---+
    0   |
    |   |
        |
        |
        |
    =========`,
    `
    +---+
    0   |
   /|   |
        |
        |
        |
    =========`,
    `
    +---+
    0   |
   /|\\  |
        |
        |
        |
    =========`,
    `
    +---+
    O   |
   /|\\  |
   /    |
        |
        |
    =========`,
    `
    +---+
    O   |
   /|\\  |
   / \\  |
        |
        |
    =========`
];

let words = ["elephant", "pizza", "basketball", "computer", "giraffe", "sushi"];
let word = "";
let wordState = [];
let wrongGuesses = 0;
let guessedLetters = [];

function startGame() {
    word = words[Math.floor(Math.random() * words.length)];
    wordState = Array(word.length).fill("_");
    wrongGuesses = 0;
    guessedLetters = [];
    document.getElementById("hangman-art").textContent = hangmanArt[wrongGuesses];
    document.getElementById("word-display").textContent = wordState.join(" ");
    document.getElementById("message").textContent = "";
    document.getElementById("wrong-message").textContent = "";
    document.getElementById("wrong-guesses").textContent = "Wrong guesses: None";
}

function makeGuess() {
    const guess = document.getElementById("guess").value.toLowerCase();
    document.getElementById("guess").value = "";

    if (!guess || guessedLetters.includes(guess)) {
        document.getElementById("message").textContent = "Invalid or repeated guess!";
        return;
    }

    guessedLetters.push(guess);

    if (word.includes(guess)) {
        document.getElementById("message").textContent = "Correct guess!";
        for (let i = 0; i < word.length; i++) {
            if (word[i] === guess) wordState[i] = guess;
        }
        document.getElementById("word-display").textContent = wordState.join(" ");
        if (!wordState.includes("_")) {
            document.getElementById("message").textContent = "🥳 YOU WON THE GAME!!! 🎉";
        }
    } else {
        wrongGuesses++;
        document.getElementById("wrong-message").textContent = "Wrong Guess!";
        document.getElementById("hangman-art").textContent = hangmanArt[wrongGuesses];
        document.getElementById("wrong-guesses").textContent = "Wrong guesses: " + guessedLetters.filter(letter => !word.includes(letter)).join(", ");
        if (wrongGuesses === hangmanArt.length - 1) {
            document.getElementById("message").textContent = "GAME OVER! The word was: " + word;
        }
    }
}

startGame();
