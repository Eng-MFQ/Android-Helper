package helper;

/**
 * Created by Bayan on 5/17/2015.
 */
public class ReflectionHelper {

    public static void setField(Object object, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
        field.setAccessible(false);
    }

    public static Object getFieldValue(Object object, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object returnValue = (Object) field.get(object);
        field.setAccessible(false);
        return returnValue;
    }
}
