package daydreme;

interface SchemeObject {
    /**
     * Evaluate the scheme object in some environment, and return the result. For
     * constant objects, this should return the object itself. For lists,
     * this will evaluate the list in the given environment.
     *
     * @param environment the environment which includes all currently defined
     * identifiers and locally bound variables.
     * @return the result of evaluating this object 
     */
    SchemeObject evaluate(Environment environment);
}