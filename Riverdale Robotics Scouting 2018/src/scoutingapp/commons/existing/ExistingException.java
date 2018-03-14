package scoutingapp.commons.existing;

/**
 * 
 * This exception is used to indicate when any type defined by
 * {@link scoutingapp.commons.existing#ExistingType ExistingException} already
 * exists in the current querry
 * 
 * @see scoutingapp.commons.RegionalCollection RegionalCollection
 * @see scoutingapp.commons.ScoutingApp ScoutingApp
 * 
 */
public class ExistingException extends Exception {

	private static final long serialVersionUID = 5968066293658020317L;

	int identifier;
	ExistingType type;

	/**
	 * 
	 * @param identifier
	 *            is used to indicate what ID already exists for the specific type
	 * @param types
	 *            as defined by {@link scoutingapp.commons.existing#ExistingType
	 *            ExistingException}
	 */
	public ExistingException(int identifier, ExistingType type) {

		this.identifier = identifier;
		this.type = type;

	}

}