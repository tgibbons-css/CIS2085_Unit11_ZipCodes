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

        // Search for the zip code
        System.out.print("Searching . . . ");
        for (int index = 0; index < zipCodes.length; index++) {
            if (myZip.equals(zipCodes[index])) {
                String myCity = cityNames[index];
                System.out.println("Found matching Zip code : " + myCity);
            }
        }

    }

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

            // Print the extracted data
//            System.out.println("Zip Codes: " + String.join(", ", zipCodes));
//            System.out.println("City Names: " + String.join(", ", cityNames));
        } catch (IOException e) {
            System.out.println("Exception - " + e.getMessage());
        }
    }

}
