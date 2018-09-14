package animation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author H
 *
 */
public class HighScoresTable {

  private LinkedList<ScoreInfo> scores;
  public static final int SIZE = 5;

  /**
   * Create an empty high-scores table with the specified size.
   * @param size
   *          maximum amount of scores to show
   */
  public HighScoresTable(int size) {
    this.scores = new LinkedList<ScoreInfo>();
   }

  /**
   * Add a high-score.
   * @param score
   *          to add
   */
  public void add(ScoreInfo score) {
    int index = 0;
    if (this.scores.isEmpty()) {
      this.scores.add(score);
      return;
  }
    for (ScoreInfo previous : this.scores) {
      if (index > SIZE) {
        return;
      }
      if (score.getScore() >= previous.getScore()) {
        this.scores.add(index, score);
        return;
      }
      index++;
    }
    this.scores.add(score);

  }

  /**
   *
   * @return table size.
   */
  public int getsize() {
    return this.scores.size();
  }

  /**
   * Return the current high scores.
   * @return the list sorted
   */
  public List<ScoreInfo> getHighScores() {
    return this.scores;
  }

  /**
   * return the rank of the current score: where will it be on the list if
   * added?
   * @param score
   *          of the game
   * @return the rank between the current table
   */
  public int getRank(int score) {
    int index = 1;
    for (ScoreInfo previous : this.scores) {
      if (index > SIZE) {
        return -1;
      } else if (score >= previous.getScore()) {
        return index;
      }
      index++;
    }
    return index;
  }

  /**
   * Clears the table.
   */
   public void clear() {
  this.scores = new LinkedList<ScoreInfo>();
   }

  /**
   * Load table data from file. Current table data is cleared.
   * @param filename
   *          to load from
   * @throws IOException
   *           if there is an error with the file
   */
  @SuppressWarnings("unchecked")
  public void load(File filename) throws IOException {
    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(new FileInputStream(filename));
      this.scores = (LinkedList<ScoreInfo>) objectInputStream.readObject();
    } catch (FileNotFoundException e) { // Can't find file to open
      System.err.println("Unable to find file: " + filename);
      return;
    } catch (ClassNotFoundException e) { // The class in the stream is unknown
                                         // to the JVM
      System.err.println("Unable to find class for object in file: " + filename);
      return;
    } catch (IOException e) { // Some other problem
      System.err.println("Failed reading object");
      e.printStackTrace(System.err);
      return;
      } catch (Exception e) {
      System.out.println("Error load");
      } finally {
          try {
        if (objectInputStream != null) {
          objectInputStream.close();
        }
      } catch (IOException e) {
        System.err.println("Failed closing file: " + filename);
      }
    }
  }

  /**
   * Save table data to the specified file.
   * @param filename
   *          to save into
   * @throws IOException
   *           if error in file
   */
   public void save(File filename) throws IOException {
    ObjectOutputStream objectOutputStream = null;
       try {
           objectOutputStream = new ObjectOutputStream(
                                  new FileOutputStream(filename));
           try {
           objectOutputStream.writeObject(scores);
           } catch (Exception e) {
        System.err.println("Failed writing object");
           }
       } catch (IOException e) {
           System.err.println("Failed saving object");
           e.printStackTrace(System.err);
       } finally {
           try {
        if (objectOutputStream != null) {
                   objectOutputStream.close();
               }
           } catch (IOException e) {
               System.err.println("Failed closing file: " + filename);
           }
       }
   }

  /**
   * Read a table from file and return it. If the file does not exist, or there
   * is a problem with reading it, an empty table is returned.
   * @param filename
   *          file to load from
   * @return the table
   */
  public static HighScoresTable loadFromFile(File filename) {
    HighScoresTable table = new HighScoresTable(SIZE);
    if (!filename.exists()) {
      try {
        table.save(filename);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        table.load(filename);
      } catch (Exception e) {
        System.err.println("Error loading file");
      }
    }
  return table;
   }
}