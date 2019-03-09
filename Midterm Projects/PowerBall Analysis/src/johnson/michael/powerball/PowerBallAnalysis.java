package johnson.michael.powerball;

import java.util.Arrays;
import johnson.michael.powerball.BallStatistics.FrequencyComparator;
import johnson.michael.powerball.BallStatistics.LastSeenComparator;
import johnson.michael.powerball.BallStatistics.NumberComparator;

public class PowerBallAnalysis {
  /**
   * The results of multiple PowerBall drawings.
   */
  final PowerBallDrawing[] drawings;

  /**
   * The ball statistics, ordered by ball number.
   */
  private BallStatistics[] ballStatistics;
  /**
   * The PowerBall statistics, ordered by ball number.
   */
  private BallStatistics[] powerBallStatistics;

  /**
   * The 10 most common drawings, ordered by frequency.
   */
  private BallStatistics[] mostCommonNumbers;
  /**
   * The 10 most common PowerBalls, ordered by frequency.
   */
  private BallStatistics[] mostCommonPowerBallNumbers;

  /**
   * The 10 least common drawings, ordered by frequency.
   */
  private BallStatistics[] leastCommonNumbers;

  /**
   * The 10 most overdue drawings, ordered by how long ago they were seen.
   */
  private BallStatistics[] mostOverdueNumbers;

  /**
   * Analyzes a set of lottery drawings.
   * @param drawings The drawings to be analyzed.
   */
  public PowerBallAnalysis(final PowerBallDrawing[] drawings) {
    this.drawings =
        drawings.clone(); // Clone so that we have our own copy and not a reference to the caller's
    this.doAnalysis();
  }

  /**
   * Performs the actual analysis of the drawings.
   */
  private void doAnalysis() {
    this.initializeStatistics();
    this.gatherData();
    this.analyzeBalls();
    this.analyzePowerBalls();
  }

  private void initializeStatistics() {
    this.ballStatistics =
        new BallStatistics[PowerBallDrawing.MAX_NUMBER - PowerBallDrawing.MIN_NUMBER + 1];
    for (int i = 0; i < this.ballStatistics.length; i++) {
      this.ballStatistics[i] = new BallStatistics(PowerBallDrawing.MIN_NUMBER + i);
    }

    this.powerBallStatistics =
        new BallStatistics[PowerBallDrawing.MAX_POWERBALL - PowerBallDrawing.MIN_POWERBALL + 1];
    for (int i = 0; i < this.powerBallStatistics.length; i++) {
      this.powerBallStatistics[i] = new BallStatistics(PowerBallDrawing.MIN_POWERBALL + i);
    }
  }

  private void gatherData() {
    for (int i = 0; i < this.drawings.length; i++) {
      PowerBallDrawing drawing = this.drawings[i];

      for (int j = 0; j < 5; j++) {
        int number = drawing.getNumber(j);
        BallStatistics statistics = this.ballStatistics[number - PowerBallDrawing.MIN_NUMBER];
        statistics.setLastSeenIndex(i);
        statistics.setFrequency(statistics.getFrequency() + 1);
      }

      BallStatistics powerBall =
          this.powerBallStatistics[drawing.getPowerBall() - PowerBallDrawing.MIN_POWERBALL];
      powerBall.setLastSeenIndex(i);
      powerBall.setFrequency(powerBall.getFrequency() + 1);
    }
  }

  private void analyzeBalls() {
    // Sort the RollNumber objects in order of their frequency
    Arrays.sort(this.ballStatistics, new FrequencyComparator());

    // Set the most common numbers
    this.mostCommonNumbers = new BallStatistics[10];
    for (int i = 0; i < this.mostCommonNumbers.length; i++) {
      this.mostCommonNumbers[i] = this.ballStatistics[this.ballStatistics.length - i - 1];
    }

    // Set the least common numbers
    this.leastCommonNumbers = new BallStatistics[10];
    for (int i = 0; i < this.leastCommonNumbers.length; i++) {
      this.leastCommonNumbers[i] = this.ballStatistics[i];
    }

    // Sort the RollNumber objects in order of their lastSeenIndex
    Arrays.sort(this.ballStatistics, new LastSeenComparator());

    // Set the most overdue numbers
    this.mostOverdueNumbers = new BallStatistics[10];
    for (int i = 0; i < this.mostOverdueNumbers.length; i++) {
      this.mostOverdueNumbers[i] = this.ballStatistics[i];
    }

    // Return to normal order
    Arrays.sort(this.ballStatistics, new NumberComparator());
  }

  private void analyzePowerBalls() {
    // Sort by frequency for getting the most common numbers
    Arrays.sort(this.powerBallStatistics, new FrequencyComparator());

    // Set the most common PowerBall numbers
    this.mostCommonPowerBallNumbers = new BallStatistics[10];
    for (int i = 0; i < this.mostCommonPowerBallNumbers.length; i++) {
      this.mostCommonPowerBallNumbers[i] =
          this.powerBallStatistics[this.powerBallStatistics.length - i - 1];
    }

    // Return to normal order
    Arrays.sort(this.powerBallStatistics, new NumberComparator());
  }

  /**
   * @return A clone of the drawings array.
   */
  public PowerBallDrawing[] getDrawings() {
    return this.drawings.clone();
  }

  /**
   * @return The number of drawings.
   */
  public int getNumDrawings() {
    return this.drawings.length;
  }

  /**
   * @return A clone of the ball statistics array.
   */
  public BallStatistics[] getBallStatistics() {
    return this.ballStatistics.clone();
  }

  /**
   * @return An array of the top 10 most common drawings.
   */
  public BallStatistics[] getMostCommonNumbers() {
    return this.mostCommonNumbers.clone();
  }

  /**
   * @return An array of the top 10 most common PowerBall numbers.
   */
  public BallStatistics[] getMostCommonPowerBallNumbers() {
    return this.mostCommonPowerBallNumbers.clone();
  }

  /**
   * @return An array of the 10 least common drawings.
   */
  public BallStatistics[] getLeastCommonNumbers() {
    return this.leastCommonNumbers.clone();
  }

  /**
   * @return An array of the drawings that haven't been seen for the longest time.
   */
  public BallStatistics[] getMostOverdueNumbers() {
    return this.mostOverdueNumbers.clone();
  }
}
