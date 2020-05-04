package com.binary_studio.tree_max_depth_test;

import com.binary_studio.tree_max_depth.Department;
import com.binary_studio.tree_max_depth.DepartmentMaxDepth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxTreeDepthTest {

	public static final String TASK = "department";

	static Department constructExample() {
		return new Department("A", new Department("B", new Department("D")), new Department("C"));
	}

	static Department constructExampleWithNull() {
		return new Department("A", null, new Department("B", new Department("D")), null, new Department("C"));
	}

	static Department constructWithNullLevel() {
		return new Department("A", new Department("B", null, null, null, null),
				new Department("C", null, null, null, null), new Department("D"));
	}

	@Test
	@DisplayName("Should be zero on null")
	void testOnNull() {
		assertEquals(0, DepartmentMaxDepth.calculateMaxDepth(null), "Depth is 0 on null root");
	}

	@Test
	@DisplayName("Should handle basic cases")
	void testBasic() {
		assertEquals(1, DepartmentMaxDepth.calculateMaxDepth(new Department("Chief dept")),
				"Depth should be 1 when root is leaf");
		assertEquals(3, DepartmentMaxDepth.calculateMaxDepth(constructExample()), "Should handle example");
		assertEquals(3, DepartmentMaxDepth.calculateMaxDepth(constructExampleWithNull()),
				"Should handle example with null");
		assertEquals(2, DepartmentMaxDepth.calculateMaxDepth(constructWithNullLevel()),
				"Should handle null children correctly");
	}

}
