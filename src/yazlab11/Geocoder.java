package yazlab11;
import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Geocoder. See: http://www.google.com/apis/maps/documentation/services.html# Geocoding_Direct
 *
 * @author Thijs Vonk
 */
public class Geocoder 
{

    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
        
        JPanel jpanel = new JPanel();
        String apiKey = "AIzaSyDUCJ9n9dc_xBq3Bwbt3Sonk9oqt_na1uA";
        conn db = new conn();
        db.connectDb();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results = GeocodingApi.geocode(context,
                "Veliahmet Mahallesi Alemdar Caddesi No:105").await();
        

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(results[0].addressComponents));

        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
        Browser browser = engine.newBrowser();

        browser.navigation().loadUrl("C:\\HTMLGMaps\\yeni_map.html");

        BrowserView view = BrowserView.newInstance(browser);
        
        

        jpanel.add(view, BorderLayout.CENTER);
        jpanel.setVisible(true);
        JFrame a = new JFrame();
        a.add(view,BorderLayout.CENTER);
        a.setSize(800,600);
        a.setVisible(true);
    }
}