package phonebook;

// bubble sort + jump search
public class SecondPart extends FirstPart implements Sort{

    private boolean sorted = false;

    SecondPart(String[] base){
        super(base);
    }

    public boolean isSorted() { return this.sorted; };

    @Override
    public int startSearch(String[] persons) {
        int founds = 0;
        if (this.sorted){
            for (String person : persons){
                if (jumpSearch(this.base, person) != -1){
                    founds++;
                }
            }
        }
        else{
            return super.startSearch(persons);
        }
        return founds;
    }

    private int backSearch(String[] base, String target, int leftBorder, int rightBorder){
        for (int i = rightBorder; i > leftBorder; i--){
            if (base[i].contains(target)){
                return i;
            }
        }
        return -1;
    }

    private int jumpSearch(String[]base, String target){
        int rightBorder = 0;
        int leftBorder = 0;

        if (base.length == 0){
            return -1;
        }
        if (base[0].contains(target)){
            return 0;
        }
        int jumpLength = (int)Math.sqrt(base.length);
        while (rightBorder < base.length - 1){
            rightBorder = Math.min(base.length - 1, rightBorder + jumpLength);
            if (giveName(base[rightBorder]).compareTo(target) >= 0){
                break;
            }
            leftBorder = rightBorder;
        }
        if (rightBorder == base.length - 1 && target.compareTo(giveName(base[rightBorder])) > 0){
            return -1;
        }
        return backSearch(base, target, leftBorder, rightBorder);

    }

    @Override
    public boolean startSort() {
        for (int i = 0; i < this.base.length - 1; i++){
            for (int j = 0; j != this.base.length - 1 - i; j++){
                if (giveName(this.base[j]).compareTo(giveName(this.base[j + 1])) > 0){
                    swap(this.base, j, j + 1);
                }
                if (Main.timeLinearSearch * 10 < System.currentTimeMillis() - Main.timeBubbleSort){
                    return false;
                }
            }
        }
        this.sorted = true;
        return true;
    }
}
