package pt.pxinxas.fcpp.util;

/**
 * Utils to reflection
 *
 * @author JSalavisa
 */
public class ReflectionUtil {

    public static <T> T getInstance(Class<? extends T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
