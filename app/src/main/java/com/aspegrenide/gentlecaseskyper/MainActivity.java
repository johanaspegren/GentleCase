package com.aspegrenide.gentlecaseskyper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this should rather be a service ...
        setContentView(R.layout.activity_main);

//        initiateSkypeUri(); // works, though inits in phonemode
//        initiateSydsvenskan(); // opens nicely in web
//        initiateNetflix(); // was not available for the tablet at hand
//        initiateSvtPlay(); // ok
//        initiateSvtBarnPlay(); // ok
//        initiateFacebook(); // ok
    }

    public void initiateFacebook() {
        String profile= "johan.aspegren.96";
        //try { //exception handling are for wusses, rather have a nice big crash
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + profile));
        startActivity(intent);
    }

    private void initiateSvtBarnPlay() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName("svt.se.barnkanalen",
                "svt.se.barnkanalen.activity.MainActivity");
        intent.setData(Uri.parse("https://www.svt.se/barnkanalen/"));
        startActivity(intent);
    }

    private void initiateSvtPlay() {
        Intent launchSvtplay = getPackageManager().getLaunchIntentForPackage(
                "se.svt.android.svtplay");
        startActivity(launchSvtplay);
    }

    public void initiateNetflix() {
        String netFlixId = "43598743"; // <== isn't a real movie id
        String watchUrl = "http://www.netflix.com/watch/" + netFlixId;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.netflix.mediaclient",
                    "com.netflix.mediaclient.ui.launch.UIWebViewActivity");
            intent.setData(Uri.parse(watchUrl));
            startActivity(intent);
        }
        catch(Exception e)
        {
            // netflix app isn't installed, send to website.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(watchUrl));
            startActivity(intent);
        }

    }

    public void initiateSydsvenskan() {
        String webSydsvenskan = "https://www.sydsvenskan.se/";
        initiateWeb(webSydsvenskan);
    }

    public void initiateWeb(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void initiateSkypeUri() {

        // Make sure the Skype for Android client is installed.
        if (!isSkypeClientInstalled(getApplicationContext())) {
            goToMarket();
            return;
        }
        // Skype is so annoying, so many attempts that did not work
        // kept all tries that did not really work for later
        String mySkypeUri = "skype:" + "live:.cid.e9736959a90450ad" + "?call&video=true";
        mySkypeUri = "skype:" + "live:.cid.e9736959a90450ad" + "?call&video=true";
        mySkypeUri = "skype:" + "gentle_case@hotmail.com" + "?call&video=true";
        mySkypeUri = "skype:" + ".cid.e9736959a90450ad" + "?call&video=true";
        mySkypeUri = "skype:" + "Gentle Case" + "?call&video=true";
        mySkypeUri = "tel:" + "+46736509716" + "?call";

        mySkypeUri = "skype:echo123?call"; //funkar
        mySkypeUri = "skype:gentle_case@hotmail.com?call"; // satte igång ett call men det kopplar inte upp
        mySkypeUri = "skype:Gentle Case?call"; // öppnar bara appen
        mySkypeUri = "live:.cid.e9736959a90450ad?call"; // startar bara appen
        mySkypeUri = "skype:live.cid.e9736959a90450ad?call"; // satte igång ett call men koplpar inte upp
        mySkypeUri = "skype:live:.cid.e9736959a90450ad?call&video=true"; // video iniiering stöds inte av android !!!
        mySkypeUri = "skype:live:.cid.e9736959a90450ad?call"; // Ringer till Gentle Case!!!

        // Create the Intent from our Skype URI.
        Uri skypeUri = Uri.parse(mySkypeUri);
        Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

        // Restrict the Intent to being handled by the Skype for Android client only.
        myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Initiate the Intent. It should never fail because you've already established the
        // presence of its handler (although there is an extremely minute window where that
        // handler can go away).
        startActivity(myIntent);

        return;
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */
    public void goToMarket() {
        Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);

        return;
    }
}
