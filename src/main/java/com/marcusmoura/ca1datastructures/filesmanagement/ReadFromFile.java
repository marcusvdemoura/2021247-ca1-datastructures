package com.marcusmoura.ca1datastructures.filesmanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromFile {
    private ArrayList<String[]> lines = new ArrayList<>();



    public ReadFromFile() {


    }

    public ArrayList<String[]> getLines(String file){

        String line;

        try {
            FileReader fileReader =
                    new FileReader(file);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            bufferedReader.readLine();
            String line1 = null;

            while((line = bufferedReader.readLine()) != null) {

                /*
                As the csv file contains people separated by a comma, we can
                split the line into a String array.
                I created an ArrayList of String arrays to store the data. Each String array
                contains the data necessary to create a person object.
                 */

                String[] lineSeparated = line.split(",");
                lines.add(lineSeparated);


            }



            bufferedReader.close();

            return lines;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            file + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + file + "'");

        }

        return lines;
    }

    public ArrayList<String[]> getLines() {
        return lines;
    }
}

