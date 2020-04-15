package levelsio;

import levels.LevelInformation;

import java.util.List;

/**
 * read a level from a file.
 */
public class LevelSpecificationReader {
    /**
     * get a list of level information by a reader.
     * @param reader the reader
     * @return a list of level information
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        LevelFile levelReadFiles = new LevelFile(reader);
        try {
            levelReadFiles.getLevelsInfo();
        } catch (Exception error) {
            System.out.println("Error level specification reader");
            error.printStackTrace();
        }
        return levelReadFiles.getLevelsList();
    }
}
