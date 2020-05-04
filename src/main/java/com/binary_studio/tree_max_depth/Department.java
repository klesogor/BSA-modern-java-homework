package com.binary_studio.tree_max_depth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Department {

	public final String name;

	public final List<Department> subDepartments;

	public Department(String name) {
		this.name = name;
		this.subDepartments = new ArrayList<>();
	}

	public Department(String name, Department... departments) {
		this.name = name;
		this.subDepartments = Arrays.asList(departments);
	}

}
