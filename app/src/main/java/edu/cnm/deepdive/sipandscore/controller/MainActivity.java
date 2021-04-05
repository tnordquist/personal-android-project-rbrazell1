package edu.cnm.deepdive.sipandscore.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.ActivityMainBinding;
import edu.cnm.deepdive.sipandscore.service.GoogleSignInService;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

  private AppBarConfiguration appBarConfiguration;
  private NavController navController;

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

  @Override
  public void onRequestPermissionsResult(int requestCode,
      @NonNull @NotNull String[] permissions,
      @NonNull @NotNull int[] grantResults) {
    if (requestCode == REQUEST_CODE_PERMISSIONS)
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