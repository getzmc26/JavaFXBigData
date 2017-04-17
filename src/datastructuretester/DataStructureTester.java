package datastructuretester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static sort.SimpleSorts.bubbleSort;
import static sort.SimpleSorts.selectionSort;
import static sort.SimpleSorts.insertionSort;
import static sort.ComplexSorts.mergeSort;
import static sort.ComplexSorts.quickSort;
import static search.Searches.sequentialSearch;
import static search.Searches.sequentialSearchOutput;
import static search.Searches.sequentialSearchClearOutput;
import static search.Searches.binarySearch;
import static search.Searches.binarySearchOutput;
import static search.Searches.binarySearchClearOutput;

/**
 * A JavaFX 8 program to help experiment with data structures and algorithms.
 *
 * @author Matt Getz
 */
public class DataStructureTester extends Application {

    Stage pStage;
    TextArea taStatus;
    ScrollPane spStatus;
    TextArea taData;
    ScrollPane spData;

    //int parsedInt;
    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;

        taData = new TextArea();
        spData = new ScrollPane(taData);
        spData.setFitToWidth(true);
        spData.setFitToHeight(true);

        taStatus = new TextArea();
        spStatus = new ScrollPane(taStatus);
        spStatus.setFitToWidth(true);
        spStatus.setPrefViewportHeight(80);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(myMenuBar());
        borderPane.setCenter(spData);
        borderPane.setBottom(spStatus);

        Scene scene = new Scene(borderPane);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setTitle("Data Structures");
        primaryStage.setScene(scene);

//        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);
        primaryStage.hide();
        primaryStage.show();
    }

    public MenuBar myMenuBar() {
        MenuBar myBar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu dataMenu = new Menu("Data");
        final Menu simpleSortMenu = new Menu("Simple Sorts");
        final Menu complexSortMenu = new Menu("Complex Sorts");
        final Menu searchMenu = new Menu("Search");
        final Menu helpMenu = new Menu("Help");

        myBar.getMenus().addAll(fileMenu, dataMenu, simpleSortMenu, complexSortMenu, searchMenu, helpMenu);

        /**
         * *********************************************************************
         * File Menu Section
         */
        MenuItem newCanvas = new MenuItem("New");
        newCanvas.setOnAction((ActionEvent e) -> {
            taData.clear();
            taStatus.clear();
        });
        fileMenu.getItems().add(newCanvas);

        MenuItem open = new MenuItem("Open");
        open.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(pStage);
            if (file != null) {
                readFile(file);
            }
        });
        fileMenu.getItems().add(open);

        MenuItem save = new MenuItem("Save");
        save.setOnAction((ActionEvent e) -> {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(pStage);
            if (file != null) {
                writeFile(file);
            }
        });
        fileMenu.getItems().add(save);

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exit);

        /**
         * *********************************************************************
         * Data Menu Section
         */
        MenuItem miGenerateIntegers = new MenuItem("Generate Integers");
        miGenerateIntegers.setOnAction(e -> {
            MyTimer.startMicroTime();
            try {
                generateIntegers();
            } catch (ParseException ex) {
                Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
            }
            taStatus.setText("Integers generated in " + MyTimer.stopMicroTime() + "us");
        });
        dataMenu.getItems().add(miGenerateIntegers);

        MenuItem miGenerateRandomIntegers = new MenuItem("Generate Random Integers");
        miGenerateRandomIntegers.setOnAction(e -> {
            MyTimer.startMicroTime();
            try {
                generateRandomIntegers();
            } catch (ParseException ex) {
                Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
            }
            taStatus.setText("Random integers generated in " + MyTimer.stopMicroTime() + "us");
        });
        dataMenu.getItems().add(miGenerateRandomIntegers);

        MenuItem miRandom = new MenuItem("Randomize Data");
        miRandom.setOnAction(e -> {
            randomizeIntegers();
        });
        dataMenu.getItems().add(miRandom);

        /**
         * *********************************************************************
         * Sort Menu Section
         */
