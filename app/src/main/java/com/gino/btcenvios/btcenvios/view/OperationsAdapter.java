package com.gino.btcenvios.btcenvios.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.gino.btcenvios.BR;
import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.model.SavedOperations;
import com.gino.btcenvios.databinding.OperationsItemViewBinding;

import java.util.List;

/**
 * @author gino.ghiotto
 */
public class OperationsAdapter  extends RecyclerView.Adapter<OperationsAdapter.GenericViewHolder>{

    private HistoryViewModel viewModel;
    private List<SavedOperations> operations;
    private int layoutId;

    public OperationsAdapter(@LayoutRes int layoutId, HistoryViewModel viewModel) {
        this.viewModel = viewModel;
        this.layoutId = layoutId;
    }

    void setList(List<SavedOperations> ops){
        operations = ops;
        notifyDataSetChanged();
    }

    protected int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public OperationsAdapter.GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.operations_item_view,
                new FrameLayout(parent.getContext()), false);
        return new GenericViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsAdapter.GenericViewHolder holder, int position) {
        holder.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return operations == null ? 0 : operations.size();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        GenericViewHolder(View view) {
            super(view);
            bind();
        }

        void setViewModel(HistoryViewModel viewModel) {
            if (binding != null) {
                binding.setVariable(BR.list, viewModel.getOperations().getValue());

            }
        }

            void bind () {
                if (binding == null) {
                    binding = DataBindingUtil.bind(itemView);
                }
            }

        }
    }

