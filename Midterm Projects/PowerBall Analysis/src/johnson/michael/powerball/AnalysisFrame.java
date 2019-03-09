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
 * AnalysisFrame displays a {@code PowerBallAnalysis} to the user.
 */
public class AnalysisFrame extends JFrame {
  private PowerBallAnalysis analysis;

  /**
   * Constructs an {@code AnalysisFrame} based on {@code analysis}.
   * @param analysis The analysis to base the frame's information on.
   */
  public AnalysisFrame(final PowerBallAnalysis analysis) {
    super();

    this.setTitle("PowerBall Statistics");
    this.setLayout(new GridLayout(1, 1));

    this.analysis = analysis;

    JTabbedPane tabbedPane = new JTabbedPane();

    JComponent mostCommonPowerBallTable =
        this.createNumberFrequencyTable(this.analysis.getMostCommonPowerBallNumbers());
    tabbedPane.addTab("Most Common PowerBalls", mostCommonPowerBallTable);

    JComponent mostCommonTable =
        this.createNumberFrequencyTable(this.analysis.getMostCommonNumbers());
    tabbedPane.addTab("Most Common", mostCommonTable);

    JComponent leastCommonTable =
        this.createNumberFrequencyTable(this.analysis.getLeastCommonNumbers());
    tabbedPane.addTab("Least Common", leastCommonTable);

    JComponent mostOverdueTable = this.createMostOverdueTable();
    tabbedPane.addTab("Most Overdue", mostOverdueTable);

    this.add(tabbedPane);

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
  }

  private JComponent createNumberFrequencyTable(final BallStatistics[] statistics) {
    final String[] headers = {"Ball Number", "Frequency"};

    String[][] rows = new String[statistics.length][2];
    for (int i = 0; i < rows.length; i++) {
      String[] row = rows[i];
      BallStatistics rowStatistics = statistics[i];
      row[0] = Integer.toString(rowStatistics.getNumber());
      row[1] = Integer.toString(rowStatistics.getFrequency());
    }

    JPanel panel = new JPanel(false);
    panel.setLayout(new GridLayout(1, 1));

    JTable table = new JTable(rows, headers);
    JScrollPane scrollPane = new JScrollPane(table);

    panel.add(scrollPane);
    return panel;
  }

  private JComponent createMostOverdueTable() {
    final String[] headers = {"Ball Number", "Last Seen"};

    final BallStatistics[] mostOverdue = this.analysis.getMostOverdueNumbers();
    final int numDrawings = this.analysis.getNumDrawings() - 1;
    String[][] rows = new String[mostOverdue.length][2];
    for (int i = 0; i < rows.length; i++) {
      String[] row = rows[i];
      BallStatistics rowStatistics = mostOverdue[i];
      row[0] = Integer.toString(rowStatistics.getNumber());
      row[1] = String.format("%,d drawings ago", numDrawings - rowStatistics.getLastSeenIndex());
    }

    JPanel panel = new JPanel(false);
    panel.setLayout(new GridLayout(1, 1));

    JTable table = new JTable(rows, headers);
    JScrollPane scrollPane = new JScrollPane(table);

    panel.add(scrollPane);
    return panel;
  }
}
