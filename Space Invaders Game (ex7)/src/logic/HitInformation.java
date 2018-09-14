package logic;

import basecomponents.Bullet;

/**
 *
 * @author H
 *
 */
public class HitInformation {

  private Shootable shooter;
  private Hittable wasHit;
  private Bullet bullet;

  /**
   * Constructor.
   * @param s object who shot
   * @param h object that got hit
   * @param b the bullet
   */
  public HitInformation(Shootable s, Hittable h, Bullet b) {
    this.shooter = s;
    this.wasHit = h;
    this.bullet = b;
  }

  /**
   * return shooter.
   * @return shooter
   */
  public Shootable getShooter() {
    return shooter;
  }

  /**
   * return the object that was hit.
   * @return hittable
   */
  public Hittable getWasHit() {
    return wasHit;
  }

  /**
   * return the bullet.
   * @return bullet
   */
  public Bullet getBullet() {
    return bullet;
  }
}