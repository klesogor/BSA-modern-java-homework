package com.binary_studio.fleet_commander_test;

import com.binary_studio.fleet_commander.core.actions.attack.AttackAction;
import com.binary_studio.fleet_commander.core.actions.defence.RegenerateAction;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.subsystems.DefenciveSubsystemBuilder;
import com.binary_studio.fleet_commander.core.subsystems.DefenciveSubsystemImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefenciveSubsystemTest {

	@Test
	@DisplayName("Should not create defensive with empty or missing name")
	public void testShouldNotCreateWeaponWithoutName() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> DefenciveSubsystemImpl.construct("     ", PositiveInteger.of(10), PositiveInteger.of(15),
						PositiveInteger.of(15), PositiveInteger.of(5), PositiveInteger.of(5)));

		assertEquals(exception.getMessage(), "Name should be not null and not empty");
	}

	@Test
	@DisplayName("Should act as subsystem correctly")
	public void testShouldActAsSubsystem() {
		String name = "Overcharged Void Shield";
		Integer powerGridRequirments = 100;
		Integer capacitorRequirments = 20;

		DefenciveSubsystemImpl subsystem = DefenciveSubsystemBuilder.named(name).pg(powerGridRequirments)
				.capacitorUsage(capacitorRequirments).impactReduction(0).hullRegen(0).shieldRegen(0).construct();

		assertEquals(name, subsystem.getName(), "Names should be equal");
		assertEquals(powerGridRequirments, subsystem.getPowerGridConsumption().value(),
				"Powergrid requirments should be equal");
		assertEquals(capacitorRequirments, subsystem.getCapacitorConsumption().value(),
				"Capacitor consumption should be equal");
	}

	@Test
	@DisplayName("Should reduce impact correctly")
	public void testShouldReduceImpactCorrectly() {
		String shieldName = "Overcharged Void Shield Mock";
		String weaponName = "Hypervelocity Mass Driver Gun Mock";
		String targetName = "TSS Flyin' Coffin Mock";
		String attackerName = "ISS Mock of the Abyss";
		PositiveInteger powerGridRequirments = PositiveInteger.of(100);
		PositiveInteger capacitorRequirments = PositiveInteger.of(20);
		PositiveInteger damageReductionPercent = PositiveInteger.of(5);

		DefenciveSubsystemImpl subsystem = DefenciveSubsystemImpl.construct(shieldName, powerGridRequirments,
				capacitorRequirments, damageReductionPercent, PositiveInteger.of(10), PositiveInteger.of(5));
		NamedMock weapon = new NamedMock(weaponName);
		NamedMock attacker = new NamedMock(attackerName);
		NamedMock target = new NamedMock(targetName);

		AttackAction shouldDealSameDamage = subsystem
				.reduceDamage(new AttackAction(PositiveInteger.of(10), attacker, target, weapon));
		assertEquals(PositiveInteger.of(10), shouldDealSameDamage.damage, "Damage should not be reduced");
		assertEquals(attacker, shouldDealSameDamage.attacker, "Attacker should not be same");
		assertEquals(weapon, shouldDealSameDamage.weapon, "Weapon should be same");
		assertEquals(target, shouldDealSameDamage.target, "Target should be same");

		AttackAction shouldDealReducedDamage = subsystem
				.reduceDamage(new AttackAction(PositiveInteger.of(50), attacker, target, weapon));
		assertEquals(PositiveInteger.of(48), shouldDealReducedDamage.damage, "Damage should be reduced correctly");
		assertEquals(attacker, shouldDealReducedDamage.attacker, "Attacker should not be same");
		assertEquals(weapon, shouldDealReducedDamage.weapon, "Weapon should be same");
		assertEquals(target, shouldDealReducedDamage.target, "Target should be same");
	}

	@Test
	@DisplayName("Should not reduce impact over cap")
	public void testShouldNotReduceImpactOverCap() {
		String shieldName = "Overcharged Void Shield Mock";
		String weaponName = "Hypervelocity Mass Driver Gun Mock";
		String targetName = "TSS Flyin' Coffin Mock";
		String attackerName = "ISS Mock of the Abyss";
		PositiveInteger powerGridRequirments = PositiveInteger.of(100);
		PositiveInteger capacitorRequirments = PositiveInteger.of(20);
		PositiveInteger damageReductionPercent = PositiveInteger.of(147);

		DefenciveSubsystemImpl subsystem = DefenciveSubsystemImpl.construct(shieldName, powerGridRequirments,
				capacitorRequirments, damageReductionPercent, PositiveInteger.of(10), PositiveInteger.of(5));
		NamedMock weapon = new NamedMock(weaponName);
		NamedMock attacker = new NamedMock(attackerName);
		NamedMock target = new NamedMock(targetName);

		AttackAction shouldDealAtLeastOneDamage = subsystem
				.reduceDamage(new AttackAction(PositiveInteger.of(1), attacker, target, weapon));
		assertEquals(PositiveInteger.of(1), shouldDealAtLeastOneDamage.damage, "Should deal at least one damage");

		AttackAction shouldDealFivePercentOfDamage = subsystem
				.reduceDamage(new AttackAction(PositiveInteger.of(101), attacker, target, weapon));
		assertEquals(PositiveInteger.of(6), shouldDealFivePercentOfDamage.damage, "Should deal at least one damage");
	}

	@Test
	@DisplayName("Should regen correctly")
	public void testShouldRegenCorrectly() {
		String name = "Overcharged Void Shield";
		PositiveInteger powerGridRequirments = PositiveInteger.of(100);
		PositiveInteger capacitorRequirments = PositiveInteger.of(20);
		PositiveInteger shieldRegen = PositiveInteger.of(11);
		PositiveInteger hullRegen = PositiveInteger.of(12);

		DefenciveSubsystemImpl subsystem = DefenciveSubsystemImpl.construct(name, powerGridRequirments,
				capacitorRequirments, PositiveInteger.of(0), shieldRegen, hullRegen);
		RegenerateAction regen = subsystem.regenerate();

		assertEquals(regen.hullHPRegenerated, hullRegen, "Hull HP regenerated should be same");
		assertEquals(regen.shieldHPRegenerated, shieldRegen, "Hull HP regenerated should be same");
	}

}
