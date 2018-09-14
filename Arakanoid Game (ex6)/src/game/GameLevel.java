package game;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Collidable;
import gameobjects.Counter;
import gameobjects.LevelName;
import gameobjects.LivesIndicator;
import gameobjects.Paddle;
import gameobjects.ScoreIndicator;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Velocity;
import usergraphics.Animation;
import usergraphics.AnimationRunner;
import usergraphics.CountdownAnimation;
import usergraphics.KeyPressStoppableAnimation;
import usergraphics.PauseScreen;

/**
 * Game class.
 * @author H
 *
 */
public class GameLevel implements Animation {
  private LevelInformation levelInfo;
  private AnimationRunner runner;
  private boolean running;
  private SpriteCollection sprites;
  private GameEnvironment environment;
  private Counter remainingBlocks;
  private Counter score;
  private Counter remainingBalls;
  private Counter lives;
  private Paddle paddle;
  private KeyboardSensor keyboard;

  /**
   * One level of a game.
   * @param levelinfo information about the setting of the game
   * @param keyboard sensor
   * @param runner of the animation
   * @param score tracker
   * @param lives left to play
   */
  public GameLevel(LevelInformation levelinfo, KeyboardSensor keyboard, AnimationRunner runner,
      Counter score, Counter lives) {
    this.levelInfo = levelinfo;
    this.keyboard = keyboard;
    this.runner = runner;
    this.score = score;
    this.lives = lives;
    this.environment = new GameEnvironment();
    this.sprites = new SpriteCollection();
  }

  /**
   * How many lives are left to play.
   * @return lives
   */
  public int howManyLives() {
    return this.lives.getValue();
  }

  /**
   * How many blocks are left to kill.
   * @return blocks
   */
  public int howManyBlocks() {
    return this.remainingBlocks.getValue();
  }

  /**
   * add object to collide.
   * @param c the object
   */
  public void addCollidable(Collidable c) {
    this.environment.addCollidable(c);
  }

  /**
   * add object that moves.
   * @param s the object
   */
  public void addSprite(Sprite s) {
    this.sprites.addSprite(s);
  }

  /**
   * Remove from collidable list.
   * @param c to remove
   */
  public void removeCollidable(Collidable c) {
  this.environment.removeCollidable(c);
  }

  /**
   * Remove an object that moves in the game.
   * @param s the object
   */
  public void removeSprite(Sprite s) {
    this.sprites.removeSprite(s);
  }

  /**
   * When should the one turn end.
   * @return if the game should be running
   */
  public boolean shouldStop() {
   return (!this.running);
  }

  /**
   * Draw all game sprite objects for an animation frame.
   * @param d
   *          the draw surface for the frame
   * @param dt
   *          time passed in milliseconds
   */
  public void doOneFrame(DrawSurface d, double dt) {
    if (this.keyboard.isPressed("p")) {
      this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
    }
    this.sprites.notifyAllTimePassed(dt);
    if (this.remainingBlocks.getValue() < 1) {
      this.score.increase(100);
    }
    this.sprites.drawAllOn(d);
    if (this.remainingBalls.getValue() < 1) {
      this.lives.decrease(1);
      this.running = false;
    }
    if (this.remainingBlocks.getValue() < 1) {
      this.running = false;
    }
  }

  /**
   * run the game.
   */
  public void run() {
    while ((this.lives.getValue() > 0) && (this.remainingBlocks.getValue() > 0)) {
      this.playOneTurn();
      this.lives.decrease(1);
    }
      return;
  }

  /**
   * One life to play.
   */
  public void playOneTurn() {
    this.addBalls(this.levelInfo.numberOfBalls(), this.levelInfo.initialBallVelocities());
    this.paddle.reset();

    this.runner.run(new CountdownAnimation(2, 3, this.sprites));

    this.running = true;
    //use our runner to run the current animation -- which is one turn of the game.
    this.runner.run(this);
  }

  /**
  * Initialize a new game: create the Blocks, Ball and Paddle and add them to the game.
  */
  public void initialize() {
    this.running = true;

    this.remainingBlocks = new Counter();
    this.remainingBalls = new Counter();

    BlockRemover blockRemove = new BlockRemover(this, this.remainingBlocks);
    ScoreTrackingListener tracker = new ScoreTrackingListener(this.score);
    BallRemover ballRemove = new BallRemover(this, this.remainingBalls);
    ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
    LivesIndicator livesIndicator = new LivesIndicator(this.lives);
    LevelName name = new LevelName(this.levelInfo.levelName());

    this.addSprite(this.levelInfo.getBackground());
    this.addPaddle(this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
    this.addDeathRegion(ballRemove);
    this.addBoundaryBlocks();
    this.addSprite(scoreIndicator);
    this.addSprite(livesIndicator);
    this.addSprite(name);
    this.addBlocks(this.levelInfo.blocks(), blockRemove, tracker);
  }

  /**
   * Add the balls to the game.
   * @param numberOfBalls to add
   * @param initialBallVelocities velocities of balls
   */
  public void addBalls(int numberOfBalls, List<Velocity> initialBallVelocities) {
    Ball[] balls = new Ball[numberOfBalls];
    for (int i = 0; i < numberOfBalls; i++) {
      balls[i] = new Ball(new Point(400, 555), 6, Color.WHITE);
      balls[i].setVelocity(initialBallVelocities.get(i));
      balls[i].setEnvironment(environment);
      balls[i].addToGame(this);
    }
    this.remainingBalls.increase(numberOfBalls);
  }

  /**
   * Add the paddle to the game.
   * @param paddleSpeed of movement
   * @param paddleWidth width
   */
  public void addPaddle(int paddleSpeed, int paddleWidth) {
    this.paddle = new Paddle(this.keyboard, paddleSpeed, paddleWidth);
    this.paddle.addToGame(this);
  }

  /**
   * add boundary blocks.
   */
  public void addBoundaryBlocks() {
    Block[] ends = new Block[4];
    ends[0] = new Block(new Point(0, 0), 800, 40, Color.GRAY, -1);
    ends[1] = new Block(new Point(780, 0), 40, 600, Color.GRAY, -1);
    ends[2] = new Block(new Point(-20, 0), 40, 600, Color.GRAY, -1);
    for (int i = 0; i < 3; i++) {
      this.addCollidable(ends[i]);
      this.addSprite(ends[i]);
    }
  }

  /**
   * Add death region beneath the screen.
   * @param hl the ball remover which needs to know if a heat was made
   */
  public void addDeathRegion(HitListener hl) {
    Block deathRegion = new Block(new Point(0, 590), 800, 40, Color.BLACK, -1);
    deathRegion.addHitListener(hl);
    this.addCollidable(deathRegion);
  }

  /**
   * add the blocks to the game.
   * @param blocks list to add
   * @param r the listener that takes the blocks out of the game
   * @param s adds score after each hit
   */
  public void addBlocks(List<Block> blocks, BlockRemover r, ScoreTrackingListener s) {
    for (Block b : blocks) {
    b.addToGame(this);
    b.addHitListener(s);
    b.addHitListener(r);
    this.remainingBlocks.increase(1);
  }
  }
}
