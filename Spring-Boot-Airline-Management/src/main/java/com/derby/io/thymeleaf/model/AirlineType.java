package com.derby.io.thymeleaf.model;

public enum AirlineType {
	Passenger("Passenger"),
	Cargo("Cargo");
	
	public static final AirlineType[] ALL = {Passenger,Cargo};
	
	private final String name;
	
	public static AirlineType forName(final String name) {
		if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for type");
        }if (name.toUpperCase().equals("Passenger")) {
            return Passenger;
        } else if (name.toUpperCase().equals("Cargo")) {
            return Cargo;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Type");
	}
	
	private AirlineType(final String name) {
        this.name = name;
    }
	
	public String getName() {
        return this.name;
    }
	
	@Override
    public String toString() {
        return getName();
    }
}
