package com.taras.app;
import com.taras.main.GrammarLexer;
import com.taras.main.GrammarParser;
import com.taras.main.ThrowingErrorListener;
import com.taras.main.VisitorClass;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


class RowHeaderRenderer extends JLabel implements ListCellRenderer {

    RowHeaderRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}



public class App extends JFrame {
    FileInputStream in = null;
    FileOutputStream out = null;
    static int row_count=14, col_count=12;
    private DefaultTableModel dm = new DefaultTableModel(row_count, col_count){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable table = new JTable(dm);


    private JButton addcol = new JButton("Add collum");
    private JButton addrow = new JButton("Add row");
    private JButton remrow = new JButton("Remove row");
    private  JButton remcol = new JButton("Remove collum");
    private JButton calculate = new JButton("Calculate");
    private  JButton close = new JButton("Close");


    private JButton save;
    private JButton load;
    private JButton tableButton;
    private JButton menuButton;
    private JButton help;

    private JScrollPane scroll = new JScrollPane(table);

    private JTextField formula= new JTextField();
    private String[][] formulas=new String[25][25];



    public App() {

        super("Table");

       table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       table.setRowSelectionAllowed(false);

        MouseListener tableMouseListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                formula.setText(formulas[col][row]);
            }
        };


        addrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm.addRow(new Vector<>());
                row_count++;
            }
        });


        remrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm.removeRow(--row_count);
            }
        });


        addcol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String header=String.valueOf ((char)(65+col_count));
                dm.addColumn(header);
                col_count++;
            }
        });


        remcol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumn col = table.getColumnModel().getColumn(col_count-1);
                table.removeColumn(col);
                dm.setColumnCount(--col_count);
            }
        });



        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int l=0; l<2; l++){
                var col=table.getSelectedColumn();
                var row=table.getSelectedRow();
                formulas[col][row]=formula.getText();
                for (int i = 0; i < row_count; i++) {
                     for (int j = 0; j < col_count; j++) {
                         try {
                               String cell=formulas[j][i];
                               dm.setValueAt(Evaluate.evaluate((Evaluate.transform(cell, dm))), i, j);
                           }catch(Exception ex){
                               dm.setValueAt(0.0, i, j);
                           }}}}}});




        tableButton = new JButton("Table");
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuButton = new JButton("Menu");
                menuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Box menuContents = new Box(BoxLayout.Y_AXIS);
                        menuContents.add(tableButton);
                        menuContents.add(save);
                        menuContents.add(load);
                        menuContents.add(help);
                        menuContents.add(close);
                        setContentPane(menuContents);
                        setSize(150, 200);
                        setVisible(true);
                    }
                });

                Box tableContents = new Box(BoxLayout.Y_AXIS);

                tableContents.add(scroll);
                tableContents.add(formula);
                tableContents.add(addrow);
                tableContents.add(addcol);
                tableContents.add(remrow);
                tableContents.add(remcol);
                tableContents.add(calculate);
                tableContents.add(menuButton);
                tableContents.add(close);
                setContentPane(tableContents);
                setSize(1000, 500);
                setVisible(true);
            }
        });

        load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter file name");
                    File file = new File(fileName);
                    if(file.exists()){
                        in = new FileInputStream(file);
                        BufferedReader bi = new BufferedReader(new InputStreamReader(in));
                       int rows = Integer.parseInt(bi.readLine());
                       int columns = Integer.parseInt(bi.readLine());
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                formulas[i][j] = "0.0";
                            }
                        }
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                formulas[i][j] = bi.readLine();
                                if(formulas[i][j]==null)
                                    formulas[i][j]="0.0";
                            }
                        }
                        for (int k = 0; k < 2; k++) {
                        for (int i = 0; i < rows ; i++) {
                            for (int j = 0; j < columns; j++) {
                                String cell=formulas[j][i];
                                try {
                                    dm.setValueAt(Evaluate.evaluate((Evaluate.transform(cell, dm))), i, j);
                                }catch(Exception ex){
                                    dm.setValueAt(0.0, i, j);
                                }
                            }
                        }}
                    }
                    else throw new Exception();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"ERROR: WRONG FILE NAME");
                }
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter file name");
                    String temp = fileName.substring(fileName.length() - 4);
                    if (temp.equals(".txt")){
                        temp = "";
                        out = new FileOutputStream(fileName);
                        BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(out));
                        bo.write(String.valueOf(dm.getRowCount()));
                        bo.newLine();
                        bo.write(String.valueOf(dm.getColumnCount()));
                        bo.newLine();
                        for (int i = 0; i < dm.getRowCount() ; i++) {
                            for (int j = 0; j < dm.getColumnCount(); j++) {
                                bo.write(String.valueOf(formulas[i][j]));
                                bo.newLine();
                            }
                        }
                        bo.close();
                    }
                    else throw new Exception();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"ERROR: WRONG FILE NAME");
                }
            }
        });
        help = new JButton("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "Created by a K25 student: Taras Korolchuk. Variant N4\n \n";
                temp += "Operations examples:\n";
                temp += "Binar opperations:            1+1      4-1      5*6    48/4\n\n";
                temp += "Unar operations:               +1                        -1   \n\n";
                temp += "mod/div opperations:        5mod3                  78div6    \n\n";
                temp += "max/min for n operands:    nmax(4,8,-1,50,-90)        nmin(5,9,20,7)\n\n";
                temp += "Link to the cell:                    #C4 \n\n";
                JOptionPane.showMessageDialog(null, temp);
            }
        });


        setSize(100, 200);

        ListModel lm = new AbstractListModel() {
            String headers[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
                    , "17", "18", "19", "20", "21", "22", "23", "24", "25"};

            public int getSize() {
                return headers.length;
            }

            public Object getElementAt(int index) {
                return headers[index];
            }
        };
        JList rowHeader = new JList(lm);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(table));

        scroll.setRowHeaderView(rowHeader);
        getContentPane().add(scroll, BorderLayout.CENTER);


        table.addMouseListener(tableMouseListener);
        Box contents = new Box(BoxLayout.Y_AXIS);


        contents.add(tableButton);
        contents.add(save);
        contents.add(load);
        contents.add(help);
        contents.add(close);



        setContentPane(contents);

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});
    }


    public static void main(String[] args) {
        new App();
    }
}