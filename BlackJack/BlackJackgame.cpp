
#include <iostream>
#include <random>
#include <string>
using namespace std;
int jugador = 0;
int maquina = 0;
string JMessage = "Las cartas del jugador son: ";
string MMessage = "Las cartas del dealer son: ";
int cards[52];

void createDeck(){
    int cardValue = 2;
    int cardCount = 0;
    for (int figure = 1; figure <= 4; figure++) {

        for (int card = 1; card <= 13; card++) {
            switch (card) {
                case 10:
                case 11:
                case 12:
                    cardValue = 10;
                break;
                case 13:
                    cardValue = 11;
                break;
                default:
                    break;
            }
            cards[cardCount] = cardValue;

            cardCount++;
            cardValue++;
        }
        cardValue = 2;
    }
}

int drawCard() {

    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution <int> dist(1, 52);
    int card = dist(gen);
    return cards[card - 1];
}

void plusCard(bool eljugador) {
    int card = drawCard();
    if (eljugador) {
        jugador += card;
        JMessage += " " + to_string(card);
    } else {
        maquina += card;
        MMessage += " " + to_string(card);
    }
}

void showCards(bool eljugador) {
    cout << ((eljugador) ? JMessage : MMessage) << endl;
}

void winner() {

    if (jugador > 21) {
        cout << "Haz Perdido, son mas de 21" << endl;
    } else if (jugador == 21) {
        cout << "Haz Ganado, igual a 21" << endl;
    } else {
        if (jugador > maquina) {
            cout << "Haz Ganado, mas que la maquina" << endl;
        } else if (jugador < maquina) {
            cout << "Haz Perdido, menos que la maquina" << endl;
        } else {
            cout << "Empate" << endl;
        }
    }
}

void initGame() {

    createDeck();
    plusCard(true);
    plusCard(true);
    plusCard(false);
    plusCard(false);
    showCards(true);
    showCards(false);
}

int main() {

    initGame();
    winner();
    return 0;
}