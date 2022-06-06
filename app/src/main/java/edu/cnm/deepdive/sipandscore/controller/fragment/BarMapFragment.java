package edu.cnm.deepdive.sipandscore.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.cnm.deepdive.codebreaker14.R;
import edu.cnm.deepdive.codebreaker14.databinding.FragmentBarMapBinding;

public class BarMapFragment extends Fragment implements OnMapReadyCallback {

  private FragmentBarMapBinding binding;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentBarMapBinding
        .inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(
      @NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
        .findFragmentById(R.id.map_fragment);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    LatLng Abq = new LatLng(35.150250, -106.574840);
    googleMap.addMarker(new MarkerOptions().position(Abq)
                                           .title("Marker in Abq"));
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(Abq));
    googleMap.setMinZoomPreference(15);
  }

}
