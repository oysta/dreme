package dreme;

public class Tokens
{
    public static interface Token {}

    public static final Token OPEN_PARENS = new Token() {
        public String toString()
        {
            return "OPEN_PARENS";
        }
    };

    public static final Token CLOSE_PARENS = new Token() {
        public String toString()
        {
            return "CLOSE_PARENS";
        }
    };

    public static final Token DOT = new Token() {
        public String toString() {
            return "DOT";
        }
    };

    public static final Token QUOTE = new Token() {
        public String toString() {
            return "QUOTE";
        }
    };

    public static final Token QUASIQUOTE = new Token() {
        public String toString() {
            return "QUASIQUOTE";
        }
    };

    public static final Token UNQUOTE = new Token() {
        public String toString() {
            return "UNQUOTE";
        }
    };

    public static final Token ELLIPSIS = new Token() {
        public String toString() {
            return "ELLIPSIS";
        }
    };

    public static abstract class Value implements Token {
        private final String value;

        public Value(String value)
        {
            if (value == null)
                throw new IllegalArgumentException("Value cannot be null");
            this.value = value;
        }

        public String toString()
        {
            return value;
        }

        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return value.equals(((Value) o).value);
        }

        public int hashCode()
        {
            return value.hashCode();
        }

        public String getValue() {
            return value;
        }
    }

    public static final Token END_OF_STREAM = new Token() {
        public String toString()
        {
            return "EOS";
        }
    };

    public static class BareWord extends Value
    {
        public BareWord(String value)
        {
            super(value);
        }
    }

    public static class SString extends Value
    {
        public SString(String value)
        {
            super(value);
        }

        public String toString() {
            return "\"" + super.toString() + "\"";
        }
    }

    public static class Integer extends Value
    {
        public Integer(String value)
        {
            super(value);
        }

        public long getLong()
        {
            return java.lang.Long.parseLong(getValue());
        }
    }

    public static class Decimal extends Value
    {
        public Decimal(String value)
        {
            super(value);
        }

        public double getDouble() {
            return java.lang.Double.parseDouble(getValue());
        }
    }

    public static class Boolean implements Token {
        public static Boolean TRUE = new Boolean();
        public static Boolean FALSE = new Boolean();

        private Boolean() {}
    }
}
