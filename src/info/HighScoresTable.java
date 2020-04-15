package info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * High Score table of the game.
 */
public class HighScoresTable {
    private List<ScoreInfo> scoreTable;
    private int sizeLimit;
    private static final String FILE_NAME = "highscores";

    /**
     * create an empty high-scores table with the specified sizeLimit.
     * The sizeLimit means that the table holds up to sizeLimit top scores.
     *
     * @param sizeLimit - size limit
     */
    public HighScoresTable(int sizeLimit) {
        this.scoreTable = new ArrayList<>();
        this.sizeLimit = sizeLimit;
    }

    /**
     * adding a score to the table.
     *
     * @param score the score to add
     */
    public void add(ScoreInfo score) {
        if (this.getRank(score.getScore()) < this.sizeLimit) {
            this.scoreTable.add(score);
        }
        if (this.getHighScores().size() > this.sizeLimit) {
            List sortedList = this.getHighScores();
            this.scoreTable.remove(sortedList.get(this.sizeLimit));
        }
        File file = new File(this.FILE_NAME);
        try {
            this.save(file);
        } catch (IOException error) {
            System.out.println("ERROR");
        }
    }

    /**
     * return the max size of the table.
     *
     * @return the max size of the table
     */
    public int size() {
        return this.sizeLimit;
    }


    /**
     * Return the current high scores.
     * The list is sorted such that the highest is firs
     *
     * @return a sorted table list
     */
    public List<ScoreInfo> getHighScores() {
        List<ScoreInfo> sortedList = new ArrayList<>();
        sortedList.addAll(this.scoreTable);
        Collections.sort(sortedList, Collections.reverseOrder());
        return sortedList;
    }

    /**
     * return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `sizeLimit` means the score will be lowest.
     * Rank > `sizeLimit` means the score is too low and will not be added to the list.
     *
     * @param score the score to check
     * @return the rank that the score will (or not will) be
     */
    public int getRank(int score) {
        //sort the score table
        List<ScoreInfo> sortedList = new ArrayList<>();
        sortedList.addAll(this.scoreTable);
        Collections.sort(sortedList, Collections.reverseOrder());
        //get only the score value
        List<Integer> scoreValue = new ArrayList<>();
        for (int i = 0; i < sortedList.size(); i++) {
            scoreValue.add(sortedList.get(i).getScore());
        }
        //find the place of the input score
        scoreValue.add(score);
        Collections.sort(scoreValue, Collections.reverseOrder());
        return scoreValue.indexOf(score);

    }

    /**
     * Clear the table.
     */
    public void clear() {
        this.scoreTable.clear();
    }


    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename the file name.
     * @throws IOException if something goes wrong
     */
    public void load(File filename) throws IOException {
        BufferedReader inputStream = null;
        inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String line;
        //ignore the first line with the max sieze
        line = inputStream.readLine();
        while ((line = inputStream.readLine()) != null) {
            String[] arr = line.split(" ");
            ScoreInfo scoreInfo = new ScoreInfo(arr[0], Integer.parseInt(arr[1]));
            this.scoreTable.add(scoreInfo);
        }
        inputStream.close();
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename the file name.
     * @throws IOException if something goes wrong
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        //the first line be the max size of the score table
        os.printf("%d", this.size());
        os.println();
        for (int i = 0; i < this.scoreTable.size(); i++) {
            os.printf("%s %d", this.scoreTable.get(i).getName(), this.scoreTable.get(i).getScore());
            os.println();
        }
        os.close();

    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with reading it, an empty table is returned.
     *
     * @param filename the file name
     * @return a high score table
     */
    public static HighScoresTable loadFromFile(File filename) {
        BufferedReader is = null;
        try {
            is = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = is.readLine();
            int numOfLines = Integer.parseInt(line);
            HighScoresTable highScoresTable = new HighScoresTable(numOfLines);
            highScoresTable.load(filename);
            is.close();
            return highScoresTable;
        } catch (IOException e) {
            return new HighScoresTable(0);
        }
    }

    /**
     * get the file name.
     * @return the file name
     */
    public static String getFileName() {
        return FILE_NAME;
    }
}
