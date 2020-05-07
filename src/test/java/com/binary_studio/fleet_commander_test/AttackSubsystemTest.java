package com.binary_studio.fleet_commander_test;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.subsystems.AttackSubsystemBuilder;
import com.binary_studio.fleet_commander.core.subsystems.AttackSubsystemImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttackSubsystemTest {

	@Test
	@DisplayName("Should not create weapon with empty or missing name")
	public void testShouldNotCreateWeaponWithoutName() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> AttackSubsystemImpl.construct("        ", PositiveInteger.of(1), PositiveInteger.of(1),
						PositiveInteger.of(1), PositiveInteger.of(1), PositiveInteger.of(1)));
		assertEquals(exception.getMessage(), "Name should be not null and not empty");
	}

	@Test
	@DisplayName("Should act as subsystem correctly")
	public void testShouldActAsSubsystem() {
		String name = "Antimatter Blaster Battery";
		Integer powerGridRequirments = 100;
		Integer capacitorRequirments = 20;

		AttackSubsystemImpl subsystem = AttackSubsystemBuilder.named(name).pg(100).capacitorUsage(20).damage(1)
				.optimalSize(1).optimalSpeed(1).construct();

		assertEquals(name, subsystem.getName(), "Names should be equal");
		assertEquals(powerGridRequirments, subsystem.getPowerGridConsumption().value(),
				"Powergrid requirments should be equal");
		assertEquals(capacitorRequirments, subsystem.getCapacitorConsumption().value(),
				"Capacitor consumption should be equal");
	}

	@Test
	@DisplayName("Should apply full damage in optimal conditions")
	public void testShouldApplyFullDamageOnUpperEdge() {
		String name = "Mock weapon";
		Integer optimalSize = 50;
		Integer optimalTargetSpeed = 150;
		Integer baseDamage = 100;
		AttackSubsystemImpl weapon = AttackSubsystemBuilder.named(name).pg(1).capacitorUsage(1).optimalSize(optimalSize)
				.optimalSpeed(optimalTargetSpeed).damage(baseDamage).construct();
		AttackableMock target = new AttackableMock("Mock target", PositiveInteger.of(optimalSize),
				PositiveInteger.of(optimalTargetSpeed));

		PositiveInteger attackResult = weapon.attack(target);

		assertEquals(baseDamage, attackResult.value());
	}

	@Test
	@DisplayName("Should apply half of damage by speed")
	public void testShouldApplyHalfOfDamageWithBySpeedReduction() {
		String name = "Mock weapon";
		Integer optimalSize = 50;
		Integer optimalTargetSpeed = 150;
		Integer baseDamage = 100;
		AttackSubsystemImpl weapon = AttackSubsystemBuilder.named(name).pg(1).capacitorUsage(1).optimalSize(optimalSize)
				.optimalSpeed(optimalTargetSpeed).damage(baseDamage).construct();
		AttackableMock target = new AttackableMock("Mock target", PositiveInteger.of(optimalSize),
				PositiveInteger.of(optimalTargetSpeed + 1));

		PositiveInteger attackResult = weapon.attack(target);

		assertEquals(PositiveInteger.of(baseDamage / 2), attackResult);
	}

	@Test
	@DisplayName("Should apply half of damage by size and speed")
	public void testShouldApplyHalfOfDamageWithBySizeReduction() {
		String name = "Mock weapon";
		Integer optimalSize = 50;
		Integer optimalTargetSpeed = 150;
		Integer baseDamage = 100;
		AttackSubsystemImpl weapon = AttackSubsystemBuilder.named(name).pg(1).capacitorUsage(1).optimalSize(optimalSize)
				.optimalSpeed(optimalTargetSpeed).damage(baseDamage).construct();
		AttackableMock target = new AttackableMock("Mock target", PositiveInteger.of(optimalSize / 2),
				PositiveInteger.of(optimalTargetSpeed / 2));

		PositiveInteger attackResult = weapon.attack(target);

		assertEquals(PositiveInteger.of(baseDamage / 2), attackResult);
	}

	@Test
	@DisplayName("Should apply zero damage by size formulae")
	public void testShouldApplyZeroDamage() {
		String name = "Mock weapon";
		Integer optimalSize = 50;
		Integer optimalTargetSpeed = 150;
		Integer baseDamage = 100;
		AttackSubsystemImpl weapon = AttackSubsystemBuilder.named(name).pg(1).capacitorUsage(1).optimalSize(optimalSize)
				.optimalSpeed(optimalTargetSpeed).damage(baseDamage).construct();
		AttackableMock target = new AttackableMock("Mock target", PositiveInteger.of(0),
				PositiveInteger.of(optimalTargetSpeed));

		PositiveInteger attackResult = weapon.attack(target);

		assertEquals(PositiveInteger.of(0), attackResult);
	}

}
