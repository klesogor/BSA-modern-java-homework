package com.binary_studio.fleet_commander.core.actions.defence;

import com.binary_studio.fleet_commander.core.common.PositiveInteger;

public final class RegenerateAction {

	public final PositiveInteger shieldHPRegenerated;

	public final PositiveInteger hullHPRegenerated;

	public RegenerateAction(PositiveInteger shieldRegenerated, PositiveInteger hullRegenerated) {
		this.shieldHPRegenerated = shieldRegenerated;
		this.hullHPRegenerated = hullRegenerated;
	}

}
