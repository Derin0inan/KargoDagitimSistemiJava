package yazlab11;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static com.teamdev.jxbrowser.engine.RenderingMode.*;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.navigation.event.FrameLoadFinished;
import java.awt.BorderLayout;
import java.awt.Panel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.View;

public class GUI1Controller implements Initializable {

//Tanımlamalar
    @FXML
    private JFXButton cikisYapButon;

    @FXML
    private Label girisLabel;

    @FXML
    private JFXButton girisYapButon;

    @FXML
    private Label girisYapHataLabel;

    @FXML
    private TextField girisYapMail;

    @FXML
    private PasswordField girisYapSifre;

    @FXML
    private JFXButton haritalaraGitButon;

    @FXML
    private Label hosGeldinizAd;

    @FXML
    private Label hosGeldinizLabel;

    @FXML
    private GridPane kargoDurumGridPane;

    @FXML
    private JFXButton kargoEkleButon;

    @FXML
    private Label kargoEkleHataLabel;

    @FXML
    private TextField kargoEkleKargoAdi;

    @FXML
    private TextField kargoEkleMusteriID;

    @FXML
    private Label kayitLabel;

    @FXML
    private TextField kayitOlAd;

    @FXML
    private JFXButton kayitOlButon;

    @FXML
    private Label kayitOlHataLabel;

    @FXML
    private TextField kayitOlMail;

    @FXML
    private PasswordField kayitOlSifre;

    @FXML
    private TextField kayitOlSoyad;

    @FXML
    private JFXButton musteriEkleButon;

    @FXML
    private Label musteriEkleHataLabel;

    @FXML
    private TextField musteriEkleIlce;

    @FXML
    private TextField musteriEkleMahalle;

    @FXML
    private TextField musteriEkleMusteriAdi;

    @FXML
    private GridPane musteriIDSirala;

    @FXML
    private Label olLabel;

    @FXML
    private PasswordField sifreDegistir1;

    @FXML
    private PasswordField sifreDegistir2;

    @FXML
    private JFXButton sifreDegistirButon;

    @FXML
    private Label sifreDegistirLabel;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab21;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    @FXML
    private Label yapLabel;

