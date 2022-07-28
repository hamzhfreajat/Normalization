package com.progressoft.tools;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class NormalizerImpl implements Normalizer{
    int indexOfColumn;
    ArrayList<BigDecimal> integerData = new ArrayList<>();
    ArrayList<ArrayList<String>> twoDimArray = new ArrayList<>();


    @Override
    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize) {
        String prefix = "_z" ;

        // Read  the csv file and convert it
        readFileAndConvertToArray(csvPath , colToStandardize , prefix);

        // Initialize scoringSummary instance in order to use the functions inside it
        ScoringSummary scoringSummary = new ScoringSummaryImpl(integerData);

        BigDecimal mean = scoringSummary.mean();
        BigDecimal standardDeviation = scoringSummary.standardDeviation();
        BigDecimal med = scoringSummary.median();
        System.out.println(med);

        // Create new twoDimArray and add Normalized column to array
        for (int i = 1; i <= integerData.size(); i++) {
            twoDimArray.get(i).add(indexOfColumn + 1  , String.valueOf(integerData.get(i-1).subtract(mean).divide(standardDeviation,RoundingMode.HALF_EVEN)));
        }


        // Convert two dim array to file
        convertListToFile(destPath);

        return scoringSummary;
    }

    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) {

        String prefix = "_mm" ;

        // Read  the csv file and convert it
        readFileAndConvertToArray(csvPath , colToNormalize , prefix);


        // Initialize scoringSummary instance in order to use the functions inside it
        ScoringSummary scoringSummary = new ScoringSummaryImpl(integerData);

        BigDecimal min = scoringSummary.min();
        BigDecimal max = scoringSummary.max();


        // Create new twoDimArray and add Normalized column to array

        for (int i = 1; i <= integerData.size(); i++) {
            twoDimArray.get(i).add(indexOfColumn + 1  , String.valueOf(integerData.get(i-1).subtract(min).divide(max.subtract(min),RoundingMode.HALF_EVEN)));
        }


        // Convert two dim array to file
        convertListToFile(destPath);

        return scoringSummary;
    }


    /**
     * Read file and set the array
     * @param resource
     * @param colToNormalize
     * @param prefix
     */
    private void readFileAndConvertToArray(Path resource , String colToNormalize , String prefix){

        List<String> data;

        try {
            data = Files.readAllLines(new File(resource.toUri().getPath()).toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("source file not found");
        }

        String[] firstLine = data.get(0).split(",");
        ArrayList<String> targetColumn = new ArrayList<>(List.of(firstLine));
        indexOfColumn = targetColumn.indexOf(colToNormalize);
        if (indexOfColumn == -1 ){
            throw new IllegalArgumentException("column " +  colToNormalize + " not found");
        }

        targetColumn.add(indexOfColumn + 1 , colToNormalize + prefix);
        twoDimArray.add(targetColumn);
        ArrayList<String> row;


        for (int i = 1; i < data.size(); i++) {
            row = new ArrayList<>(Arrays.asList(data.get(i).split(",")));
            BigDecimal integer = BigDecimal.valueOf(Long.parseLong(row.get(indexOfColumn))).setScale(2 , RoundingMode.HALF_EVEN );
            twoDimArray.add(row);
            integerData.add(integer);
        }

    }

    /**
     * Extract the two dim array to new file
     * @param resource
     */
    public final void convertListToFile(Path resource) {

        String absolutePath = resource.toFile().getAbsolutePath() ;
        File file = new File(absolutePath);
        System.out.println(absolutePath);
        FileWriter writer = null;
        try {
            file.createNewFile();
            writer = new FileWriter(absolutePath);
            for(List<String> str: twoDimArray) {
                String line = String.join(",", str);
                if (line.charAt(line.length() - 1) == ',') line = line.substring( 0,line.length() - 1);
                writer.write(line+ System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}




