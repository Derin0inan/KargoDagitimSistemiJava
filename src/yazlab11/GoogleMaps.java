package yazlab11;

import static com.teamdev.jxbrowser.engine.RenderingMode.*;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.navigation.event.FrameLoadFinished;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GoogleMaps {

    private static final int MIN_ZOOM = 0;
    private static final int MAX_ZOOM = 21;

    /**
     * In map.html file default zoom value is set to 4.
     */
    private static int zoomValue = 4;

    public static void main(String[] args) {
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN)
                .licenseKey("6P830J66YBC9KU6X6622S9P15MPSBHBRL3XSNLQ8XN9ZL3V36IKDHZZBOWRQFZQYXYAU").build());
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            BrowserView view = BrowserView.newInstance(browser);

            JButton zoomInButton = new JButton("Zoom In");
            zoomInButton.addActionListener(e -> {
                if (zoomValue < MAX_ZOOM) {
                    browser.mainFrame().ifPresent(frame
                            -> frame.executeJavaScript("map.setZoom("
                                    + ++zoomValue + ")"));
                }
            });

            JButton zoomOutButton = new JButton("Zoom Out");
            zoomOutButton.addActionListener(e -> {
                if (zoomValue > MIN_ZOOM) {
                    browser.mainFrame().ifPresent(frame
                            -> frame.executeJavaScript("map.setZoom("
                                    + --zoomValue + ")"));
                }
            });

            String setMarkerScript
                    = "for (i = 0; i < 10; i++) {\n"
                    + "                markerekle(i,i);\n"
                    + "            }";
            JButton setMarkerButton = new JButton("Set Marker");
            
            
            browser.navigation().on(FrameLoadFinished.class, event -> {
            event.frame().executeJavaScript(setMarkerScript);
        });
            JPanel toolBar = new JPanel();
            toolBar.add(zoomInButton);
            toolBar.add(zoomOutButton);
            toolBar.add(setMarkerButton);

            JFrame frame = new JFrame("Google Maps");
            frame.add(toolBar, BorderLayout.SOUTH);
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(800, 500);
            frame.setVisible(true);

            browser.navigation().loadUrl("C:\\HTMLGMaps\\yeni_map.html");
        });
    }
}
