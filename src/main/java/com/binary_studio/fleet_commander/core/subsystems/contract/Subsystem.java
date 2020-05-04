package com.binary_studio.fleet_commander.core.subsystems.contract;

import com.binary_studio.fleet_commander.core.common.NamedEntity;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public interface Subsystem extends NamedEntity {

	PositiveInteger getPowerGridConsumption();

	PositiveInteger getCapacitorConsumption();

}
