package ua.edu.sumdu.operations;

import ua.edu.sumdu.operations.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for produce new operation class depends from sign.
 */
public class OperationFactory {
    private Map<String,String> mapOperations = new HashMap<String,String>();

    {
        mapOperations.put("+", "AddOperation");
        mapOperations.put("-", "SubtractionOperation");
        mapOperations.put("/", "DivisionOperation");
        mapOperations.put("*", "MultipleOperation");
        mapOperations.put("^", "PowerOperation");
    }

    /**
     * Return object of sprecific operation.
     * @param operation sign specific operation
     * @return object of concrete operation.
     */
    public Operation getOperation(String operation) {
        try {
            String name = new StringBuilder().append("ua.edu.sumdu.operations.")
                                             .append(mapOperations.get(operation)).toString();
            Class actionClass = Class.forName(name);
            return (Operation) actionClass.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        }
    }
}
