package org.loic.api_soap_team.exceptions;

public class TeamNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(int id) {
		
		super("Could not find the team with id " + id);
		
	}

}
