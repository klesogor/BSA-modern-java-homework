package com.binary_studio.fleet_commander.core.exceptions;

@SuppressWarnings("serial")
public final class InsufficientPowerGridException extends Exception {

	public InsufficientPowerGridException(Integer missingPowerGrid) {
		super(String.format("Missing %d MW to fit this module", missingPowerGrid));
	}

}
