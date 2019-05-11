
package Objects;


public class IdCandidate {
        
    public static String NUMEROS = "0123456789";
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String getPinNumber() {
            return getID(NUMEROS, 4);
    }

    public static String getID() {
            return getID(8);
    }

    public static String getID(int length) {
            return getID(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getID(String key, int length) {
            String ID = "";

            for (int i = 0; i < length; i++) {
                    ID+=(key.charAt((int)(Math.random() * key.length())));
            }

            return ID;
    }
}
