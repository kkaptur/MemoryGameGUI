package MemoryGame;
import java.io.*;
import java.util.ArrayList;

public class Result {
    String userName;
    int gameTime;
    int boardSize;
    int points;
    static final String resultsPath = "resources/results.txt";

    public Result(String userName, long gameTime, int boardSize) {
        this.userName = userName;
        this.gameTime = (int) gameTime;
        this.boardSize = boardSize;
        points = (int) (100 * boardSize / gameTime);
        saveResult();
    }

    public String toString() {
        return userName + " (time: " + String.format("%02d:%02d", gameTime / 60, gameTime % 60) + ", board size: " + boardSize + ", points: " + points + ")";
    }

    public void saveResult() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultsPath, true)));
            writer.write(this.toString() + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList getResultsList() {

        ArrayList<String> resultList = new ArrayList<>();
        try
                (BufferedReader br = new BufferedReader(new FileReader("resources/results.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}