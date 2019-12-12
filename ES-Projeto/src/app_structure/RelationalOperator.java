
package app_structure;

import java.util.NoSuchElementException;

/**
 * @author ES1-2019-IC1-24
 *
 */
public enum RelationalOperator {
	
	/**
	 *  Less RelationalOperator (<).
	 *  Can do apply with int's or double's. 
	 */
	LESS("<") { 
		@Override 
		public boolean apply(int left, int right) {
			return left < right;
		}

		@Override
		public boolean apply(double left, double right) {
			return left < right;
		}
	},
	
	
	/**
	 * More RelationalOperator (>).
	 *  Can do apply with int's or double's.
	 */
	MORE(">") {
		@Override 
		public boolean apply(int left, int right) {
			return left > right;
		}

		@Override
		public boolean apply(double left, double right) {
			return left > right;
		}
	};

	
    private final String operator;

  
    /**
     * Sole constructor. Not used by programmers.
     * @param operator - the name of enum constant.
     */
    private RelationalOperator(String operator) {
        this.operator = operator;
    }
    
  
    @Override
    public String toString() {
    	return "Operator: " + this.operator;
    }
    
    
    /**
     * Returns the enum constant of this type with the specified name.
     * The string must match exactly an identifier used to declare an enum constant.
     * @param operator - string name of the enum constant.
     * @return the enum constant with the specified name.
     * @throws NoSuchElementException if this enum type has no constant with the specified name.
     */
    public static RelationalOperator parseOperator(String operator) {
        for (RelationalOperator o : values()) {
            if (o.operator.equals(operator)) 
            	return o;
        }
        throw new NoSuchElementException(String.format("Unknown relational operator:", operator));
    }
    
    
    /**
     * Compares two specified boolean conditions. 
     * The result is true if and only if the left condition </> (according to the relational operator) the right condition are true.
     * Otherwise the result is false.
     * @param left - The int/double left condition.
     * @param right - The int/double right condition.
     * @return true if the left condition </> the right condition are true. Otherwise returns false.
     * can do with int's or double's.
     */
    public abstract boolean apply(int left, int right);
    public abstract boolean apply(double left, double right);
}
