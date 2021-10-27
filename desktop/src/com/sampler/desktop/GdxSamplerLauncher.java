package com.sampler.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.sampler.common.SampleFactory;
import com.sampler.common.SampleInfos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int CELL_WIDTH= 200;
    private static final int CANVAS_WIDTH= WIDTH-CELL_WIDTH;

    // AWT Abstraction Window Toolkit
    //enables us to embed libgdx app/game into java desktop app
    private LwjglAWTCanvas lwjglAWTCanvas;

    private JList sampleList;
    private JPanel controlPanel;

    public GdxSamplerLauncher() throws HeadlessException {
        init();

    }

    private void init(){
        createControlPanel();
        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if(lwjglAWTCanvas != null){
                    //will call our dispose and stop our Libgdx app
                    lwjglAWTCanvas.stop();
                }
            }
        });
        setTitle(GdxSamplerLauncher.class.getName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setVisible(true);



    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weighty = 1;

        sampleList = new JList(SampleInfos.getSampleNames().toArray());

        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    launchSelectedSample();
                }
            }
        });

            JScrollPane jScrollPane = new JScrollPane(sampleList);
            controlPanel.add(jScrollPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0;

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedSample();

            }
        });

        controlPanel.add(launchButton, constraints);
    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();

        if(sampleName == null || sampleName.isEmpty()){
            System.out.println("Sample name is empty cannot launch");
            return;
        }
        launchSample(sampleName);
    }

    private void launchSample(String name) {
        System.out.println("Launching sample name = " + name);

        Container container = getContentPane();

        if (lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample = SampleFactory.newSample(name);



        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();
    }



    public static void main(String[] args) {

        //must be used to run our JFrame properly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }

}
