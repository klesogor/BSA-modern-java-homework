package com.binary_studio.fleet_commander.core.subsystems;

import com.binary_studio.fleet_commander.core.common.Attackable;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.subsystems.contract.AttackSubsystem;

public final class AttackSubsystemImpl implements AttackSubsystem {

	public static AttackSubsystemImpl construct(String name, PositiveInteger powergridRequirments,
			PositiveInteger capacitorConsumption, PositiveInteger optimalSpeed, PositiveInteger optimalSize,
			PositiveInteger baseDamage) throws IllegalArgumentException {
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
	public PositiveInteger attack(Attackable target) {
		// TODO: Ваш код здесь :)
		return null;
	}

	@Override
	public String getName() {
		// TODO: Ваш код здесь :)
		return null;
	}

}
