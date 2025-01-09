package com.powerup.common.model.common;

import java.io.Serial;
import java.io.Serializable;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The <code>Range</code> class is a container for a range constraint on a value.
 *
 * <p>This object is immutable.
 *
 * @param <T> Type of the value that the range is a constraint of.
 *
 * @author Chris Picard
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Range<T extends Comparable & Serializable> implements Serializable, Comparable<Range<T>> {
    /**
     * Serial Version ID allowing required by the {@link Serializable} contract.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Representation of the minimum value allowed for the value the range is a constraint of.
     */
    private final T minimum;
    /**
     * Representation of the maximum value allowed for the value the range is a constraint of.
     */
    private final T maximum;
    /**
     * Flag indicating if the minimum value is inclusive meaning that if a value is equal to it then it is considered in range.
     */
    private final boolean minimumInclusive;
    /**
     * Flag indicating if the maximum value is inclusive meaning that if a value is equal to it then it is considered in range.
     */
    private final boolean maximumInclusive;

    /**
     * Base constructor taking all parameters making the object immutable.
     *
     * @param minimum The value of {@link #minimum}.
     * @param maximum The value of {@link #maximum}.
     *
     * @throws IllegalArgumentException One or both of the values are null.
     */
    public Range(final T minimum, final T maximum) {
        this(minimum, maximum, true, true);
    }

    /**
     * Base constructor taking all parameters making the object immutable.
     *
     * @param minimum The value of {@link #minimum}.
     * @param maximum The value of {@link #maximum}.
     * @param minimumInclusive The value of {@link #minimumInclusive}.
     * @param maximumInclusive The value of {@link #maximumInclusive}.
     */
    public Range(final T minimum, final T maximum, final boolean minimumInclusive, final boolean maximumInclusive) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.minimumInclusive = minimumInclusive;
        this.maximumInclusive = maximumInclusive;
    }

    /**
     * Accessor for the {@link #minimum} member variable.
     *
     * @return Current value of the {@link #minimum} member variable.
     */
    public T getMinimum() {
        return minimum;
    }

    /**
     * Accessor for the {@link #maximum} member variable.
     *
     * @return Current value of the {@link #maximum} member variable.
     */
    public T getMaximum() {
        return maximum;
    }

    /**
     * Accessor for the {@link #minimumInclusive} member variable.
     *
     * @return Current value of the {@link #minimumInclusive} member variable.
     */
    public boolean isMinimumInclusive() {
        return minimumInclusive;
    }

    /**
     * Accessor for the {@link #maximumInclusive} member variable.
     *
     * @return Current value of the {@link #maximumInclusive} member variable.
     */
    public boolean isMaximumInclusive() {
        return maximumInclusive;
    }

    /**
     * Utility method that checks is the provided value is within the range or not.
     *
     * @param value The value to check if it is in range or not.
     *
     * @return True if the value is within the range, false if it is not.
     */
    public boolean isInRange(final T value) {
        boolean result = true;
        if (minimum != null && value.compareTo(minimum) <= 0) {
            if (!value.equals(minimum) || !minimumInclusive) {
                result = false;
            }
        }
        if (maximum != null && value.compareTo(maximum) >= 0) {
            if (!value.equals(maximum) || !maximumInclusive) {
                result = false;
            }
        }
        return result;
    }

    // BEGIN_GENERATED_CODE

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Range<T> other) {
        if (other != null) {
            CompareToBuilder compareToBuilder = new CompareToBuilder();
            compareToBuilder.append(minimum, other.minimum);
            compareToBuilder.append(maximum, other.maximum);
            return compareToBuilder.toComparison();

        }
        throw new IllegalArgumentException("Compare to is undefined on a null object");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Range<?> range = (Range<?>) o;

        return new EqualsBuilder()
                .append(minimum, range.minimum)
                .append(maximum, range.maximum)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(minimum)
                .append(maximum)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    // END_GENERATED_CODE
}