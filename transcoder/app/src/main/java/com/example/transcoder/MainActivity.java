package com.example.transcoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import com.example.transcoder.format.MediaFormatStrategyPresets;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean ret = checkPermission();
        Log.w("yangliu","ret:"+ret);
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPath = "/sdcard/DCIM/HEVC.mp4";
                String outputPath = "/sdcard/DCIM/output.mp4";
                try {
                    MediaTranscoder.getInstance().transcodeVideo(inputPath, outputPath, MediaFormatStrategyPresets.createExportPreset960x540Strategy(), new MediaTranscoder.Listener() {
                        @Override
                        public void onTranscodeProgress(double progress) {

                        }

                        @Override
                        public void onTranscodeCompleted() {

                        }

                        @Override
                        public void onTranscodeCanceled() {

                        }

                        @Override
                        public void onTranscodeFailed(Exception exception) {

                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
        return true;
    }
}