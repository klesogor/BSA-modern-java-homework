package com.binary_studio.fleet_commander.core.exceptions;

@SuppressWarnings("serial")
public final class NotAllSubsystemsFitted extends Exception {

	public static final String BOTH_MISSING_MSG = "This ship misses attack and defensive subsystems";

	public static final String ATTACK_MISSING_MSG = "This ship misses attack subsystem";

	public static final String DEFENCIVE_MISSING_MSG = "This ship misses defencive subsystem";

	private NotAllSubsystemsFitted(String message) {
		super(message);
	}

	public static NotAllSubsystemsFitted bothMissing() {
		return new NotAllSubsystemsFitted(BOTH_MISSING_MSG);
	}

	public static NotAllSubsystemsFitted attackMissing() {
		return new NotAllSubsystemsFitted(ATTACK_MISSING_MSG);
	}

	public static NotAllSubsystemsFitted defenciveMissing() {
		return new NotAllSubsystemsFitted(DEFENCIVE_MISSING_MSG);
	}

}
