package search;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

/**
 *
 * @author Matt Getz
 */
public class Searches {

    static String sequentialSearchOutput = "";
    static String binarySearchOutput = "";

    public static void sequentialSearch(int[] a) throws ParseException {
        TextInputDialog searchAlert = new TextInputDialog("");
        searchAlert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        searchAlert.initStyle(StageStyle.TRANSPARENT);

        searchAlert.setTitle("");
        searchAlert.setHeaderText("Enter number to search:");
        Optional<String> searchResult = searchAlert.showAndWait();

        String userInput = searchResult.get();
        NumberFormat removeComma = NumberFormat.getNumberInstance(Locale.US);
        int parsedInt = removeComma.parse(userInput).intValue();
        int i;
        int count = 0;
        for (i = 0; i < a.length; i++) {
            if (parsedInt == a[i]) {
                count++;
            }
        }
        if (count == 0) {
            sequentialSearchOutput += "Number not found: " + parsedInt;
        } else if (count == 1) {
            sequentialSearchOutput += "Number found: " + parsedInt + " occurs one time";
        } else {
            sequentialSearchOutput += "Number found: " + parsedInt + " occurs " + count + " times";
        }
    }

    public static String sequentialSearchOutput() {
        return sequentialSearchOutput;
    }

    public static void sequentialSearchClearOutput() {
        sequentialSearchOutput = "";
    }

    public static void binarySearch(int[] a) throws ParseException {
        TextInputDialog searchAlert = new TextInputDialog("");
        searchAlert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        searchAlert.initStyle(StageStyle.TRANSPARENT);

        searchAlert.setTitle("");
        searchAlert.setHeaderText("Enter number to search:");
        Optional<String> searchResult = searchAlert.showAndWait();

        String userInput = searchResult.get();
        NumberFormat removeComma = NumberFormat.getNumberInstance(Locale.US);
        int parsedInt = removeComma.parse(userInput).intValue();

        int lowerBound = 0;
        int upperBound = a.length - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (parsedInt == a[curIn]) {
                binarySearchOutput += "Number found: " + parsedInt;
                break;
            } else if (lowerBound > upperBound) {
                binarySearchOutput += "Number not found: " + parsedInt;
                break;
            } else {
                if (parsedInt > 0 && curIn > 0) {
                    lowerBound = curIn + 1; // it's in upper half
                } else {
                    upperBound = curIn - 1; // it's in lower half
                }
            }
        }
    }

    public static String binarySearchOutput() {
        return binarySearchOutput;
    }

    public static void binarySearchClearOutput() {
        binarySearchOutput = "";
    }
}
