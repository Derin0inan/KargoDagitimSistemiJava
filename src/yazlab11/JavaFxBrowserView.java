package yazlab11;

import static com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This example demonstrates how to embed JavaFX BrowserView into JavaFX app.
 */
public final class JavaFxBrowserView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
        Browser browser = engine.newBrowser();

        browser.navigation().loadUrl("C:\\HTMLGMaps\\yeni_map.html");

        BrowserView view = BrowserView.newInstance(browser);
        BorderPane a = new BorderPane();
        a.getChildren().add(view);
       
        Scene scene = new Scene(a, 700, 500);
        primaryStage.setTitle("JavaFx BrowserView");
        primaryStage.setScene(scene);
        primaryStage.show();

        browser.navigation().loadUrl("https://www.google.com");
    }
}
