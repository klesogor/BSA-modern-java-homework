package com.binary_studio.fleet_commander_test;

import com.binary_studio.fleet_commander.core.common.NamedEntity;

final class NamedMock implements NamedEntity {

	private final String name;

	NamedMock(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
