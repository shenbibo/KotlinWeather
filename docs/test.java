class test {
    public static shortToast(Context context, String msg){
        Toast.makeText(context, make, Toast.LENGTH_SHORT).show();
    }

    public static longToast(Context context, String msg){
        Toast.makeText(context, make, Toast.LENGTH_LONG).show();

        // java中
        view.setOnClickListener(new OnClickListener(){
            @Override
            public onClick(View v){
                longToast(MainActivity.this, "java click v = " + v);
            }
        });
    }
     
    interface Base {
        void print();
        void print2();
    }

    class BaseImpl implements Base {
        private int value;
        public BaseImpl(int value) {this.value = value;}

        @Override public void print() {System.out.print(value);}

        @Override public void print2() {System.out.print(value + 2);}
    }

    class TestImpl implements Base {
        private Base delegate;

        TestImpl(Base delegate) { this.delegate = delegate;}

        @Override public void print() {delegate.print();}

        @Override public void print2() {System.out.print("TestImpl");}
    }

    public void test() {
        Base baseImpl = new BaseImpl(10);
        Base testImpl = new TestImpl(baseImpl);
        testImpl.print();   // 10
        testImpl.print2();  // TestImpl 
    }
}