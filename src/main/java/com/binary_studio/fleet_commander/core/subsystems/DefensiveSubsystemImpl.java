package com.binary_studio.fleet_commander.core.subsystems;

import com.binary_studio.fleet_commander.core.actions.attack.AttackAction;
import com.binary_studio.fleet_commander.core.actions.defence.RegenerateAction;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.subsystems.contract.DefensiveSubsystem;

public final class DefensiveSubsystemImpl implements DefensiveSubsystem {

	public static DefensiveSubsystemImpl construct(String name, PositiveInteger powerGridConsumption,
												   PositiveInteger capacitorConsumption, PositiveInteger impactReductionPercent,
												   PositiveInteger shieldRegeneration, PositiveInteger hullRegeneration) throws IllegalArgumentException {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public PositiveInteger getPowerGridConsumption() {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public PositiveInteger getCapacitorConsumption() {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public String getName() {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public AttackAction reduceDamage(AttackAction incomingDamage) {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public RegenerateAction regenerate() {
		// TODO: Ваш код здесь :)
		return null;
	}

}
