package flashcards;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Game {
    FlashCardDeck flashCardDeck = new FlashCardDeck();
    Scanner scanner = new Scanner(System.in);
    List<String> log = new LinkedList<>();

    String repairScanner() { //метод который чинит nextLine после nextInt
        String empty = scanner.nextLine();
        return empty;
    }

    public void menu() throws IOException {

        System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "add": {
                add();
                menu();
                break;
            }
            case "ask": {
                if (flashCardDeck.isEmpty()) {
                    System.out.println("No cards in deck\n");
                    menu();
                } else {
                    ask();
                    menu();
                    break;
                }

            }
            case "remove": {
                remove();
                menu();
                break;
            }

            case "hardest card":{
                hardestCard();
                menu();
                break;
            }
            case "import": {
                importDeck();
                menu();
                break;
            }
            case "export": {
                exportDeck();
                menu();
                break;
            }
            case "cards": {

                cards();
                menu();
                break;
            }

            case "reset stats": {
                reset();
                menu();
                break;
            }

            case "exit": {
                System.out.println("Bye bye!");
                break;
            }

            case "set": {
                System.out.println(flashCardDeck.getSetTerm().toString());
            }
            default: {
                menu();
            }
        }
    }

    public void reset() {
        flashCardDeck.resetStat();
        System.out.println("Card statistics has been reset.\n");
    }

    public void ask() {


        System.out.println("How many times to ask?");
        int howMuchTimeToAsk = scanner.nextInt();
        repairScanner();
        String randomTerm;
        howMuchTimeToAsk = Math.abs(howMuchTimeToAsk);
        while (true) {
            for (int askTime = 0; askTime < howMuchTimeToAsk; askTime++) {
                randomTerm = flashCardDeck.getRandomTerm();
                System.out.printf("Print the definition of \"%s\":\n", randomTerm);
                String def = scanner.nextLine();
                if (flashCardDeck.getDefs().contains(def)) {
                    if (flashCardDeck.getDefinition(randomTerm).equals(def)) {
                        System.out.println("Correct answer.");
                    } else {
                        flashCardDeck.addError(randomTerm);
                        System.out.println("Wrong answer. The correct one is \"" + flashCardDeck.getDefinition(randomTerm) +
                                "\", you've just written the definition of \"" + flashCardDeck.getTermByDef(def) + "\".\n");
                    }
                } else {
                    flashCardDeck.addError(randomTerm);
                    System.out.println("Wrong answer. The correct one is \"" + flashCardDeck.getDefinition(randomTerm) + "\".\n");
                }


            }
            break;
        }
    }

    public void add() {
        while (true) {
            System.out.println("The Card:");
            String term = scanner.nextLine();
            if (flashCardDeck.getSetTerm().contains(term)) {
                System.out.printf("The card \"%s\" already exists.\n\n", term);
                break;
            }
            System.out.println("The definition of the card:");
            String def = scanner.nextLine();
            if (flashCardDeck.getDefs().contains(def)) {
                System.out.printf("The definition \"%s\" already exists.\n\n", def);
                break;
            }
            flashCardDeck.addCard(term, def);
            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n\n", term, def);
            break;
        }
    }

    public void remove() {
        while (true) {
            System.out.println("The card:");
            String term = scanner.nextLine();
            if (flashCardDeck.getSetTerm().contains(term)) {
                flashCardDeck.removeCard(term);
                System.out.println("The card has been removed.\n");
                break;
            } else {
                System.out.printf("Can't remove \"%s\": there is no such card.\n", term);
                break;
            }
        }
    }


    public void exportDeck() throws IOException {
        System.out.println("Input filename for export");
        String fileName = scanner.nextLine();
        fileName = fileName + ".txt";
        File dir = new File("C:\\Users\\Likvi\\Documents\\export\\");
        File exportFile = new File(dir, fileName);

        FileWriter export = new FileWriter(exportFile);
        try {
            for (String s : flashCardDeck.getSetTerm()
            ) {
                export.write(flashCardDeck.printEntryForExport(s));

            }
            export.close();
            System.out.println(flashCardDeck.size() + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("IO Error" + e);
        }
    }

    public void importDeck() throws IOException {
        int countOfStrings = 0;
        System.out.println("Input filename for import");
        String fileName = scanner.nextLine();
        fileName = fileName + ".txt";

        File dir = new File("C:\\Users\\Likvi\\Documents\\export\\" + fileName);
        if (dir.exists()) {
            FileReader read = new FileReader(dir, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(read);
            while (reader.hasNext()) {
                String unparsed = reader.nextLine();
                String reg = "\\|";
                String[] parsed = unparsed.split(reg);
                flashCardDeck.updateOrAddCard(parsed[0], parsed[1], Integer.parseInt(parsed[2]));
                System.out.println(parsed[0] + parsed[2]);
                countOfStrings++;
            }
            read.close();
            System.out.printf("%s cards have been loaded.\n", countOfStrings);
        } else {
            System.out.println("File not found.\n");
        }
    }

    void cards() {
        for (String s : flashCardDeck.getSetTerm()
        ) {
            System.out.println(flashCardDeck.printEntryForExport(s));
        }
    }

    void hardestCard(){
        Map <String, Integer> hardestCards = new TreeMap<>();
        hardestCards = flashCardDeck.hardestCard();
        int count = hardestCards.size();
        if (count > 1){
            System.out.printf("The hardest cards are " + flashCardDeck.readyString(flashCardDeck.hardestCard()) + ". You have %s errors answering them.\n", flashCardDeck.getMaxErrors());
        } else if (count == 1){
            System.out.printf("The hardest card is %s. You have %s errors answering it.\n", flashCardDeck.readyString(flashCardDeck.hardestCard()), flashCardDeck.getMaxErrors());
        } else if (count == 0){
            System.out.println("There are no cards with errors\n");
        }
    }

    void logInput(){

    }

    void logOutput(){

    }
}
