package com.barryzea.simpleadmob.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.OnClickInterface;
import com.barryzea.simpleadmob.common.Utils;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.common.entities.MeasureCardEntity;
import com.barryzea.simpleadmob.databinding.FragmentHistoryBinding;
import com.barryzea.simpleadmob.view.adapters.HistoryAdapter;
import com.barryzea.simpleadmob.viewModel.HistoryViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryFragment extends Fragment implements OnClickInterface {

      private FragmentHistoryBinding bind;
    private HistoryAdapter adapter;
    private HistoryViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind= FragmentHistoryBinding.inflate(inflater, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewModel();
        setUpAdapter();
        setUpObservers();
        setUpListeners();
        bind.fabClear.shrink();

    }
    private void setUpAdapter(){
        adapter = new HistoryAdapter(this);

        bind.rvHistory.setHasFixedSize(true);
        bind.rvHistory.setLayoutManager(new LinearLayoutManager(requireActivity()));
        bind.rvHistory.setAdapter(adapter);
        bind.rvHistory.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){

                    if(bind.fabClear.isShown()){
                        bind.fabClear.hide();
                    }
                }else if(dy<0){

                    if(!bind.fabClear.isShown()){
                        bind.fabClear.show();
                        bind.fabClear.extend();
                    }
                }
            }
        });
    }
    private void setUpViewModel(){
        viewModel= new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
    }
    private void setUpObservers(){
       viewModel.getAllHistory().observe(getViewLifecycleOwner(), historyEntities -> {
           if(!historyEntities.isEmpty()) {
               bind.fabClear.setVisibility(View.VISIBLE);
               adapter.addAll(historyEntities);
           }
        });
    }
    private void setUpListeners(){
        bind.fabClear.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireActivity())
                    .setTitle(R.string.deleteAll)
                    .setMessage(R.string.deleteAllMsg)
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        deleteAllHistory();
                    })
                    .setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();

        });
    }
    private void deleteAllHistory(){
        try {
            viewModel.deleteAllHistory();
            adapter.clearAll();
            bind.fabClear.setVisibility(View.GONE);
        }catch(Exception e){
            Log.e("ERROR_DELETE_ALL", e.getMessage());
        }

    }
    @Override
    public void onClick(View v ,HistoryEntity history) {
        showPopup(v,history);
    }

    @Override
    public void onCardClick(MeasureCardEntity cardEntity) {

    }

    private void showPopup(View v, HistoryEntity history){
        PopupMenu popup=new PopupMenu(requireActivity(),v);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true);
        }

        popup.setOnMenuItemClickListener(menuItem->{
            switch(menuItem.getItemId()){
                case R.id.shareItem:
                    String shareValue=String.format("%s %s(s) = %s %s(s)\n%s(E)"
                            , history.getFromUnitValue(),history.getFromUnitName()
                            , history.getToUnitValue(),history.getToUnitName(),history.getScientificNotation());
                    Utils.shareResult(requireActivity(),shareValue);
                    break;
                case R.id.copyItem:
                    Utils.textCopy(requireActivity(),String.valueOf(history.getToUnitValue()));
                    break;
            }
            return false;
        });
        popup.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearAll();
        viewModel.callHistory();
    }
}