package unit11_zipcodes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Unit11_ZipCodes {

    static String[] zipCodes;
    static String[] cityNames;

    public static void main(String[] args) {
        readCSVfile();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the zipcode to lookup: ");
        String myZip = input.nextLine();

        int foundIndex = sequentialSearch(myZip);
        //int foundIndex = binarySearch(myZip);

        if (foundIndex>=0) {
            // found the city
            String myCity = cityNames[foundIndex];
            System.out.println("Found the City: "+myCity);
        } else {
            // zip code not found 
            System.out.println("Zipcode not found ");
        }
    }  // end of main

    private static int sequentialSearch(String myZip) {
        // Search for the zip code
        System.out.print("Sequential Searching . . . ");
        int index = 0;
        while (!myZip.equals(zipCodes[index])) {
            index++;
        }
        System.out.println("Found match at index : " + index);
        return index;
    }  // end of sequentialSearch
    
    private static int binarySearch(String myZip) {
        int lowestPossibleLoc = 0;
        int highestPossibleLoc = zipCodes.length - 1;

        while (highestPossibleLoc >= lowestPossibleLoc) {
            int middle = (lowestPossibleLoc + highestPossibleLoc) / 2;
            if (myZip.equals(zipCodes[middle])) {
                // myZip has been found at this index!
                return middle;
            } else if (myZip.compareTo(zipCodes[middle])<0) {
                // eliminate locations >= middle
                highestPossibleLoc = middle - 1;
            } else {
                // eliminate locations <= middle
                lowestPossibleLoc = middle + 1;
            }
        }
        // At this point, highestPossibleLoc < lowestPossibleLoc,
        // which means that myZip is known to be not in the array.  Return
        // a -1 to indicate that myZip could not be found in the array.
        return -1;
    }  // end of binarySearch

    // ================ ignore this method that reads in the Zip Code file ====================
    private static void readCSVfile() {
        //String csvFile = "MinnesotaZipCodes.csv"; // Replace with the path to your CSV file
        String csvFile = "MinnesotaZipCodes_Sorted.csv"; // Replace with the path to your CSV file

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String[]> csvData = new ArrayList<>();

            // Read CSV data line by line
            String line;
            while ((line = br.readLine()) != null) {
                String[] entry = line.split(",");
                csvData.add(entry);
            }

            // Assuming the CSV structure is ZipCode, City
            int numEntries = csvData.size() - 1; // Subtracting 1 to exclude the header
            zipCodes = new String[numEntries];
            cityNames = new String[numEntries];

            // Extract zip codes and city names
            for (int i = 1; i < csvData.size(); i++) {
                String[] entry = csvData.get(i);
                zipCodes[i - 1] = entry[0].trim();
                cityNames[i - 1] = entry[1].trim();
            }
//            System.out.println("Zip Codes: " + String.join(", ", zipCodes));
//            System.out.println("City Names: " + String.join(", ", cityNames));
        } catch (IOException e) {
            System.out.println("Exception - " + e.getMessage());
        }
    }  // end of readCSVfile

}
