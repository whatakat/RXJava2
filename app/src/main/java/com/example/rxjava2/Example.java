package com.example.rxjava2;


import androidx.annotation.IntDef;

import io.reactivex.annotations.NonNull;



public class Example {
    public void something(){
        doSomethingWithNotNull(null);//you can't put null this. @Nullable - you can
        functionWithTwoModes(MODE1);// annotations
    }
    private void doSomethingWithNotNull(@NonNull String string){

    }
    /*support annotation:
    @StringRes - you can write only R.string.app_name for example
    @ColorRes
    @Nullable
    @NotNull
    @FloatRange - for interval value - from 1 to 5 for example
    @IntRange
    @RequiresPermission(value = Manifest.permission.WRITE_EXTERNAL_STORAGE) - it's good idea for method works with storage
    @CheckResult

     */
    static final int MODE1 = 0;
    static final int MODE2 = 1;

    @IntDef({MODE1,MODE2})
    @interface Mode{}

    private void functionWithTwoModes(@Mode Integer mode){

    }



}
