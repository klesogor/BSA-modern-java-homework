package com.binary_studio.fleet_commander.core.subsystems.contract;

import com.binary_studio.fleet_commander.core.common.Attackable;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public interface AttackSubsystem extends Subsystem {

	PositiveInteger attack(Attackable target);

}
