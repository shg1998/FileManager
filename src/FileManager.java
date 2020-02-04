import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BaseMultiResolutionImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

public class FileManager {
    static DefaultMutableTreeNode root = new DefaultMutableTreeNode("This PC");
    static JTree fileTree = new JTree(root);
    static JFrame mainFrame = new JFrame("Shg Manager");
    static JPanel mainPanel = new JPanel(new BorderLayout());
    static JScrollPane jScrollPane = new JScrollPane(fileTree);
    static JMenuBar menuBar = new JMenuBar();
    static JTable jTable;
    static JScrollPane jScrollPane1;
    static HashMap<String, DefaultMutableTreeNode> nameNodeHashMap = new HashMap<>();
    static File[] paths;
    static JMenu file = new JMenu("File");
    static JMenu edit = new JMenu("Edit");
    static JMenu help = new JMenu("Help");
    static JMenu view = new JMenu("View");
    static JMenuItem about = new JMenuItem("About");
    static JMenuItem setting = new JMenuItem("Setting");
    static JMenuItem guide = new JMenuItem("Guide");
    static JMenuItem newFile = new JMenuItem("NewFile");
    static JMenuItem newFolder = new JMenuItem("NewFolder");
    static JMenuItem delete = new JMenuItem("Delete");
    static JMenuItem Sync = new JMenuItem("Sync");
    static JMenuItem anotherFileM = new JMenuItem("wonderful showing");
    static JMenuItem byTable = new JMenuItem("table");
    static JMenuItem byGrid = new JMenuItem("grid");
    static JMenuItem rename = new JMenuItem("Rename");
    static JMenuItem copy = new JMenuItem("Copy");
    static JMenuItem cut = new JMenuItem("Cut");
    static JMenuItem paste = new JMenuItem("Paste");
    static PlaceholderTextField textField1 = new PlaceholderTextField();
    static JTextField textField2 = new JTextField();
    static JPanel TextPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    static JButton btn1 = new JButton();
    static Icon icon1 = new ImageIcon("Icons/Shg.png");
    static JPanel treePanel = new JPanel();
    static ImageIcon imageIcon = new ImageIcon("Icons/shg-squarelogo-1450871532370.png");
    static JFrame aboutFrame = new JFrame("About this Application");
    static JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    static FileSystemView fileSystemView;
    static File currentFile = new File(System.getProperty("user.home"));
    private static File[] children;
    static JPopupMenu rightClickMenu = new JPopupMenu();
    static JMenuItem menu_copy = new JMenuItem("Copy");
    static JMenuItem menu_open = new JMenuItem("Open");
    static JMenuItem menu_paste = new JMenuItem("Paste");
    static JMenuItem menu_properties = new JMenuItem("Properties");
    static JMenuItem menu_cut = new JMenuItem("Cut");

    static String source_path;

    public FileManager() {
    }

