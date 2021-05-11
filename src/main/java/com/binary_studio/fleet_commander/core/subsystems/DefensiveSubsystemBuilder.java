package com.binary_studio.fleet_commander.core.subsystems;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class DefensiveSubsystemBuilder {

	private String name;

	private PositiveInteger impactReduction;

	private PositiveInteger shieldRegen;

	private PositiveInteger hullRegen;

	private PositiveInteger capacitorUsage;

	private PositiveInteger pgRequirement;

	public static DefensiveSubsystemBuilder named(String name) {
		var builder = new DefensiveSubsystemBuilder();
		builder.name = name;

		return builder;
	}

	public DefensiveSubsystemBuilder pg(Integer val) {
		this.pgRequirement = PositiveInteger.of(val);
		return this;
	}

	public DefensiveSubsystemBuilder hullRegen(Integer val) {
		this.hullRegen = PositiveInteger.of(val);
		return this;
	}

	public DefensiveSubsystemBuilder shieldRegen(Integer val) {
		this.shieldRegen = PositiveInteger.of(val);
		return this;
	}

	public DefensiveSubsystemBuilder impactReduction(Integer val) {
		this.impactReduction = PositiveInteger.of(val);
		return this;
	}

	public DefensiveSubsystemBuilder capacitorUsage(Integer val) {
		this.capacitorUsage = PositiveInteger.of(val);
		return this;
	}

	public DefensiveSubsystemImpl construct() {
		return DefensiveSubsystemImpl.construct(this.name, this.pgRequirement, this.capacitorUsage,
				this.impactReduction, this.shieldRegen, this.hullRegen);
	}

}
