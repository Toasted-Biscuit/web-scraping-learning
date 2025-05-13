import java.io.File;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MonkeyType {
    public static void main(String[]args) {
        System.out.println("Text taken:");
        char[] text = getLetters();
        System.out.println(text);
        System.out.println("Starting Robot");
        robotType(text);
    }

    public static char[] getLetters() {
        // File path for the html code
        File htmlFile = new File("C:\\Users\\Nrhansi\\Desktop\\Coding\\Scraping_Stuffs\\src\\MonkeyTypeSite.html");
        // Array of letters 
        char[] charLetters = new char[0];

        try {
            Document doc = Jsoup.parse(htmlFile, "UTF-8", "https://monkeytype.com/");

            Elements text = doc.getElementsByClass("word");
            for(Element word : text) {
                for (char letter : word.text().toCharArray()) {
                    charLetters = addToArray(charLetters, letter);
                }
                charLetters = addToArray(charLetters, ' ');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return charLetters;
    }

    public static void robotType(char[] text) {
        try {
            Robot bob = new Robot();
            bob.delay(5000);
            for(char letter : text) {

                if (letter >= 'A' && letter <= 'Z') {
                    bob.keyPress(KeyEvent.VK_SHIFT);
                }

                bob.keyPress(KeyEvent.getExtendedKeyCodeForChar(letter));
                bob.keyRelease(KeyEvent.getExtendedKeyCodeForChar(letter));

                if (letter >= 'A' && letter <= 'Z') {
                    bob.keyRelease(KeyEvent.VK_SHIFT);
                }

                System.out.print(letter);
                bob.delay(1);
            }
        } catch (AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    public static char[] addToArray(char[] oldArr, char letter) {
        char[] newArr = new char[oldArr.length + 1];
        for(int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        newArr[oldArr.length] = letter;
        return newArr;
    }
}
