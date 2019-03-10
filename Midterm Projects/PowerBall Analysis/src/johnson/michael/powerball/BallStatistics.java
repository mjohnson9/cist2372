package johnson.michael.powerball;

import java.util.Comparator;

/**
 * BallStatistics represents statistical information about a single ball.
 */
public class BallStatistics {
  /**
   * The number of the ball.
   */
  private final int number;

  /**
   * The number of times the ball has been seen.
   */
  private int frequency;

  /**
   * The index of the last drawing that the ball was seen in.
   * -1 means that it has never been seen.
   */
  private int lastSeenIndex;

  /**
   * Creates a new statistics container for a ball.
   * @param number The number of the ball that this container is for.
   */
  public BallStatistics(final int number) {
    if (number < Drawing.MIN_NUMBER || number > Drawing.MAX_NUMBER) {
      throw new IllegalArgumentException("number is outside of the range [" + Drawing.MIN_NUMBER
          + ", " + Drawing.MAX_NUMBER + "]");
    }

    this.number = number;
    this.setFrequency(0);
    this.setLastSeenIndex(-1);
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

  /**
   * NumberComparator is a Comparator for BallStatistics that compares the ball numbers.
   */
  public static class NumberComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(final BallStatistics o1, final BallStatistics o2) {
      return o1.getNumber() - o2.getNumber();
    }
  }

  /**
   * LastSeenComparator is a Comparator for BallStatistics that compares the lastSeenIndex.
   */
  public static class LastSeenComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(final BallStatistics o1, final BallStatistics o2) {
      return o1.getLastSeenIndex() - o2.getLastSeenIndex();
    }
  }

  /**
   * FrequencyComparator is a Comparator for BallStatistics that compares the frequencies.
   */
  public static class FrequencyComparator implements Comparator<BallStatistics> {
    @Override
    public int compare(final BallStatistics o1, final BallStatistics o2) {
      return o1.getFrequency() - o2.getFrequency();
    }
  }
}
