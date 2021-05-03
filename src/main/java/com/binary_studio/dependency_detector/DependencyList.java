package com.binary_studio.dependency_detector;

import java.util.List;

public class DependencyList {

	public final List<String> libraries;

	public final List<String[]> dependencies;

	public DependencyList(List<String> libraries, List<String[]> dependencies) {
		this.libraries = libraries;
		this.dependencies = dependencies;
	}

}
