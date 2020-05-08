package flashcards;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    static String inputString() {
        String input = scanner.nextLine();
        return input;
    }

    public static void main(String[] args) {


        // FlashCard flashCard = new FlashCard(term, def);//создаю конструктором
        //   String ans = scanner.next();

        game(createArrayOfCards());

    }


    public static void game(FlashCard[] arrayOfCards) {
        for (int i = 0; i <= arrayOfCards.length - 1; i++) {
            System.out.println("Print the definition of \"" + arrayOfCards[i].getTerm() + "\"");
            String ans = scanner.nextLine();
            if (ans.equals(arrayOfCards[i].getDefinition())) {
                System.out.println("Correct answer.");
                continue;
            } else {
                System.out.println("Wrong answer. The correct one is \"" + arrayOfCards[i].getDefinition() + "\"");
                continue;
            }
        }

    }

    public static FlashCard[] createArrayOfCards() {
        System.out.println("Input the number of cards:");
        int howMuchCardCreate = scanner.nextInt();
        inputString();
        FlashCard[] arrayOfCards = new FlashCard[howMuchCardCreate];
        for (int numberOfCard = 1; numberOfCard <= howMuchCardCreate; numberOfCard++) {
            System.out.println("The card #" + numberOfCard + ":");
            String term = scanner.nextLine();
            System.out.println("Print the definition of the card #" + numberOfCard + ":");
            String def = scanner.nextLine();
            FlashCard flashCard = new FlashCard(term, def);
            arrayOfCards[numberOfCard - 1] = flashCard;
        }
        return arrayOfCards;
    }

}


class FlashCard {
    String term;
    String definition;


    public FlashCard(String card, String definition) {
        this.term = card;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean checkAnswer(String ans) {
        return ans.equals(getDefinition());
    }

    public String getCardInfo() {
        return "Card:\n" + term + "\nDefinition:\n" + definition;
    }


    static public FlashCard createCard(String term, String definition) {
        FlashCard flashCard = new FlashCard(term, definition); //бесполезный метод, создаёт одну карту.
        flashCard.term = term;
        flashCard.definition = definition;
        return flashCard;
    }

}