package com.binary_studio.uniq_in_sorted_stream;

//You CAN modify this class
public final class Row<RowData> {

	private final Long id;

	public Row(Long id) {
		this.id = id;
	}

	public Long getPrimaryId() {
		return this.id;
	}

}
