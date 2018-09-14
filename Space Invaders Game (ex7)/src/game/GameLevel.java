package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import basecomponents.AlienGroup;
import basecomponents.Bullet;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import initializastion.MainInitialize;
import logic.Counter;
import logic.Hittable;
import logic.Sprite;
import logic.SpriteCollection;

/**
 *
 * @author H
 *
 */
public class GameLevel implements Animation {

  private AnimationRunner runner;
  private KeyboardSensor keyboard;
  private SpriteCollection sprites;
  private List<Bullet> bullets;
  private List<Hittable> hittables;
  private boolean shouldStop;
  private AlienGroup aliens;
  private Counter lives;
  private Counter score;
  private int levelNumber;

  /**
   * Constructor.
   * @param runner for the animation
   * @param keyboard sensor
   * @param score counter
   * @param lives counter
   * @param level number
   */
  public GameLevel(AnimationRunner runner, KeyboardSensor keyboard, Counter score, Counter lives, int level) {
    this.runner = runner;
    this.keyboard = keyboard;
    this.sprites = new SpriteCollection();
    this.bullets = new ArrayList<Bullet>();
    this.hittables = new ArrayList<Hittable>();
    this.shouldStop = false;
    this.lives = lives;
    this.score = score;
    this.levelNumber = level;
  }

  /**
   * a life should be lost.
   */
  public void spaceShipHit() {
    shouldStop = true;
  }

  /**
   * initialize the level.
   */
  public void initialize() {
    MainInitialize.initialize(this, keyboard, score, lives, levelNumber);
  }

  /**
   * one turn of the game when life is lost / start of game.
   */
  public void playOneTurn() {
    aliens.moveToStart();
    runner.run(new CountdownAnimation(2, 3, sprites));
    shouldStop = false;
    runner.run(this);
    for (Bullet b : bullets) {
      sprites.removeSprite(b);
    }
    bullets.removeAll(bullets);
  }

  /**
   * run the level until finished / no lives remain.
   */
  public void run() {
    while ((lives.getValue() > 0) && (aliens.howMany() > 0)) {
      playOneTurn();
    }
    return;
  }

  /**
   * add the alien group as a field for turn initialize.
   * @param group of aliens
   */
  public void addAliens(AlienGroup group) {
    this.aliens = group;
  }

  /**
   * add a sprite.
   * @param s sprite
   */
  public void addSprite(Sprite s) {
    sprites.addSprite(s);
  }

  /**
   * remove a sprite.
   * @param s sprite
   */
  public void removeSprite(Sprite s) {
    sprites.removeSprite(s);
  }

  /**
   * add a bullet to the game.
   * @param b bullet
   */
  public void addBullet(Bullet b) {
    bullets.add(b);
    sprites.addSprite(b);
    b.setHittables(hittables);
  }

  /**
   * remove a bullet when it is hit.
   * @param b bullet
   */
  public void removeBullet(Bullet b) {
    bullets.remove(b);
    sprites.removeSprite(b);
  }

  /**
   * add a hittable object.
   * @param h hittable
   */
  public void addHittable(Hittable h) {
    hittables.add(h);
  }

  /**
   * remove a hittable.
   * @param h hittable
   */
  public void removeHittable(Hittable h) {
    if (hittables.contains(h)) {
      hittables.remove(h);
    }
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    d.setColor(Color.BLACK);
    d.fillRectangle(0, 0, 800, 600);
    for (Bullet b : bullets) {
      b.setHittables(hittables);
    }
    sprites.timePassed(dt);
    sprites.drawAllOn(d);
    if (aliens.howMany() == 0) {
      shouldStop = true;
    }
    if (aliens.bottom() >= 485) {
      shouldStop = true;
    }
  }

  @Override
  public boolean shouldStop() {
    return shouldStop;
  }
}