package br.com.produban.openbus.f5.util;

//https://devcentral.f5.com/questions/high-low-value-in-systemstatisticsget_global_statistics

import br.com.produban.openbus.f5.api.CommonULong64;

public class ULong {

    public static String toString(CommonULong64 ulong) {
        long high = ulong.getHigh();
        long low = ulong.getLow();
        Double retVal;
        Double rollOver = new Double((double) 0x7fffffff);
        rollOver = new Double(rollOver.doubleValue() + 1.0);
        if (high >= 0) {
            retVal = new Double((high << 32 & 0xffff0000));
        } else {
            retVal = new Double(((high & 0x7fffffff) << 32) + (0x80000000 << 32));
        }
        if (low >= 0) {
            retVal = new Double(retVal.doubleValue() + (double) low);
        } else {
            retVal = new Double(retVal.doubleValue() + (double) ((low & 0x7fffffff)) + rollOver.doubleValue());
        }
        return retVal.toString();
    }
}
