package johnson.michael.temperature;

public class Temperature {

  private double ftemp;

  Temperature(final double fahrenheitTemperature) {
    this.ftemp = fahrenheitTemperature;
  }

  public double getFahrenheit() {
    return this.ftemp;
  }

  public void setFahrenheit(double fahrenheitTemperature) {
    this.ftemp = fahrenheitTemperature;
  }

  public double getCelsius() {
    return (5d / 9d) * (this.ftemp - 32d);
  }

  public void setCelsius(double celsiusTemperature) {
    this.ftemp = ((9d * celsiusTemperature) / 5d) + 32d;
  }

  public double getKelvin() {
    return this.getCelsius() + 273d;
  }

  public void setKelvin(double kelvinTemperature) {
    this.setCelsius(kelvinTemperature - 273d);
  }
}
