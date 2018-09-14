package filesinput;

import gameobjects.Block;

/**
 *
 * @author H
 *
 */
public interface BlockCreator {
  /**
   * Create a block at the specified location.
   * @param xpos
   *          coordinate of the left side of the block
   * @param ypos
   *          coordinate of the top side of the block
   * @return the factory block
   */
  Block create(int xpos, int ypos);

  /**
   * get the width of the block.
   * @return width
   */
  int dX();
}
