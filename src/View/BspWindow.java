/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Direction;
import Model.Heuristique;
import Model.Point;
import Model.Segment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author zakariae
 */
public class BspWindow extends JFrame implements ActionListener {

    /**
     *
     */
    public static int HEIGHT = 950;

    /**
     *
     */
    public static int WIDTH = 1400;

    private View view;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem, menuItemClose;
    private JMenu parameter, menuHeur, menuDirection;
  
    private JRadioButtonMenuItem randomHeur;
    private JRadioButtonMenuItem firstSegmentHeur;
    private JRadioButtonMenuItem H2;

    private JRadioButtonMenuItem Top;
    private JRadioButtonMenuItem Down;
    private JRadioButtonMenuItem Left;
    private JRadioButtonMenuItem Right;

    private BspPanel bspPanel;
    private JPanel backGroundPanel;
    private LineViewPanel lineViewPanel;

    private JFileChooser fc;
    private ButtonGroup heurGroup;

    /**
     *
     * @param view
     */
    public BspWindow(final View view) {
        this.view = view;
        setSize(WIDTH, HEIGHT);
        componentsInitialisation();

        backGroundPanel = new JPanel();
        bspPanel = new BspPanel(this);
        bspPanel.setPreferredSize(new Dimension(1400, 800));
        bspPanel.setBackground(Color.white);

        lineViewPanel = new LineViewPanel(this);
        // lineViewPanel.setBackground(Color.white);
        lineViewPanel.setPreferredSize(new Dimension(1400, 150));//1400

        backGroundPanel.add(bspPanel, BorderLayout.NORTH);
        backGroundPanel.add(lineViewPanel, BorderLayout.SOUTH);

        setContentPane(backGroundPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        this.setResizable(false);
        menuItem.addActionListener(this);

    }

    private void componentsInitialisation() {
        menuBar = new JMenuBar();
        //first Menu
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("open File");
        menuItemClose = new JMenuItem("Close");
        menu.add(menuItem);
        menu.addSeparator();
        menu.add(menuItemClose);
        menuBar.add(menu);
        //parameter Menu
        parameter = new JMenu("Parameter");
        //heuristique SubMenu
        menuHeur = new JMenu("choix de l'heuristque");
        randomHeur = new JRadioButtonMenuItem("Random");
        firstSegmentHeur = new JRadioButtonMenuItem("firstSegmentHeuristique");
        H2 = new JRadioButtonMenuItem("H2");
        heurGroup = new ButtonGroup();
        heurGroup.add(randomHeur);
        heurGroup.add(H2);
        heurGroup.add(firstSegmentHeur);
        menuHeur.add(randomHeur);
        menuHeur.add(H2);
        menuHeur.add(firstSegmentHeur);
        randomHeur.addActionListener(this);
        H2.addActionListener(this);
        firstSegmentHeur.addActionListener(this);
        randomHeur.setSelected(true);
        parameter.add(menuHeur);
        //direction subMenu
        menuDirection = new JMenu("choix de la direction");
        Top = new JRadioButtonMenuItem("Top");
        Down = new JRadioButtonMenuItem("Down");
        Right = new JRadioButtonMenuItem("Right");
        Left = new JRadioButtonMenuItem("Left");
        ButtonGroup directionGroup = new ButtonGroup();
        directionGroup.add(Top);
        directionGroup.add(Down);
        directionGroup.add(Right);
        directionGroup.add(Left);
        menuDirection.add(Top);
        menuDirection.add(Down);
        menuDirection.add(Right);
        menuDirection.add(Left);
        Top.addActionListener(this);
        Down.addActionListener(this);
        Right.addActionListener(this);
        Left.addActionListener(this);
        Top.setSelected(true);
        menuItemClose.addActionListener(this);
        parameter.add(menuDirection);
        menuBar.add(parameter);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menuItem)) {
            fc = new JFileChooser();

            int returnVal = fc.showOpenDialog(BspWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                // System.out.println("Opening: " + file.getName());
                //System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
                Heuristique heur = getTheHeur();
                view.parseFile(file, heur);
                System.out.println("the direction " + BspPanel.direction);
            }
        } else if (e.getSource().equals(Top)) {
            BspPanel.direction = Direction.Top;
            System.out.println("Top");
            bspPanel.refreshTheView();
        } else if (e.getSource().equals(Down)) {
            BspPanel.direction = Direction.Down;
            System.out.println("Down");
            bspPanel.refreshTheView();

        } else if (e.getSource().equals(Left)) {
            BspPanel.direction = Direction.Left;
            System.out.println("Left");
            bspPanel.refreshTheView();

        } else if (e.getSource().equals(Right)) {
            BspPanel.direction = Direction.Right;
            System.out.println("Right");
            bspPanel.refreshTheView();

        }else if (e.getSource().equals(menuItemClose )) {
           System.exit(1);

        }

    }

    /**
     *
     * @return
     */
    public View getView() {
        return view;
    }

    /**
     *
     * @return
     */
    public ArrayList<Segment> getListOfSegments() {
        return view.getListOfSegments();
    }

    /**
     *
     * @param viewPoint
     * @return
     */
    public ArrayList<Segment> getListOfSortedSegments(Point viewPoint) {
        return view.getListOfSortedSegments(viewPoint);

    }

    /**
     *
     * @return
     */
    public Point getViewPoint() {
        return bspPanel.getViewPoint();
    }

    /**
     *
     * @return
     */
    public LineViewPanel getlineViewPanel() {
        return lineViewPanel;
    }

    /**
     *
     * @return
     */
    public BspPanel getBspPanel() {
        return bspPanel;
    }

    /**
     *
     * @return
     */
    public Heuristique getTheHeur() {

        if (H2.isSelected()) {
            return Heuristique.H2;
        } else if (firstSegmentHeur.isSelected()) {
            return Heuristique.thefirstsegemetofthelist;
        } else {
            return Heuristique.random;
        }
    }

    /**
     *
     * @param direction
     */
    public void changeDirection(Direction direction) {
        switch (direction) {
            case Right:
                Right.setSelected(true);
                break;
            case Left:
                Left.setSelected(true);
                break;
            case Down:
                Down.setSelected(true);
                break;
            default:
                Top.setSelected(true);
        }
    }

}
