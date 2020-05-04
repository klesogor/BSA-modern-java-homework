package com.binary_studio.fleet_commander.core.subsystems.contract;

import com.binary_studio.fleet_commander.core.actions.attack.AttackAction;
import com.binary_studio.fleet_commander.core.actions.defence.RegenerateAction;
import com.binary_studio.fleet_commander.core.common.NamedEntity;

public interface DefenciveSubsystem extends Subsystem, NamedEntity {

	AttackAction reduceDamage(AttackAction incomingDamage);

	RegenerateAction regenerate();

}
