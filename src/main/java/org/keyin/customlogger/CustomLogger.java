package org.keyin.customlogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomLogger {
    private static final String filePath = "applicationlogs.txt";
    private static BufferedWriter bufferedWriter;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    // static initializer block
    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Logs an error message to the application log file.
     * This method should write the provided error message to the log file,
     * prefixed with an appropriate error label (e.g., "ERROR:").
     * Ensure the message is appended to the file and handle any I/O exceptions.
     * @param message The error message to log.
     */
    public  void logError(String message){
        // TODO: Write the error message to the log file, prefixed with "ERROR:"
        try{
            bufferedWriter.write("ERROR: " + message + " Date: " + date + " Time: " + time + " \n");
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Logs an informational message to the application log file.
     * This method should write the provided informational message to the log file,
     * prefixed with an appropriate info label (e.g., "INFO:").
     * Ensure the message is appended to the file and handle any I/O exceptions.
     * @param message The info message to log.
     */
    public  void logInfo(String message){
        // TODO: Write the info message to the log file, prefixed with "INFO:"
        try{
            bufferedWriter.write("INFO: " + message + " Date: " + date + " Time: " + time + " \n");
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