    public static void main(String[] args) {
        systemTrayDemo();
        //splitPane.add(fileTree);
        // jScrollPane.setViewportView(newFile);
        splitPane.add(jScrollPane);
        //splitPane.setResizeWeight(0.001);
        textField1.setPlaceholder("search");
        // on top on Text panel
        btn1.setIcon(icon1);
        btn1.setToolTipText("BackWard");
        btn1.setPreferredSize(new Dimension(20, 20));
        textField2.setPreferredSize(new Dimension(940, 20));
        textField2.setToolTipText("Path of file");
        textField1.setPreferredSize(new Dimension(200, 20));
        textField1.setToolTipText("type for search");
        TextPanel.add(btn1);
        TextPanel.add(textField2);
        TextPanel.add(textField1);
        //for menu bar
        //ToolTipText:
        newFile.setToolTipText("Create new File");
        newFolder.setToolTipText("create new folder");
        delete.setToolTipText("delete one or multi selection");
        Sync.setToolTipText("For Sync!");
        rename.setToolTipText("Change the name of file or folder");
        copy.setToolTipText("copy file or directory");
        cut.setToolTipText("Cut file or directory");
        paste.setToolTipText("Paste file or directory which is selected! ");
        setting.setToolTipText("Setting for modify and etc!");
        guide.setToolTipText("Program Guide");
        about.setToolTipText("about this application(Program)!");
        anotherFileM.setToolTipText("Another representation of the file manager");
        // adding
        file.add(newFile);
        file.add(newFolder);
        file.add(delete);
        file.add(Sync);
        edit.add(rename);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        help.add(setting);
        help.add(guide);
        help.add(about);
        view.add(anotherFileM);
        view.add(byTable);
        view.add(byGrid);
        // adding too!!
        menuBar.add(file);
        menuBar.add(help);
        menuBar.add(edit);
        menuBar.add(view);
        //adding too!!
        mainPanel.add(TextPanel, BorderLayout.NORTH);

        rightClickMenu.add(menu_open);
        rightClickMenu.add(menu_copy);
        rightClickMenu.add(menu_cut);
        rightClickMenu.add(menu_paste);
        rightClickMenu.add(menu_properties);


        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setResizable(true);
        mainFrame.setIconImage(new BaseMultiResolutionImage(imageIcon.getImage()));
        // this block is for handle of About field.
//        Sync.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SwingWorker swingWorker=new SwingWorker() {
//                    @Override
//                    protected Object doInBackground() throws Exception {
//                        return null;
//                    }
//                }
//            }
//        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });
        anotherFileM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFile();
            }
        });
        guide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGuidePage();
            }
        });
        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingPanel();
            }
        });
//        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
//            @Override
//            public void valueChanged(TreeSelectionEvent e) {
//                File node = new File((String) e.getPath().getLastPathComponent());
//                currentFile = node;
//                JFrame f = new JFrame();
//                JOptionPane.showConfirmDialog(f, "select");
//            }
//        });
        newFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFolder();
            }
        });
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFile();
            }
        });
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rename();
            }
        });
        byGrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.remove(jScrollPane1);
                showGrid();
            }
        });
        // key events(shortcuts):
        newFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        menu_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menu_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menu_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        // eliciting from files that be in my pc !
        btn1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btn1MouseClicked(evt);
            }
        });


        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        for (File path : paths) {
            if (fileSystemView.getSystemTypeDescription(path).equals("Local Disk")) {
                String rootName = path.getPath().toString();
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(rootName);

                nameNodeHashMap.put(rootName, node);
                root.add(node);
            }
        }
        mainPanel.add(splitPane, BorderLayout.WEST);
        mainFrame.setSize(1300, 700);
        mainFrame.setVisible(true);

        fileTree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                try {
                    childShower(me);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mainFrame.setContentPane(mainPanel);

    }

    /**
     * this method is for showing child of parents in folder in jtree!
     *
     * @param me ITS A MOUSE EVENT
     * @throws IOException this method may throws exception!
     */
    private static void childShower(MouseEvent me) throws IOException {
        TreePath tp = fileTree.getPathForLocation(me.getX(), me.getY());
        if (tp != null && !tp.getLastPathComponent().equals("This PC"))
            textField2.setText(tp.getLastPathComponent().toString());
        else
            return;
        DefaultMutableTreeNode clickedNode = nameNodeHashMap.get(tp.getLastPathComponent().toString());
        File clickedFile = new File(tp.getLastPathComponent().toString());
        currentFile = clickedFile;
        if (clickedFile.isFile()) {
            Desktop.getDesktop().open(clickedFile);
            return;
        }
        children = clickedFile.listFiles();
        if (children != null)
            for (File child : children) {
                String childName = child.getPath();
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childName);
                nameNodeHashMap.put(childName, childNode);
                clickedNode.add(childNode);
            }
        showtable();
