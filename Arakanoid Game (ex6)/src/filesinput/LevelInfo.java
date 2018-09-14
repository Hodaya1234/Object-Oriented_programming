package filesinput;

import java.util.ArrayList;
import java.util.List;

import game.LevelInformation;
import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Velocity;

/**
 *
 * @author H
 *
 */
public class LevelInfo implements LevelInformation {

  private String levelName;
  private List<Velocity> ballVelocities;
  private Sprite background;
  private int paddleSpeed;
  private int paddleWidth;
  private List<Block> blocks;


  @Override
  public int numberOfBalls() {
    return ballVelocities.size();
  }


  @Override
  public List<Velocity> initialBallVelocities() {
    return ballVelocities;
  }

  @Override
  public int paddleSpeed() {
    return paddleSpeed;
  }

  @Override
  public int paddleWidth() {
    return paddleWidth;
  }

  @Override
  public String levelName() {
    return levelName;
  }

  @Override
  public Sprite getBackground() {
    return background;
  }

  @Override
  public List<Block> blocks() {
    return blocks;
  }

  @Override
  public int numberOfBlocksToRemove() {
    return blocks.size();
  }

  /**
   * @param name
   *          the levelName to set
   */
  public void setLevelName(String name) {
    this.levelName = name;
  }

  /**
   * @param ballV
   *          the ballVelocities to set
   */
  public void setBallVelocities(List<Velocity> ballV) {
    this.ballVelocities = ballV;
  }

  /**
   * @param numbers
   *          the ballVelocities to set
   */
  public void setBallVelocities(String numbers) {
    this.ballVelocities = new ArrayList<Velocity>();
    String[] numbersString = numbers.split(" ");
    for (String s : numbersString) {
      s = s.trim();
      String[] velocityString = s.split(",");
      double angle = Double.parseDouble(velocityString[0]);
      double speed = Double.parseDouble(velocityString[1]);
      this.ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
    }
  }

  /**
   * @param back
   *          the background to set
   */
  public void setBackground(Sprite back) {
    this.background = back;
  }


  /**
   * @param speed
   *          the paddleSpeed to set
   */
  public void setPaddleSpeed(int speed) {
    this.paddleSpeed = speed;
  }

  /**
   * @param width
   *          the paddleWidth to set
   */
  public void setPaddleWidth(int width) {
    this.paddleWidth = width;
  }

  /**
   * @param blocksList
   *          the blocks to set
   */
  public void setBlocks(List<Block> blocksList) {
    this.blocks = blocksList;
  }

}
