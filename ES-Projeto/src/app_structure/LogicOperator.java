/**
 * 
 */
package app_structure;

import java.util.NoSuchElementException;

/**
 * @author ana0s
 *
 */
public enum LogicOperator {
	
	AND("AND") {
		@Override 
		public boolean apply(boolean left, boolean right) {
			return left && right;
		}
	},
	
	OR("OR") {
		@Override 
		public boolean apply(boolean left, boolean right) {
			return left || right;
		}
	};

    private final String operator;

    private LogicOperator(String operator) {
        this.operator = operator;
    }
    
    @Override
    public String toString() {
    	return "Operator: " + this.operator;
    }
    
    public static LogicOperator parseOperator(String operator) {
        for (LogicOperator o : values()) {
            if (o.operator.equals(operator)) 
            	return o;
        }
        throw new NoSuchElementException(String.format("Unknown logic operator:", operator));
    }
    
    
    public abstract boolean apply(boolean left, boolean right);

}
