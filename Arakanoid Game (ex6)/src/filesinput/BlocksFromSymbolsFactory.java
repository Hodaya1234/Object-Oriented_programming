package filesinput;

import java.util.Map;

import gameobjects.Block;

/**
 *
 * @author H
 *
 */
public class BlocksFromSymbolsFactory {

  private Map<String, Integer> spacerWidths;
  private Map<String, BlockCreator> blockCreators;

  /**
   * Constructor.
   * @param spacerWidths
   *          map of spaces
   * @param blockCreators
   *          map of block creators
   */
  public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
    this.spacerWidths = spacerWidths;
    this.blockCreators = blockCreators;
  }

  /**
   * returns true if 's' is a valid space symbol.
   * @param s
   *          symbol
   * @return if it is a space
   */
  public boolean isSpaceSymbol(String s) {
    return this.spacerWidths.containsKey(s);
  }

  /**
   * returns true if 's' is a valid block symbol.
   * @param s
   *          symbol
   * @return if it is a block
   */
  public boolean isBlockSymbol(String s) {
    return this.blockCreators.containsKey(s);
  }

  /**
   * Return a block according to the definitions associated with symbol s.
   * @param s
   *          symbol
   * @param x
   *          position
   * @param y
   *          position
   * @return the block
   */
  public Block getBlock(String s, int x, int y) {
    if (!this.blockCreators.containsKey(s)) {
      throw new RuntimeException();
    }
    return this.blockCreators.get(s).create(x, y);
  }

  /**
   * Returns the width in pixels associated with the given spacer-symbol.
   * @param s
   *          symbol
   * @return how many pixels of space
   */
  public int getSpaceWidth(String s) {
    if (!this.spacerWidths.containsKey(s)) {
      throw new RuntimeException();
    }
    return this.spacerWidths.get(s);
  }

  /**
   * get width of a block.
   * @param s
   *          symbol of block
   * @return the number of pixels to move after drawing the block
   */
  public int getBlockDx(String s) {
    if (isBlockSymbol(s)) {
      return blockCreators.get(s).dX();
    } else if (isSpaceSymbol(s)) {
      return spacerWidths.get(s);
    }
    return 0;
  }
}