//        showGrid();
        String string = textField1.getText().toString();
        for (File file : children) {
            if (string.equals(file.getPath().substring(3))) {
                Desktop.getDesktop().open(file);
            } else {
                // System.out.println(file.getPath()+","+string +" are NOT the same");
            }
        }

        //fileTree = new JTree(root);
        //mainFrame.add(fileTree);
        fileTree.repaint();
        //fileTree.validate();
        mainFrame.validate();

    }

    /**
     * this method is for showing another type of showing that called grid!
     */
    private static void showGrid() {
        JPanel panel = new JPanel(new GridLayout(3, 4));
        System.out.println(currentFile.getPath() + "   " + currentFile.listFiles().length);
        File[] files = currentFile.listFiles();
        panel.setLayout(new GridLayout(3, 4));

        for (File f : files) {
            try {
                panel.removeAll();
            } catch (Exception ex) {
            } finally {
                JPanel panel1 = new JPanel(new BorderLayout());
                JButton btn2 = new JButton();
                // Icon icon = fileSystemView.getSystemIcon(f);
                //btn2.setIcon(icon);
                JLabel lbl1 = new JLabel();
                lbl1.setText(f.getName().toString());
                panel1.add(btn2, BorderLayout.NORTH);
                panel1.add(lbl1, BorderLayout.SOUTH);
                panel.add(panel1);
            }
        }
        mainPanel.add(panel, BorderLayout.CENTER);
    }

    /**
     * this method is for showing another type of showing that called table!
     */
    private static void showtable() {
        jTable = new JTable(salam(currentFile));
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point clickedPoint = new Point(e.getX(), e.getY());
                    rightClickMenu.show(jTable, clickedPoint.x, clickedPoint.y);

                    File selectedFile = new File(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
                    //System.out.println(selectedFile.toString());

                    menu_copy.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            source_path = selectedFile.getPath();
                        }
                    });

                    menu_properties.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame f = new JFrame();
                            JOptionPane.showMessageDialog(f, "type:" + selectedFile.getPath() + "\n" + "name:   " + selectedFile.getName()
                                            + "\nsize:    " + selectedFile.length() + "MB" + "\ncreated:     " + selectedFile.lastModified(), "properties",
                                    JOptionPane.WARNING_MESSAGE);
                            f.setVisible(true);
                        }
                    });

                    menu_paste.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String destination_path = selectedFile.getPath();

                            if (selectedFile.isDirectory()) {
                                try {
                                    System.out.println(destination_path);
                                    System.out.println(source_path);
                                    copy(Paths.get(source_path), Paths.get(destination_path));

                                    //showtable();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                    menu_cut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            source_path = selectedFile.getPath();
                        }
                    });

                    menu_open.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Desktop.getDesktop().open(selectedFile);


                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

        try {
            jScrollPane1.removeAll();
            mainPanel.remove(jScrollPane1);
        } catch (Exception ep) {
        } finally {
            jScrollPane1 = new JScrollPane(jTable);
            jScrollPane1.createVerticalScrollBar();
            jScrollPane1.createHorizontalScrollBar();


            //            JPanel panel = new JPanel(new BorderLayout());
//            panel.add(jTable, BorderLayout.CENTER);
//            jScrollPane1.add(jScrollPane1,BorderLayout.CENTER);


            mainPanel.add(jScrollPane1, BorderLayout.CENTER);

        }


