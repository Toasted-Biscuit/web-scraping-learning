import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BabysFirstScraper {
    public static void main(String[] args) {
        System.out.println("Code ran!");
        // scrapeQuotesPage(7);
        // scrapeAllQuotes();
        scrapeQuote(24);
    }

    public static void scrapeQuotesPage(int page) {
        // The url of the website
        String url = "https://quotes.toscrape.com/page/" + page + "/";

        try {
            // Connects to the webpage
            Document doc = Jsoup.connect(url).get();

            Elements quotes = doc.getElementsByClass("quote");

            for (Element quoteBlock : quotes) {
                Elements author = quoteBlock.getElementsByClass("author");
                Elements text = quoteBlock.getElementsByClass("text");
                System.out.println("\"" + text.text().substring(1, text.text().length() - 1) + "\"");
                System.out.println("- " + author.text() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Couldn't find page lol: " + e.getMessage());
        }     
    }

    public static void scrapeAllQuotes() {
        
        for (int i = 1; i <= 10; i++) {
            String url = "https://quotes.toscrape.com/page/" + i + "/";

            try {
                // Connects to the webpage
                Document doc = Jsoup.connect(url).get();
    
                Elements quotes = doc.getElementsByClass("quote");
    
                for (Element quoteBlock : quotes) {
                    Elements author = quoteBlock.getElementsByClass("author");
                    Elements text = quoteBlock.getElementsByClass("text");
                    System.out.println("\"" + text.text().substring(1, text.text().length() - 1) + "\"");
                    System.out.println("- " + author.text() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Couldn't find page lol: " + e.getMessage());
            }
        }
    }

    public static void scrapeQuote(int quoteNum) {
        int pageNum = quoteNum / 10 + 1;

        int onPageNum;
        if (quoteNum > 10) {
            onPageNum = quoteNum % 10;
        } else {
            onPageNum = quoteNum;
        }

        String url = "https://quotes.toscrape.com/page/" + pageNum + "/";

        try {
            // Connects to the webpage
            Document doc = Jsoup.connect(url).get();

            // Gets all quotes on page
            Elements quotes = doc.getElementsByClass("quote");

            // Picks out the selected quote
            Element selectedQuote = quotes.get(onPageNum - 1);

            //Get the text and author and prints it
            Elements author = selectedQuote.getElementsByClass("author");
            Elements text = selectedQuote.getElementsByClass("text");
            System.out.println("\"" + text.text().substring(1, text.text().length() - 1) + "\"");
            System.out.println("- " + author.text() + "\n");
            
        } catch (IOException e) {
            System.out.println("Couldn't find page lol: " + e.getMessage());
        }
    }
}