package edu.cnm.deepdive.sipandscore.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.jetbrains.annotations.NotNull;

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

  public Single<String> storePrivateFile(Uri uri) {
    return Single.create(new SingleOnSubscribe<String>() {
      @Override
      public void subscribe(@NotNull SingleEmitter<String> emitter) throws Exception {
        try (
            Cursor cursor = resolver.query(uri, null, null, null, null);
            InputStream input = resolver.openInputStream(uri)
        ) {
          int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
          cursor.moveToFirst();
          String filename = cursor.getString(nameIndex);
          File outputFile = File.createTempFile("img", filename, imageDirectory);
          Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
          emitter.onSuccess(outputFile.getName());
        } catch (IOException e) {
          emitter.onError(e);
        }
      }
    })
        .subscribeOn(Schedulers.io());

  }

  public String resolvePath(String path) {
    return new File(imageDirectory, path).getAbsolutePath();
  }
//  public Single<List<Drink>> add(String title, String comment)
}
