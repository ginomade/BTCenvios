package com.gino.btcenvios.btcenvios.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.gino.btcenvios.btcenvios.model.SavedOperations;

import java.util.List;

/**
 * @author gino.ghiotto
 */
public class OperationsAdapter  extends RecyclerView.Adapter<OperationsAdapter.GenericViewHolder>{

    private HistoryViewModel viewModel;
    private List<SavedOperations> operations;
    private int layoutId;

    public OperationsAdapter(@LayoutRes int layoutId, HistoryViewModel viewModel, List<SavedOperations> operations) {
        this.viewModel = viewModel;
        this.operations = operations;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public OperationsAdapter.GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsAdapter.GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(HistoryViewModel viewModel, Integer position) {
            viewModel.fetchDogBreedImagesAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
