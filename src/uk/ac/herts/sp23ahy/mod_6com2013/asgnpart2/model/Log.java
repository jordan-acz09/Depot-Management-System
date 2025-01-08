package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance;
    private StringBuffer logBuffer;

    private Log() {
        logBuffer = new StringBuffer();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    public void saveLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(logBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}