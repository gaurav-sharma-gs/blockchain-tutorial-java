public class MyUtility {
    public static String zeros(int length){
        StringBuilder sb = new StringBuilder();
        while(sb.length() < length){
            sb.append('0');
        }
        return sb.toString();
    }
}
