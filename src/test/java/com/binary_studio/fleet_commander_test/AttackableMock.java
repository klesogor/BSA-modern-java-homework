package com.binary_studio.fleet_commander_test;

import com.binary_studio.fleet_commander.core.common.Attackable;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;

final class AttackableMock implements Attackable {

	private String name;

	private PositiveInteger size;

	private PositiveInteger speed;

	AttackableMock(String name, PositiveInteger size, PositiveInteger speed) {
		super();
		this.name = name;
		this.size = size;
		this.speed = speed;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public PositiveInteger getSize() {
		return this.size;
	}

	@Override
	public PositiveInteger getCurrentSpeed() {
		return this.speed;
	}

}
