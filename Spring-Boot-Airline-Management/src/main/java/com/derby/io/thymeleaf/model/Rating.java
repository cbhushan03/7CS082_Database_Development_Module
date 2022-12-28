package com.derby.io.thymeleaf.model;

public enum Rating {
	NA("NA"),
	FSC("FSC"),
	LCC("LCC");
	
	public static final Rating[] ALL = {NA,FSC,LCC};
	
	private final String name;
	
	public static Rating forName(final String name) {
		if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for type");
        }if (name.toUpperCase().equals("FSC")) {
            return FSC;
        } else if (name.toUpperCase().equals("LCC")) {
            return LCC;
        }else if (name.toUpperCase().equals("NA")) {
            return NA;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Type");
	}
	
	private Rating(final String name) {
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
