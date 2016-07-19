/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.lang.Runnable;
import java.lang.Thread;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JMenuBar;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MainController implements Runnable, ActionListener{
    final public static int LINE_REFRESH_INTERVAL = 250;

    protected AbstractMainFrame mainFrame;

    protected AbstractGraphPanel graphPanel;
    protected AbstractLiveDataPanel liveDataPanel;
    protected AbstractDataSelectPanel dataSelectPanel;

    protected Timer dataUpdater;

    public MainController (AbstractMainFrame frame) {
        mainFrame = frame;

        createLineUpdater();
    }

    public void useMenu (AbstractMenu menu) {
        mainFrame.useMenu(menu);
    }

    public void setTypes (DataTypeCollectionInterface types) {
        liveDataPanel.setTypes(types);
        dataSelectPanel.setTypes(types);

        mainFrame.removeLinePanels();

        AbstractLinePanel[] panels = new AbstractLinePanel[types.size()];
        int i = 0;

        for (DataTypeInterface type : types)
            panels[i++] = new LinePanel(type);

        mainFrame.useLinePanels(panels);
    }

    public void useGraphPanel (AbstractGraphPanel panel) {
        mainFrame.useGraphPanel(graphPanel = panel);
    }

    public void useDataSelectPanel (AbstractDataSelectPanel panel) {
        mainFrame.useDataSelectPanel(dataSelectPanel = panel);
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