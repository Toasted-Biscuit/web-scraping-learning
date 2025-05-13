import java.io.File;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class HumanBenchmark {
    public static void main(String[] args) {
        System.out.println("Text taken: ");
        char[] text = getLetters();
        System.out.println(text);
        System.out.println("\nStarting Robot...");
        robotType(text);
    }

    public static char[] getLetters() {
        // File path for the html code
        File htmlFile = new File("C:\\Users\\Nrhansi\\Desktop\\Coding\\Scraping_Stuffs\\src\\HumanBenchmarkSite.html");
        // This array will hold the chars of the text
        char[] charLetters = new char[0];

        try {
            Document doc = Jsoup.parse(htmlFile, "UTF-8", "https://humanbenchmark.com/tests/typing");
            
            Elements letters = doc.getElementsByClass("incomplete");

            for (Element letter : letters) {
                // If there is no char at in the element add a space to array
                if (letter.text().length() == 0) {
                    charLetters = addToArray(charLetters, ' ');
                } else {
                    charLetters = addToArray(charLetters, letter.text().charAt(0));
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't do it sadly: " + e.getMessage());
        }
        return charLetters;
    }

    // Makes a new array with the char at the end
    public static char[] addToArray(char[] arr, char letter) {
        char[] newArr = new char[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = letter;
        return newArr;
    }

    public static void robotType(char[] text) {
        try {
            Robot barry = new Robot();
            barry.delay(5000);
            for (char letter : text) {
                if (Character.isUpperCase(letter) || letter == '?' || letter == '!' || letter == '"' || letter == ':') {
                    barry.keyPress(KeyEvent.VK_SHIFT);
                }

                if (letter == '?') {
                    barry.keyPress(KeyEvent.VK_SLASH);
                    barry.keyRelease(KeyEvent.VK_SLASH);
                } else if (letter == '!') {
                    barry.keyPress(KeyEvent.VK_1);
                    barry.keyRelease(KeyEvent.VK_1);
                } else if (letter == '"') {
                    barry.keyPress(KeyEvent.VK_QUOTE);
                    barry.keyRelease(KeyEvent.VK_QUOTE);
                } else if (letter == ':') {
                    barry.keyPress(KeyEvent.VK_SEMICOLON);
                    barry.keyRelease(KeyEvent.VK_SEMICOLON);
                }else {
                    barry.keyPress(KeyEvent.getExtendedKeyCodeForChar(letter));
                    barry.keyRelease(KeyEvent.getExtendedKeyCodeForChar(letter));
                }

                System.out.print(letter);
                // barry.delay(5);

                if (Character.isUpperCase(letter) || letter == '?' || letter == '!' || letter == '"' || letter == ':') {
                    barry.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
        } catch (AWTException e) {
            System.out.println("Whoopsie: " + e.getMessage());
        }
    }
}
