package dreme;

/**
 * An operator that executes its contents and returns the last result.
 */
public final class Container extends BasicSchemeObject implements Operator {
    public void apply(ExecutionContext context) {
        context.returnLastResult();
    }
}
