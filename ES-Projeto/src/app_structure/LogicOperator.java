/**
 * 
 */
package app_structure;

import java.util.NoSuchElementException;

public enum LogicOperator {
	
	/**
	 * And logic operator (&&).
	 */
	AND("AND") {
		@Override 
		public boolean apply(boolean left, boolean right) {
			return left && right;
		}
	},
	
	/**
	 * Or logic operator (||).
	 */
	OR("OR") {
		@Override 
		public boolean apply(boolean left, boolean right) {
			return left || right;
		}
	};

    private final String operator;

    /**
     * Sole constructor. Not used by programmers.
     * @param operator - the name of enum constant.
     */
    private LogicOperator(String operator) {
        this.operator = operator;
    }
    
    @Override
    public String toString() {
    	return "Operator: " + this.operator;
    }
    
    /**
     * Returns the enum constant of this type with the specified name.
     * The string must match exactly an identifier used to declare an enum constant in this type.
     * @param operator - string name of the enum constant.
     * @return the enum constant with the specified name.
     * @throws NoSuchElementException if this enum type has no constant with the specified name.
     */
    public static LogicOperator parseOperator(String operator) {
        for (LogicOperator o : values()) {
            if (o.operator.equals(operator)) 
            	return o;
        }
        throw new NoSuchElementException(String.format("Unknown logic operator:", operator));
    }
    
    /**
     * Compares two specified boolean conditions. 
     * The result is true if and only if the left condition and/or (according to the logic operator) the right condition are true.
     * Otherwise the result is false.
     * @param left - The boolean left condition.
     * @param right - The boolean right condition.
     * @return true if the left condition and/or (according to the logic operator) the right condition are true. Otherwise returns false.
     */
    public abstract boolean apply(boolean left, boolean right);
}
