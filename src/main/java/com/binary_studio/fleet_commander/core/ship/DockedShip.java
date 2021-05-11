package com.binary_studio.fleet_commander.core.ship;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.exceptions.InsufficientPowerGridException;
import com.binary_studio.fleet_commander.core.exceptions.NotAllSubsystemsFitted;
import com.binary_studio.fleet_commander.core.ship.contract.ModularVessel;
import com.binary_studio.fleet_commander.core.subsystems.contract.AttackSubsystem;
import com.binary_studio.fleet_commander.core.subsystems.contract.DefensiveSubsystem;

public final class DockedShip implements ModularVessel {

	public static DockedShip construct(String name, PositiveInteger shieldHP, PositiveInteger hullHP,
			PositiveInteger powerGridOutput, PositiveInteger capacitorAmount, PositiveInteger capacitorRechargeRate,
			PositiveInteger speed, PositiveInteger size) {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public void fitAttackSubsystem(AttackSubsystem subsystem) throws InsufficientPowerGridException {
		// TODO: Ваш код здесь :)
	}

	@Override
	public void fitDefensiveSubsystem(DefensiveSubsystem subsystem) throws InsufficientPowerGridException {
		// TODO: Ваш код здесь :)

	}

	public CombatReadyShip undock() throws NotAllSubsystemsFitted {
		// TODO: Ваш код здесь :)
		return null;
	}

}
