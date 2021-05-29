import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ActivityMonitorGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Activity Monitor Tracker");
		frame.setLayout(new GridLayout(3, 1));

		var activityMonitor = new ActivityMonitor();

		frame.setPreferredSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.add(newPanel(activityMonitor.cpuValue()));
		frame.add(newPanel(activityMonitor.memoryValue()));
		frame.add(newPanel(activityMonitor.deskValue()));

		frame.setVisible(true);
	}

	public static JPanel newPanel(ActivityMonitor.Value value) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(value.alertName()));
		var layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);

		var emptySpace0 = new JPanel();
		emptySpace0.setMaximumSize(new Dimension(0, 20));
		panel.add(emptySpace0);

		var color = new JPanel();
		color.setMaximumSize(new Dimension((int) (value.ratio() * 180) + 20, 50));
		color.setBackground(value.color);
		color.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(color);

		var emptySpace1 = new JPanel();
		emptySpace1.setMaximumSize(new Dimension(0, 10));
		panel.add(emptySpace1);

		JLabel text = new JLabel();
		var format = new DecimalFormat("#.0");
		text.setText(value.alertText() + " --> " + format.format(value.value));
		text.setAlignmentX(Component.LEFT_ALIGNMENT);
		text.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		panel.add(text);
		return panel;
	}
}
