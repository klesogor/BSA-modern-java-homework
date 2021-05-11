package com.binary_studio.fleet_commander.core.ship.contract;

import com.binary_studio.fleet_commander.core.exceptions.InsufficientPowerGridException;
import com.binary_studio.fleet_commander.core.subsystems.contract.AttackSubsystem;
import com.binary_studio.fleet_commander.core.subsystems.contract.DefensiveSubsystem;

public interface ModularVessel {

	void fitAttackSubsystem(AttackSubsystem subsystem) throws InsufficientPowerGridException;

	void fitDefensiveSubsystem(DefensiveSubsystem subsystem) throws InsufficientPowerGridException;

}
