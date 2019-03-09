package johnson.michael.powerball;

import java.util.Comparator;

public class BallStatistics {
  /**
   * The number of the ball.
   */
  private int number;

  /**
   * The number of times the ball has been seen.
   */
  private int frequency;

  /**
   * The index of the last drawing that the ball was seen in.
   */
  private int lastSeenIndex;

  public BallStatistics(final int number) {
    if (number < PowerBallDrawing.MIN_NUMBER || number > PowerBallDrawing.MAX_NUMBER) {
      throw new IllegalArgumentException("number is outside of the range ["
          + PowerBallDrawing.MIN_NUMBER + ", " + PowerBallDrawing.MAX_NUMBER + "]");
    }

    this.number = number;
  }

  /**
   * @return The number of the ball.
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * @return The number of times this ball has been seen.
   */
  public int getFrequency() {
    return this.frequency;
  }

  /**
   * Sets the number of times this ball has been seen.
   * @param frequency The number of times this ball has been seen.
   */
  public void setFrequency(final int frequency) {
    this.frequency = frequency;
  }

  /**
   * @return The index of the last drawing that this ball was in.
   */
  public int getLastSeenIndex() {
    return this.lastSeenIndex;
  }

  /**
   * Sets the index of the last drawing that this ball was in.
   * @param lastSeenIndex The index of last drawing that this ball was in.
   */
  public void setLastSeenIndex(final int lastSeenIndex) {
    this.lastSeenIndex = lastSeenIndex;
  }

  public static class NumberComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(BallStatistics o1, BallStatistics o2) {
      return o1.getNumber() - o2.getNumber();
    }
  }

  public static class LastSeenComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(BallStatistics o1, BallStatistics o2) {
      return o1.getLastSeenIndex() - o2.getLastSeenIndex();
    }
  }

  public static class FrequencyComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(BallStatistics o1, BallStatistics o2) {
      return o1.getFrequency() - o2.getFrequency();
    }
  }
}
