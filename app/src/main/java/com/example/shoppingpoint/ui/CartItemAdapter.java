package com.example.shoppingpoint.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingpoint.R;
import com.example.shoppingpoint.model.CartItem;
import com.example.shoppingpoint.model.Product;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "CartItemAdapter";
    private OnItemClick mCallback;

    private List<CartItem> mCartItemList;
    private List<Product> mProductList;


    public CartItemAdapter(List<CartItem> cartItemList, List<Product> productList, OnItemClick listener) {
        mCartItemList = cartItemList;
        mProductList = productList;
        mCallback = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mCartItemList != null && mCartItemList.size() > 0) {
            return mCartItemList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvCartItemTitle;
        TextView tvCartItemPrice;
        ImageButton ibRemoveCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvCartItemTitle = itemView.findViewById(R.id.tvCartItemTitle);
            tvCartItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
            ibRemoveCart = itemView.findViewById(R.id.cartRemoveButton);
        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvCartItemTitle.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final CartItem mCartItem = mCartItemList.get(position);
            Product _mproduct = new Product();
            int _id = mCartItem.getProductId();
            for (int j = 0; j < mProductList.size(); j++) {
                if (_id == mProductList.get(j).getId()) {
                    _mproduct = mProductList.get(j);
                }
            }
            final Product mProduct = _mproduct;

            if (mProduct.getName() != null)
                tvCartItemTitle.setText(mProduct.getName());

            if (mProduct.getPrice() != null)
                tvCartItemPrice.setText(mProduct.getPrice());

            //ivThumbnail.setImageResource(R.drawable.android2);

            ibRemoveCart.setOnClickListener(v -> {
                if (mProduct.getPrice() != null) {
                    try {
                        mCallback.onClick(mCartItem.getId(), "", "", true, false, false);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: " + e.getMessage());
                    }
                }
            });
        }
    }

}
