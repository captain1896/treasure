package com.buzz.abstracts;

public class AssertUtils implements IAssert {
    @Override
    public void isNull(Object object) throws IllegalArgumentException {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    @Override
    public void isNull(Object object, String message) throws IllegalArgumentException {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void notNull(Object object) throws IllegalArgumentException {
        notNull(object, "[Assertion failed] - object argument must not be null");
    }

    @Override
    public void notNull(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void isTrue(boolean expression) throws IllegalArgumentException {
        isTrue(expression, "[Assertion failed] - this expression must be true ");
    }

    @Override
    public void isTrue(boolean expression, String message) throws IllegalArgumentException {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void hasText(String object) throws IllegalArgumentException {
        hasText(object, "[Assertion failed] - this string argument must have text; it must not be null, or blank");
    }


    @Override
    public void hasText(String object, String message) throws IllegalArgumentException {
        if (!hasTextOrNot(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    public boolean hasTextOrNot(CharSequence str) {

        boolean hasLength = str != null && str.length() > 0;
        if (!hasLength) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void hasLength(String object) throws IllegalArgumentException {
        hasLength(object, "[Assertion failed] - this string argument must have length;it must not be null or empty");
    }

    @Override
    public void hasLength(String object, String message) throws IllegalArgumentException {
        boolean hasLength = object != null && object.length() > 0;

        if (!hasLength) {
            throw new IllegalArgumentException(message);
        }

    }

    @Override
    public void isInstanceOf(Class<?> clazz, Object obj) throws IllegalArgumentException {

    }

    @Override
    public void isInstanceOf(Class<?> clazz, Object obj, String message) throws IllegalArgumentException {

    }
}
