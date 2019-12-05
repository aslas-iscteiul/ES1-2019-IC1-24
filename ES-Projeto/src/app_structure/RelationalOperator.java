/**
 * 
 */
package app_structure;

import java.util.NoSuchElementException;

public enum RelationalOperator {
	
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

    private RelationalOperator(String operator) {
        this.operator = operator;
    }
    
    @Override
    public String toString() {
    	return "Operator: " + this.operator;
    }
    
    public static RelationalOperator parseOperator(String operator) {
        for (RelationalOperator o : values()) {
            if (o.name().equals(operator)) 
            	return o;
        }
        throw new NoSuchElementException(String.format("Unknown relational operator:", operator));
    }
    
    public abstract boolean apply(int left, int right);
    public abstract boolean apply(double left, double right);
}
