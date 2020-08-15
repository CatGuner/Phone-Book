package phonebook;

// linear search
public class FirstPart implements Searching{

    protected String[] base;

    FirstPart(String[] base){
        this.base = base.clone();
    }

    public String[] getBase(){
        return this.base;
    }

    @Override
    public  int startSearch(String[] persons){
        int founds = 0;
        for (String person : persons){
            if (linearSearch(person) != -1){
                founds++;
            }
        }
        return founds;
    }

    private int linearSearch(String person){
        for (int i = 0; i < this.base.length; i++){
            if (this.base[i].contains(person)){
                return i;
            }
        }
        return -1;
    }

    protected void swap(String[] array, int i, int j){
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected String giveName(String str){
        return str.split(" ", 2)[1];
    }

}
