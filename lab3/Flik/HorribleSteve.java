public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < 500; ++i, ++j, ++k) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