//Simple Sorts
        MenuItem miBubbleSortAsc = new MenuItem("Bubble Sort Ascending");
        miBubbleSortAsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Bubble Sort Ascending \n"
                    + "-------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            bubbleSort(nums, "A");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miBubbleSortAsc);

        MenuItem miBubbleSortDsc = new MenuItem("Bubble Sort Descending");
        miBubbleSortDsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Bubble Sort Descending \n"
                    + "--------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            bubbleSort(nums, "D");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miBubbleSortDsc);

        MenuItem miSelectionSortAsc = new MenuItem("Selection Sort Ascending");
        miSelectionSortAsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Selection Sort Ascending \n"
                    + "---------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            selectionSort(nums, "A");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miSelectionSortAsc);

        MenuItem miSelectionSortDsc = new MenuItem("Selection Sort Descending");
        miSelectionSortDsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Selection Sort Descending \n"
                    + "-----------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            selectionSort(nums, "D");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miSelectionSortDsc);

        MenuItem miInsertionSortAsc = new MenuItem("Insertion Sort Ascending");
        miInsertionSortAsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Insertion Sort Ascending \n"
                    + "---------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            insertionSort(nums, "A");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miInsertionSortAsc);

        MenuItem miInsertionSortDsc = new MenuItem("Insertion Sort Descending");
        miInsertionSortDsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Insertion Sort Descending \n"
                    + "-----------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            insertionSort(nums, "D");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        simpleSortMenu.getItems().add(miInsertionSortDsc);

