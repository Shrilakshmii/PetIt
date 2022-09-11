package com.example.petit_forkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.petit_forkt.adapter.ProductCategoryAdapter;
import com.example.petit_forkt.adapter.ProductsAdapter;
import com.example.petit_forkt.model.ProductCategory;
import com.example.petit_forkt.model.Products;

import java.util.ArrayList;
import java.util.List;

public class foodActivity extends AppCompatActivity {

    ProductCategoryAdapter productCatAdapter;
    RecyclerView productCatRecycler,productItemRecycler;
    ProductsAdapter productsAdapter;
    Button cart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        cart1=findViewById(R.id.cart1);

        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(foodActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        List<ProductCategory> productCategoryList=new ArrayList<>();
        productCategoryList.add(new ProductCategory(1,"Good"));
        productCategoryList.add(new ProductCategory(2,"Food Is"));
        productCategoryList.add(new ProductCategory(3,"Foundation Of"));
        productCategoryList.add(new ProductCategory(4,"Genuine"));
        productCategoryList.add(new ProductCategory(5,"Happiness"));
        setProductRecycler(productCategoryList);

        List<Products>productsList=new ArrayList<>();
        productsList.add(new Products(1,"The delicious Pulav","100/-",R.drawable.pulav));
        productsList.add(new Products(2,"The Amazing Naan","60/-",R.drawable.naan));
        productsList.add(new Products(3,"Idli Saambaar","50/-",R.drawable.idli));
        productsList.add(new Products(4,"The Famous KT","20/-",R.drawable.tea2));
        productsList.add(new Products(5,"Masala Dosa","60/-",R.drawable.masaldosa));

        setProductItemRecycler(productsList);
    }
    private void setProductRecycler(List<ProductCategory> productCategoryList)
    {
        productCatRecycler=findViewById(R.id.cat);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCatAdapter=new ProductCategoryAdapter(this,productCategoryList);
        productCatRecycler.setAdapter(productCatAdapter);
    }
    private void setProductItemRecycler(List<Products> productsList)
    {
        productItemRecycler=findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productItemRecycler.setLayoutManager(layoutManager);
        productsAdapter=new ProductsAdapter(this,productsList);
        productItemRecycler.setAdapter(productsAdapter);
    }
}