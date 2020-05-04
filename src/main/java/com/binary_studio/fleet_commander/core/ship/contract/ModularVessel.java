package com.binary_studio.fleet_commander.core.ship.contract;

import com.binary_studio.fleet_commander.core.exceptions.InsufficientPowergridException;
import com.binary_studio.fleet_commander.core.subsystems.contract.AttackSubsystem;
import com.binary_studio.fleet_commander.core.subsystems.contract.DefenciveSubsystem;

public interface ModularVessel {

	void fitAttackSubsystem(AttackSubsystem subsystem) throws InsufficientPowergridException;

	void fitDefensiveSubsystem(DefenciveSubsystem subsystem) throws InsufficientPowergridException;

}