//Complex sorts
        MenuItem miMergeSortAsc = new MenuItem("Merge Sort Ascending");
        miMergeSortAsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Merge Sort Ascending \n"
                    + "-------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            mergeSort(nums, "A");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        complexSortMenu.getItems().add(miMergeSortAsc);

        MenuItem miMergeSortDsc = new MenuItem("Merge Sort Descending");
        miMergeSortDsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            taStatus.setText("Merge Sort Descending \n"
                    + "--------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            mergeSort(nums, "D");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        complexSortMenu.getItems().add(miMergeSortDsc);

        MenuItem miQuickSortAsc = new MenuItem("Quick Sort Ascending");
        miQuickSortAsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            randomizeIntegers();
            taStatus.setText("Quick Sort Ascending \n"
                    + "--------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            quickSort(nums, "A");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        complexSortMenu.getItems().add(miQuickSortAsc);

        MenuItem miQuickSortDsc = new MenuItem("Quick Sort Descending");
        miQuickSortDsc.setOnAction(e -> {
            MyTimer.startMicroTime();
            int[] nums = text2IntArray(taData.getText());
            randomizeIntegers();
            taStatus.setText("Quick Sort Descending \n"
                    + "--------------------------");
            taStatus.appendText("\nConverting text to array took " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            quickSort(nums, "D");
            taStatus.appendText("\nSort finished in " + MyTimer.stopMicroTime() + "us");
            MyTimer.startMicroTime();
            taData.setText(intArray2Text(nums));
            taStatus.appendText("\nArray to text finished in " + MyTimer.stopMicroTime() + "us");
        });
        complexSortMenu.getItems().add(miQuickSortDsc);

        /**
         * *********************************************************************
         * Search Menu Section
         */
        MenuItem miSequentialSearch = new MenuItem("Sequential Search");
        miSequentialSearch.setOnAction(e -> {
            int[] nums = text2IntArray(taData.getText());
            try {
                sequentialSearch(nums);
            } catch (ParseException ex) {
                Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
            }
            taStatus.setText("Sequential Search \n"
                    + "--------------------");
            taStatus.appendText("\nSearch finished in " + MyTimer.stopMicroTime() + "us\n");
            taStatus.appendText(sequentialSearchOutput());
            sequentialSearchClearOutput();
        });
        searchMenu.getItems().add(miSequentialSearch);

        MenuItem miBinarySearch = new MenuItem("Binary Search");
        miBinarySearch.setOnAction(e -> {
            int[] nums = text2IntArray(taData.getText());
            try {
                binarySearch(nums);
            } catch (ParseException ex) {
                Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
            }
            taStatus.setText("Binary Search \n"
                    + "---------------");
            taStatus.appendText("\nSearch finished in " + MyTimer.stopMicroTime() + "us\n");
            taStatus.appendText(binarySearchOutput());
            binarySearchClearOutput();
        });
        searchMenu.getItems().add(miBinarySearch);

        /**
         * *********************************************************************
         * Help Menu Section
         */
        MenuItem about = new MenuItem("About");
        about.setOnAction((ActionEvent e) -> {
            String message = "Data Structures and Alogorithms\n";
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION, message);
            aboutAlert.initStyle(StageStyle.TRANSPARENT);
            aboutAlert.setTitle("About");
            aboutAlert.setHeaderText("v1.0 by Matt Getz");
            aboutAlert.showAndWait();
        });
        helpMenu.getItems().add(about);

        return myBar;
    }

    /**
     * *************************************************************************
     * Helper methods
     */
    private void readFile(File myFile) {
        int y = 0;
        try (Scanner sc = new Scanner(myFile)) {
            taData.clear();
            while (sc.hasNextLine()) {
                taData.appendText(sc.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataStructureTester.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeFile(File myFile) {
        try (PrintWriter writer = new PrintWriter(myFile)) {
            for (String line : taData.getText().split("\\n")) {
                writer.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int[] text2IntArray(String s) {
        Scanner sc = new Scanner(s);
        int n = s.split("\n").length;
        int[] nums = new int[n];
        for (int i = 0; sc.hasNextInt(); i++) {
            nums[i] = sc.nextInt();
        }
        return nums;
    }

    public static String intArray2Text(int[] a) {
        StringBuilder sb = new StringBuilder();
        String newLine = "\n";
        for (int value : a) {
            sb.append(Integer.toString(value)).append(newLine);
        }
        return sb.toString();
    }

    public void generateIntegers() throws ParseException {
        TextInputDialog integersAlert = new TextInputDialog("10,000");
        integersAlert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        integersAlert.initStyle(StageStyle.TRANSPARENT);

        integersAlert.setTitle("");
        integersAlert.setHeaderText("Enter number of integers to generate:");
        Optional<String> integersResult = integersAlert.showAndWait();

        String userInput = integersResult.get();
        NumberFormat removeComma = NumberFormat.getNumberInstance(Locale.US);
        int parsedInt = removeComma.parse(userInput).intValue();

        StringBuilder sb = new StringBuilder();
        String newLine = "\n";

        for (int i = 0; i < parsedInt; i++) {
            sb.append(i).append(newLine);
        }
        taData.setText(sb.toString());
    }

    public void generateRandomIntegers() throws ParseException {
        TextInputDialog integersAlert = new TextInputDialog("10,000");
        integersAlert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        integersAlert.initStyle(StageStyle.TRANSPARENT);

        integersAlert.setTitle("");
        integersAlert.setHeaderText("Enter number of random integers to generate:");
        Optional<String> integersResult = integersAlert.showAndWait();

        String userInput = integersResult.get();
        NumberFormat removeComma = NumberFormat.getNumberInstance(Locale.US);
        int parsedInt = removeComma.parse(userInput).intValue();

        StringBuilder sb = new StringBuilder();
        String newLine = "\n";

        for (int i = 0; i < parsedInt; i++) {
            int randomInt = (int) (Math.random() * 999 + 1);
            sb.append(randomInt).append(newLine);
        }
        taData.setText(sb.toString());
    }

    public void randomizeIntegers() {
        int[] nums = text2IntArray(taData.getText());
        Random gen = new Random();
        MyTimer.startMicroTime();
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int j = gen.nextInt(nums.length);
            nums[i] = nums[j];
            nums[j] = temp;
        }
        taStatus.setText("Randomize finished in " + MyTimer.stopMicroTime() + "us");
        taData.setText(intArray2Text(nums));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
