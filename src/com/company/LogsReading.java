/*
 *
 * Classname: Main
 *
 * @version 24.06.2020
 * @author Serhii Tronevych
 * Module 4 task 2
 *
 * Home Task: Multi-threading.
 *
 * 1. Use the file from the previous task - logs.txt.
 *
 * 2. Create a class that manages logs in this file.
 *
 * 3. Create a method that finds all the ERROR logs for a specific date and
 *  write them into a specific file (name = ERROR  + Date  + .log)
 *
 * 4. In your main class develop a functionality to create 5 such a files
 * for 5 different days. Launch them in consistent way (one after another).
 *
 * 5. Repeat the above task in parallel way. Multi-threading.
 *
 * 6. Compare the results.
 *
 *
 */
package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class LogsReading {

    private String dateTimeLog;

    // create empty Constructor
    public LogsReading() {
    }

    // create Constructor with parameter dateTimeLog
    public LogsReading(String dateTimeLog) {
        this.dateTimeLog = dateTimeLog;
    }

    // create Getter for parameter dateTimeLog
    public String getDateTimeLog() {
        return dateTimeLog;
    }

    // create Getter for parameter dateTimeLog
    public void setDateTimeLog(String dateTimeLog) {
        this.dateTimeLog = dateTimeLog;
    }

    /*
     * @param String str - a date of searching in a format yyyy-mm-dd
     */

    public void getLogsByDate(String str) throws IOException {

        // @param start return time start
        LocalDateTime start = LocalDateTime.now();

        System.out.println(str + "  start searching - " + start);

        List<String> errorLinesList = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\logs.txt"))
                .filter(line -> line.contains(str))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        int countLines = errorLinesList.size();

        LocalDateTime finish = LocalDateTime.now();

        long duration = ChronoUnit.MILLIS.between(start, finish);

        System.out.println("---------------------ERRORS---------------------");
        System.out.println("Have " + countLines + " ERROR lines." + " on " + str);
        System.out.println("time for which it is executed: " + duration);

        String stringData = "";
        for (String line : errorLinesList) {
            stringData += line + "\n";
        }

        String outputPath = "C:\\Users\\Administrator\\Desktop\\ERROR-date " + str + ".txt";

        Files.write(Paths.get(outputPath), stringData.getBytes());

    }


}