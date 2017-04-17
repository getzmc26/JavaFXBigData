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

        int low = 0;
        int high = a.length - 1;
        boolean numberFound = false;

        while (high + 1 > low) {
            int middle = (low + high) / 2;
            if (a[middle] == parsedInt) {
                binarySearchOutput += "Number found: " + parsedInt;
                numberFound = true;
                break;
            } else if (a[middle] < parsedInt) {
                low = middle + 1;
            } else if (a[middle] > parsedInt) {
                high = middle - 1;
            }
        }
        if (numberFound == false) {
            binarySearchOutput += "Number not found: " + parsedInt;
        }
    }

    public static String binarySearchOutput() {
        return binarySearchOutput;
    }

    public static void binarySearchClearOutput() {
        binarySearchOutput = "";
    }
}
