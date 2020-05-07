package com.binary_studio.fleet_commander.core.ship;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.exceptions.InsufficientPowergridException;
import com.binary_studio.fleet_commander.core.exceptions.NotAllSubsystemsFitted;
import com.binary_studio.fleet_commander.core.ship.contract.ModularVessel;
import com.binary_studio.fleet_commander.core.subsystems.contract.AttackSubsystem;
import com.binary_studio.fleet_commander.core.subsystems.contract.DefenciveSubsystem;

public final class DockedShip implements ModularVessel {

	public static DockedShip construct(String name, PositiveInteger shieldHP, PositiveInteger hullHP,
			PositiveInteger powergridOutput, PositiveInteger capacitorAmount, PositiveInteger capacitorRechargeRate,
			PositiveInteger speed, PositiveInteger size) {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public void fitAttackSubsystem(AttackSubsystem subsystem) throws InsufficientPowergridException {
		// TODO: Ваш код здесь :)
	}

	@Override
	public void fitDefensiveSubsystem(DefenciveSubsystem subsystem) throws InsufficientPowergridException {
		// TODO: Ваш код здесь :)

	}

	public CombatReadyShip undock() throws NotAllSubsystemsFitted {
		// TODO: Ваш код здесь :)
		return null;
	}

}
