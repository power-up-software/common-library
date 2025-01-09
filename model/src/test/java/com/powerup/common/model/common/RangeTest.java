package com.powerup.common.model.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RangeTest {

    @Test
    void testIsInRange_NominalCase() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertTrue(instance.isInRange(0));
    }

    @Test
    void testIsInRange_BottomEdge() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertTrue(instance.isInRange(-2));
    }

    @Test
    void testIsInRange_TopEdge() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertTrue(instance.isInRange(3));
    }

    @Test
    void testIsInRange_BottomOver() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertFalse(instance.isInRange(-3));
    }

    @Test
    void testIsInRange_TopOver() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertFalse(instance.isInRange(4));
    }

    @Test
    void testIsInRange_TopNotIncluded() {
        Range<Integer> instance = new Range<>(-2, 3, false, false);
        assertFalse(instance.isInRange(3));
    }

    @Test
    void testIsInRange_BottomNotIncluded() {
        Range<Integer> instance = new Range<>(-2, 3, false, false);
        assertFalse(instance.isInRange(-2));
    }

    @Test
    void testIsInRange_TopNull() {
        Range<Integer> instance = new Range<>(-2, null);
        assertTrue(instance.isInRange(4));
        assertFalse(instance.isInRange(-3));
    }

    @Test
    void testIsInRange_BottomNull() {
        Range<Integer> instance = new Range<>(null, 3);
        assertTrue(instance.isInRange(2));
        assertFalse(instance.isInRange(4));
    }

    @Test
    void testCompareTo_NominalCase() {
        Range<Integer> instance = new Range<>(-2, 3);
        Range<Integer> instance2 = new Range<>(-2, 2);
        assertTrue(instance.compareTo(instance2) > 0);
    }

    @Test
    void testCompareTo_Null() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertThrows(IllegalArgumentException.class, () -> instance.compareTo(null));
    }

    @Test
    void testHashCode_NominalCase() {
        Range<Integer> instance = new Range<>(-2, 3);
        Range<Integer> instance2 = new Range<>(-2, 3);
        assertEquals(instance.hashCode(), instance2.hashCode());
    }

    @Test
    void testEquals_NominalCase() {
        Range<Integer> instance = new Range<>(-2, 3);
        Range<Integer> instance2 = new Range<>(-2, 3);
        assertEquals(instance, instance2);
    }

    @Test
    void testEquals_Null() {
        Range<Integer> instance = new Range<>(-2, 3);
        //noinspection SimplifiableAssertion,ConstantConditions
        assertFalse(instance.equals(null));
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    void testEquals_Same() {
        Range<Integer> instance = new Range<>(-2, 3);
        assertEquals(instance, instance);
    }
}