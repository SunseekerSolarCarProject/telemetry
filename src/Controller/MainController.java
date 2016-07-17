/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.lang.Runnable;
import java.lang.Thread;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class MainController extends AbstractController implements Runnable, ActionListener{
    final public static int LINE_REFRESH_INTERVAL = 250;

    protected AbstractMainFrame mainFrame;

    protected AbstractGraphPanel graphPanel;

    protected AbstractLiveDataPanel liveDataPanel;

    protected boolean paused = false;

    protected Timer dataUpdater;

    public MainController (AbstractMainFrame frame) {
        mainFrame = frame;

        createLineUpdater();
    }

    public void useMenu (AbstractMenu menu) {
        mainFrame.useMenu(menu);
    }

    public void useGraphPanel (AbstractGraphPanel panel) {
        mainFrame.useGraphPanel(graphPanel = panel);
    }

    public void useDataSelectPanel (AbstractDataSelectPanel panel) {
        mainFrame.useDataSelectPanel(panel);
    }

    public void useLiveDataPanel (AbstractLiveDataPanel panel) {
        mainFrame.useLiveDataPanel(liveDataPanel = panel);
    }

    public void start () {
        mainFrame.showFrame();

        EventQueue.invokeLater(this);
    }

    public void run () {
        dataUpdater.start();
    }

    public void actionPerformed (ActionEvent evt) {
        graphPanel.repaint();
        liveDataPanel.refresh();
    }

    protected void createLineUpdater () {
        dataUpdater = new Timer(LINE_REFRESH_INTERVAL, this);
    }

}