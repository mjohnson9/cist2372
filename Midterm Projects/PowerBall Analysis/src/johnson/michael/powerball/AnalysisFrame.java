package johnson.michael.powerball;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * AnalysisFrame displays a {@code Analysis} to the user.
 */
public class AnalysisFrame extends JFrame {
  /**
   * Constructs an {@code AnalysisFrame} based on {@code analysis}.
   * @param analysis The analysis to base the frame's information on.
   */
  public AnalysisFrame(final Analysis analysis) {
    super();

    this.setTitle("PowerBall Statistics");
    this.setLayout(new GridLayout(1, 1));

    final JTabbedPane tabbedPane = new JTabbedPane();

    final JComponent mostCommonPowerBallTable =
        this.createNumberFrequencyTable(analysis.getMostCommonPowerBallNumbers());
    tabbedPane.addTab("Most Common PowerBalls", mostCommonPowerBallTable);

    final JComponent mostCommonTable =
        this.createNumberFrequencyTable(analysis.getMostCommonNumbers());
    tabbedPane.addTab("Most Common", mostCommonTable);

    final JComponent leastCommonTable =
        this.createNumberFrequencyTable(analysis.getLeastCommonNumbers());
    tabbedPane.addTab("Least Common", leastCommonTable);

    final JComponent mostOverdueTable = this.createMostOverdueTable(analysis);
    tabbedPane.addTab("Most Overdue", mostOverdueTable);

    this.add(tabbedPane);

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
  }

  /**
   * Create a table showing the frequency of ball numbers.
   * @param statistics The statistics to base the table off of.
   * @return A table containing the given statistics, showing the frequency of a ball number.
   */
  private JComponent createNumberFrequencyTable(final BallStatistics[] statistics) {
    final String[] headers = {"Ball Number", "Frequency"};

    final String[][] rows = new String[statistics.length][2];
    for (int i = 0; i < rows.length; i++) {
      final String[] row = rows[i];
      final BallStatistics rowStatistics = statistics[i];
      row[0] = Integer.toString(rowStatistics.getNumber());
      row[1] = Integer.toString(rowStatistics.getFrequency());
    }

    return this.createTable(rows, headers);
  }

  /**
   * Create a "Most Overdue" table, showing ball number and how many drawings ago it was last drawn.
   * @param analysis The analysis to base the table on.
   * @return The created table.
   */
  private JComponent createMostOverdueTable(final Analysis analysis) {
    final String[] headers = {"Ball Number", "Last Seen"};

    final BallStatistics[] mostOverdue = analysis.getMostOverdueNumbers();
    final int numDrawings = analysis.getNumDrawings() - 1;
    final String[][] rows = new String[mostOverdue.length][2];
    for (int i = 0; i < rows.length; i++) {
      final String[] row = rows[i];
      final BallStatistics rowStatistics = mostOverdue[i];
      row[0] = Integer.toString(rowStatistics.getNumber());
      row[1] = String.format("%,d drawings ago", numDrawings - rowStatistics.getLastSeenIndex());
    }

    return this.createTable(rows, headers);
  }

  /**
   * Create a scrollable table inside of a JPanel, based on {@code rows} and {@code headers}.
   * @param rows The rows to put into the table.
   * @param headers The headers of the table.
   * @return A panel containing a table with the specified data in it.
   */
  private JComponent createTable(final String[][] rows, final String[] headers) {
    final JPanel panel = new JPanel(false);
    panel.setLayout(new GridLayout(1, 1));

    final JTable table = new JTable(rows, headers);
    final JScrollPane scrollPane = new JScrollPane(table);

    panel.add(scrollPane);
    return panel;
  }
}
