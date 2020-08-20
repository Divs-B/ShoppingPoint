package com.example.shoppingpoint.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingpoint.R;
import com.example.shoppingpoint.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "ProductAdapter";
    private OnItemClick mCallback;
    private Context mContext;
    private List<Product> mProductList;

    public ProductAdapter(Context context, List<Product> productList, OnItemClick listener) {
        mContext = context;
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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mProductList != null && mProductList.size() > 0) {
            return mProductList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvPrice;
        TextView tvOldPrice;
        TextView tvStock;
        ImageButton ibWishlist;
        ImageButton ibCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvOldPrice = itemView.findViewById(R.id.tvOldPrice);
            tvStock = itemView.findViewById(R.id.tvStock);
            ibWishlist = itemView.findViewById(R.id.wishlistButton);
            ibCart = itemView.findViewById(R.id.cartButton);
        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvTitle.setText("");
            tvCategory.setText("");
            tvPrice.setText("");
            tvOldPrice.setText("");
            tvStock.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Product mProduct = mProductList.get(position);

            if (mProduct.getName() != null) {
                tvTitle.setText(mProduct.getName());
            }

            //ivThumbnail.setImageResource(R.drawable.android2);

            if (mProduct.getCategory() != null) {
                tvCategory.setText(mProduct.getCategory());
            }

            if (mProduct.getPrice() != null) {
                tvPrice.setText(mContext.getResources().getString(R.string.price) + mProduct.getPrice());
            }

            if (mProduct.getOldPrice() != null)
                tvOldPrice.setText(mContext.getResources().getString(R.string.discount) + getDiscount(mProduct) + "%");
            else
                tvOldPrice.setText(mContext.getResources().getString(R.string.old_price));


            if (mProduct.getStock() == 0)
                tvStock.setText(mContext.getResources().getString(R.string.outofstock));
            else
                tvStock.setText(mContext.getResources().getString(R.string.stock) + String.valueOf(mProduct.getStock()));


            ibWishlist.setOnClickListener(v -> {
                if (mProduct.getPrice() != null && mProduct.getStock() > 0) {
                    try {
                        if (!mProduct.getIsWishlistAdded()) {
                            ibWishlist.setImageResource(R.drawable.remove_wishlist);
                            mProduct.setIsWishlistAdded(true);
                            mCallback.onClick(mProduct.getId(), mProduct.getName(), mProduct.getPrice(), false, true, false);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: " + e.getMessage());
                    }
                } else if (mProduct.getStock() <= 0)
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.outofstockerror), Toast.LENGTH_SHORT).show();
            });

            ibCart.setOnClickListener(v -> {
                if (mProduct.getPrice() != null && mProduct.getStock() > 0) {
                    try {
                        mCallback.onClick(mProduct.getId(), "", "", false, false, false);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: " + e.getMessage());
                    }
                } else if (mProduct.getStock() <= 0)
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.outofstockerror), Toast.LENGTH_SHORT).show();
            });
        }
    }

    private Long getDiscount(Product mProduct) {
        double Originalprice = Double.valueOf(mProduct.getOldPrice());
        double newPrice = Double.valueOf(mProduct.getPrice());
        double discount = ((Originalprice - newPrice) / Originalprice) * 100;
        return Math.round(discount);
    }

}
