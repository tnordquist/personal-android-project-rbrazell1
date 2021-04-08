package edu.cnm.deepdive.sipandscore;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.sipandscore.service.GoogleSignInService;
import edu.cnm.deepdive.sipandscore.service.SipAndScoreDatabase;
import io.reactivex.schedulers.Schedulers;

public class SipAndScoreApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    Stetho.initializeWithDefaults(this);
    SipAndScoreDatabase.setContext(this);
    SipAndScoreDatabase.getInstance()
        .getDrinkDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
            .loggingEnabled(BuildConfig.DEBUG)
            .build()
    );
  }

}
