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
  
}