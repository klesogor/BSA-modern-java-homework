package com.binary_studio.fleet_commander.core.exceptions;

@SuppressWarnings("serial")
public final class InsufficientPowergridException extends Exception {

	public InsufficientPowergridException(Integer missingPowergrid) {
		super(String.format("Missing %d MW to fit this module", missingPowergrid));
	}

}
