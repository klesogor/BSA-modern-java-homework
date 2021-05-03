package com.binary_studio.fleet_commander_test;

import java.util.Optional;

import com.binary_studio.fleet_commander.core.actions.attack.AttackAction;
import com.binary_studio.fleet_commander.core.actions.defence.AttackResult;
import com.binary_studio.fleet_commander.core.actions.defence.RegenerateAction;
import com.binary_studio.fleet_commander.core.common.PositiveInteger;
import com.binary_studio.fleet_commander.core.exceptions.InsufficientPowergridException;
import com.binary_studio.fleet_commander.core.exceptions.NotAllSubsystemsFitted;
import com.binary_studio.fleet_commander.core.ship.CombatReadyShip;
import com.binary_studio.fleet_commander.core.ship.DockedShip;
import com.binary_studio.fleet_commander.core.ship.DockedShipBuilder;
import com.binary_studio.fleet_commander.core.subsystems.AttackSubsystemBuilder;
import com.binary_studio.fleet_commander.core.subsystems.AttackSubsystemImpl;
import com.binary_studio.fleet_commander.core.subsystems.DefenciveSubsystemBuilder;
import com.binary_studio.fleet_commander.core.subsystems.DefenciveSubsystemImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VesselTest {

	@Test
	@DisplayName("Should use powergrid correctly")
	public void testPgFitting() {

		DockedShipBuilder baseShip = DockedShipBuilder.named("ISS F for FAX").capacitor(1).capacitorRegen(1).hull(1)
				.shield(1).pg(100);
		AttackSubsystemBuilder baseWeapon = AttackSubsystemBuilder.named("Mock weapon").capacitorUsage(1).damage(1)
				.optimalSpeed(1).optimalSize(1).pg(100);

		DefenciveSubsystemBuilder baseDefence = DefenciveSubsystemBuilder.named("Mock def").capacitorUsage(1)
				.shieldRegen(1).hullRegen(1).impactReduction(0).pg(100);
		DockedShip vessel = baseShip.construct();

		try {
			vessel.fitDefensiveSubsystem(baseDefence.construct());
		}
		catch (InsufficientPowergridException ex) {
			fail("Should have enough capacity to fit only defencive");
		}
		assertThrows(InsufficientPowergridException.class, () -> vessel.fitAttackSubsystem(baseWeapon.construct()),
				"Should not fit over power grid capacity");

		DockedShip stableVessel = baseShip.pg(200).construct();

		try {
			stableVessel.fitDefensiveSubsystem(baseDefence.construct());
			stableVessel.fitAttackSubsystem(baseWeapon.construct());
		}
		catch (InsufficientPowergridException ex) {
			fail("Should have enough capacity to fit both defencive");
		}

		assertThrows(InsufficientPowergridException.class,
				() -> stableVessel.fitDefensiveSubsystem(baseDefence.pg(101).construct()),
				"Should not re-fit module over capacity");
	}

	@Test
	@DisplayName("Should not be able to undock without both modules")
	public void testShouldNotUndockWithoutBothModules() {

		DockedShip vessel = DockedShipBuilder.named("ISS Rusty Bucket").pg(1000).capacitor(1).capacitorRegen(1).hull(1)
				.shield(1).construct();

		assertThrows(NotAllSubsystemsFitted.class, () -> vessel.undock(),
				"Should not be able to undock without any module");

		DefenciveSubsystemImpl defSub = DefenciveSubsystemBuilder.named("DefSys").pg(1).capacitorUsage(1).hullRegen(1)
				.impactReduction(1).shieldRegen(1).construct();

		try {
			vessel.fitDefensiveSubsystem(defSub);
		}
		catch (InsufficientPowergridException ex) {
			fail("Should fit defencive and attack subsystems");
		}

		assertThrows(NotAllSubsystemsFitted.class, () -> vessel.undock(),
				"Should not be able to undock without attack module");

		try {
			vessel.fitDefensiveSubsystem(null);
		}
		catch (InsufficientPowergridException ex) {
			fail("Should be able to unfit subsystems");
		}

		AttackSubsystemImpl attackSub = AttackSubsystemBuilder.named("Wep").damage(1).capacitorUsage(1).optimalSize(1)
				.optimalSpeed(1).pg(1).construct();

		try {
			vessel.fitAttackSubsystem(attackSub);
		}
		catch (InsufficientPowergridException ex) {
			fail("Should fit defencive and attack subsystems");
		}

		assertThrows(NotAllSubsystemsFitted.class, () -> vessel.undock(),
				"Should not be able to undock without defence module");

		try {
			vessel.fitDefensiveSubsystem(defSub);
		}
		catch (InsufficientPowergridException ex) {
			fail("Should be able to fit defencive system back");
		}

		try {
			vessel.undock();
		}
		catch (NotAllSubsystemsFitted e) {
			fail("Should be able to undock with both subsystems in place");
		}
	}

	@Test
	@DisplayName("Capacitor should recharge")
	public void testCapacitorRecharge() {
		DockedShip vessel = DockedShipBuilder.named("ISS Overcharge").shield(1).hull(1).capacitorRegen(50)
				.capacitor(100).pg(2).construct();
		AttackableMock target = new AttackableMock("ISS Unlucky", PositiveInteger.of(10), PositiveInteger.of(10));

		try {
			vessel.fitAttackSubsystem(AttackSubsystemBuilder.named("Test Arm").pg(1).damage(10).capacitorUsage(100)
					.optimalSpeed(10).optimalSize(10).construct());
			vessel.fitDefensiveSubsystem(DefenciveSubsystemBuilder.named("Test Shield").pg(1).capacitorUsage(10)
					.shieldRegen(10).hullRegen(20).impactReduction(50).construct());
		}
		catch (InsufficientPowergridException ex) {
			fail("Should fit attack and defence subsystem");
		}

		try {
			CombatReadyShip ship = vessel.undock();

			ship.startTurn();
			Optional<AttackAction> attack = ship.attack(target);
			assertTrue(attack.isPresent(), "Should be able to attack with full cap");
			assertEquals(10, attack.get().damage.value(), "Should deal max damage");
			assertEquals("ISS Overcharge", attack.get().attacker.getName(), "Should have correct attacker");
			assertEquals("ISS Unlucky", attack.get().target.getName(), "Should have correct target");
			assertEquals("Test Arm", attack.get().weapon.getName(), "Should attack with right weapon");
			ship.endTurn();

			ship.startTurn();
			attack = ship.attack(target);
			assertFalse(attack.isPresent(), "Should not be able to attack in one turn");
			ship.endTurn();

			ship.startTurn();
			attack = ship.attack(target);
			assertTrue(attack.isPresent(), "Should be able to attack after cap regen");
			assertEquals(10, attack.get().damage.value(), "Should deal max damage");
			assertEquals("ISS Overcharge", attack.get().attacker.getName(), "Should have correct attacker");
			assertEquals("ISS Unlucky", attack.get().target.getName(), "Should have correct target");
			assertEquals("Test Arm", attack.get().weapon.getName(), "Should attack with right weapon");
			ship.endTurn();

		}
		catch (NotAllSubsystemsFitted ex) {
			fail("Should be able to undock field");
		}
	}

	@Test
	@DisplayName("Should apply damage correctly")
	public void testDamageApplication() {
		DockedShip vessel = DockedShipBuilder.named("ISS Overcharge").shield(100).hull(100).capacitorRegen(10)
				.capacitor(100).pg(2).construct();
		NamedMock weapon = new NamedMock("Boson field");
		NamedMock attacker = new NamedMock("Escal Zepher");

		try {
			vessel.fitAttackSubsystem(AttackSubsystemBuilder.named("Test Arm").pg(1).damage(10).capacitorUsage(100)
					.optimalSpeed(10).optimalSize(10).construct());
			vessel.fitDefensiveSubsystem(DefenciveSubsystemBuilder.named("Test Shield").pg(1).capacitorUsage(50)
					.shieldRegen(10).hullRegen(20).impactReduction(50).construct());
		}
		catch (InsufficientPowergridException ex) {
			fail("Should fit attack and defence subsystem");
		}

		try {
			CombatReadyShip ship = vessel.undock();

			ship.startTurn();
			// assert that don't regenerate over cap
			var emptyRegen = ship.regenerate();
			assertTrue(emptyRegen.isPresent(), "Should regenerate");
			assertEquals(0, emptyRegen.get().hullHPRegenerated.value(), "Should regen hull correctly");
			assertEquals(0, emptyRegen.get().shieldHPRegenerated.value(), "Should regen shield correctly");

			AttackResult.DamageRecived res = (AttackResult.DamageRecived) ship
					.applyAttack(new AttackAction(PositiveInteger.of(100), attacker, ship, weapon));
			assertEquals(50, res.damage.value(), "Should reduce impact correctly");
			Optional<RegenerateAction> regen = ship.regenerate();
			assertTrue(regen.isPresent(), "Should regenerate");
			assertEquals(0, regen.get().hullHPRegenerated.value(), "Should regen hull correctly");
			assertEquals(10, regen.get().shieldHPRegenerated.value(), "Should regen shield correctly");
			ship.endTurn();

			ship.startTurn();
			assertFalse(ship.regenerate().isPresent(), "Should not be able regen with not enough capacitor");

			ship.applyAttack(new AttackAction(PositiveInteger.of(100), attacker, ship, weapon));
			ship.applyAttack(new AttackAction(PositiveInteger.of(100), attacker, ship, weapon));
			ship.applyAttack(new AttackAction(PositiveInteger.of(100), attacker, ship, weapon));

			AttackResult finalBlow = ship
					.applyAttack(new AttackAction(PositiveInteger.of(100), attacker, ship, weapon));
			assertTrue(finalBlow instanceof AttackResult.Destroyed, "Ship should be destroyed exactly in 5 attacks");
			ship.endTurn();

		}
		catch (NotAllSubsystemsFitted ex) {
			fail("Should be able to undock field");
		}
	}

}
