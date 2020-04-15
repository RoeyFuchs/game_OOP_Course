package levelsio;

import java.io.IOException;

/**
 * block definition by file.
 */
public class BlocksDefinitionReader {
    /**
     * get a block factory by reader of a file.
     * @param reader the reaer of the file.
     * @return a block factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFile blocksFile = new BlocksFile(reader);
        try {
            return blocksFile.getBlocksInfo();
        } catch (IOException error) {
            System.out.println("Error Block Definition reader");
        }
        return null;
    }
}
