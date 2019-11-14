package com.wsv.right_light_wsv;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_LONG;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener{


    private Button addProductButton;
    private EditText productID;
    private Spinner category;
    private Spinner type;

    private ArrayAdapter categoryAdapter;
    private ArrayAdapter typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        type = findViewById(R.id.typeDropdown);
        category = findViewById(R.id.categoryDropdown);
        productID = findViewById(R.id.etProductId);
        addProductButton = findViewById(R.id.btnAdd);

        // add items to category dropdown list
        category.setAdapter(categoryAdapter);
        categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.spinner);
        categoryAdapter.notifyDataSetChanged();
        category.setAdapter(categoryAdapter);

        // add items to type dropdown list
        type.setAdapter(typeAdapter);
        typeAdapter = ArrayAdapter.createFromResource(this, R.array.lampTypes, R.layout.spinner);
        typeAdapter.notifyDataSetChanged();
        type.setAdapter(typeAdapter);


        addProductButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:

                Product product = new Product();

                product.setProduct_category(String.valueOf(category.getSelectedItem()));
                product.setProduct_type(String.valueOf(type.getSelectedItem()));
                product.setProduct_id(String.valueOf(productID.getText()));
                product.setAvailable(true);
                product.setRented(false);
                product.setDamaged(false);

                Toast.makeText(this, category.getSelectedItem() + " added successfully", LENGTH_LONG).show();

                break;

            default:
                break;
        }

    }
}
