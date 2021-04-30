package com.binary_studio.dependency_detector;

import com.binary_studio.dependency_detector.DependencyDetector;
import com.binary_studio.dependency_detector.DependencyList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CyclicDependencyDetectorTest {

    public static final String TASK = "dependency_detector";

    @Test
    @DisplayName("No Internal Dependencies")
    void testNoInternalDependencies() {
        assertTrue(DependencyDetector.canBuild(new DependencyList(List.of("foo.bar", "foo.quax"), List.of())), "Should be able to build dependencies when no internal dependencies are present");
    }

    @Test
    @DisplayName("Resolvable internal dependencies")
    void testResolvableInternalDependencies() {
        assertTrue(DependencyDetector.canBuild(new DependencyList(List.of("foo.bar", "foo.quax", "foo.pax"), List.of(new String[]{"foo.bar", "foo.quax" }, new String[]{"foo.bar", "foo.pax" }))), "Should be able to build dependencies when internal dependencies are resolvable");
    }

    @Test
    @DisplayName("Resolve with no libraries")
    void testNoFall() {
        assertTrue(DependencyDetector.canBuild(new DependencyList(List.of(), List.of())), "Should be able to build dependencies when internal dependencies are resolvable");
    }

    @Test
    @DisplayName("Fail with direct cyclic dependency")
    void testDirectCyclic() {
        assertFalse(DependencyDetector.canBuild(new DependencyList(List.of("foo.bar", "foo.quax"), List.of(new String[]{"foo.bar", "foo.quax" }, new String[]{"foo.quax", "foo.bar" }))), "Should fail with direct internal dependency");
    }

    @Test
    @DisplayName("Fail with indirect cyclic dependency")
    void testInderectCyclic() {
        assertFalse(DependencyDetector.canBuild(new DependencyList(List.of("foo.bar", "foo.quax", "foo.hit"), List.of(new String[]{"foo.bar", "foo.quax" }, new String[]{"foo.quax", "foo.hit" }, new String[]{"foo.hit", "foo.bar" }))), "Should fail with inderect dependencies");
    }
}