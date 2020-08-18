package phonebook;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static long timeLinearSearch = 0;
    public static long timeBubbleSort = 0;
    public static long timeJumpSearch = 0;
    public static long timeQuickSort = 0;
    public static long timeBinarySearch = 0;
    public static long creatingTimeHash = 0;
    public static long searchingTimeHash = 0;

    public static void main(String[] args) throws InterruptedException {
        String[] persons = readFile("/Users/a18574357/Downloads/find.txt");
        String[] base = readFile("/Users/a18574357/Downloads/directory.txt");


        int founds = 0;
        FirstPart firstPart = new FirstPart(base);
        System.out.println("Start searching (linear search)...");
        timeLinearSearch = System.currentTimeMillis();
        founds = firstPart.startSearch(persons);
        timeLinearSearch = System.currentTimeMillis() - timeLinearSearch;
        printTotalFound(founds, timeLinearSearch);




        System.out.println();
        SecondPart secondPart = new SecondPart(base);
        System.out.println("Start searching (bubble sort + jump search)...");

        timeBubbleSort = System.currentTimeMillis();
        secondPart.startSort();
        timeBubbleSort = System.currentTimeMillis() - timeBubbleSort;

        timeJumpSearch = System.currentTimeMillis();
        founds = secondPart.startSearch(persons);
        timeJumpSearch = System.currentTimeMillis() - timeJumpSearch;

        printTotalFound(founds, timeBubbleSort, timeJumpSearch, secondPart.isSorted());

        System.out.println();
        ThirdPart thirdPart = new ThirdPart(base);
        System.out.println("Start searching (quick sort + binary search)...");
        timeQuickSort = System.currentTimeMillis();
        thirdPart.startSort();
        timeQuickSort = System.currentTimeMillis() - timeQuickSort;
        timeBinarySearch = System.currentTimeMillis();
        founds = thirdPart.startSearch(persons);
        timeBinarySearch = System.currentTimeMillis() - timeBinarySearch;
        printTotalFound(++founds, timeQuickSort, timeBinarySearch, true);


        System.out.println();
        System.out.println("Start searching (hash table)...");
        creatingTimeHash = System.currentTimeMillis();
        HashRealisation hashRealisation = new HashRealisation(base);
        creatingTimeHash = System.currentTimeMillis() - creatingTimeHash;
        searchingTimeHash = System.currentTimeMillis();
        founds = hashRealisation.startSearching(persons);
        searchingTimeHash = System.currentTimeMillis() - searchingTimeHash;
        printTotalHash(++founds);
    }



    public static String parseDate(Long endTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("m:s:ms");
        String[] time = dateFormat.format(new Date(endTime)).split(":");
        return time[0] + " min. " + time[1] + " sec. " + time[2] + " ms.";
    }

    public static void printTotalFound(int founds, long searchTime){
        System.out.println("Found " + founds + " / 500 entries. Time taken: " + parseDate(searchTime));
    }

    public static void printTotalHash(int founds){
        System.out.println("Found " + founds + " / 500 entries. Time taken: " + parseDate(creatingTimeHash + searchingTimeHash));
        System.out.println("Creating time: " + parseDate(creatingTimeHash));
        System.out.println("Searching time: " + parseDate(searchingTimeHash));
    }

    public static void printTotalFound(int founds, long sortTime, long searchTime, boolean isItSorted){
        System.out.println("Found " + founds + " / 500 entries. Time taken: " + parseDate(sortTime + searchTime));
        String sortingStatus = isItSorted ? "" : " - STOPPED, moved to linear search";
        System.out.println("Sorting time: " + parseDate(sortTime) + sortingStatus);
        System.out.println("Searching time: " + parseDate(searchTime));
    }

    public static String[] readFile(String file){
        try {
            return readFileAsString(file).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
