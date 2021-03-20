package org.loic.ws_soap_team.exceptions;

public class TeamNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(long teamId) {
		
		super("Could not find the team with id " + teamId);
		
	}

}
