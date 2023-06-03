package com.github.MikeMalenkov2005.jage;

public abstract class GLResource {
    private boolean valid = true;

    protected abstract void cleanUp();

    public void delete() {
        if (valid) {
            cleanUp();
            valid = false;
        }
    }

    public boolean isValid() {
        return valid;
    }

    public void exceptInvalid(String name) {
        if (!valid) throw new InvalidGLResourceException("Invalid " + name);
    }
}
