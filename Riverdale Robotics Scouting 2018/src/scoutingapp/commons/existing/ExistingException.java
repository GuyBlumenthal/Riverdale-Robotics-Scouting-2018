package scoutingapp.commons.existing;

public class ExistingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5968066293658020317L;

	int identifier;
	ExistingType type;
	
	public ExistingException(int identifier, ExistingType type) {

		this.identifier = identifier;
		this.type = type;
		
	}

}