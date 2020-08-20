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
import com.example.shoppingpoint.db.WishlistDBModel;
import com.example.shoppingpoint.model.CartItem;
import com.example.shoppingpoint.model.Product;

import java.util.List;

public class WishListItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "WishListItemAdapter";
    private OnItemClick mCallback;

    private List<WishlistDBModel> mWishListItemList;
    private List<Product> mProductList;


    public WishListItemAdapter(List<WishlistDBModel> wishListList, List<Product> productList, OnItemClick listener) {
        mWishListItemList = wishListList;
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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mWishListItemList != null && mWishListItemList.size() > 0) {
            return mWishListItemList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvWishListItemTitle;
        TextView tvWishListItemPrice;
        ImageButton ibAddCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvWishListItemTitle = itemView.findViewById(R.id.tvWishListItemTitle);
            tvWishListItemPrice = itemView.findViewById(R.id.tvWishListItemPrice);
            ibAddCart = itemView.findViewById(R.id.wishListItemCartButton);
        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvWishListItemTitle.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);
            final WishlistDBModel mWishListItem = mWishListItemList.get(position);
            Product _mproduct = new Product();
            int _id = mWishListItem.getProductId();
            for (int j = 0; j < mProductList.size(); j++) {
                if (_id == mProductList.get(j).getId()) {
                    _mproduct = mProductList.get(j);
                }
            }
            final Product mProduct = _mproduct;
            if (mProduct.getName() != null)
                tvWishListItemTitle.setText(mProduct.getName());
            if (mProduct.getPrice() != null)
                tvWishListItemPrice.setText(mProduct.getPrice());

            //ivThumbnail.setImageResource(R.drawable.android2);

            ibAddCart.setOnClickListener(v -> {
                if (mProduct.getPrice() != null) {
                    try {
                        mCallback.onClick(mProduct.getId(), "", "", false, false, true);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: " + e.getMessage());
                    }
                }
            });
        }
    }

}
