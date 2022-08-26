package com.android.bookonline.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.bookonline.R;
import com.android.bookonline.model.Book;
import com.android.bookonline.function.FunctionDeleteBook;
import com.android.bookonline.function.FunctionUpdateBook;
import com.android.bookonline.model.BookUpdate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.SPVH> {

    List<Book> list;
    Context context;
    RecyclerView rvSP;

    String strId, strName, strAuthor, strPrice, strDes, strAvatar;

    public BookAdapter(List<Book> list, Context context, RecyclerView rvSP) {
        this.list = list;
        this.context = context;
        this.rvSP = rvSP;
    }

    @NonNull
    @Override
    public SPVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sp_item, parent, false);
        return new SPVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPVH holder, int position) {
        Book sp = list.get(position);


        if (sp == null) {
            return;
        }

        Picasso.get().load(sp.linkAvatar).into(holder.imgBook);
        holder.tvTenBook.setText(sp.name);
        holder.tvGiaBook.setText(sp.price);

        holder.detail.setOnClickListener(view -> {

            try {

                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.sp_detail_dialog);
                dialog.setCanceledOnTouchOutside(true);

                TextView idSP, tacGiaSP, tenSP, giaSP, motaSP;
                ImageView imgBookDetail;
                Button btnDeleteSP, btnUpdateSP;
                imgBookDetail = dialog.findViewById(R.id.imgBookDetail);
                idSP = dialog.findViewById(R.id.tvIdSP);
                tenSP = dialog.findViewById(R.id.tvTenSP);
                tacGiaSP = dialog.findViewById(R.id.tvTacGiaSP);
                giaSP = dialog.findViewById(R.id.tvGiaSP);
                motaSP = dialog.findViewById(R.id.tvMoTaSP);
                btnDeleteSP = dialog.findViewById(R.id.btnDeleteSP);
                btnUpdateSP = dialog.findViewById(R.id.btnUpdateSP);

                strId = sp.pid;
                strName = sp.name;
                strAuthor = sp.author;
                strPrice = sp.price;
                strDes = sp.description;
                strAvatar = sp.linkAvatar;

                Log.d("//==Detail: ",strId+"-"+strName+"-"+strPrice+"-"+strDes);

                Picasso.get().load(strAvatar).into(imgBookDetail);
                idSP.setText("Mã sách: " + strId);
                tenSP.setText(strName);
                tacGiaSP.setText(strAuthor);
                giaSP.setText("Giá: " + strPrice);
                motaSP.setText("" + strDes);

                btnUpdateSP.setOnClickListener(view1 -> {
                    dialog.dismiss();
                    openDialogUpdate(view.getContext(), rvSP);
                });
                btnDeleteSP.setOnClickListener(view1 -> {
                    dialog.dismiss();
                    // hàm xóa
                    openDialogDelete(view.getContext(), rvSP, strId, strName);
                });
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("//===err", e.toString());
            }
        });


    }

    private void openDialogUpdate(final Context context, RecyclerView rvSP) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.sp_add_dialog);

        TextView tvTitle5, tv_messageSP;
        EditText edTenSP, edTacGiaSP, edGiaSP, edMota, edLinkAvatar;
        Button btnCancelSP, btnSaveSP;

            tvTitle5 = dialog.findViewById(R.id.tvTitle5);
            edTenSP = dialog.findViewById(R.id.edTenSP);
            edTacGiaSP = dialog.findViewById(R.id.edTacGiaSP);
            edGiaSP = dialog.findViewById(R.id.edGiaSP);
            edMota = dialog.findViewById(R.id.edMotaSP);
            edLinkAvatar = dialog.findViewById(R.id.edLinkAvatar);
            tv_messageSP = dialog.findViewById(R.id.tv_messageSP);
            btnSaveSP = dialog.findViewById(R.id.btnSaveSP);
            btnCancelSP = dialog.findViewById(R.id.btnCancelSP);

        tvTitle5.setText("Cập nhật Sản Phẩm");
        edTenSP.setText(strName);
        edTacGiaSP.setText(strAuthor);
        edGiaSP.setText(strPrice);
        edMota.setText(strDes);
        edLinkAvatar.setText(strAvatar);

        Log.d("//==Update: ",strId+"-"+strName+"-"+strPrice+"-"+strDes);

        btnCancelSP.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btnSaveSP.setOnClickListener(view -> {
            String id = strId;
            String ten = edTenSP.getText().toString();
            String tacgia = edTacGiaSP.getText().toString();
            String gia = edGiaSP.getText().toString();
            String mota = edMota.getText().toString();
            String avatar = edLinkAvatar.getText().toString();

            if (!ten.isEmpty() && !tacgia.isEmpty() && !gia.isEmpty() && !mota.isEmpty() && !avatar.isEmpty()) {
//                FunctionUpdateSP functionUpdateSP = new FunctionUpdateSP();
//                functionUpdateSP.postUpdateSanPham(new SanPhamUpdate(strId, ten, gia, mota), context, rvSP);
                ShowChangeUpdate(context, ten, tacgia, gia, mota, avatar);
            } else {
                tv_messageSP.setText("Không được để trống");
            }

            dialog.dismiss();
        });

        dialog.show();
    }
    private void ShowChangeUpdate(final Context context,
                  String name, String author, String price, String des, String avatar){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_update);

        TextView tvTenOld, tvTenNew, tvTacGiaOld, tvTacGiaNew, tvGiaOld, tvGiaNew, tvMotaOld, tvMotaNew, tvLinkOld, tvLinkNew;
        Button btnUpdateOK;

            tvTenOld = dialog.findViewById(R.id.tvTenOld);
            tvTenNew = dialog.findViewById(R.id.tvTenNew);
            tvTacGiaOld = dialog.findViewById(R.id.tvTacGiaOld);
            tvTacGiaNew = dialog.findViewById(R.id.tvTacGiaNew);
            tvGiaOld = dialog.findViewById(R.id.tvGiaOld);
            tvGiaNew = dialog.findViewById(R.id.tvGiaNew);
            tvMotaOld = dialog.findViewById(R.id.tvMotaOld);
            tvMotaNew = dialog.findViewById(R.id.tvMoTaNew);
            tvLinkOld = dialog.findViewById(R.id.tvLinkOld);
            tvLinkNew = dialog.findViewById(R.id.tvLinkNew);
            btnUpdateOK = dialog.findViewById(R.id.btnUpdateOK);

            tvTenOld.setText(strName);
            tvTenNew.setText(name);
            tvTacGiaOld.setText(strAuthor);
            tvTacGiaNew.setText(author);
            tvGiaOld.setText(strPrice);
            tvGiaNew.setText(price);
            tvMotaOld.setText(strDes);
            tvMotaNew.setText(des);
            tvLinkOld.setText(strAvatar);
            tvLinkNew.setText(avatar);
            if (!tvTenOld.getText().toString().equals(tvTenNew.getText().toString())) tvTenNew.setTextColor(Color.RED);
            if (!tvTacGiaOld.getText().toString().equals(tvTacGiaNew.getText().toString())) tvTacGiaNew.setTextColor(Color.RED);
            if (!tvGiaOld.getText().toString().equals(tvGiaNew.getText().toString())) tvGiaNew.setTextColor(Color.RED);
            if (!tvMotaOld.getText().toString().equals(tvMotaNew.getText().toString())) tvMotaNew.setTextColor(Color.RED);
            if (!tvLinkOld.getText().toString().equals(tvLinkNew.getText().toString())) tvLinkNew.setTextColor(Color.RED);

            btnUpdateOK.setOnClickListener(view -> {
                FunctionUpdateBook functionUpdateBook = new FunctionUpdateBook();
                functionUpdateBook.postUpdateSanPham(new BookUpdate(strId, name, author, price, des, avatar), context, rvSP);
                dialog.dismiss();
            });
        dialog.show();
    }

    private void openDialogDelete(final Context context, RecyclerView rvSP, String pid, String name){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_delete);
        // Khi chạm bên ngoài dialog sẽ ko đóng
        dialog.setCanceledOnTouchOutside(false);

        TextView tvDelete;
        Button btnYesDel, btnNoDel;

        tvDelete = dialog.findViewById(R.id.tvDelete);
        btnYesDel = dialog.findViewById(R.id.btnYesDel);
        btnNoDel = dialog.findViewById(R.id.btnNoDel);

        tvDelete.setText("Bạn muốn xóa Sản phẩm \n\n"
                +"ID: "+pid+ " | Tên: "+ name);

        btnNoDel.setOnClickListener(view -> {
            dialog.cancel();
        });
        btnYesDel.setOnClickListener(view -> {
            new FunctionDeleteBook().postDeleteSP(context, rvSP, pid);
            dialog.dismiss();
        });
        dialog.show();
    }
    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class SPVH extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView tvTenBook, tvGiaBook;
        CardView detail;

        public SPVH(View itemView) {
            super(itemView);

            detail = itemView.findViewById(R.id.detailBook);
            imgBook = itemView.findViewById(R.id.imgBook);
            tvTenBook = itemView.findViewById(R.id.tvTenBook);
            tvGiaBook = itemView.findViewById(R.id.tvGiaBook);
        }
    }
}
