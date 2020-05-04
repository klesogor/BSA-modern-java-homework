package com.binary_studio.fleet_commander.core.subsystems;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class AttackSubsystemBuilder {

	private String name;

	private PositiveInteger baseDamage;

	private PositiveInteger optimalSize;

	private PositiveInteger optimalSpeed;

	private PositiveInteger capacitorUsage;

	private PositiveInteger pgRequirement;

	public static AttackSubsystemBuilder named(String name) {
		var builder = new AttackSubsystemBuilder();
		builder.name = name;

		return builder;
	}

	public AttackSubsystemBuilder pg(Integer val) {
		this.pgRequirement = PositiveInteger.of(val);
		return this;
	}

	public AttackSubsystemBuilder damage(Integer val) {
		this.baseDamage = PositiveInteger.of(val);
		return this;
	}

	public AttackSubsystemBuilder optimalSize(Integer val) {
		this.optimalSize = PositiveInteger.of(val);
		return this;
	}

	public AttackSubsystemBuilder optimalSpeed(Integer val) {
		this.optimalSpeed = PositiveInteger.of(val);
		return this;
	}

	public AttackSubsystemBuilder capacitorUsage(Integer val) {
		this.capacitorUsage = PositiveInteger.of(val);
		return this;
	}

	public AttackSubsystemImpl construct() {
		return AttackSubsystemImpl.construct(this.name, this.pgRequirement, this.capacitorUsage, this.optimalSpeed,
				this.optimalSize, this.baseDamage);
	}

}
