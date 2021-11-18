package com.taras.main;
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



public class Excelb extends JFrame {
    FileInputStream input = null;
    FileOutputStream output = null;
    static int row_count=25, col_count=26;
    private DefaultTableModel defaultTableModel = new DefaultTableModel(row_count, col_count){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable table = new JTable(defaultTableModel);


    private JButton addcol = new JButton("Add collum");
    private JButton addrow = new JButton("Add row");
    private JButton remrow = new JButton("Remove row");
    private JButton remcol = new JButton("Remove collum");
    private JButton calculate = new JButton("Calculate");
    private JButton close = new JButton("Close");


    private JButton save = new JButton("Save");
    private JButton load = new JButton("Load");
    private JButton tableButton = new JButton("Table");
    private JButton menuButton = new JButton("Menu");
    private JButton help = new JButton("Help");

    private JScrollPane tableScreen = new JScrollPane(table);

    private JTextField formulaField = new JTextField();
    private String[][] formulas=new String[row_count+1][col_count+1];



    public Excelb() {

        super("Table");

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(false);

        MouseListener tableMouseListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                formulaField.setText(formulas[col][row]);
            }
        };


        addrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (row_count<25){
                    defaultTableModel.addRow(new Vector<>());
                    row_count++;}
                else
                    JOptionPane.showMessageDialog(null, "You have reached max number of rows");
            }
        });


        remrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(row_count==0))
                    defaultTableModel.removeRow(--row_count);
                else
                    JOptionPane.showMessageDialog(null, "You have no rows");

            }
        });


        addcol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (col_count<26){
                    String header=String.valueOf ((char)(65+col_count));
                    defaultTableModel.addColumn(header);
                    col_count++;}
                else
                    JOptionPane.showMessageDialog(null, "You reached max number of collums");
            }
        });


        remcol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(col_count==0)){
                    TableColumn col = table.getColumnModel().getColumn(col_count-1);
                    table.removeColumn(col);
                    defaultTableModel.setColumnCount(--col_count);}
                else
                    JOptionPane.showMessageDialog(null, "You have no collums");
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
                    formulas[col][row]= formulaField.getText();
                    for (int i = 0; i < row_count; i++) {
                        for (int j = 0; j < col_count; j++) {
                            try {
                                String cell=formulas[j][i];
                                defaultTableModel.setValueAt(Evaluate.evaluate((Evaluate.transform(cell, defaultTableModel))), i, j);
                            }catch(Exception ex){
                                defaultTableModel.setValueAt(0.0, i, j);
                            }}}}}});


        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Box menuContents = new Box(BoxLayout.X_AXIS);
                        menuContents.add(tableButton);
                        menuContents.add(save);
                        menuContents.add(load);
                        menuContents.add(help);
                        menuContents.add(close);
                        setContentPane(menuContents);
                        setSize(350, 200);
                        setVisible(true);
                    }
                });

                Box tableContents = new Box(BoxLayout.Y_AXIS);

                tableContents.add(tableScreen);
                tableContents.add(formulaField);
                tableContents.add(addrow);
                tableContents.add(addcol);
                tableContents.add(remrow);
                tableContents.add(remcol);
                tableContents.add(calculate);
                tableContents.add(save);
                tableContents.add(load);
                tableContents.add(menuButton);
                tableContents.add(close);
                setContentPane(tableContents);
                setSize(1484, 730);
                setVisible(true);
            }
        });


        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter file name");
                    File file = new File(fileName);

                    if(file.exists()){
                        input = new FileInputStream(file);
                        BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));
                        int rows = Integer.parseInt(buffReader.readLine());
                        int columns = Integer.parseInt(buffReader.readLine());

                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                formulas[i][j] = "0.0";
                            }
                        }

                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                formulas[i][j] = buffReader.readLine();
                                if(formulas[i][j]==null)
                                    formulas[i][j]="0.0";
                            }
                        }

                        for (int k = 0; k < 2; k++) {
                            for (int i = 0; i < rows ; i++) {
                                for (int j = 0; j < columns; j++) {
                                    String cell=formulas[j][i];
                                    try {
                                        defaultTableModel.setValueAt(Evaluate.evaluate((Evaluate.transform(cell, defaultTableModel))), i, j);
                                    }catch(Exception ex){
                                        defaultTableModel.setValueAt(0.0, i, j);
                                    }
                                }
                            }
                        }
                    }
                    else throw new Exception();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Not existing file");
                }
            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter file name(must end with \".txt\"):");
                    String temp = fileName.substring(fileName.length() - 4);

                    if (temp.equals(".txt")){
                        temp = "";
                        output = new FileOutputStream(fileName);
                        BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(output));
                        buffWriter.write(String.valueOf(defaultTableModel.getRowCount()));
                        buffWriter.newLine();
                        buffWriter.write(String.valueOf(defaultTableModel.getColumnCount()));
                        buffWriter.newLine();

                        for (int i = 0; i < defaultTableModel.getRowCount() ; i++) {
                            for (int j = 0; j < defaultTableModel.getColumnCount(); j++) {
                                buffWriter.write(String.valueOf(formulas[i][j]));
                                buffWriter.newLine();
                            }
                        }

                        buffWriter.close();}

                    else throw new Exception();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Error: wrong name");
                }
            }
        });


        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "Created by a K25 student: Taras Korolchuk. Variant N4\n \nOperations examples:\n\n" +
                        "Binar opperations:            1+1      4-1      5*6    48/4\n\n" +
                        "Unar operations:               +1                        -1   \n\n" +
                        "mod/div opperations:        5mod3                  78div6    \n\n" +
                        "max/min for n operands:    nmax(4,8,-1,50,-90)        nmin(5,9,20,7)\n\n" +
                        "Binar opperations:            1+1      4-1      5*6    48/4\n\n" +
                        "Link to the cell:                    #C4 \n\n";

                JOptionPane.showMessageDialog(null, temp);
            }
        });

        setSize(350, 200);

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

        tableScreen.setRowHeaderView(rowHeader);
        getContentPane().add(tableScreen, BorderLayout.CENTER);


        table.addMouseListener(tableMouseListener);
        Box startMenuContents = new Box(BoxLayout.X_AXIS);


        startMenuContents.add(tableButton);
        startMenuContents.add(load);
        startMenuContents.add(help);
        startMenuContents.add(close);

        setContentPane(startMenuContents);

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});
    }


    public static void main(String[] args) {
        new Excelb();
    }
}