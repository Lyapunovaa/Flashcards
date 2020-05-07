package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String term = scanner.nextLine();
        String def = scanner.nextLine();
        FlashCard flashCard = new FlashCard(term, def);//создаю конструктором
        String ans = scanner.next();

        game(flashCard, ans);

    }


    public static void game(FlashCard flashCard, String ans) {
        if (flashCard.checkAnswer(ans)) {             //Заглушка для прохождения задания
            System.out.println("Your answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
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