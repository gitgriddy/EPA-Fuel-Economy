package edu.miracostacollege.cs112.ic27_epafueleconomy.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * The <code>Vehicle</code> class represents a gasoline vehicle, with information such as its year, make,
 * model, city MPG, highway MPG, combined MPG (55% city, 45% highway), annual fuel cost and fuel economy rating.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Vehicle implements Serializable, Comparable<Vehicle> {
	// TODO: Ensure the class is Serializable so it may be written to a binary file.
	// TODO: Ensure the class implements Comparable, so that Vehicles may be sorted
	// TODO: by year, then by make, then by model.
	private int year;
	private String make;
	private String model;
	private double cityMPG;
	private double highwayMPG;
	private double combinedMPG;
	private double annualFuelCost;
	private int fuelEconomyRating;

	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	public static final DecimalFormat noDP = new DecimalFormat("0");

	public Vehicle(int year, String make, String model, double cityMPG, double highwayMPG, double annualFuelCost, int fuelEconomyRating) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.cityMPG = cityMPG;
		this.highwayMPG = highwayMPG;
		// Combined MPG is calculated as 55% city mpg and 45% highway mpg by the EPA
		combinedMPG = 0.55 * this.cityMPG + 0.45 * this.highwayMPG;
		this.annualFuelCost = annualFuelCost;
		this.fuelEconomyRating = fuelEconomyRating;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getCityMPG() {
		return cityMPG;
	}

	public void setCityMPG(double cityMPG) {
		this.cityMPG = cityMPG;
	}

	public double getHighwayMPG() {
		return highwayMPG;
	}

	public void setHighwayMPG(double highwayMPG) {
		this.highwayMPG = highwayMPG;
	}

	public double getCombinedMPG() {
		return combinedMPG;
	}

	public void setCombinedMPG(double combinedMPG) {
		this.combinedMPG = combinedMPG;
	}

	public double getAnnualFuelCost() {
		return annualFuelCost;
	}

	public void setAnnualFuelCost(double annualFuelCost) {
		this.annualFuelCost = annualFuelCost;
	}

	public int getFuelEconomyRating() {
		return fuelEconomyRating;
	}

	public void setFuelEconomyRating(int fuelEconomyRating) {
		this.fuelEconomyRating = fuelEconomyRating;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vehicle vehicle = (Vehicle) o;
		return year == vehicle.year && Double.compare(cityMPG, vehicle.cityMPG) == 0 && Double.compare(highwayMPG, vehicle.highwayMPG) == 0 && Double.compare(combinedMPG, vehicle.combinedMPG) == 0 && Double.compare(annualFuelCost, vehicle.annualFuelCost) == 0 && fuelEconomyRating == vehicle.fuelEconomyRating && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model);
	}

	@Override
	public int hashCode() {
		return Objects.hash(year, make, model, cityMPG, highwayMPG, combinedMPG, annualFuelCost, fuelEconomyRating);
	}

	@Override
	public String toString() {
		return "Vehicle[" +
						"Year=" + year +
						", Make=" + make +
						", Model=" + model +
						", City=" + noDP.format(cityMPG) + " mpg" +
						", Hwy=" + noDP.format(highwayMPG) + " mpg" +
						", Comb=" + noDP.format(combinedMPG) + " mpg" +
						", Annual Fuel Cost =" + currency.format(annualFuelCost) +
						", FE Rating (1-10)=" + fuelEconomyRating +
						']';
	}

	@Override
	public int compareTo(Vehicle other) {
		// sorted first by year, then
		// by make, and finally by model
		// Deafult sort is Ascending: this.year - other.year
		// Descending:
		int yearComp = other.year - this.year;
		if (yearComp != 0) return yearComp;

		int makeComp = this.make.compareTo(other.make);
		if (makeComp != 0) return makeComp;

		return this.model.compareTo(other.model);
	}
}
