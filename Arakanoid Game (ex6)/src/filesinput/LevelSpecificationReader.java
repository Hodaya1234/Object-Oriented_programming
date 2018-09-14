package filesinput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import game.LevelInformation;
import gameobjects.Block;

/**
 *
 */
public class LevelSpecificationReader {

  private BlocksFromSymbolsFactory blockFactory;
  private int xBlock;
  private int yBlock;
  private int rowHeight;
  // private int numBlocks;
  private List<Block> blocklist;

  /**
   * Create a list of level information from a file.
   * @param reader
   *          reader of description file
   * @return list of level information
   */
  public List<LevelInformation> fromReader(java.io.Reader reader) {
    ArrayList<LevelInformation> levelList = new ArrayList<LevelInformation>();
    BufferedReader buffReader = new BufferedReader(reader);

    ArrayList<ArrayList<String>> allLevelsLines = linesArray(buffReader);
    for (ArrayList<String> lines : allLevelsLines) {
      levelList.add(stringToInfo(lines, reader));
    }
    return levelList;
  }

  /**
   * turning an array of lines describing a level to level information.
   * @param lines
   *          the array of lines of the file
   * @param reader
   *          of the file
   * @return level information
   */
  public LevelInfo stringToInfo(ArrayList<String> lines, Reader reader) {
    LevelInfo info = new LevelInfo();
    for (String line : lines) {
      if (line.startsWith("START_LEVEL")) {
        continue;
      } else if (line.startsWith("level_name:")) {
        info.setLevelName(line.substring("level_name:".length()));
      } else if (line.startsWith("ball_velocities:")) {
        info.setBallVelocities(line.substring("ball_velocities:".length()));
      } else if (line.startsWith("background:")) {
        String background = line.split(":")[1];
        if (background.contains("image")) {
          String path = background.substring(background.lastIndexOf("(") + 1, background.indexOf(")"));
          info.setBackground(new CreateBackground().createBackgroundFromFile(path));
        } else {
          info.setBackground(new CreateBackground().createBackgroundFromColor(background));
        }
      } else if (line.startsWith("paddle_speed:")) {
        info.setPaddleSpeed(Integer.parseInt(line.substring("paddle_speed:".length())));
      } else if (line.startsWith("paddle_width:")) {
        info.setPaddleWidth(Integer.parseInt(line.substring("paddle_width:".length())));
      } else if (line.startsWith("block_definitions")) {
        String blockDefFile = line.split(":")[1];
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefFile);
        Reader blockDefReader = new InputStreamReader(is);
        blockFactory = BlocksDefinitionReader.fromReader(blockDefReader);
      } else if (line.startsWith("blocks_start_x")) {
        this.xBlock = Integer.parseInt(line.split(":")[1].trim());
      } else if (line.startsWith("blocks_start_y")) {
        this.yBlock = Integer.parseInt(line.split(":")[1].trim());
      } else if (line.startsWith("row_height")) {
        this.rowHeight = Integer.parseInt(line.split(":")[1].trim());
        // }
        // else if (line.startsWith("num_blocks")) {
        // this.numBlocks = Integer.parseInt(line.split(":")[1].trim());
      } else if (line.contains("START_BLOCKS")) {
        ListIterator<String> lineIterator = lines.listIterator(lines.indexOf(line) + 1);
        line = lineIterator.next();
        blocklist = new ArrayList<Block>();
        String symbol;
        while (!line.contains("END_BLOCKS") && (!line.contains("END_LEVEL"))) {
          int tempX = xBlock;
          for (int i = 0; i < line.length(); i++) {
            symbol = line.substring(i, i + 1);
            if (blockFactory.isSpaceSymbol(symbol)) {
              tempX += blockFactory.getSpaceWidth(symbol);
            } else if (blockFactory.isBlockSymbol(symbol)) {
              blocklist.add(blockFactory.getBlock(symbol, tempX, yBlock));
              tempX += blockFactory.getBlockDx(symbol);
            }
          }
            yBlock += rowHeight;
          line = lineIterator.next();
        }
        info.setBlocks(blocklist);
      }
    }
    return info;
  }

  /**
   * turn the file to a list of a lines of each level.
   * @param reader
   *          of the file
   * @return an array list of all the array list of every level
   */
  private ArrayList<ArrayList<String>> linesArray(BufferedReader reader) {
    ArrayList<ArrayList<String>> allLevelsLines = new ArrayList<ArrayList<String>>();
    try {
      String line = reader.readLine();
      while (line != null) {
        ArrayList<String> levelLines = new ArrayList<String>();
        while (!line.contains("END_LEVEL")) {
          levelLines.add(line);
          line = reader.readLine();
        }
        allLevelsLines.add(levelLines);
        line = reader.readLine();
      }
    } catch (Exception e) {
      System.err.println("error");
    } finally {
    try {
      if (reader != null) {
        reader.close();
      }
    } catch (IOException e) {
      System.err.println("Failed closing file");
      }
    }
    return allLevelsLines;
  }


}
