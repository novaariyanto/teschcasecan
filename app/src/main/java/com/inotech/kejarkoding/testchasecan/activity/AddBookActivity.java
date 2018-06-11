package com.inotech.kejarkoding.testchasecan.activity;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inotech.kejarkoding.testchasecan.R;
import com.inotech.kejarkoding.testchasecan.entity.Pesan;
import com.inotech.kejarkoding.testchasecan.rest.ApiClient;
import com.inotech.kejarkoding.testchasecan.rest.ApiInterface;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {
    @BindView(R.id.edit_id)
    EditText editid;
    @BindView(R.id.edt_judul)
    EditText editjdl;
    @BindView(R.id.edt_penerbit)
    EditText edtpenerbit;
    @BindView(R.id.edt_pengarang)
    EditText edtpengarang;
    @BindView(R.id.edt_sinopsis)
    EditText edtsinopsis;
    @BindView(R.id.edt_tempat)
    EditText edttempat;
    @BindView(R.id.edt_stok)
    EditText edtstok;
    @BindView(R.id.edt_tahunterbit)
    EditText edttahunterbit;
    @BindView(R.id.btn_inputgambar)
    Button btninputgambar;
    @BindView(R.id.btn_tambah)
    Button btntambah;

    String imagePath;

    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        ButterKnife.bind(this);

    }

    private void uploadimage() {
        String id,judul,pengarang,penerbit,tahun,stok,sinopsis,tempat;

        id = editid.getText().toString();
        judul = editjdl.getText().toString();
        pengarang = edtpengarang.getText().toString();
        penerbit = edtpenerbit.getText().toString();
        tahun = edttahunterbit.getText().toString();
        stok = edtstok.getText().toString();
        sinopsis = edtsinopsis.getText().toString();
        tempat = edttempat.getText().toString();

        File file = new File(imagePath);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("cover_buku", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));


        HashMap<String, String> map = new HashMap<>();
        map.put("BIB_ID", id);
        map.put("judul_buku", judul);
        map.put("pengarang_buku", pengarang);
        map.put("penerbit_buku", penerbit);
        map.put("tahun_buku", tahun);
        map.put("stok_buku", stok);
        map.put("sinopsis_buku", sinopsis);
        map.put("tempat_buku", tempat);

        Call<Pesan> pesanCall = apiInterface.tambahbuku(map,filePart);
        pesanCall.enqueue(new Callback<Pesan>() {
            @Override
            public void onResponse(Call<Pesan> call, Response<Pesan> response) {
                Toast.makeText(AddBookActivity.this, "pesan :"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pesan> call, Throwable t) {
                Log.d("onfailure",t.toString());
            }
        });
    }

    @OnClick(R.id.btn_inputgambar)
    public void input(){

        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,0);
    }
    @OnClick(R.id.btn_tambah)
    public void tambah(){
        if(edttahunterbit.getText().toString().isEmpty() ||
                editjdl.getText().toString().isEmpty() ||
                edtpengarang.getText().toString().isEmpty() ||
                edttempat.getText().toString().isEmpty() ||
                edtsinopsis.getText().toString().isEmpty() ||
                editid.getText().toString().isEmpty() ||
                edttahunterbit.getText().toString().isEmpty() ){
            if(edtpenerbit.getText().toString().isEmpty()){edtpenerbit.setError("Isi Penerbit cuy");}
            if(editjdl.getText().toString().isEmpty()){editjdl.setError("Isi Judul dulu cuy");}
            if(edtpengarang.getText().toString().isEmpty()){edtpengarang.setError("Isi Pengarang dulu cuy");}
            if(edttempat.getText().toString().isEmpty()){edttempat.setError("Isi tempat dulu cuy");}
            if(edtsinopsis.getText().toString().isEmpty()){edtsinopsis.setError("Isi sinopsis dulu cuy");}
            if(edttahunterbit.getText().toString().isEmpty()){edttahunterbit.setError("Isi tahun terbitdulu cuy");}
            if(editid.getText().toString().isEmpty()){editid.setError("Isi id dulu cuy");}
        }else{
//            Toast.makeText(AddBookActivity.this, "Tunggu website aktif bro", Toast.LENGTH_SHORT).show();
            uploadimage();
    }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "unable to choose image", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri imageUri = data.getData();
            imagePath= getRealPathFromUri(imageUri);
        }
    }
        private String getRealPathFromUri(Uri uri){
            String[] projection = {MediaStore.Images.Media.DATA};
            CursorLoader loader =  new CursorLoader(getApplicationContext(),uri,projection,null,null,null);

            Cursor cursor  = loader.loadInBackground();
            int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            String result = cursor.getString(column_idx);
            cursor.close();
            return result;


    }
}
