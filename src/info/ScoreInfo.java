package info;

/**
 * score info for the high score table.
 */
public class ScoreInfo implements Comparable<ScoreInfo> {
    private String name;
    private int score;

    /**
     * constructor.
     * @param name the name of the high scorer
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * return the name of the scorer.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * return the score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(ScoreInfo other) {
        if (this.getScore() > other.getScore()) {
            return 1;
        }
        if (other.getScore() > this.getScore()) {
            return -1;
        }
        return 0;
    }
}