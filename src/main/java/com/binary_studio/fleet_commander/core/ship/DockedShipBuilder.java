package com.binary_studio.fleet_commander.core.ship;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class DockedShipBuilder {

	private String name;

	private PositiveInteger shieldHP;

	private PositiveInteger hullHP;

	private PositiveInteger capacitor;

	private PositiveInteger capacitorRegeneration;

	private PositiveInteger pg;

	private PositiveInteger speed;

	private PositiveInteger size;

	public static DockedShipBuilder named(String name) {
		var builder = new DockedShipBuilder();

		builder.speed = PositiveInteger.of(1);
		builder.size = PositiveInteger.of(1);

		builder.name = name;

		return builder;
	}

	public DockedShipBuilder pg(Integer val) {
		this.pg = PositiveInteger.of(val);
		return this;
	}

	public DockedShipBuilder hull(Integer val) {
		this.hullHP = PositiveInteger.of(val);
		return this;
	}

	public DockedShipBuilder shield(Integer val) {
		this.shieldHP = PositiveInteger.of(val);
		return this;
	}

	public DockedShipBuilder capacitorRegen(Integer val) {
		this.capacitorRegeneration = PositiveInteger.of(val);
		return this;
	}

	public DockedShipBuilder capacitor(Integer val) {
		this.capacitor = PositiveInteger.of(val);
		return this;
	}

	public DockedShipBuilder size(Integer size) {
		this.size = PositiveInteger.of(size);
		return this;
	}

	public DockedShipBuilder speed(Integer speed) {
		this.speed = PositiveInteger.of(speed);
		return this;
	}

	public DockedShip construct() {
		return DockedShip.construct(this.name, this.shieldHP, this.hullHP, this.pg, this.capacitor,
				this.capacitorRegeneration, this.speed, this.size);
	}

}
