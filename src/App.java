import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static int count (String first, String second) {
        return ( (first.length() - first.replace(second, "").length()) / second.length() );
    }
    public static int numberOfDiffSymbols (String word) {
        return (int) word.chars().distinct().count();
    }
    public static boolean isInArray (String[] stringArray, String x) {
        boolean result = false;
        for (String element: stringArray) {
            if (element.equals(x)) {
                result = true;
            }
        }
        return result;
    }
    public static boolean isPalyndrom (String str) {
        String reversedString =  new StringBuilder(str).reverse().toString();
        if (reversedString.equals(str)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        String defaultString = s.nextLine();
        String string = defaultString;
        String toReplace = "Object-oriented programming";
        String stringLow = string.toLowerCase();
        String toReplaceLow = toReplace.toLowerCase();
        final int countOfOccurences = count(string,toReplace);
        String[] arrayOfOccs = new String[countOfOccurences];
        
        for (int i = 0;i<countOfOccurences;i++) {
            arrayOfOccs[i] = string.substring(stringLow.indexOf(toReplaceLow),stringLow.indexOf(toReplaceLow) + toReplace.length());
            string = string.replaceFirst(arrayOfOccs[i], "*");
            stringLow = string.toLowerCase();
        }

        int k = 0;
        while (k < countOfOccurences) {
            if (k % 2 != 0) {
                string = string.replaceFirst("*", "OOP");
            }
            else {
                string = string.replaceFirst("*", arrayOfOccs[k]);
            }
            k++;
        }

        System.out.println(string);

        // находим слово с наименьшим количеством различных символов

        String[] arrayOfWords = defaultString.split(" ");
        int minNumberOfDiffs = 100000;
        int currentNumberOfDiffs = 0;
        String wordWithMinNumberOfDiffs = "";
        
        for (String word: arrayOfWords) {
            // System.out.println(word);
            currentNumberOfDiffs = numberOfDiffSymbols(word);
            if (minNumberOfDiffs > currentNumberOfDiffs) {
                minNumberOfDiffs = currentNumberOfDiffs;
                wordWithMinNumberOfDiffs = word;
            }
        }
        System.out.println(wordWithMinNumberOfDiffs);

        // находим количество слов, содержащих только символы латинского алфавита

        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        
        char[] currentWord;
        boolean onlySymbFromAlph = true;
        int count = 0;
        int n = 0;
        ArrayList<String> listOfPalyndroms = new ArrayList<String>();
        for (String word: arrayOfWords) {
            currentWord = word.toCharArray();
            onlySymbFromAlph = true;
            for (char symbol: currentWord) {
                String symbolString = "" + symbol;
                onlySymbFromAlph = isInArray(alphabet, symbolString);  
            }
            if (onlySymbFromAlph) {
                count++;
            }
            if (isPalyndrom(word)) {
                listOfPalyndroms.add(word);
                n++;
            }
        }
        System.out.println(count);

        for (int i = 0;i < n;i++) {
            System.out.println(listOfPalyndroms.get(i));
        }

        
        s.close();
    }
}
