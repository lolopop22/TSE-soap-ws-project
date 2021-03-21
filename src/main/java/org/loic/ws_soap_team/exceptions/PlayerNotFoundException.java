package org.loic.ws_soap_team.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(Long id) {
		
		super("Could not find the player with id " + id);
		
	}
	
}
