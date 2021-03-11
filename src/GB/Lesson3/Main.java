package GB.Lesson3;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final String EXITMSG = "\nDo you want to try again? (1 - Yes | 0 - No)?";
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        gameOne(0, 10, 3); // Guess a number
        gameTwo(); // guess a word

        scn.close();
    }



    // Guess a number
    static void gameOne(int numFrom, int numTo, int tries) {
        var isCont = true;
        guessTheNumber(numFrom, numTo, tries);
        while (isCont) {
            var isNumber = scn.hasNextInt();
            if (isNumber) {
                switch (scn.nextInt()) {
                    case 1:
                        guessTheNumber(numFrom, numTo, tries);
                        break;
                    case 0:
                        isCont = false;
                }
                continue;
            }
            System.out.println("Enter 1 to continue and 0 to exit");
            scn.next();
        }
        System.out.println("Bye!");
    }

    // Guess a word
    static void gameTwo() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        var guessWord = words[new Random().nextInt(words.length)];
        System.out.println("I want to play game with you..\nGuess a word..");
        String userGuess = "";
        var tries = 0;
        while (!userGuess.toLowerCase().equals(guessWord)) {
            tries++;
            if (tries>guessWord.length()){
                System.out.println("Really? You couldn't guess? It was "+guessWord+"\nYOU LOOSE!");
                return;
            }
            System.out.println("Word is " + String.format("%-15s", guessWord.substring(0, tries)).replace(' ', '#') );
            System.out.print("And yor word is.....");
            userGuess = scn.next();
        }
        System.out.println("You guessed it!");

    }
    static void guessTheNumber(int numFrom, int numTo, int tries) {
        var guessNum = new Random().nextInt(numTo + numFrom) + numFrom;
        System.out.println("Welcome!\nTry to guess number from " + numFrom + " to " + (numTo - 1));
        System.out.println("You have only " + tries + (tries > 1 ? " tries!" : " try!"));
        System.out.println("So, let's begin..");
        var t = 0;
        while (t < tries) {

            System.out.print("And your number is.....");
            var isNumber = scn.hasNextInt();
            if (isNumber) {
                t++;
                var userNum = scn.nextInt();
                if (userNum > guessNum) {
                    System.out.println("Nope, my number is less, try again =)");
                } else if (userNum < guessNum) {
                    System.out.println("Nope, my number is bigger, try again =)");
                } else {
                    System.out.println("Great! You guessed it!" + EXITMSG);
                    return;
                }
            } else {
                System.out.println("Please, type only digits!");
                scn.next();

            }
        }
        System.out.println("We are so sorry but you LOOSE!" + EXITMSG);
    }
}
