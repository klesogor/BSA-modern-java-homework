package com.binary_studio.fleet_commander.core.common;

import java.util.Optional;

import com.binary_studio.fleet_commander.core.actions.attack.AttackAction;
import com.binary_studio.fleet_commander.core.actions.defence.AttackResult;
import com.binary_studio.fleet_commander.core.actions.defence.RegenerateAction;

public interface CombatCapable extends Attackable {

	Optional<AttackAction> attack(Attackable target);

	AttackResult applyAttack(AttackAction attack);

	Optional<RegenerateAction> regenerate();

}
