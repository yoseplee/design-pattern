public class What {
    private static What onlyWhat;

    private What() {
        System.out.println("What is ready");
    }

    //constructor와 같은 역할을 한다
    //new가 한 번만 호출되는 것을 보장해야 한다 -> onlyWhat(플래그 역할)
    //multi-thread의 경우 Thread Lock이 필요함 -> synchronized keyword 사용
    //coalse-grained lock을 지원함. 즉 그 스코프가 끝날 때 까지 lock을 걸어 놓음 -> 매우 비싸다!
    public static What getInstance() {
        //double locking
        if(onlyWhat == null) {
            synchronized(What.class) {
                if(onlyWhat == null) onlyWhat = new What();
            }
        }
        return onlyWhat;
    }

    void doThis() {
        System.out.println("Ok!");
    }
    
}