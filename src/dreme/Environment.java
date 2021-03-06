package dreme;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Environment implements Cloneable, Iterable<Map.Entry<Identifier, SchemeObject>> {
    private Environment parent = null;
    private Map<Identifier, SchemeObject> bindings = new TreeMap<Identifier, SchemeObject>();

    public Environment() {
    }

    private Environment(Environment parent) {
        this.parent = parent;
    }

    public void bind(SchemeObject var, SchemeObject val) {
        if (!(var instanceof Identifier))
            throw new IllegalArgumentException("Invalid binding: " + var);
        bindings.put((Identifier) var, val);
    }

    public void bindAll(Environment other) {
        bindings.putAll(other.bindings);
    }

    public void define(Identifier name, SchemeObject value) {
        Environment topLevel = this;
        while (topLevel.parent != null)
            topLevel = topLevel.parent;
        topLevel.bindings.put(name, value);
    }

    public SchemeObject get(Identifier name) {
        if (bindings.containsKey(name))
            return bindings.get(name);
        return parent != null ? parent.get(name) : null;
    }

    public Environment copy() {
        return new Environment(this);
    }

    public boolean contains(Identifier identifier) {
        return bindings.containsKey(identifier) ||
            (parent != null && parent.contains(identifier));
    }

    public void addTransformer(Identifier name, SchemeObject transformer) {
        bindings.put(name, transformer);
    }

    public void set(Identifier identifier, SchemeObject schemeObject) {
        if (bindings.containsKey(identifier)) {
            bindings.put(identifier, schemeObject);
        }
        else if (parent != null) {
            parent.set(identifier, schemeObject);
        }
        else {
            throw new IllegalArgumentException("Unbound variable: " + identifier);
        }
    }

    public String toString() {
        return bindings.toString();
    }

    public Iterator<Map.Entry<Identifier, SchemeObject>> iterator() {
        return bindings.entrySet().iterator();
    }
}
