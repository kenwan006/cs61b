public class OffByOne implements CharacterComparator{

    public OffByOne(){

    }
    /** if the difference is exactly one, return true */
    @Override
    public boolean equalChars(char x, char y){
        return Math.abs(x-y) ==1;
    }
}
