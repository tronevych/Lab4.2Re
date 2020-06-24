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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {

    static class MyThread extends Thread {

        private String dateLog;

        MyThread(String dateLog){
            this.dateLog = dateLog;
        }

        /**
         *  A method which initialize LogsReading class and calls its method "getErrorByDate"
         */
        @Override
        public void run() {
            LogsReading service = new LogsReading();
            try {
                service.getLogsByDate(dateLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        LogsReading service1 = new LogsReading();

        // @param startConsistent return time start consistent way
        LocalDateTime startConsistent = LocalDateTime.now();

        // Create 5 files for 5 different days
        new MyThread("2019-11-23").start();
        new MyThread("2019-12-01").start();
        new MyThread("2020-01-09").start();
        new MyThread("2020-02-15").start();
        new MyThread("2020-03-29").start();

        // @param finishConsistent return time finish consistent way
        LocalDateTime finishConsistent = LocalDateTime.now();

        long consistentTime = ChronoUnit.MILLIS.between(startConsistent, finishConsistent);

        System.out.println("--------------Start Searching--------------");
        System.out.println("Total duration: "
                + consistentTime + " ms");

        // @param startParallel return time start parallel way
        LocalDateTime startParallel = LocalDateTime.now();

        // creating 5 such a files for 5 different days for parallel way
        service1.getLogsByDate("2019-11-23");
        service1.getLogsByDate("2019-12-01");
        service1.getLogsByDate("2020-01-09");
        service1.getLogsByDate("2020-02-15");
        service1.getLogsByDate("2020-03-29");

        // @param finishParallel return time finish parallel way
        LocalDateTime finishParallel = LocalDateTime.now();

        long parallelTime = ChronoUnit.MILLIS.between(startParallel, finishParallel);

        System.out.println("Total duration: "
                + parallelTime + " ms");

        /*
         * Comparing the duration results of 2 methods
         */
        if (consistentTime > parallelTime) {
            System.out.println("\n Consequent have best time than Multi-Threading");
        } else if (consistentTime < parallelTime) {
            System.out.println("\n Multi-Threading have best time  than Consequent");
        } else {
            System.out.println("\n Time of this methods is the same");
        }

    }
}