//        jScrollPane1.getViewport().add(panel);
        //jScrollPane1.removeAll();
    }

    /**
     * this method is for copy file and folder in file manager!
     *
     * @param destination its the destination of transfer!
     * @param source      its the source of transfer!
     * @throws IOException this method may throws exception!
     */
    private static void copy(Path destination, Path source) throws IOException {
        if (source != null && destination != null) {
            //copy source to target using Files Class

            Files.copy(source, destination);
        }
    }

    /**
     * this method is for rename file and folder in file manager!
     */
    private static void rename() {
        if (currentFile == null) {
            System.out.println("No file selected for deletion.");
            return;
        }
        String name = JOptionPane.showInputDialog("Please Enter yor new file name:");
        currentFile.renameTo(new File(
                currentFile.getParentFile(), name));
        showtable();
    }

    /**
     * this method is for delete file and folder in file manager!
     */
    private static void deleteFile() {
        if (currentFile == null) {
            System.out.println("No file selected for deletion.");
            return;
        }
        int result = JOptionPane.showOptionDialog(
                null,
                "Are you sure you want to delete this file?",
                "Delete File",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );
        if (result == JOptionPane.YES_OPTION) {
            currentFile.delete();
        }
        showtable();
    }

    /**
     * this method is for create new  file  in file manager!
     */
    private static void newFile() {
        String nameOfFile = JOptionPane.showInputDialog("Please Enter The name of file!");
        System.out.println("New file!!!");
        File file = new File(currentFile.getAbsolutePath() + "\\" + nameOfFile);
        System.out.println(currentFile);
        try {
            file.createNewFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        showtable();
    }

    /**
     * this method is for create new  folder in file manager!
     */
    private static void newFolder() {

        String nameOfFolder = JOptionPane.showInputDialog("Please Enter The name of folder!");
        System.out.println("New folder!!!");

        String string = currentFile.getAbsolutePath() + "\\" + nameOfFolder;
        new File(string).mkdir();

        Path path = Paths.get(string);
        try {
            Files.createDirectories(path);

        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        showtable();
    }

    /**
     * this method is for operation in j tree.
     *
     * @param childrenFile is an instance of file
     * @param rootNode     its an instance of DefaultMutableTreeNode
     * @throws IOException this method may throw exception
     */
    private static void buildTree(File childrenFile, DefaultMutableTreeNode rootNode) throws IOException {
        System.out.println(childrenFile.getPath().substring(0, childrenFile.getPath().length() - 2));
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(childrenFile.getPath().substring(0, childrenFile.getPath().length() - 2));
        rootNode.add(node);

        if (childrenFile.isFile()) {
            Desktop.getDesktop().open(childrenFile);
            return;
        }

        children = childrenFile.listFiles();
        if (children == null)
            return;

        for (File child : children) {
            buildTree(child, node);
        }
    }

    /**
     * this method is for design the setting panel!
     */
    private static void settingPanel() {
        JFrame j1 = new JFrame();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();
        JTextField jTextField4 = new JTextField();
        JComboBox jComboBox1 = new JComboBox<>();
        JCheckBox jCheckBox1 = new JCheckBox();
        JComboBox jComboBox2 = new JComboBox<>();
        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();
        jLabel1.setText("Main Address");
        jLabel2.setText("Location of Storing");
        jLabel3.setText("Address...");
        jLabel4.setText("Port of Destination computer!");
        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Nimbus", "Windows", "Metal", "Windows Classic", "CDE/Motif"}));
        jCheckBox1.setText("Which?");
        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Every 5 minutes", "Every 10 minutes", "Every 30 minutes", "Every 60 minutes", "never"}));
        jButton1.setText("OK");
        jButton2.setText("Apply");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(j1.getContentPane());
        j1.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(54, 54, 54).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE).addComponent(jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(jTextField4, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addComponent(jTextField1)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabel2).addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)).addComponent(jTextField3))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(92, 92, 92).addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(69, 69, 69).addComponent(jCheckBox1)).addGroup(layout.createSequentialGroup().addGap(175, 175, 175).addComponent(jButton2))).addGap(15, 15, 15).addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 35, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2).addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(29, 29, 29).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jLabel4).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(38, 38, 38).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(jCheckBox1).addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(57, 57, 57).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jButton2)).addContainerGap(64, Short.MAX_VALUE)));
        j1.pack();
        j1.setVisible(true);
    }

    /**
     * this method is for showing file(with file chooser)
     */
    private static void showFile() {
        JFrame j1 = new JFrame();
        JFileChooser jFileChooser1 = new JFileChooser();
        GroupLayout layout = new GroupLayout(j1.getContentPane());
        j1.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 111, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 22, Short.MAX_VALUE)));
        j1.pack();
        j1.setResizable(false);
        j1.setVisible(true);
    }

    /**
     * this method is for designing about page!
     */
    private static void about() {
        JTextField lbl1 = new JTextField("this program is for AP course in fall 98.\n");
        JTextField lbl2 = new JTextField(" it's author is Mohammadhossein Nezhadhendi\n ");
        JTextField lbl3 = new JTextField(" good luck");
        JTextField lbl4 = new JTextField("shg");
        lbl1.setEditable(false);
        lbl2.setEditable(false);
        lbl3.setEditable(false);
        lbl4.setEditable(false);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        p1.add(lbl1);
        p2.add(lbl2);
        p3.add(lbl3);
        p4.add(lbl4);
        p5.add(p1);
        p5.add(p2);
        p5.add(p3);
        p5.add(p4);
        aboutFrame.add(p5);
        aboutFrame.setSize(400, 300);
        aboutFrame.setBackground(Color.MAGENTA);
        aboutFrame.setVisible(true);
    }

    /**
     * this method is for designing guide page!
     */
    private static void showGuidePage() {
        JFrame guideFrame = new JFrame("Guide for this Application");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JTextField f1 = new JTextField("you can go to my git hub and show more:\n");
        JTextField f2 = new JTextField("github.com/shg1998");
        JTextField f3 = new JTextField("For the search, you must enter the file or folder name you want and Then by tapping on the files in tree that be in left side of panel , find it ");
        f1.setEditable(false);
        f2.setEditable(false);
        f3.setEditable(false);
        f1.setBackground(Color.BLUE);
        f2.setBackground(Color.cyan);
        f2.setBackground(Color.LIGHT_GRAY);
        p1.add(f1);
        p2.add(f2);
        p4.add(f3);
        p3.add(p1);
        p3.add(p2);
        p3.add(p4);
        guideFrame.add(p3);
        guideFrame.setSize(900, 200);
        guideFrame.setBackground(Color.gray);
        guideFrame.setVisible(true);
    }

    /**
     * this method is for backward!
     *
     * @param evt is an instance of mouse event
     */
    private static void btn1MouseClicked(MouseEvent evt) {
        currentFile = currentFile.getParentFile();
        showtable();
    }

    /**
     * this method is for system tray
     */
    private static void systemTrayDemo() {
        SystemTray tray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("Icons/shg-squarelogo-1450871532370.png");
        PopupMenu me = new PopupMenu();
        MenuItem messageItem = new MenuItem("Show Message");
        messageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to my app!!!I am shg1998");

            }
        });
        me.add(messageItem);
        MenuItem show = new MenuItem("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileManager();
            }
        });
        me.add(show);
        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        me.add(closeItem);
        TrayIcon icon = new TrayIcon(image, "SystemTray Demo", me);
        icon.setImageAutoSize(true);
        try {
            tray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method is for set and fill of jtable!
     *
     * @param file is an instance of file class
     * @return the return type of this method is AbstractModel
     */
    public static AbstractTableModel salam(File file) {
        File[] files = file.listFiles();
        AbstractTableModel tableModel = new AbstractTableModel() {
            String[] column = {
                    "icon",
                    "name",
                    "type",
                    "size",
                    "lastModified"
            };

            @Override
            public int getRowCount() {
                return files.length;
            }

            @Override
            public int getColumnCount() {
                return column.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return FileSystemView.getFileSystemView().getSystemIcon(files[rowIndex]);
                    case 1:
                        return FileSystemView.getFileSystemView().getSystemDisplayName(files[rowIndex]);
                    case 2:
                        return files[rowIndex].getPath();
                    case 3:
                        return files[rowIndex].length();
                    case 4:
                        return files[rowIndex].lastModified();
                    case 5:
                        return files[rowIndex].getName().split(".", 3);
                    default:
                        System.err.println("Logic error!");
                }
                return "";
            }

            /**
             * this method is for set and fill of table!
             * @param column is the column of the table
             * @return the return type of this method is Class<?>
             */
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImageIcon.class;
                    case 3:
                        return Long.class;
                    case 4:
                        return Date.class;
                }
                return String.class;
            }

            @Override
            public String getColumnName(int index) {
                return column[index];
            }

        };
        return tableModel;
    }
}

