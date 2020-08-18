package phonebook;

// quick sort + binary search
public class ThirdPart extends FirstPart implements Sort {

    ThirdPart(String[] base) {
        super(base);
    }

    @Override
    public boolean startSort() {
        quickSort(this.base, 0, this.base.length - 1);
        return true;
    }

    @Override
    public int startSearch(String[] persons) {
        int founds = 0;
        for (String person : persons){
            if (binarySearch(this.base, person, 0, this.base.length) != -1){
                founds++;
            }
        }
        return founds;
    }


    private void quickSort(String[] array, int left, int right){
        if (left < right){
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    private int partition(String[] array, int left, int right){
        String pivot = giveName(array[right]);
        int partitionIndex = left;

        for (int i = left; i < right; i++){
            if (pivot.compareTo(giveName(array[i])) >= 0){
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(array, partitionIndex, right);
        return partitionIndex;
    }
    private int binarySearch(String[] array, String elem, int left, int right){
        while (left <= right){
            int mid = (left + right) >>> 1;

            if (elem.equals(giveName(array[mid]))){
                return mid;
            } else if (elem.compareTo(giveName(array[mid])) > 0){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return -1;
    }
}
