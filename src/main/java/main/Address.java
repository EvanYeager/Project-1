package main;

public class Address {
	String city;
	String state;
	
	public Address(String city, String state)
	{
		setCity(city);
		setState(state);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s", city, state);
	}
}
