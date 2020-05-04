package com.binary_studio.fleet_commander.core.subsystems;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class DefenciveSubsystemBuilder {

	private String name;

	private PositiveInteger impactReduction;

	private PositiveInteger shieldRegen;

	private PositiveInteger hullRegen;

	private PositiveInteger capacitorUsage;

	private PositiveInteger pgRequirement;

	public static DefenciveSubsystemBuilder named(String name) {
		var builder = new DefenciveSubsystemBuilder();
		builder.name = name;

		return builder;
	}

	public DefenciveSubsystemBuilder pg(Integer val) {
		this.pgRequirement = PositiveInteger.of(val);
		return this;
	}

	public DefenciveSubsystemBuilder hullRegen(Integer val) {
		this.hullRegen = PositiveInteger.of(val);
		return this;
	}

	public DefenciveSubsystemBuilder shieldRegen(Integer val) {
		this.shieldRegen = PositiveInteger.of(val);
		return this;
	}

	public DefenciveSubsystemBuilder impactReduction(Integer val) {
		this.impactReduction = PositiveInteger.of(val);
		return this;
	}

	public DefenciveSubsystemBuilder capacitorUsage(Integer val) {
		this.capacitorUsage = PositiveInteger.of(val);
		return this;
	}

	public DefenciveSubsystemImpl construct() {
		return DefenciveSubsystemImpl.construct(this.name, this.pgRequirement, this.capacitorUsage,
				this.impactReduction, this.shieldRegen, this.hullRegen);
	}

}
