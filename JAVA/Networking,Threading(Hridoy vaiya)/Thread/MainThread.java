class MainThread {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current: "+t);
        t.setName("My thread");
        System.out.println("Modified: "+t);
        for(int i=5;i>=0;i--){
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}