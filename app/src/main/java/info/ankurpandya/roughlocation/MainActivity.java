package info.ankurpandya.roughlocation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import info.ankurpandya.lib_roughlocation.LocationListener;
import info.ankurpandya.lib_roughlocation.RoughLocation;
import info.ankurpandya.lib_roughlocation.RoughLocationFetcher;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private View progress;
    private View buttonGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txt_result);
        progress = findViewById(R.id.progress);
        buttonGetLocation = findViewById(R.id.button_get_location);

        progress.setVisibility(View.GONE);
        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGetLocationRequested();
            }
        });

        RoughLocationFetcher.setDebug(true);
    }

    private void handleGetLocationRequested() {
        progress.setVisibility(View.VISIBLE);
        txtResult.setText("");
        RoughLocationFetcher.getLocationInBackground(this, new LocationListener() {
            @Override
            public void onLocationReceived(RoughLocation roughLocation) {
                progress.setVisibility(View.GONE);
                txtResult.setText(roughLocation.toString());
                txtResult.setTextColor(getColor(R.color.color_result));
            }

            @Override
            public void onLocationFailed() {
                progress.setVisibility(View.GONE);
                txtResult.setText(R.string.msg_unexpected_error);
                txtResult.setTextColor(getColor(R.color.color_error));
            }

            @Override
            public void onNetworkError() {
                progress.setVisibility(View.GONE);
                txtResult.setText(R.string.msg_connection_error);
                txtResult.setTextColor(getColor(R.color.color_error));
            }
        });
    }
}