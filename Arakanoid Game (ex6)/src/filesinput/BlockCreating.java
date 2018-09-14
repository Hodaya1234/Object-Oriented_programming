package filesinput;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import gameobjects.Block;
import geometry.Point;

/**
 *
 * @author H
 *
 */
public class BlockCreating implements BlockCreator {

  private int width;
  private int height;
  private int hits;
  private Color color;
  private Color stroke;
  private Map<Integer, Color> fillingColor;
  private Map<Integer, Image> fillingImage;

  /**
   * Constructor.
   */
  public BlockCreating() {
    this.fillingColor = new HashMap<Integer, Color>();
    this.fillingImage = new HashMap<Integer, Image>();
  }

  @Override
  public Block create(int x, int y) {
    Block b = new Block(new Point(x, y), width, height, color, hits);
    b.setStroke(stroke);
    b.setFillingColor(this.fillingColor);
    b.setFillingImage(this.fillingImage);
    return b;
  }

  /**
   * set block width.
   * @param blockWidth
   *          of block
   */
  public void setWidth(int blockWidth) {
    this.width = blockWidth;
  }

  /**
   * set block height.
   * @param blockHeight
   *          of block
   */
  public void setHeight(int blockHeight) {
    this.height = blockHeight;
  }

  /**
   * set block hits.
   * @param hitsNum
   *          of block
   */

  public void setHits(int hitsNum) {
    this.hits = hitsNum;
  }

  /**
   * set block color.
   * @param fill
   *          of block
   */
  public void setColor(Color fill) {
    this.color = fill;
  }

  /**
   * set block stroke color.
   * @param strokeColor
   *          color of stroke
   */
  public void setStroke(Color strokeColor) {
    this.stroke = strokeColor;
  }

  /**
   * set block fill color for a specific hits number left.
   * @param index
   *          of hits
   * @param fillColor
   *          of block
   */
  public void addFillColor(int index, Color fillColor) {
    this.fillingColor.put(index, fillColor);
  }

  /**
   * set block fill image for a specific hits number left.
   * @param index
   *          of hits
   * @param image
   *          of block
   */
  public void addFillImage(int index, Image image) {
    this.fillingImage.put(index, image);
  }

  @Override
  public int dX() {
    return this.width;
  }
}