package strategy;

public class Application {
    private SortAlg sort;

    public static void main(String[] args) {
        Application app = new Application();
        app.setSort(new BubbleSort());
        app.sort();
        app.setSort(new QuickSort());
        app.sort();
        app.setSort(new ShellSort());
        app.sort();

        int[] arr = {1,8,2,4,5,9};
        int length = arr.length;

        for(int i = 0; i <length; i++) {
            for(int j = i+1; j < length; j++) {
                if(arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        //print
        for(int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }

    }

    public void sort() {
        sort.sorting();
    }

    public SortAlg getSort() {
        return sort;
    }

    public void setSort(SortAlg sort) {
        this.sort = sort;
    }
}