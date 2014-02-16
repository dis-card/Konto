package in.darkstars.konto.dto;

import java.io.Serializable;

/**
 * @author Vikash
 * 
 * Purpose :- act as data transfer object for City.
 *
 */
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -756679818838295947L;
	private String acronym;
	private String name;
	/**
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}
	/**
	 * @param acronym the acronym to set
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
