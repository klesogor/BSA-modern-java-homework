package com.binary_studio.fleet_commander.core.actions.attack;

import com.binary_studio.fleet_commander.core.common.NamedEntity;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class AttackAction {

	public final PositiveInteger damage;

	public final NamedEntity target;

	public final NamedEntity attacker;

	public final NamedEntity weapon;

	public AttackAction(PositiveInteger damage, NamedEntity attacker, NamedEntity target, NamedEntity weapon) {
		this.damage = damage;
		this.target = target;
		this.weapon = weapon;
		this.attacker = attacker;
	}

}
