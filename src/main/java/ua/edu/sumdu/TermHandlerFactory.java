package ua.edu.sumdu;

import ua.edu.sumdu.handlers.term.TermHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class factory for handlers different term type.
 */
public class TermHandlerFactory {

    private static Map<String,String> typeTerm = new HashMap<String,String>();

    static {
        typeTerm.put("CellReferenceHandler","(" + Config.LETTER + Config.DIGIT + ")");
        typeTerm.put("NumberHandler",Config.DIGIT);
    }

    /**
     * Method define type of term and return appropriate handler.
     * @param term - term value.
     * @return - object handler for term.
     */
    public TermHandler handler(String term) {

        for ( Map.Entry<String,String> entry : typeTerm.entrySet() ) {
            Pattern pTypeTerm = Pattern.compile(entry.getValue());
            Matcher mTypeTerm = pTypeTerm.matcher(term);
            if ( mTypeTerm.find() ) {
                return getHandler(entry.getKey());
            }
        }
        return null;
    }

    /**
     * Method return object handler by name handler.
     * @param nameHandler - name handler
     * @return -  object handler for term.
     */
    public TermHandler getHandler(String nameHandler) {
        try {
            String name = new StringBuilder().append("ua.edu.sumdu.handlers.term.")
                                             .append(nameHandler).toString();
            Class actionClass = Class.forName(name);
            return (TermHandler) actionClass.newInstance();
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
