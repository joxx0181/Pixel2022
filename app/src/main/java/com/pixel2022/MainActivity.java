package com.pixel2022;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pixel2022.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 * @author Joxx0181
 * @version 2022.0503
 */
@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    /**
     * Initializing
     * @param spinnerText for text in spinner
     * @param rgbColorText for info about touch enable
     * @param rgbColorOfImage for color code of touched color
     * @param pixelText for counted pixels of image
     * @param pixelOfSelectedImageButton for start counting pixels
     * @param arrayListImages for a arraylist of image text
     * @param arrayListDrawables for a arraylist of images
     * @param arrayAdapterImage for binding arraylists
     */
    Spinner spinnerText;
    TextView rgbColorText;
    TextView rgbColorOfImage;
    TextView pixelText;
    Button pixelOfSelectedImageButton;
    ImageView selectedImage;
    Bitmap bitmap;

    ActivityMainBinding binding;
    ArrayList<String> arraylistImages;
    ArrayList<Integer> arrayListDrawables;
    ArrayAdapter<String> arrayAdapterImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        // Using binding.root for returning the root layout (activity_main.xml)
        setContentView(binding.getRoot());

        // Calling method for displaying images
        loadImages();

        // Perform selecting item from spinner event with Overriding methods
        binding.imageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                binding.image.setImageResource(arrayListDrawables.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Getting view that is identified by the android:id
        spinnerText = findViewById(R.id.imageSpinner);
        rgbColorText = findViewById(R.id.rgbColorText);
        rgbColorOfImage = findViewById(R.id.rgbColorCode);
        pixelOfSelectedImageButton = findViewById(R.id.pixelButton);
        pixelText = findViewById(R.id.pixelText);
        selectedImage = findViewById(R.id.image);

        rgbColorText.setText("TOUCH THE IMAGE ANYWHERE TO GET\n COLOR CODE");
        selectedImage.setDrawingCacheEnabled(true);
        selectedImage.buildDrawingCache(true);

        // Perform touch event on selected image
        selectedImage.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {

                // Using switch statement for identify which image is selected
                String selectedImageText = spinnerText.getSelectedItem().toString();
                Drawable drawable;
                switch (selectedImageText) {
                    case "Car image":
                        drawable = getResources().getDrawable(R.drawable.image01);
                        selectedImage.setImageDrawable(drawable);
                        break;
                    case "Music image":
                        drawable = getResources().getDrawable(R.drawable.image02);
                        selectedImage.setImageDrawable(drawable);
                        break;
                    case "Butterfly image":
                        drawable = getResources().getDrawable(R.drawable.image03);
                        selectedImage.setImageDrawable(drawable);
                        break;
                    case "Chameleon image":
                        drawable = getResources().getDrawable(R.drawable.image04);
                        selectedImage.setImageDrawable(drawable);
                        break;
                    case "Flower image":
                        drawable = getResources().getDrawable(R.drawable.image05);
                        selectedImage.setImageDrawable(drawable);
                        break;
                    default:
                        drawable = getResources().getDrawable(R.drawable.imagefront);
                        selectedImage.setImageDrawable(drawable);
                        break;
                }
                    // Initializing
                    bitmap = selectedImage.getDrawingCache();
                    int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                    int rColor = Color.red(pixel);
                    int gColor = Color.green(pixel);
                    int bColor = Color.blue(pixel);

                    // Displaying color code based on where an image has been touch
                    rgbColorOfImage.setBackgroundColor(Color.rgb(rColor, gColor, bColor));
                    rgbColorOfImage.setText("R:(" + rColor + ")\n" + "G:(" + gColor + ")\n" + "B:(" + bColor + ")");
            }
            return true;
        });

        // Perform click event using lambda on pixelButton
        pixelOfSelectedImageButton.setOnClickListener(view -> {

            // Using switch statement for identify which image is selected
            String selectedImageText = spinnerText.getSelectedItem().toString();
            Drawable drawable;
            switch (selectedImageText) {
                case "Car image":
                    drawable = getResources().getDrawable(R.drawable.image01);
                    selectedImage.setImageDrawable(drawable);
                    break;
                case "Music image":
                    drawable = getResources().getDrawable(R.drawable.image02);
                    selectedImage.setImageDrawable(drawable);
                    break;
                case "Butterfly image":
                    drawable = getResources().getDrawable(R.drawable.image03);
                    selectedImage.setImageDrawable(drawable);
                    break;
                case "Chameleon image":
                    drawable = getResources().getDrawable(R.drawable.image04);
                    selectedImage.setImageDrawable(drawable);
                    break;
                case "Flower image":
                    drawable = getResources().getDrawable(R.drawable.image05);
                    selectedImage.setImageDrawable(drawable);
                    break;
                default:
                    drawable = getResources().getDrawable(R.drawable.imagefront);
                    selectedImage.setImageDrawable(drawable);
                    break;
            }

            // Initializing
            bitmap = selectedImage.getDrawingCache();
            int countX = bitmap.getWidth();
            int countY = bitmap.getHeight();
            int colorXY;
            int redCount = 0;
            int greenCount = 0;
            int blueCount = 0;

            // Get pixel values by iterating
            for (int y = 0; y < countY; y++) {
                for (int x = 0; x < countX; x++) {
                    colorXY = bitmap.getPixel(x, y);
                    redCount += Color.red(colorXY);
                    greenCount += Color.green(colorXY);
                    blueCount += Color.blue(colorXY);
                }
            }

            // Displaying how many categorized color pixels as red, green and blue in selected image
            pixelText.setText("R:(" + redCount + " pixels categorized as red" +")\n"
                    + "G:(" + greenCount + " pixels categorized as green" +")\n"
                    + "B:(" + blueCount + " pixels categorized as blue" +")");
        });
    }

    // Create method for displaying image after selected in spinner
    public void loadImages() {

        // Create arraylist and adding text items for the spinner
        arraylistImages = new ArrayList<>();
        arraylistImages.add("Select an image...");
        arraylistImages.add("Car image");
        arraylistImages.add("Music image");
        arraylistImages.add("Butterfly image");
        arraylistImages.add("Chameleon image");
        arraylistImages.add("Flower image");

        // Create arrayadapter for binding arraylist of text items, textview and spinner together
        arrayAdapterImages = new ArrayAdapter<>(MainActivity.this,R.layout.image_textview,arraylistImages);
        binding.imageSpinner.setAdapter(arrayAdapterImages);

        // Create arraylist and adding images files for the imageview
        arrayListDrawables = new ArrayList<>();
        arrayListDrawables.add(R.drawable.imagefront);
        arrayListDrawables.add(R.drawable.image01);
        arrayListDrawables.add(R.drawable.image02);
        arrayListDrawables.add(R.drawable.image03);
        arrayListDrawables.add(R.drawable.image04);
        arrayListDrawables.add(R.drawable.image05);
    }
}