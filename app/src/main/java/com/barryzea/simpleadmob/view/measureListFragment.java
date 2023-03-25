package com.barryzea.simpleadmob.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.Constants;
import com.barryzea.simpleadmob.common.OnClickInterface;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.common.entities.MeasureCardEntity;
import com.barryzea.simpleadmob.databinding.FragmentMeasureListBinding;
import com.barryzea.simpleadmob.view.adapters.MeasureAdapter;
import com.barryzea.simpleadmob.view.dialogs.ConverterMeasureDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class measureListFragment extends Fragment implements OnClickInterface {

    private FragmentMeasureListBinding bind;
    private MeasureAdapter adapter;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert getActivity() != null;
        bind= FragmentMeasureListBinding.inflate(inflater,container,false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpAdapter();
        setUpDetails();
    }
    private void setUpAdapter(){
        adapter= new MeasureAdapter(this);
        bind.rvMeasuresList.setHasFixedSize(true);
        bind.rvMeasuresList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        bind.rvMeasuresList.setAdapter(adapter);
    }
    private void showBottomSheetDialog(int optionConverter){
        ConverterMeasureDialog.newInstance(optionConverter).show(getParentFragmentManager(),ConverterMeasureDialog.class.getSimpleName());
    }

    private void setUpDetails(){

        List<MeasureCardEntity> cardEntityList= new ArrayList<>(Arrays.asList(
                new MeasureCardEntity(getString(R.string.lengthUnits),Constants.LENGTH, R.drawable.ic_rule),
                new MeasureCardEntity(getString(R.string.temperatureUnits),Constants.TEMPERATURE, R.drawable.ic_thermostat),
                new MeasureCardEntity(getString(R.string.timeUnits),Constants.TIME, R.drawable.ic_time),
                new MeasureCardEntity(getString(R.string.massUnits),Constants.MASS, R.drawable.weight_icon),
                new MeasureCardEntity(getString(R.string.electricityUnits),Constants.ELECTRICITY, R.drawable.ic_electric),
                new MeasureCardEntity(getString(R.string.speedUnits),Constants.SPEED, R.drawable.ic_speed),
                new MeasureCardEntity(getString(R.string.areaUnits),Constants.AREA, R.drawable.ic_area),
                new MeasureCardEntity(getString(R.string.pressureUnits),Constants.PRESSURE, R.drawable.ic_pressure),
                new MeasureCardEntity(getString(R.string.energyUnits),Constants.ENERGY, R.drawable.ic_energy),
                new MeasureCardEntity(getString(R.string.volumeUnits),Constants.VOLUME, R.drawable.ic_volume)
        ));
        adapter.addAll(cardEntityList);

    }

    @Override
    public void onClick(View v, HistoryEntity history) {

    }

    @Override
    public void onCardClick(MeasureCardEntity cardEntity) {
        showBottomSheetDialog(cardEntity.getUnitType());
    }
}