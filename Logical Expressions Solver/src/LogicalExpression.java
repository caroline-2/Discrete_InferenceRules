import java.util.*;

public class LogicalExpression implements Expression{
    private String representation;
    private Map<Character, Boolean> variableValues;
    public LogicalExpression(String representation) {
        this.representation = representation;
    }
    @Override
    public String getRepresentation() {
        return representation;
    }
    @Override
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
    public void setVariableValues(Map<Character, Boolean> variableValues) {
        this.variableValues = variableValues;
    }
    public Map<Character, Boolean> getVariableValues() {
        return variableValues;
    }

}
