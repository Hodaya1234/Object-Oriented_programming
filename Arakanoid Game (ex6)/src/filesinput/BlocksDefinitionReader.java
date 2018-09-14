package filesinput;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 *
 * @author H
 *
 */
public class BlocksDefinitionReader {
  /**
   * create a block factory from the definitions file of blocks.
   * @param reader
   *          for the definitions file
   * @return a block factory to freate the blocks
   */
  public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
    BufferedReader buffReader = new BufferedReader(reader);
    Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
    Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
    Map<String, String> defaultValues = new HashMap<String, String>();
    try {
      String line = buffReader.readLine();
      while (line != null) {
        if ((line.startsWith("#")) || (line.startsWith(" "))) {
          line = buffReader.readLine();
          continue;
        }
        if (line.startsWith("default")) {
          String[] defaultValuesArray = line.substring("default ".length()).split(" ");
          for (int i = 0; i < defaultValuesArray.length; i++) {
            String[] couple = defaultValuesArray[i].split(":");
            if (couple.length != 2) {
              throw new RuntimeException();
            }
            defaultValues.put(couple[0].trim().toLowerCase(), couple[1].trim().toLowerCase());
          }
        } else if (line.startsWith("bdef")) {
          BlockCreator creator = blockCreatorFromLine(line.substring("bdef ".length()), defaultValues, reader);
          int place = line.indexOf("symbol") + "symbol:".length();
          String symbol = line.substring(place, place + 1);
          blockCreators.put(symbol, creator);
        } else if (line.startsWith("sdef")) {
          String[] spaces = line.substring(line.indexOf("symbol")).split(" ");
          String symbol = spaces[0].split(":")[1];
          int width = Integer.parseInt(spaces[1].split(":")[1]);
          spacerWidths.put(symbol, width);
          }
        line = buffReader.readLine();
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
  }

  /**
   * create a certain block creator from a line.
   * @param line
   *          text of definition
   * @param defaultValues
   *          to insert before reading the specific block definition
   * @param reader
   *          to open the file and get images
   * @return the block creator
   */
  private static BlockCreator blockCreatorFromLine(String line, Map<String, String> defaultValues, Reader reader) {
    BlockCreating creator = new BlockCreating();
    String[] rawValues = line.split(" ");
    for (String key : defaultValues.keySet()) {
      if (key.startsWith("height")) {
        String height = defaultValues.get(key).trim();
        creator.setHeight(Integer.parseInt(height));
      } else if (key.startsWith("width")) {
        String width = defaultValues.get(key).trim();
        creator.setWidth(Integer.parseInt(width));
      } else if (key.startsWith("hit_points")) {
        String hits = defaultValues.get(key).trim();
        creator.setHits(Integer.parseInt(hits));
      } else if (key.startsWith("stroke")) {
        String stroke = defaultValues.get(key).trim();
        creator.setStroke(new ColorsParser().colorFromString(stroke));
      } else if (key.startsWith("fill-")) {
        String sNumber = key.split("-")[1];
        int iNumber = Integer.parseInt(sNumber);
        String filling = defaultValues.get(key).trim();
        addFillingToCreator(reader, filling, iNumber, creator);
      } else if (key.startsWith("fill:")) {
        String filling = defaultValues.get(key).trim();
        addFillingToCreator(reader, filling, 0, creator);
      }
    }

    for (int i = 0; i < rawValues.length; i++) {
      if (rawValues[i].startsWith("height")) {
        String height = rawValues[i].split(":")[1].trim();
        creator.setHeight(Integer.parseInt(height));
      } else if (rawValues[i].startsWith("width")) {
        String width = rawValues[i].split(":")[1].trim();
        creator.setWidth(Integer.parseInt(width));
      } else if (rawValues[i].startsWith("hit_points")) {
        String hits = rawValues[i].split(":")[1].trim();
        creator.setHits(Integer.parseInt(hits));
      } else if (rawValues[i].startsWith("stroke")) {
        String stroke = rawValues[i].split(":")[1].trim();
        creator.setStroke(new ColorsParser().colorFromString(stroke));
      } else if (rawValues[i].startsWith("fill-")) {
        String sNumber = rawValues[i].split(":")[0].split("-")[1].trim();
        int iNumber = Integer.parseInt(sNumber);
        String filling = rawValues[i].split(":")[1].trim();
        addFillingToCreator(reader, filling, iNumber, creator);
      } else if (rawValues[i].startsWith("fill:")) {
        String filling = rawValues[i].split(":")[1].trim();
        addFillingToCreator(reader, filling, 0, creator);
      }
    }
    return creator;
  }

  /**
   * add an image to fill the block.
   * @param reader
   *          to open the image file
   * @param filling
   *          string that describes it
   * @param hits
   *          number of hits left to display a certain filling
   * @param creator
   *          that will be added to the factory
   */
  public static void addFillingToCreator(Reader reader, String filling, int hits, BlockCreating creator) {
    if (filling.startsWith("color")) {
      creator.addFillColor(hits, new ColorsParser().colorFromString(filling));
    } else if (filling.startsWith("image")) {
      String filename = filling.substring(filling.lastIndexOf("(") + 1, filling.indexOf(")"));
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
      try {
        Image image = ImageIO.read(is);
        creator.addFillImage(hits, image);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
