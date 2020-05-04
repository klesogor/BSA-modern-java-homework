package com.binary_studio.uniq_in_sorted_stream_test;

import java.util.stream.Stream;

import com.binary_studio.uniq_in_sorted_stream.Row;
import com.binary_studio.uniq_in_sorted_stream.UniqueSortedStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueSortedStreamTest {

	static final String TEST = "db_test";

	@Test
	@DisplayName("Should remove duplicated integers")
	void testBasicIntegerStream() {
		var stream = Stream.of(1, 1, 1, 2, 3, 4, 4, 4, 4, 10, 12, 12, 15, 20, 20, 40, 42, 43, 100, 101, 101)
				.map(Integer::toUnsignedLong).map(Row<Object>::new);
		var uniqSortedStream = UniqueSortedStream.uniqueRowsSortedByPK(stream);
		assertArrayEquals(new Long[] { 1L, 2L, 3L, 4L, 10L, 12L, 15L, 20L, 40L, 42L, 43L, 100L, 101L },
				uniqSortedStream.map(Row::getPrimaryId).toArray(Long[]::new), "Duplicates should be removed");
	}

	@Test
	@DisplayName("Should work with empty stream")
	void testEmptyIntegerStream() {
		Stream<Row<Object>> stream = Stream.of();
		Stream<Row<Object>> sortedStream = UniqueSortedStream.uniqueRowsSortedByPK(stream);

		assertEquals(0, sortedStream.count(), "Should be empty if stream is empty");
	}

	@Test
	@DisplayName("Should work with already unique sorted stream")
	void testAlreadyUniqIntegerStream() {
		var stream = Stream.of(1, 2, 3, 4, 10, 12, 15, 20, 40, 42, 43, 100, 101).map(Integer::toUnsignedLong)
				.map(Row<Object>::new);
		var sortedStream = UniqueSortedStream.uniqueRowsSortedByPK(stream);
		assertArrayEquals(new Long[] { 1L, 2L, 3L, 4L, 10L, 12L, 15L, 20L, 40L, 42L, 43L, 100L, 101L },
				sortedStream.map(Row::getPrimaryId).toArray(Long[]::new), "Sorted uniq stream should not be changed");
	}

}
