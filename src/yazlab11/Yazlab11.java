package yazlab11;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import java.awt.BorderLayout;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Yazlab11 extends Application {

    Scene GUI1;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI1.fxml"));
        GUI1 = new Scene(root, 1200, 650);
        primaryStage.setScene(GUI1);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        launch(args);
    }

}
