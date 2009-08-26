package daydreme;

import static daydreme.Procedures.LET;
import static daydreme.Procedures.PLUS;
import static daydreme.Procedures.MULTIPLY;

final class SchemeObjects
{
    public static final Environment ENVIRONMENT = new Environment(LET, PLUS, MULTIPLY);

    private SchemeObjects() {} // DENIED

    static List list(SchemeObject... items)
    {
        List list = new List();
        for (SchemeObject item : items) {
            list.add(item);
        }
        return list;
    }

    static Integer num(int value)
    {
        return new Integer(value);
    }

    static Pair pair(SchemeObject car, SchemeObject cdr)
    {
        return new Pair(car, cdr);
    }

    static Identifier word(String value)
    {
        return new Identifier(value);
    }
}