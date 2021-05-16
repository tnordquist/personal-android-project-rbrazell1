package edu.cnm.deepdive.sipandscore.controller.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.controller.activity.LoginActivity;
import edu.cnm.deepdive.sipandscore.service.GoogleSignInService;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

  static final int PERMISSIONS_REQUEST_CODE = 1515;
  static final int REQUEST_IMAGE_CAPTURE = 1414;
  private AppBarConfiguration appBarConfiguration;
  private NavController navController;
  private String currentPhotoPath;
  private File image;
  private Uri uri;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView navView = findViewById(R.id.nav_view);
    appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_drinks, R.id.navigation_bars, R.id.navigation_map)
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);
  }

  @Override
  public boolean onSupportNavigateUp() {
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.sign_out:
        signOut();
        break;
      case R.id.settings:
        navController.navigate(R.id.navigation_settings);
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "DRINK_" + timeStamp + "_";
    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    image = File.createTempFile(
        imageFileName,
        ".jpg",
        storageDir
    );
    currentPhotoPath = image.getAbsolutePath();
    return image;
  }

  private void dispatchTakePictureIntent() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      try {
        File photoFile = createImageFile();
        uri = FileProvider.getUriForFile(this,
            "edu.cnm.deepdive.sipandscore",
            photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
      } catch (IOException ex) {
        // Error occurred while creating the File
        Snackbar.make(findViewById(R.id.drink_list), "Failed to take a picture",
            BaseTransientBottomBar.LENGTH_INDEFINITE)
                .show();
      }
    }
  }

  private void signOut() {
    GoogleSignInService
        .getInstance()
        .signOut()
        .addOnCompleteListener((ignored) -> startActivity(
            new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
        ));
  }

}