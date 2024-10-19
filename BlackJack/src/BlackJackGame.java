import java.util.ArrayList;
//Añadimos mas del codigo usando el ejemplo de clase
 enum Suit {
    SPADES('♠'),
    HEARTS('♥'),
    DIAMONDS('♦'),
    CLUBS('♣');

    String suitName;
    char suitSymbol;

    Suit(char symbol) {
        suitSymbol = symbol;
        suitName = switch (symbol) {
            case '♠' -> "Espadas";
            case '♥' -> "Corazones";
            case '♦' -> "Diamantes";
            default -> "Tréboles";
        };
    }
}

enum Figure {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(11, "A");

    int figureValue;
    String figureName;

    Figure(int figureValue, String figureName) {
        this.figureValue = figureValue;
        this.figureName = figureName;
    }
}

class Card {
    Suit suit;
    Figure figure;
    boolean isTaken;

    Card(Suit suit, Figure figure) {
        this.suit = suit;
        this.figure = figure;
    }

    public String showCard() {

        return String.format("%s%c", figure.figureName, suit.suitSymbol);
    }

    int getValue() {
        return figure.figureValue;
    }

    void take() {
        isTaken = true;
    }

    boolean isTaken() {
        return isTaken;
    }
}

class Deck {
    Card[] cards;

    Deck() {

        cards = new Card[52];
        int index = 0;
        for (Suit suit : Suit.values()) {
            for (Figure figure : Figure.values()) {
                cards[index++] = new Card(suit, figure);
            }
        }
    }

    void shuffle() {

        for (int i = 0; i < cards.length; i++) {
            int randomIndex = (int) (Math.random() * cards.length);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    void show() {

        for (Card card : cards) {
            card.showCard();
        }
    }

    Card draw() {

        int randomIndex;
        do {
            randomIndex = (int) (Math.random() * cards.length);
        } while (cards[randomIndex].isTaken());
        cards[randomIndex].take();
        return cards[randomIndex];
    }
}

class Player {
    String name;
    ArrayList<Card> hand;
    int score;

    Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        score = 0;
    }

    void drawCard(Deck deck) {
        Card card = deck.draw();
        hand.add(card);
        score += card.getValue();
    }

    void showHand() {
        String handString = "";
        for (Card card : hand) {
            handString += card.showCard() + " ";
        }
        System.out.println(handString);
    }

    int getScore() {
        return score;
    }
}

class BlackJack {
    Deck deck;
    Player player;
    Player dealer;

    BlackJack() {
        deck = new Deck();
        deck.shuffle();
        player = new Player("Player");
        dealer = new Player("Dealer");
    }

    void start() {
        player.drawCard(deck);
        player.drawCard(deck);
        dealer.drawCard(deck);
        dealer.drawCard(deck);
    }

    void showHands() {
        System.out.println("Mano del jugador:");
        player.showHand();
        System.out.println("Mano del dealer:");
        dealer.showHand();
    }

    void winner() {
        if (player.getScore() > 21) {
            System.out.println("¡Has perdido, tienes mas de 20!");
        } else if (player.getScore() == 21) {
            System.out.println("¡Has ganado, igual a 20!");
        } else {
            if (dealer.getScore() == player.getScore()) {
                System.out.println("¡Empate!");
            } else if (player.getScore() > dealer.getScore()) {
                System.out.println("¡Has ganado, mas que la maquina!");
            } else {
                System.out.println("¡Has perdido!, menos que la maquina");
            }
        }
    }
}

    public class BlackJackGame {
    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.start();
        blackJack.showHands();
        blackJack.winner();
    }
}