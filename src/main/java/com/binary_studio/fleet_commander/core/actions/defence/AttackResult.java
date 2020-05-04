package com.binary_studio.fleet_commander.core.actions.defence;

import com.binary_studio.fleet_commander.core.common.NamedEntity;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;

//Destroyed | DamageRecived
public abstract class AttackResult {

	public static final class DamageRecived extends AttackResult {

		public final NamedEntity weapon;

		public final PositiveInteger damage;

		public final NamedEntity target;

		public DamageRecived(NamedEntity weapon, PositiveInteger damage, NamedEntity target) {
			super();
			this.weapon = weapon;
			this.damage = damage;
			this.target = target;
		}

	}

	public static final class Destroyed extends AttackResult {

	}

}