    String url = "jdbc:postgresql://34.68.27.80:5432/yazlab11";
    String user = "postgres";
    String password = "root";
    Engine engine = Engine.newInstance(EngineOptions.newBuilder(com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN)
            .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
    Browser browser = engine.newBrowser();
    private Connection conn = null;
    private Statement st;
    private static int kayitSayisi;
    JFXButton[] silButonlari = new JFXButton[500];
    JFXButton[] durum = new JFXButton[500];

    int girenkullaniciid;
    int kargoid[];
    TextField kargoAdiTextField = new TextField();
    TextField musteriIDTextField = new TextField();
    JFXButton kargoEkleHaritadanButon = new JFXButton();
    Label haritaHataMesaji = new Label();

    //Giriş ekranı için yapılan işlemler 
    @FXML
    public void giris(ActionEvent e) throws IOException, SQLException {
        if (e.getSource().equals(girisYapButon)) {
            String email = girisYapMail.getText();
            String sifre = girisYapSifre.getText();
            boolean a = email.isEmpty() || email.trim().isEmpty();
            boolean b = sifre.isEmpty();
            if (a & b) {
                girisYapHataLabel.setTextFill(RED);
                girisYapHataLabel.setText("Lütfen Gerekli Yerleri Doldurunuz!");
            } else if (a) {
                girisYapHataLabel.setTextFill(RED);
                girisYapHataLabel.setText("Email Adresi Boş Bırakılamaz!");
            } else if (b) {
                girisYapHataLabel.setTextFill(RED);
                girisYapHataLabel.setText("Şifre Boş Bırakılamaz!");
            } else {
                int giris = basariliGirisMi(email, sifre, "kullanici");
                if (giris == 1) {
                    girisYapHataLabel.setTextFill(GREEN);
                    girisYapHataLabel.setText("Giriş Başarılı");
                    String sorgu = "SELECT kullaniciadi FROM public.kullanici WHERE kullanicimail = '" + email + "'";
                    ResultSet res = st.executeQuery(sorgu);
                    res.next();
                    hosGeldinizAd.setText(res.getString(1));
                    girisYapildi();
                } else {
                    girisYapHataLabel.setTextFill(RED);
                    girisYapHataLabel.setText("Hatalı giriş! Tekrar deneyiniz");
                }

            }
        } else if (e.getSource().equals(kayitOlButon)) {

            String email = kayitOlMail.getText();
            String sifre = kayitOlSifre.getText();
            String ad = kayitOlAd.getText();
            String soyad = kayitOlSoyad.getText();

            boolean a = email.isEmpty() || email.trim().isEmpty();
            boolean b = sifre.isEmpty();
            boolean d = ad.isEmpty() || ad.trim().isEmpty();
            boolean f = soyad.isEmpty() || soyad.trim().isEmpty();

            if (a || b || d || f) {
                kayitOlHataLabel.setTextFill(RED);
                kayitOlHataLabel.setText("Lütfen Gerekli Yerleri Doldurunuz!");
            } else {
                int kayit = kullaniciKayit(email);
                if (kayit == 1) {
                    kayitEkle(ad, soyad, email, sifre);
                    kayitOlHataLabel.setTextFill(GREEN);
                    kayitOlHataLabel.setText("Kayıt Başarılı");
                } else {
                    kayitOlHataLabel.setTextFill(RED);
                    kayitOlHataLabel.setText("Bu email daha önce kullanıldı!");
                }

            }
        }
    }

    @FXML
    public void cikis(ActionEvent e) throws IOException, SQLException {
        cikisYapildi();
    }

    @FXML
    public void haritayaGit(ActionEvent e) throws IOException, SQLException {

        String sorgu;
        ResultSet res;
        res = st.executeQuery("SELECT count(kargoid) FROM public.kargo");
        res.next();
        
        int ab = res.getInt(1);
        System.out.println("ab: "+ab);
        

        Stage primaryStage = new Stage();
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN)
                .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
        Browser browser = engine.newBrowser();
        browser.navigation().loadUrl("C:\\HTMLGMaps\\gui2map.html");

        BrowserView view = BrowserView.newInstance(browser);
        BorderPane a = new BorderPane(view);
        a.setPrefSize(400, 400);

        Scene scene = new Scene(a, 1200, 650);
        for (int i = 1; i < ab; i++) {
            
            sorgu = "select musteriadresx,musteriadresy from public.musteri m, public.kargo k where m.musteriid = k.musteriid ";
            System.out.println("girdi");res = st.executeQuery(sorgu);
            String setMarkerScript
                    = "markerekle(" + res.getString(1) + "," + res.getString(2) + ");\n";

            browser.navigation().on(FrameLoadFinished.class, event -> {
                event.frame().executeJavaScript(setMarkerScript);
            });
        }

        primaryStage.setTitle("JxBrowser JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> engine.close());

//        Stage stage;
//        Parent root;
//        stage = (Stage) haritalaraGitButon.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("GUI2.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Yol");
//        stage.show();
    }

    @FXML
    public void sifreDegistir(ActionEvent e) {
        String sifre1 = sifreDegistir1.getText();
        String sifre2 = sifreDegistir2.getText();

        if (sifre1.equals(sifre2)) {
            try {
                String sorgu = "UPDATE public.kullanici set kullanicisifre =  '" + sifre1 + "' where kullaniciid = " + girenkullaniciid;
                System.out.println(girenkullaniciid);
                System.out.println(sifre1);

                st.executeUpdate(sorgu);
                sifreDegistirLabel.setTextFill(GREEN);
                sifreDegistirLabel.setText("Şifreler başarıyla değiştirildi!");
                sifreDegistir1.clear();
                sifreDegistir2.clear();

            } catch (SQLException ex) {
                Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            sifreDegistirLabel.setTextFill(RED);
            sifreDegistirLabel.setText("Şifreler aynı olmalıdır!");
        }
    }

    @FXML
    void kargoEkle(ActionEvent event) {
        String kargoadi = kargoEkleKargoAdi.getText();
        String musteriid = kargoEkleMusteriID.getText();
        boolean a = kargoadi.isEmpty() || kargoadi.trim().isEmpty();
        boolean b = musteriid.isEmpty() || musteriid.trim().isEmpty();

        if (a & b) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Gerekli Yerleri Doldurunuz!");
        } else if (a) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Kargo Adını Giriniz!");
        } else if (b) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Müşteri ID Giriniz!");
        } else {
            ResultSet res;
            String sorgu = "select * from musteri where musteriid = " + musteriid;
            try {
                res = st.executeQuery(sorgu);
                if (res.next()) {
                    int kargoid;
                    sorgu = "select max(kargoid) from public.kargo";
                    res = st.executeQuery(sorgu);
                    res.next();
                    kargoid = res.getInt(1);
                    sorgu = "INSERT INTO public.kargo(kargodurumu, kargoid, kargoadi, musteriid) VALUES (false,"
                            + kargoid + ",'" + kargoadi + "'," + musteriid + ")";
                    st.executeUpdate(sorgu);
                    sorgu = "select musteriadresx,musteriadresy from public.musteri";
                    res = st.executeQuery(sorgu);
                    res.next();

                } else {
                    haritaHataMesaji.setTextFill(RED);
                    haritaHataMesaji.setText("Müşteri Bulunamıyor!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void musteriEkle(ActionEvent event) throws ApiException, InterruptedException, IOException {
        String musteriadi = musteriEkleMusteriAdi.getText();
        String musteriilce = musteriEkleIlce.getText();

        boolean a = musteriadi.isEmpty() || musteriadi.trim().isEmpty();
        boolean b = musteriilce.isEmpty() || musteriilce.trim().isEmpty();

        if (a || b ) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Gerekli Yerleri Doldurunuz!");
        } else {
            ResultSet res;
            String sorgu;
            try {
                String adres = musteriilce + " Kocaeli Turkey";

                String apiKey = "AIzaSyDUCJ9n9dc_xBq3Bwbt3Sonk9oqt_na1uA";
                GeoApiContext context = new GeoApiContext.Builder()
                        .apiKey(apiKey)
                        .build();

                GeocodingResult[] results = GeocodingApi.geocode(context,
                        adres).await();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                System.out.println("x" + gson.toJson(results[0].geometry.location.lat));
                System.out.println("y" + gson.toJson(results[0].geometry.location.lng));
                double x = Double.parseDouble(gson.toJson(results[0].geometry.location.lat));
                double y = Double.parseDouble(gson.toJson(results[0].geometry.location.lng));

                int musteriid;
                sorgu = "select max(musteriid) from public.musteri";
                res = st.executeQuery(sorgu);
                res.next();
                musteriid = res.getInt(1);
                musteriid++;
                sorgu = "INSERT INTO public.musteri(musteriadi, musteriid, musteriilce,musteriadresx, musteriadresy )"
                        + " VALUES ('"
                        + musteriadi + "'," + musteriid + "','" + "','" + musteriilce + "'," + x + "," + y + ")";
                st.executeUpdate(sorgu);

            } catch (SQLException ex) {
                Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void musteriidsirala() {
        musteriIDSirala.getChildren().clear();
        Label id = new Label();
        id.setText("ID");
        id.setTextFill(Color.web("#009999"));
        id.setFont(Font.font(25));
        musteriIDSirala.add(id, 0, 0);

        Label madi = new Label();
        madi.setText("  Müşteri Adı");
        madi.setTextFill(Color.web("#009999"));
        madi.setFont(Font.font(25));
        musteriIDSirala.add(madi, 1, 0);

        Label madres = new Label();
        madres.setText("  Müşteri Adres");
        madres.setTextFill(Color.web("#009999"));
        madres.setFont(Font.font(25));
        musteriIDSirala.add(madres, 2, 0);

        ResultSet res;
        String sorgu = "select count(musteriID) from public.musteri";
        try {
            res = st.executeQuery(sorgu);
            if (res.next()) {
                int kayitSayisi = res.getInt(1);
                Label[] musteriIDLabel = new Label[kayitSayisi];
                Label[] musteriAdiLabel = new Label[kayitSayisi];
                Label[] musteriAdresLabel = new Label[kayitSayisi];

                sorgu = "select musteriid,musteriadi from public.musteri ";
                res = st.executeQuery(sorgu);
                if (res.next()) {
                    for (int i = 0; i < kayitSayisi; i++) {
                        musteriIDLabel[i] = new Label();
                        musteriAdiLabel[i] = new Label();
                        musteriAdresLabel[i] = new Label();

                        musteriIDLabel[i].setText(res.getString(1));
                        musteriIDLabel[i].setFont(Font.font(15));
                        musteriIDLabel[i].setTextFill(WHITE);
                        //kargoid[i] = res.getInt(1);

                        musteriAdiLabel[i].setText(res.getString(2));
                        musteriAdiLabel[i].setFont(Font.font(15));
                        musteriAdiLabel[i].setTextFill(WHITE);

                        musteriAdresLabel[i].setText("");
                        musteriAdresLabel[i].setFont(Font.font(15));
                        musteriAdresLabel[i].setTextFill(WHITE);

                        musteriIDSirala.add(musteriIDLabel[i], 0, i + 1);
                        musteriIDSirala.add(musteriAdiLabel[i], 1, i + 1);
                        musteriIDSirala.add(musteriAdresLabel[i], 2, i + 1);
                        res.next();
                    }

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Kayıt Ekleme işlemleri
    private void kayitEkle(String ad, String soyad, String email, String sifre) {
        try {
            ResultSet res = st.executeQuery("SELECT MAX(kullaniciID) FROM public.kullanici");
            res.next();
            String gecici = res.getString(1);
            kayitSayisi = Integer.parseInt(gecici);
            kayitSayisi++;
            String sorgu = "INSERT INTO public.kullanici(kullaniciid,kullaniciadi,kullanicisoyadi,kullanicimail,kullanicisifre) VALUES ("
                    + kayitSayisi + ",'" + ad + "','" + soyad + "','" + email + "','" + sifre + "')";
            st = conn.createStatement();
            st.executeUpdate(sorgu);
            kayitOlMail.clear();
            kayitOlAd.clear();
            kayitOlSoyad.clear();
            kayitOlSifre.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error kod: " + e.getErrorCode());
        }
    }

    //Kullanıcı kayıt için gerekli kontroller
    private int kullaniciKayit(String email) {
        try {
            String sorgu = "SELECT * FROM public.kullanici WHERE kullanicimail = '" + email + "'";
            st = conn.createStatement();
            ResultSet myRs = st.executeQuery(sorgu);

            if (myRs.next()) {
                return 0;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            System.out.println("Error kod: " + e.getErrorCode());
        }
        return -1;

    }

    //Giriş için gerekli kontroller
    private int basariliGirisMi(String email, String sifre, String tablo) {
        try {
            String sb = "SELECT kullaniciid FROM public." + tablo + " WHERE kullanicimail = '" + email + "' and kullanicisifre = '" + sifre + "'";
            st = conn.createStatement();
            ResultSet myRs = st.executeQuery(sb);

            if (!myRs.next()) {
                return 0;
            } else {
                girenkullaniciid = myRs.getInt(1);
                return 1;
            }

        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            System.out.println("Error kod: " + e.getErrorCode());
        }
        return -1;

    }

    //Çıkış
    private void cikisYapildi() {
        tab2.setDisable(true);
        tab3.setDisable(true);
        tab4.setDisable(true);
        tab21.setDisable(true);

        hosGeldinizLabel.setVisible(false);
        hosGeldinizAd.setVisible(false);
        cikisYapButon.setVisible(false);
        haritalaraGitButon.setVisible(false);

        girisLabel.setVisible(true);
        yapLabel.setVisible(true);
        girisYapMail.setVisible(true);
        girisYapSifre.setVisible(true);
        girisYapButon.setVisible(true);

        kayitLabel.setVisible(true);
        olLabel.setVisible(true);
        kayitOlButon.setVisible(true);
        kayitOlAd.setVisible(true);
        kayitOlSoyad.setVisible(true);
        kayitOlMail.setVisible(true);
        kayitOlSifre.setVisible(true);

        girisYapHataLabel.setText("");
        kayitOlHataLabel.setText("");
        girisYapHataLabel.setVisible(true);
        kayitOlHataLabel.setVisible(true);
        hosGeldinizAd.setText("");
        hosGeldinizAd.setVisible(false);

        sifreDegistir1.setVisible(false);
        sifreDegistir2.setVisible(false);
        sifreDegistirButon.setVisible(false);
        sifreDegistirLabel.setText("");

    }

    //Giriş
    private void girisYapildi() {
        tab2.setDisable(false);
        tab3.setDisable(false);
        tab4.setDisable(false);
        tab21.setDisable(false);

        hosGeldinizLabel.setVisible(true);
        hosGeldinizAd.setVisible(true);
        cikisYapButon.setVisible(true);
        haritalaraGitButon.setVisible(true);

        girisLabel.setVisible(false);
        yapLabel.setVisible(false);
        girisYapMail.setVisible(false);
        girisYapSifre.setVisible(false);
        girisYapButon.setVisible(false);

        kayitLabel.setVisible(false);
        olLabel.setVisible(false);
        kayitOlButon.setVisible(false);
        kayitOlAd.setVisible(false);
        kayitOlSoyad.setVisible(false);
        kayitOlMail.setVisible(false);
        kayitOlSifre.setVisible(false);

        girisYapHataLabel.setVisible(false);
        kayitOlHataLabel.setVisible(false);
        hosGeldinizAd.setVisible(true);

        sifreDegistir1.setVisible(true);
        sifreDegistir2.setVisible(true);
        sifreDegistirButon.setVisible(true);
        
        

    }

    //Kargoların Listesi
    private void sirala() {
        kargoDurumGridPane.getChildren().clear();
        Label id = new Label();
        id.setText("  Kargo ID");
        id.setTextFill(Color.web("#009999"));
        id.setFont(Font.font(25));
        kargoDurumGridPane.add(id, 0, 0);

        Label kadi = new Label();
        kadi.setText("  Kargo Adı");
        kadi.setTextFill(Color.web("#009999"));
        kadi.setFont(Font.font(25));
        kargoDurumGridPane.add(kadi, 1, 0);

        Label madi = new Label();
        madi.setText("  Müşteri Adı");
        madi.setTextFill(Color.web("#009999"));
        madi.setFont(Font.font(25));
        kargoDurumGridPane.add(madi, 2, 0);

        Label kdurum = new Label();
        kdurum.setText("  Kargo Durumu");
        kdurum.setTextFill(Color.web("#009999"));
        kdurum.setFont(Font.font(25));
        kargoDurumGridPane.add(kdurum, 3, 0);

        Label kadres = new Label();
        kadres.setText("  Kargo Adresi");
        kadres.setTextFill(Color.web("#009999"));
        kadres.setFont(Font.font(25));
        kargoDurumGridPane.add(kadres, 4, 0);

        ResultSet res;
        String sorgu = "select count(kargoID) from kargo";
        try {
            res = st.executeQuery(sorgu);
            if (res.next()) {
                int kayitSayisi = res.getInt(1);
                Label[] kargoIDLabel = new Label[kayitSayisi];
                Label[] kargoAdiLabel = new Label[kayitSayisi];
                Label[] kargoMusteriAdiLabel = new Label[kayitSayisi];
                Label[] kargoDurumuLabel = new Label[kayitSayisi];
                Label[] kargoAdresiLabel = new Label[kayitSayisi];
                silButonlari = new JFXButton[kayitSayisi];
                int musteriid[] = new int[kayitSayisi];
                kargoid = new int[kayitSayisi];

                sorgu = "select kargoid,kargoadi,musteriid,kargodurumu from public.kargo order by kargoid asc";
                res = st.executeQuery(sorgu);
                if (res.next()) {
                    for (int i = 0; i < kayitSayisi; i++) {
                        kargoIDLabel[i] = new Label();
                        kargoAdiLabel[i] = new Label();
                        kargoDurumuLabel[i] = new Label();
                        silButonlari[i] = new JFXButton();
                        durum[i] = new JFXButton();

                        kargoIDLabel[i].setText(res.getString(1));
                        kargoIDLabel[i].setFont(Font.font(15));
                        kargoIDLabel[i].setTextFill(WHITE);
                        kargoid[i] = res.getInt(1);

                        kargoAdiLabel[i].setText(res.getString(2));
                        kargoAdiLabel[i].setFont(Font.font(15));
                        kargoAdiLabel[i].setTextFill(WHITE);

                        if (res.getString(4).equals("f")) {
                            kargoDurumuLabel[i].setText("Teslim edilmedi");
                        } else {
                            kargoDurumuLabel[i].setText("Teslim edildi");
                        }

                        kargoDurumuLabel[i].setFont(Font.font(15));
                        kargoDurumuLabel[i].setTextFill(WHITE);

                        durum[i].setText("Teslim et/İptal");
                        durum[i].setTextFill(WHITE);
                        durum[i].setStyle("-fx-background-color:  #009999");
                        durum[i].setOnAction(e -> teslimEtAction(e));

                        musteriid[i] = res.getInt(3);

                        silButonlari[i].setText("Sil");
                        silButonlari[i].setTextFill(WHITE);
                        silButonlari[i].setStyle("-fx-background-color:  #009999");
                        silButonlari[i].setOnAction(e -> silAction(e));
                        kargoDurumGridPane.add(silButonlari[i], 5, i + 1);
                        kargoDurumGridPane.add(kargoIDLabel[i], 0, i + 1);
                        kargoDurumGridPane.add(kargoAdiLabel[i], 1, i + 1);
                        kargoDurumGridPane.add(kargoDurumuLabel[i], 3, i + 1);
                        kargoDurumGridPane.add(durum[i], 6, i + 1);
                        res.next();
                    }
                    for (int i = 0; i < kayitSayisi; i++) {
                        sorgu = "select musteriadi from musteri where musteriid = " + musteriid[i];
                        res = st.executeQuery(sorgu);
                        if (res.next()) {

                            kargoMusteriAdiLabel[i] = new Label();
                            kargoMusteriAdiLabel[i].setText(res.getString(1));
                            kargoMusteriAdiLabel[i].setFont(Font.font(15));
                            kargoMusteriAdiLabel[i].setTextFill(WHITE);

                            kargoAdresiLabel[i] = new Label();

                            String adres =  "/Kocaeli";
                            kargoAdresiLabel[i].setText(adres);
                            kargoAdresiLabel[i].setFont(Font.font(15));
                            kargoAdresiLabel[i].setTextFill(WHITE);

                            kargoDurumGridPane.add(kargoMusteriAdiLabel[i], 2, i + 1);
                            kargoDurumGridPane.add(kargoAdresiLabel[i], 4, i + 1);
                        }
                    }

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void teslimEtAction(ActionEvent e) {
        for (int i = 0;; i++) {
            if (e.getSource().equals(durum[i])) {
                System.out.println("basıldı " + i);
                String sorgu = "select kargodurumu from public.kargo "
                        + "WHERE kargoID = " + kargoid[i];
                try {
                    ResultSet res = st.executeQuery(sorgu);
                    if (res.next()) {
                        if (res.getString(1).equals("f")) {
                            System.out.println("false");
                            sorgu = "update  public.kargo set kargodurumu = true "
                                    + "WHERE kargoID = " + kargoid[i];
                            st.executeUpdate(sorgu);
                            durum[i].setTextFill(WHITE);
                            durum[i].setStyle("-fx-background-color:  #009999");

                        } else {
                            System.out.println("true");
                            sorgu = "update  public.kargo set kargodurumu = false "
                                    + "WHERE kargoID = " + kargoid[i];
                            st.executeUpdate(sorgu);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                sirala();
                break;

            }

        }
    }

    private void silAction(ActionEvent e) {
        for (int i = 0;; i++) {
            if (e.getSource().equals(silButonlari[i])) {
                System.out.println("basıldı");
                String sorgu = "DELETE FROM public.kargo "
                        + "WHERE kargoID = " + kargoid[i];
                try {
                    st.executeUpdate(sorgu);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                kayitSayisi--;
                sirala();
                break;

            }

        }
    }

    private void GMap() {
        Browser browser2 = engine.newBrowser();
        browser.navigation().loadUrl("C:\\HTMLGMaps\\secimmap.html");
        BrowserView view = BrowserView.newInstance(browser);
        browser2.navigation().loadUrl("C:\\HTMLGMaps\\lokasyonmap.html");

        kargoEkleHaritadanButon.setPrefSize(900, 30);
        kargoEkleHaritadanButon.setLayoutY(50);

        kargoAdiTextField.setPromptText("Kargo adını giriniz ve daha sonra aşağıdan yer seçiniz");
        kargoAdiTextField.setPrefSize(100, 30);

        SplitPane sp = new SplitPane();
        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(view);
        borderpane.setBottom(kargoEkleHaritadanButon);
        kargoEkleHaritadanButon.setText("Kargoyu ekle");
        borderpane.setTop(sp);

        musteriIDTextField.setPromptText("Müşteri ID Giriniz");

        final StackPane sp1 = new StackPane();
        sp1.getChildren().add(kargoAdiTextField);
        final StackPane sp2 = new StackPane();
        sp2.getChildren().add(musteriIDTextField);
        final StackPane sp3 = new StackPane();
        sp3.getChildren().add(kargoEkleHaritadanButon);
        sp.getItems().addAll(sp1, sp2, sp3);
        sp.setDividerPositions(0.3f, 0.6f, 0.3f);

        kargoEkleHaritadanButon.setTextFill(WHITE);
        kargoEkleHaritadanButon.setStyle("-fx-background-color:  #009999");
        kargoEkleHaritadanButon.setOnAction(e -> kargoEkleHaritadan(e));

        borderpane.setLayoutY(100);
        borderpane.setLayoutX(100);
        borderpane.setPrefHeight(100);
        borderpane.setPrefWidth(100);
        borderpane.setScaleX(0.9);
        borderpane.setScaleY(0.9);

        tab21.setContent(borderpane);

        BrowserView view2 = BrowserView.newInstance(browser2);
        tab4.setContent(view2);
    }

    private void kargoEkleHaritadan(ActionEvent e) {
        String kargoadi = kargoAdiTextField.getText();
        String musteriid = musteriIDTextField.getText();
        boolean a = kargoadi.isEmpty() || kargoadi.trim().isEmpty();
        boolean b = musteriid.isEmpty() || musteriid.trim().isEmpty();

        if (a & b) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Gerekli Yerleri Doldurunuz!");
        } else if (a) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Kargo Adını Giriniz!");
        } else if (b) {
            haritaHataMesaji.setTextFill(RED);
            haritaHataMesaji.setText("Müşteri ID Giriniz!");
        } else {
            ResultSet res;
            String sorgu = "select * from musteri where musteriid = " + musteriid;
            try {
                res = st.executeQuery(sorgu);
                if (res.next()) {
                    int kargoid;
                    sorgu = "select max(kargoid) from public.kargo";
                    res = st.executeQuery(sorgu);
                    res.next();
                    kargoid = res.getInt(sorgu);
                    sorgu = "INSERT INTO public.kargo(kargodurumu, kargoid, kargoadi, musteriid) VALUES (false,"
                            + kargoid + ",'" + kargoadi + "'," + musteriid + ")";
                    st.executeUpdate(sorgu);

                } else {
                    haritaHataMesaji.setTextFill(RED);
                    haritaHataMesaji.setText("Müşteri Bulunamıyor!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUI1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void initialize(URL ur, ResourceBundle rb) {
        try {

            girisYapildi();
            cikisYapildi();
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            String sorgu;
            ResultSet res = st.executeQuery("SELECT * FROM public.kullanici");
            if (!res.next()) {
                System.out.println("Connected to the PostgreSQL server successfully.");
                sorgu = "INSERT INTO public.kullanici(kullaniciID,kullaniciAdi,kullanicimail) "
                        + "VALUES (0,'Default','Default')";
                st.executeUpdate(sorgu);

            }

            sorgu = "select * from public.musteri";
            res = st.executeQuery(sorgu);
            if (!res.next()) {
                sorgu = "INSERT INTO public.musteri(musteriid, musteriadi) "
                        + "VALUES (0,'Default')";
                st.executeUpdate(sorgu);
            }
            sorgu = "select * from public.kargo";
            res = st.executeQuery(sorgu);
            if (!res.next()) {
                sorgu = "INSERT INTO public.kargo(kargodurumu, kargoid, kargoadi, musteriid) "
                        + "VALUES (false,0,'Default',0)";
                st.executeUpdate(sorgu);
            }

            sirala();
            GMap();
            musteriidsirala();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error kod: " + e.getErrorCode());
        }

    }
}
