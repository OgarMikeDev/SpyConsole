import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> listWords = new ArrayList<>();
    static List<String> listCorrectWords = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://randomall.ru/custom/list/188?ysclid=mmuo50ej8d870105743").get();
            FileWriter fileWriter = new FileWriter("src/main/resources/dilctionary.html");
            fileWriter.write(document.toString());
            fileWriter.close();

            String textAllWords = document.selectFirst(".form-control").text();
            String currentWord = "";
            for (int i = 0; i < textAllWords.length(); i++) {
                if (String.valueOf(textAllWords.charAt(i)).equals(String.valueOf(textAllWords.charAt(i)).toUpperCase()) &&
                        !String.valueOf(textAllWords.charAt(i)).equals(" ")) {
                    listWords.add(currentWord);
                    currentWord = "";
                    currentWord += textAllWords.charAt(i);
                } else {
                    currentWord += textAllWords.charAt(i);
                }
            }

            for (String word : listWords) {
                if (word.length() >= 2) {
                    listCorrectWords.add(word);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
