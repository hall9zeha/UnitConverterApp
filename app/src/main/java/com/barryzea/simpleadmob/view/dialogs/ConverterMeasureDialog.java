package com.barryzea.simpleadmob.view.dialogs;




import static com.barryzea.simpleadmob.common.Constants.TEMPERATURE;
import static com.barryzea.simpleadmob.common.dataSource.DummyDS.getUnitList;


import android.app.Dialog;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.Utils;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.common.entities.Measure;
import com.barryzea.simpleadmob.common.entities.UnitEntity;
import com.barryzea.simpleadmob.databinding.MeasureConverterLayoutBinding;
import com.barryzea.simpleadmob.viewModel.HistoryViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.SlideDistanceProvider;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@AndroidEntryPoint
public class ConverterMeasureDialog extends BottomSheetDialogFragment {
    private MeasureConverterLayoutBinding bind;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    private static String ARG_FIRST_PARAM="firstParam";
    private List<Measure> measureList= new ArrayList<>();
    private int typeOperation=0;
    private Measure measureEntity;
    private HistoryViewModel viewModel;
    private HistoryEntity history= new HistoryEntity();
    private UnitEntity unitValue= new UnitEntity();
    private ArrayList<HistoryEntity> historyList= new ArrayList<>();

    public static ConverterMeasureDialog newInstance(int firstParam){
        ConverterMeasureDialog  bottomSheetFragment= new ConverterMeasureDialog();
        Bundle args= new Bundle();
        args.putInt(ARG_FIRST_PARAM,firstParam);
        bottomSheetFragment.setArguments(args);
        return bottomSheetFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert  getActivity()!=null;
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bind= MeasureConverterLayoutBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(bind.getRoot());
        setUpViewModel();
        setUpObserver();

        if(getArguments() !=null){
            typeOperation=getArguments().getInt(ARG_FIRST_PARAM);
        populateOptions(getArguments().getInt(ARG_FIRST_PARAM));

        }
        setUpNumbersPickers(measureList);
        setUpListeners();
        bottomSheetBehavior = BottomSheetBehavior.from((View) bind.getRoot().getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                MaterialFadeThrough fadeThrough = new MaterialFadeThrough();
                fadeThrough.setDuration(550L);
                fadeThrough.setSecondaryAnimatorProvider(new SlideDistanceProvider(Gravity.TOP));
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        return bottomSheetDialog;
    }
    private void setUpViewModel(){
        viewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
    }
    private void setUpObserver(){
        viewModel.getAllHistory().observe(this, historyEntities -> {
            historyList.addAll(historyEntities);
        });
    }
    private void setUpListeners(){
        bind.btnConverter.setOnClickListener(v->{
            if(bind.edtInputValue.getText().toString().isEmpty()){
                bind.edtInputValue.setError(getString(R.string.setValuePlease));
            }else{
                calculateUnits(typeOperation,bind.edtInputValue.getText().toString());
                history.setFromUnitValue(Double.parseDouble(bind.edtInputValue.getText().toString()));
                history.setFromUnitName(unitValue.getFirstUnitName());
                history.setToUnitValue(Double.parseDouble(bind.edtOutputValue.getText().toString()));
                history.setToUnitName(unitValue.getSecondUnitName());
                history.setScientificNotation(Double.parseDouble(bind.tvScientificNValue.getText().toString()));
                history.setTimestamp(new Date().getTime());
                saveHistory(history);
                //Eliminamos parte del historial de la base de datos
                deleteOldHistoryItem(historyList);
            }
        });
        bind.btnShare.setOnClickListener(v->{
                if(!bind.edtOutputValue.getText().toString().isEmpty()){
                    String shareValue=String.format("%s %s(s) = %s %s(s)"
                            ,bind.edtInputValue.getText(),unitValue.getFirstUnitName()
                            ,bind.edtOutputValue.getText(),unitValue.getSecondUnitName());
                    Utils.shareResult(requireActivity(),shareValue);
                }
        });
        bind.btnCopy.setOnClickListener(v->{
            if(!bind.edtOutputValue.getText().toString().isEmpty() ) {
                Utils.textCopy(requireActivity(), bind.edtOutputValue.getText().toString());

            }
        });
    }

    private void saveHistory(HistoryEntity history){
        try {
            viewModel.saveHistory(history);

        }catch(Exception e){
            Log.e("SAVE_ERROR", e.getMessage());
        }
    }
    private void deleteOldHistoryItem(ArrayList<HistoryEntity> historyList){
        if(!historyList.isEmpty()){
            int size = historyList.size();
            //si la lista del historial de la base de datos supera cierta cantidad
            //eliminamos los registros más antiguos, ya que la consulta nos trae los registros ordenados por el campo
            //timestamp
            if(size>25){
                HistoryEntity oldHistory= historyList.get(size-1);
                try {
                    viewModel.deleteOldHistoryItem(oldHistory);

                }catch(Exception e){
                    Log.e("DELETE_ERROR", e.getMessage() );
                }
            }
        }
    }
    private void populateOptions(int optionPopulate){
        measureList=getUnitList(requireActivity(), optionPopulate);
    }
    private void calculateUnits(int conversionType, String inputValue){
     bind.edtOutputValue.onEditorAction(EditorInfo.IME_ACTION_DONE);
     switch(conversionType){
         case TEMPERATURE:
             setResultInView(Utils.converterTempUnit(Double.parseDouble(inputValue),unitValue));
             break;
         default:
             setResultInView(Utils.converterUnit(Double.parseDouble(inputValue),unitValue));
             break;
     }

    }
    private void setResultInView(double result){
        bind.edtOutputValue.setText(Utils.decimalFormat(result));
        bind.tvScientificNValue.setText(String.valueOf(result));
    }
    private void setUpNumbersPickers(List<Measure> measureList){
        if(!measureList.isEmpty()) {
            String[] npList = new String[measureList.size()];
            int i = 0;
            for (Measure m : measureList) {
                npList[i] = m.getName();
                i++;
            }
            bind.npInternationalMeasure.setMinValue(0);
            bind.npInternationalMeasure.setMaxValue(measureList.size() - 1);
            bind.npInternationalMeasure.setDisplayedValues(npList);
            bind.npInternationalMeasure.setValue(1);//ponemos el índice por defecto que le corresponde a Metros en el array
            int indexDefFirst = bind.npInternationalMeasure.getValue();
            unitValue.setFirstUnitSystem(measureList.get(indexDefFirst).getUnitSystem());
            unitValue.setFirstUnitFactorValue(measureList.get(indexDefFirst).getFactorValue());
            unitValue.setFirstUnitName(measureList.get(indexDefFirst).getName());
            unitValue.setFirstUnitSymbol(measureList.get(indexDefFirst).getSymbol());
            unitValue.setFirstIndex(indexDefFirst);
            bind.tvInputSymbol.setText(measureList.get(indexDefFirst).getSymbol());
            bind.npInternationalMeasure.setOnValueChangedListener((picker, oldVal, newVal) -> {
                if (!measureList.isEmpty()) {
                    for (Measure m : measureList) {
                        if (m.getName().equals(npList[newVal])) {
                            unitValue.setFirstUnitSystem(m.getUnitSystem());
                            unitValue.setFirstUnitFactorValue(m.getFactorValue());
                            unitValue.setFirstUnitName(m.getName());
                            unitValue.setFirstUnitSymbol(m.getSymbol());
                            bind.tvInputSymbol.setText(m.getSymbol());
                        }
                    }
                }
            });
            bind.npAngloMeasure.setMinValue(0);
            bind.npAngloMeasure.setMaxValue(measureList.size() - 1);
            bind.npAngloMeasure.setDisplayedValues(npList);
            bind.npAngloMeasure.setValue(1);//ponemos el índice por defecto que le corresponde a Pies en el array

            //obtenemos los valores del índice por defecto al cargar por primera vez el numberPicker
            //ya que de lo si no se observa un cambio en el listener, nuestras variables para las operaciones no recibirán
            //ningún valor
            int indexDefSecond = bind.npAngloMeasure.getValue();
            unitValue.setSecondUnitSystem(measureList.get(indexDefSecond).getUnitSystem());
            unitValue.setSecondUnitFactorValue(measureList.get(indexDefSecond).getFactorValue());
            unitValue.setSecondUnitName(measureList.get(indexDefSecond).getName());
            unitValue.setSecondUnitSymbol(measureList.get(indexDefSecond).getSymbol());
            unitValue.setSecondIndex(indexDefSecond);
            //**********************************************************************

            bind.tvOutputSymbol.setText(measureList.get(indexDefSecond).getSymbol());
            bind.npAngloMeasure.setOnValueChangedListener((picker, oldVal, newVal) -> {
                if (!measureList.isEmpty()) {
                    for (Measure m : measureList) {
                        if (m.getName().equals(npList[newVal])) {
                            unitValue.setSecondUnitSystem(m.getUnitSystem());
                            unitValue.setSecondUnitFactorValue(m.getFactorValue());
                            unitValue.setSecondUnitName(m.getName());
                            unitValue.setSecondUnitSymbol(m.getSymbol());

                            bind.tvOutputSymbol.setText(m.getSymbol());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.callHistory();
    }
}
