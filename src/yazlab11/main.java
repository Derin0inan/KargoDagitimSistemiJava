package yazlab11;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.*;
import com.teamdev.jxbrowser.engine.Engine;
import com.google.gson.Gson;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import com.teamdev.jxbrowser.js.JsFunction;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import java.awt.BorderLayout;
import java.io.IOException;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import java.awt.BorderLayout;
import java.io.IOException;




    



public class main { 
  

 
    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
        
        JPanel jpanel = new JPanel();
        String apiKey = "AIzaSyDUCJ9n9dc_xBq3Bwbt3Sonk9oqt_na1uA";
        conn db = new conn();
        db.connectDb();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results = GeocodingApi.geocode(context,
                "Mühendislik Fakültesi Kocaeli ünv. kampüsü Kabaoğlu İzmit Kocaeli Turkey").await();
        

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(results[0].geometry.location.lat));
        System.out.println(gson.toJson(results[0].geometry.location.lng));
        

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
