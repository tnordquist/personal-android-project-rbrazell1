package edu.cnm.deepdive.sipandscore.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import io.reactivex.Single;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageRepository {

  private static final String IMAGE_DIRECTORY = "images";

  private final Context context;
  private final File imageDirectory;
  private final ContentResolver resolver;


  public ImageRepository(Context context) {
    this.context = context;
    imageDirectory = new File(context.getDataDir(), IMAGE_DIRECTORY);
    imageDirectory.mkdirs();
    resolver = context.getContentResolver();
  }

  public String storePrivateFile(Uri uri) throws IOException {
    try (
        Cursor cursor = resolver.query(uri, null, null, null, null);
        InputStream input = resolver.openInputStream(uri);
    ) {
      int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
      cursor.moveToFirst();
      String filename = cursor.getString(nameIndex);
      File outputFile = File.createTempFile("img", filename, imageDirectory);
      Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      return outputFile.getName();
    }
  }

//  public Single<List<Drink>> add(String title, String comment)
}
