package shop.wannab.batchserver.global;

public class NullCheckUtil {

    public static void nullCheck(Object clazz, Object obj1) {
        if (obj1 == null) {
            throw new CustomNullPointerException(clazz.getClass().toString());
        }
    }

    public static void nullCheck(Object clazz, Object obj1, Object obj2) {
        nullCheck(clazz, obj1);
        if (obj2 == null) {
            throw new CustomNullPointerException(clazz.getClass().toString());
        }
    }

    public static void nullCheck(Object clazz, Object obj1, Object obj2, Object obj3) {
        nullCheck(clazz, obj1, obj2);
        if (obj3 == null) {
            throw new CustomNullPointerException(clazz.getClass().toString());
        }
    }

    public static void nullCheck(Object clazz, Object obj1, Object obj2, Object obj3, Object obj4) {
        nullCheck(clazz, obj1, obj2, obj3);
        if (obj4 == null) {
            throw new CustomNullPointerException(clazz.getClass().toString());
        }
    }

    public static void nullCheck(Object clazz, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) {
        nullCheck(clazz, obj1, obj2, obj3, obj4);
        if (obj5 == null) {
            throw new CustomNullPointerException(clazz.getClass().toString());
        }
    }
}
