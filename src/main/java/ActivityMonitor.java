import libs.CpuUtilizationSensor;
import libs.DeskUsageSensor;
import libs.MemoryUsageSensor;

public class ActivityMonitor {
	public enum Color {
		Red,
		Yellow,
		Green,
	}

	public static class Value {
		Color color;
		double value;

		public Value(Color color, double value) {
			this.color = color;
			this.value = value;
		}
	}

	private final CpuUtilizationSensor cpuSensor;
	private final DeskUsageSensor deskSensor;
	private final MemoryUsageSensor memorySensor;

	public ActivityMonitor() {
		this.cpuSensor = new CpuUtilizationSensor();
		this.deskSensor = new DeskUsageSensor();
		this.memorySensor = new MemoryUsageSensor();
	}

	public Value cpuValue() {
		return new Value(strToColor(cpuSensor.getReport()), cpuSensor.readValue());
	}

	public Value deskValue() {
		return new Value(strToColor(deskSensor.getReport()), deskSensor.readValue());
	}

	public Value memoryValue() {
		return new Value(strToColor(memorySensor.getReport()), memorySensor.readValue());
	}

	private Color strToColor(String s) {
		return switch (s) {
			case "Critical" -> Color.Red;
			case "Danger" -> Color.Yellow;
			case "OK" -> Color.Green;
			default -> throw new IllegalArgumentException("Unexpected value: " + s);
		};
	}
}
