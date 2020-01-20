package com.example.rxjava2;


import io.reactivex.annotations.NonNull;



public class Example {
    public void something(){
        doSomethingWithNotNull(null);//you can't put null this. @Nullable - you can
    }
    private void doSomethingWithNotNull(@NonNull String string){

    }
    /*support annotation:
    @StringRes - you can write only R.string.app_name for example
    @ColorRes
    @Nullable
    @NotNull

     */

}
