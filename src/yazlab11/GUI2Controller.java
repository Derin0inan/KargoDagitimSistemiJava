package yazlab11;

import com.teamdev.jxbrowser.view.javafx.BrowserView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GUI2Controller implements Initializable {

    @FXML
    private BrowserView browserView;

//    String url = "jdbc:postgresql://34.68.27.80:5432/yazlab11";
//    String user = "postgres";
//    String password = "root";
//    private Connection conn = null;
//    private Statement st;
    @Override
    public void initialize(URL ur, ResourceBundle rb) {
        browserView.getBrowser().navigation().loadUrl("http://www.baidu.com");
//        Engine engine = Engine.newInstance(EngineOptions.newBuilder(com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN)
//                .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
//        Browser browser = engine.newBrowser();
//
//        browser.navigation().loadUrl("C:\\HTMLGMaps\\yeni_map.html");
//
//        BrowserView view = BrowserView.newInstance(browser);
//        BorderPane a = new BorderPane(view);
//        
//        a.setPrefSize(400, 400);
//
//        Scene scene = new Scene(a, 1200, 650);
//        try {
//            conn = DriverManager.getConnection(url, user, password);
//            st = conn.createStatement();
//            ResultSet res = st.executeQuery("SELECT * FROM public.kullanici");
//            if (!res.next()) {
//                System.out.println("Connected to the PostgreSQL server successfully.");
//                String sorgu2 = "INSERT INTO public.kullanici(kullaniciID,kullaniciAdi,kullanicimail) "
//                        + "VALUES (0,'Default','Default')";
//                st.executeUpdate(sorgu2);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Error kod: " + e.getErrorCode());
//        }
    }

}
