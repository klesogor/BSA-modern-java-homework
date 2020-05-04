package com.binary_studio.fleet_commander.core.common;

public final class PositiveInteger {

	private final Integer underlyingVal;

	public PositiveInteger(Integer val) {
		if (val < 0) {
			throw new IllegalArgumentException(String.format("Got negative value %d, expected positive integer", val));
		}
		this.underlyingVal = val;
	}

	public static PositiveInteger of(Integer val) {
		return new PositiveInteger(val);
	}

	public Integer value() {
		return this.underlyingVal;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != PositiveInteger.class) {
			return false;
		}
		return ((PositiveInteger) obj).underlyingVal == this.underlyingVal;
	}

	@Override
	public int hashCode() {
		return this.underlyingVal.hashCode();
	}

}
