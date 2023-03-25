package com.barryzea.simpleadmob.common;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.entities.UnitEntity;

import java.text.DecimalFormat;

/****
 * SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class Utils {
    public static Double converterUnit(double inputValue, UnitEntity unit){
        return  (inputValue * unit.getFirstUnitFactorValue()) / unit.getSecondUnitFactorValue();
    }

    public static Double converterTempUnit( double inputValue, UnitEntity unit){
        double resultValue=0;
        if(unit.getFirstUnitSystem()==unit.getSecondUnitSystem()){
            resultValue = inputValue;
        }else {
            if(unit.getFirstUnitSystem() == Constants.CELSIUS && unit.getSecondUnitSystem() == Constants.FAHRENHEIT){
                resultValue = (inputValue * Constants.CONSTANT_1_8_AVG_F) + Constants.CONSTANT_32_F;
            }else if(unit.getFirstUnitSystem() == Constants.FAHRENHEIT && unit.getSecondUnitSystem() == Constants.CELSIUS) {
                resultValue = (inputValue - Constants.CONSTANT_32_F)/Constants.CONSTANT_1_8_AVG_F;
            }else if(unit.getFirstUnitSystem() == Constants.KELVIN && unit.getSecondUnitSystem() == Constants.CELSIUS){
                resultValue = inputValue - Constants.CONSTANT_KELVIN;
            }else if(unit.getFirstUnitSystem() == Constants.CELSIUS && unit.getSecondUnitSystem() == Constants.KELVIN){
                resultValue = inputValue + Constants.CONSTANT_KELVIN;
            }else if(unit.getFirstUnitSystem() == Constants.KELVIN && unit.getSecondUnitSystem() == Constants.FAHRENHEIT){
                resultValue = ((inputValue - Constants.CONSTANT_KELVIN)*Constants.CONSTANT_1_8_AVG_F) + Constants.CONSTANT_32_F;
            }else{
                resultValue = ((inputValue - Constants.CONSTANT_32_F) * Constants.CONSTANT_1_8_AVG_F) + Constants.CONSTANT_KELVIN;
            }
        }
        return resultValue;
    }
    public static void shareResult(Activity ac,String value){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,value);
        ac.startActivity(Intent.createChooser(intent,ac.getString(R.string.shareContentMsg)));
    }
    public static void textCopy(Context ctx, String textCopied){
        ClipboardManager clipboardManager = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("",textCopied));
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2){
            Toast.makeText(ctx, R.string.copiedMsg, Toast.LENGTH_SHORT).show();
        }
    }
    public static String decimalFormat(double value){
        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(15);
        return decimalFormatter.format(value);
    }
}
