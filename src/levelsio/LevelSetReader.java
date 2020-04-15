package levelsio;

import behavior.AnimationRunner;
import behavior.GameFlow;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import menu.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * read a level set file.
 */
public class LevelSetReader {
    private LineNumberReader reader;
    private List<String> chars;
    private List<String> names;
    private List<String> files;

    /**
     * constructor.
     * @param read reader to the file
     */
    public LevelSetReader(Reader read) {
        this.reader = new LineNumberReader(read);
        this.chars = new ArrayList<>();
        this.names = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    /**
     * read the date and save it to the lists.
     * @throws IOException if something bad happened
     */
    public void readDate() throws IOException {
        String line;
        //ignore the first line with the max size
        while ((line = this.reader.readLine()) != null) {
            if (this.reader.getLineNumber() % 2 == 1) {
                 this.chars.add(line.substring(0, 1));
                 this.names.add(line.substring(2));
            } else {
                this.files.add(line);
            }
        }
    }

    /**
     * create a menu (actually a sub menu) of the levels.
     * @param as the animation runner.
     * @return a menu
     */
    public Menu createMenu(AnimationRunner as) {
        try {
            this.readDate();
        } catch (Exception error) {
            System.out.println("Error level set reader");
            error.printStackTrace();
        }

        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(as);
        for (int i = 0; i < this.chars.size(); i++) {
            String chr = this.chars.get(i);
            String name = this.names.get(i);
            String file = this.files.get(i);
            List<LevelInformation> levels = new ArrayList<>();
            try {
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(file);
                Reader read = new InputStreamReader(inputStream);
                LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                levels = levelSpecificationReader.fromReader(read);
            } catch (Exception error) {
                System.out.println("Error loading LevelSetReader file");
            }
            subMenu.addSelection(chr, name, new GameFlow(as, as.getGui().getKeyboardSensor(), levels));
        }
        return subMenu;
    }
}
