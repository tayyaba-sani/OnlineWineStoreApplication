package com.springboot.wine.store.common.Utils;

import java.util.List;

public class CommonUtils {

    public static Boolean isNullOrEmpty(Object obj) {
        if(obj != null) {
            if(obj instanceof String) {
                return obj.equals("");
            }
            else if(obj instanceof List){
                return !(((List) obj).size()>0);
            }
            return false;
        }
        return true;
    }
}
