package castsys;
import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;

public class start extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    private JTextField CategoryNameInput;
    private JTextField StockInput;
    private JTable GarageTable;
    private JTextField FixedPriceInput;
    private JTextField LastNameInput;
    private JTextField EmailInput;
    private JTextField FirstNameInput;
    private JTable TransactionTable;
    private JTextField StudentIDInput;
    private JTextField SetTicketInput;
    private JLabel lblSoldStatus;
    private JProgressBar soldProgressBar;
    private JProgressBar ticketProgressBar;
    private JProgressBar incomeBar;
    private JLabel lblIncomePercent;
    private JLabel lblIncomeText;
    private JProgressBar ticketBar;
    private JLabel lblTicketPercent;
    private JLabel lblTicketDetails;
    private JLabel lblTicketStatus;
    private JTable TicketTable;
    private JPanel grid100;
    private JPanel grid80;
    private int incomeGoal = 100;
    
    JComboBox TransactionCategoryComboBox; 
    JComboBox TransactionTableNumberComboBox;

    public static void main(String[] args) {
    	
    	Connection con = DBcon.getConnection();
    	
        EventQueue.invokeLater(() -> {
            try {
                start frame = new start();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public start() {
    	Connection con = DBcon.getConnection();
    	
        setResizable(false);
        setTitle("CAST-SYS");
        setIconImage(new ImageIcon("logocast.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1030, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(185, 11, 41));
        sidebar.setBounds(0, 0, 188, 561);
        contentPane.add(sidebar);

        JLabel title = new JLabel("CAST-SYS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 21));
        title.setBounds(21, 54, 139, 40);
        sidebar.add(title);

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(10, 149, 173, 34);
        styleNavButton(btnDashboard, true);
        sidebar.add(btnDashboard);

        JButton btnGarage = new JButton("Garage");
        btnGarage.setBounds(10, 214, 173, 34);
        styleNavButton(btnGarage, false);
        sidebar.add(btnGarage);

        JButton btnTickets = new JButton("Tickets");
        btnTickets.setBounds(10, 279, 173, 34);
        styleNavButton(btnTickets, false);
        sidebar.add(btnTickets);

        // ================= WHOLE CONTENT =================
        contentPanel = new JPanel();
        contentPanel.setBounds(188, 0, 826, 561);
        contentLayout = new CardLayout();
        contentPanel.setLayout(contentLayout);
        contentPane.add(contentPanel);

        // ===== DASSSSHHHHBOAAARRRRRRDDDDD =====
        JPanel dashboardContent = new JPanel(null);

        JLabel dashLabel = new JLabel("Dashboard");
        dashLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        dashLabel.setBounds(20, 11, 400, 40);
        dashboardContent.add(dashLabel);

        contentPanel.add(dashboardContent, "DASHBOARD");

        // ===== INCOME =====
        JPanel incomePanel = new JPanel();
        incomePanel.setLayout(null);
        incomePanel.setBounds(20, 60, 360, 145);
        incomePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dashboardContent.add(incomePanel);

        JLabel lblIncome = new JLabel("INCOME");
        lblIncome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblIncome.setBounds(20, 10, 200, 20);
        incomePanel.add(lblIncome);
        
        JButton btnEditGoal = new JButton("Edit Goal");
        btnEditGoal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnEditGoal.setBounds(20, 105, 100, 25);
        incomePanel.add(btnEditGoal);

        btnEditGoal.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "Enter new income goal:", incomeGoal);
            if (input != null && !input.trim().isEmpty()) {
                try {
                    incomeGoal = Integer.parseInt(input.trim());
                    Connection con2 = DBcon.getConnection();
                    PreparedStatement ps = con2.prepareStatement(
                        "UPDATE settings SET income_goal = ? WHERE id = 1");
                    ps.setInt(1, incomeGoal);
                    ps.executeUpdate();
                    loadDashboardData(incomeBar, lblIncomePercent, lblIncomeText,
                        ticketBar, lblTicketPercent, lblTicketDetails);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        incomeBar = new JProgressBar();
        incomeBar.setBounds(20, 50, 220, 20);
        incomeBar.setStringPainted(true);
        incomePanel.add(incomeBar);

        JLabel lblIncomePercent = new JLabel("0%");
        lblIncomePercent.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblIncomePercent.setBounds(260, 40, 80, 40);
        incomePanel.add(lblIncomePercent);

        JLabel lblIncomeText = new JLabel("0 / 0");
        lblIncomeText.setBounds(20, 75, 200, 20);
        incomePanel.add(lblIncomeText);


        // ===== TICKETS DASHY=====
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(null);
        ticketPanel.setBounds(20, 230, 360, 150);
        ticketPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dashboardContent.add(ticketPanel);
        
        
        JPanel panel100 = new JPanel();
        panel100.setLayout(null);
        panel100.setBounds(420, 60, 360, 160);
        panel100.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dashboardContent.add(panel100);

        JLabel lbl100 = new JLabel("100 Pesos Worth");
        lbl100.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl100.setBounds(20, 10, 200, 20);
        panel100.add(lbl100);
        
        JLabel dot100Sold = new JLabel("● Sold");
        dot100Sold.setForeground(new Color(0, 102, 204));
        dot100Sold.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dot100Sold.setBounds(170, 10, 60, 20);
        panel100.add(dot100Sold);

        JLabel dot100Rem = new JLabel("● Remaining");
        dot100Rem.setForeground(new Color(204, 0, 0));
        dot100Rem.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dot100Rem.setBounds(235, 10, 90, 20);
        panel100.add(dot100Rem);

        grid100 = new JPanel(new GridLayout(2, 2, 5, 5));
        grid100.setBounds(10, 38, 340,90);
        grid100.setOpaque(false);
        panel100.add(grid100);

        // ===== 80 PESOS PANEL =====
        JPanel panel80 = new JPanel();
        panel80.setLayout(null);
        panel80.setBounds(420, 230, 360, 150);
        panel80.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dashboardContent.add(panel80);

        JLabel lbl80 = new JLabel("80 Pesos Worth");
        lbl80.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl80.setBounds(20, 10, 200, 20);
        panel80.add(lbl80);
        
        JLabel dot80Sold = new JLabel("● Sold");
        dot80Sold.setForeground(new Color(230, 130, 0));
        dot80Sold.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dot80Sold.setBounds(170, 10, 60, 20);
        panel80.add(dot80Sold);

        JLabel dot80Rem = new JLabel("● Remaining");
        dot80Rem.setForeground(new Color(220, 180, 0));
        dot80Rem.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dot80Rem.setBounds(235, 10, 90, 20);
        panel80.add(dot80Rem);
        
        grid80 = new JPanel(new GridLayout(2, 2, 5, 5));
        grid80.setBounds(10, 55, 340, 90);
        grid80.setOpaque(false);
        panel80.add(grid80);

     // 80 pesos grid
        grid80 = new JPanel(new GridLayout(2, 2, 5, 5));
        grid80.setBounds(10, 35, 340, 100);
        grid80.setOpaque(false);
        panel80.add(grid80);
        
        JButton btnGenerateDashboardReport = new JButton("Generate Report");
        btnGenerateDashboardReport.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGenerateDashboardReport.setBounds(300, 400, 180, 35);
        dashboardContent.add(btnGenerateDashboardReport);
        
        

        JLabel lblTickets = new JLabel("TICKETS");
        lblTickets.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTickets.setBounds(20, 10, 200, 20);
        ticketPanel.add(lblTickets);

        ticketBar = new JProgressBar();
        ticketBar.setBounds(20, 80, 220, 20);
        ticketBar.setStringPainted(true);
        ticketBar.setForeground(new Color(0, 180, 0));
        ticketPanel.add(ticketBar);

        JLabel lblTicketPercent = new JLabel("0%");
        lblTicketPercent.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTicketPercent.setBounds(260, 70, 80, 40);
        ticketPanel.add(lblTicketPercent);

        JLabel lblTicketDetails = new JLabel("Sold: 0   Remaining: 0   Total: 0");
        lblTicketDetails.setBounds(20, 40, 300, 20);
        ticketPanel.add(lblTicketDetails);

        // ===== LOAD DATA =====
        loadDashboardData(	
            incomeBar, lblIncomePercent, lblIncomeText,
            ticketBar, lblTicketPercent, lblTicketDetails
        
        		);
        if(con != null)
        {
        	JLabel dbcheck = new JLabel("Database is Connected");
        	dbcheck.setFont(new Font("Segoe UI", Font.BOLD, 12));
        	dbcheck.setBounds(646, 11, 136, 40);
        	dashboardContent.add(dbcheck);
        }
        else
        {
        	JLabel dbcheck = new JLabel("Database is NOT Connected");
            dbcheck.setFont(new Font("Segoe UI", Font.BOLD, 12));
            dbcheck.setBounds(646, 11, 136, 40);
            dashboardContent.add(dbcheck);
        }

        // ===== GARAGGGEEEEEEEEEEE =====
        JPanel garageContent = new JPanel(null);
        JLabel garageLabel = new JLabel("Garage Management");
        garageLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        garageLabel.setBounds(21, 11, 400, 40);
        garageContent.add(garageLabel);
        contentPanel.add(garageContent, "GARAGE");
        
        JLabel lblTables = new JLabel("Tables");
        lblTables.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTables.setBounds(21, 60, 73, 40);
        garageContent.add(lblTables);
        
        JLabel lblTablesNum = new JLabel("Table Number:");
        lblTablesNum.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTablesNum.setBounds(21, 90, 83, 31);
        garageContent.add(lblTablesNum);
        
        JLabel lblCategoryName = new JLabel("Category Name:");
        lblCategoryName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCategoryName.setBounds(21, 115, 90, 31);
        garageContent.add(lblCategoryName);
        
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStock.setBounds(21, 165, 38, 31);
        garageContent.add(lblStock);
        
        CategoryNameInput = new JTextField();
        CategoryNameInput.setColumns(10);
        CategoryNameInput.setBounds(114, 120, 55, 20);
        garageContent.add(CategoryNameInput);
        
        StockInput = new JTextField();
        StockInput.setColumns(10);
        StockInput.setBounds(114, 170, 54, 20);
        garageContent.add(StockInput);
        
        JLabel lblFixedPrice = new JLabel("Table Fixed Price:");
        lblFixedPrice.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFixedPrice.setBounds(21, 140, 90, 31);
        garageContent.add(lblFixedPrice);
        
        FixedPriceInput = new JTextField();
        FixedPriceInput.setColumns(10);
        FixedPriceInput.setBounds(114, 145, 55, 20);
        garageContent.add(FixedPriceInput);
        
        JLabel lblDiscountRate = new JLabel("Table Discount:");
        lblDiscountRate.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDiscountRate.setBounds(21, 190, 90, 31);
        garageContent.add(lblDiscountRate);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"0%", "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"}));
        comboBox.setEditable(true);
        comboBox.setBounds(114, 195, 55, 22);
        garageContent.add(comboBox);
        
        JScrollPane TableScroll = new JScrollPane();
        TableScroll.setBounds(233, 62, 555, 204);
        garageContent.add(TableScroll);
        
        GarageTable = new JTable();
        GarageTable.setDefaultEditor(Object.class, null);
        GarageTable.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
        TableScroll.setViewportView(GarageTable);
        GarageTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Table Number", "Category Name", "Fixed Price", "Discount Rate", "Stock"
        	}
        ));
        GarageTable.getColumnModel().getColumn(0).setPreferredWidth(96);
        GarageTable.getColumnModel().getColumn(1).setPreferredWidth(91);
        GarageTable.getColumnModel().getColumn(3).setPreferredWidth(91);
        GarageTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        loadGarageData();
        
        JComboBox comboBoxTableNumber = new JComboBox();
        comboBoxTableNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        comboBoxTableNumber.setEditable(true);
        comboBoxTableNumber.setBounds(114, 95, 55, 22);
        garageContent.add(comboBoxTableNumber);
        
        JLabel lblall = new JLabel("(all)");
        lblall.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblall.setBounds(171, 190, 20, 31);
        garageContent.add(lblall);
        
        //============== UPDATE BUTTONNNNNNN ============
        JButton AddButton = new JButton("Update");
        AddButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        AddButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Object selectedTable = comboBoxTableNumber.getSelectedItem();
                if (selectedTable == null) {
                    JOptionPane.showMessageDialog(null, "Please select a Table Number first.");
                    return;
                }
                
                String tableIdStr = selectedTable.toString();
                String categoryName = CategoryNameInput.getText().trim();
                String fixedPriceStr = FixedPriceInput.getText().trim();
                String stockStr = StockInput.getText().trim();
                
                String discountRaw = comboBox.getSelectedItem().toString().replace("%", "");
                double discountRate = Double.parseDouble(discountRaw) / 100.0;

                Connection con = DBcon.getConnection();
                if (con != null) {
                    try {
                        con.setAutoCommit(false);
                        String sqlGarage = "INSERT INTO garagetable (TableId, FixedPrice, DiscountRate) " +
                                           "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE " +
                                           "FixedPrice = IF(VALUES(FixedPrice) = 0, FixedPrice, VALUES(FixedPrice)), " +
                                           "DiscountRate = VALUES(DiscountRate)";
                        
                        try (java.sql.PreparedStatement psGarage = con.prepareStatement(sqlGarage)) {
                            psGarage.setInt(1, Integer.parseInt(tableIdStr));
                          
                            psGarage.setInt(2, fixedPriceStr.isEmpty() ? 0 : Integer.parseInt(fixedPriceStr));
                            psGarage.setDouble(3, discountRate);
                            psGarage.executeUpdate();
                        }

                        if (!categoryName.isEmpty()) {
                            String sqlCategory = "INSERT INTO categories (TableId, CategoryName, Stock) " +
                                                 "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE " +
                                                 "Stock = VALUES(Stock)";
                            
                            try (java.sql.PreparedStatement psCat = con.prepareStatement(sqlCategory)) {
                                psCat.setInt(1, Integer.parseInt(tableIdStr));
                                psCat.setString(2, categoryName);
                                psCat.setInt(3, stockStr.isEmpty() ? 0 : Integer.parseInt(stockStr));
                                psCat.executeUpdate();
                            }
                        }

                        con.commit();
                        JOptionPane.showMessageDialog(null, "Table " + tableIdStr + " settings updated.");
                        loadGarageData(); 
                        
                        if (TransactionTableNumberComboBox != null) { 
                            Object selectedTransTable = TransactionTableNumberComboBox.getSelectedItem();
                            if (selectedTransTable != null) {
                                updateTransactionCategories(selectedTransTable.toString(), TransactionCategoryComboBox);
                            }
                        }

                    } catch (Exception ex) {
                        try { con.rollback(); } catch (Exception rex) {}
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        AddButton.setBounds(60, 226, 73, 23);
        garageContent.add(AddButton);
        
        JButton btnDeleteGarage = new JButton("Delete Selected");
        btnDeleteGarage.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnDeleteGarage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		removeSelectedRow();
        		if (TransactionTableNumberComboBox != null) { 
        		    Object selectedTransTable = TransactionTableNumberComboBox.getSelectedItem();
        		    if (selectedTransTable != null) {
        		        updateTransactionCategories(selectedTransTable.toString(), TransactionCategoryComboBox);
        		    }
        		}
        	}
        });
        btnDeleteGarage.setBounds(630, 264, 158, 23);
        garageContent.add(btnDeleteGarage);
        
        
        //============ TRANSACTION ============
        JLabel lblTransactions = new JLabel("Transactions\r\n");
        lblTransactions.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTransactions.setBounds(21, 276, 185, 40);
        garageContent.add(lblTransactions);
        
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFirstName.setBounds(21, 327, 83, 31);
        garageContent.add(lblFirstName);
        
        LastNameInput = new JTextField();
        LastNameInput.setColumns(10);
        LastNameInput.setBounds(114, 357, 77, 20);
        garageContent.add(LastNameInput);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblLastName.setBounds(21, 352, 90, 31);
        garageContent.add(lblLastName);
        
        EmailInput = new JTextField();
        EmailInput.setColumns(10);
        EmailInput.setBounds(114, 382, 77, 20);
        garageContent.add(EmailInput);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEmail.setBounds(21, 377, 90, 31);
        garageContent.add(lblEmail);
        
        JLabel lblTableNumberTransactions = new JLabel("Table Number:");
        lblTableNumberTransactions.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTableNumberTransactions.setBounds(21, 402, 83, 31);
        garageContent.add(lblTableNumberTransactions);
        
        JLabel lblDiscountRate_1 = new JLabel("Category:");
        lblDiscountRate_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDiscountRate_1.setBounds(21, 427, 90, 31);
        garageContent.add(lblDiscountRate_1);
        
        TransactionCategoryComboBox = new JComboBox();
        TransactionCategoryComboBox.setEditable(true);
        TransactionCategoryComboBox.setBounds(114, 432, 55, 22);
        garageContent.add(TransactionCategoryComboBox);
        
        FirstNameInput = new JTextField();
        FirstNameInput.setColumns(10);
        FirstNameInput.setBounds(114, 333, 77, 20);
        garageContent.add(FirstNameInput);
        
        TransactionTableNumberComboBox = new JComboBox();
        TransactionTableNumberComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        TransactionTableNumberComboBox.setEditable(true);
        TransactionTableNumberComboBox.setBounds(114, 407, 55, 22);
        garageContent.add(TransactionTableNumberComboBox);
        
        TransactionTableNumberComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object selected = TransactionTableNumberComboBox.getSelectedItem();
                if (selected != null) {
                    updateTransactionCategories(selected.toString(), TransactionCategoryComboBox);
                }
            }
        });

        updateTransactionCategories("1", TransactionCategoryComboBox);
        
        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblQuantity.setBounds(21, 452, 90, 31);
        garageContent.add(lblQuantity);
        
        JComboBox TransactionQuantity = new JComboBox();
        TransactionQuantity.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
        TransactionQuantity.setEditable(true);
        TransactionQuantity.setBounds(114, 457, 55, 22);
        garageContent.add(TransactionQuantity);
        
        JLabel lblTicketQuantity = new JLabel("Ticket Quantity:");
        lblTicketQuantity.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTicketQuantity.setBounds(21, 477, 90, 31);
        garageContent.add(lblTicketQuantity);
        
        JComboBox TicketQuantity = new JComboBox();
        TicketQuantity.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
        TicketQuantity.setEditable(true);
        TicketQuantity.setBounds(114, 482, 55, 22);
        garageContent.add(TicketQuantity);
        
        JScrollPane TransactionScroll = new JScrollPane();
        TransactionScroll.setBounds(233, 294, 555, 204);
        garageContent.add(TransactionScroll);
        
        TransactionTable = new JTable();
        TransactionTable.setDefaultEditor(Object.class, null);
        TransactionTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        			"TransactionID", "StudentID", "TableID", "Category Name", "Quantity", "Total", "Tickets"
        	}
        ));
        TransactionTable.getColumnModel().getColumn(0).setPreferredWidth(85);
        TransactionTable.getColumnModel().getColumn(3).setPreferredWidth(93);
        TransactionTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TransactionTable.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
        TransactionScroll.setViewportView(TransactionTable);
        
        JLabel lblStudentID = new JLabel("Student ID:");
        lblStudentID.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStudentID.setBounds(21, 302, 83, 31);
        garageContent.add(lblStudentID);
        
        StudentIDInput = new JTextField();
        StudentIDInput.setColumns(10);
        StudentIDInput.setBounds(114, 308, 77, 20);
        garageContent.add(StudentIDInput);
        
        JButton TransactionAdd = new JButton("Add\r\n");
        TransactionAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sId = StudentIDInput.getText().trim();
                String fName = FirstNameInput.getText().trim();
                String lName = LastNameInput.getText().trim();
                String email = EmailInput.getText().trim();
                String tId = TransactionTableNumberComboBox.getSelectedItem().toString();
                String catName = TransactionCategoryComboBox.getSelectedItem().toString();
                int ticketQty = Integer.parseInt(TicketQuantity.getSelectedItem().toString());
                int requestedQty = Integer.parseInt(TransactionQuantity.getSelectedItem().toString());

                if (sId.isEmpty() || fName.isEmpty() || catName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
                    return;
                }

                Connection con = DBcon.getConnection();
                if (con != null) {
                    try {
                        con.setAutoCommit(false); // START TRANSACTION

                        if (ticketQty > 0) {
                            String sqlCheckTickets = "SELECT COUNT(*) FROM tickets WHERE availability = 1";
                            java.sql.Statement st = con.createStatement();
                            java.sql.ResultSet rsTix = st.executeQuery(sqlCheckTickets);
                            rsTix.next();
                            int availableTix = rsTix.getInt(1);

                            if (availableTix < ticketQty) {
                                JOptionPane.showMessageDialog(null, "Not enough tickets left! Available: " + availableTix);
                                con.rollback();
                                return;
                            }
                        }

                        int currentStock = 0;
                        int catId = 0;
                        double price = 0;
                        
                        String sqlCheck = "SELECT c.CategoryId, c.Stock, g.FixedPrice FROM categories c " +
                                          "JOIN garagetable g ON c.TableId = g.TableId " +
                                          "WHERE c.TableId = ? AND c.CategoryName = ?";
                        
                        java.sql.PreparedStatement psCheck = con.prepareStatement(sqlCheck);
                        psCheck.setInt(1, Integer.parseInt(tId));
                        psCheck.setString(2, catName);
                        java.sql.ResultSet rsCheck = psCheck.executeQuery();

                        if (rsCheck.next()) {
                            catId = rsCheck.getInt("CategoryId");
                            currentStock = rsCheck.getInt("Stock");
                            price = rsCheck.getDouble("FixedPrice");
                        } else {
                            throw new Exception("Category not found in inventory.");
                        }

                        if (currentStock < requestedQty) {
                            JOptionPane.showMessageDialog(null, "Insufficient Stock! Available: " + currentStock);
                            con.rollback();
                            return;
                        }

                        String sqlStudent = "INSERT INTO students (StudentId, Email, FirstName, LastName) " +
                                            "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                                            "Email=VALUES(Email), FirstName=VALUES(FirstName), LastName=VALUES(LastName)";
                        java.sql.PreparedStatement psStud = con.prepareStatement(sqlStudent);
                        psStud.setString(1, sId);
                        psStud.setString(2, email);
                        psStud.setString(3, fName);
                        psStud.setString(4, lName);
                        psStud.executeUpdate();

                        String sqlTrans = "INSERT INTO transactions (StudentId) VALUES (?)";
                        java.sql.PreparedStatement psTrans = con.prepareStatement(sqlTrans, java.sql.Statement.RETURN_GENERATED_KEYS);
                        psTrans.setString(1, sId);
                        psTrans.executeUpdate();
                        java.sql.ResultSet rsKey = psTrans.getGeneratedKeys();
                        int genTransId = rsKey.next() ? rsKey.getInt(1) : 0;
                        
                        if (ticketQty > 0 && genTransId > 0) {
                            String sqlClaimTickets = "UPDATE tickets SET TransactionId = ?, availability = 0 " +
                                                     "WHERE availability = 1 LIMIT ?";
                            java.sql.PreparedStatement psTix = con.prepareStatement(sqlClaimTickets);
                            psTix.setInt(1, genTransId);
                            psTix.setInt(2, ticketQty);
                            psTix.executeUpdate();
                        }

                        double total = requestedQty * price;
                        String sqlItem = "INSERT INTO transactionitems (TransactionId, CategoryId, TableId, Quantity, Total) " +
                                         "VALUES (?, ?, ?, ?, ?)";
                        java.sql.PreparedStatement psItem = con.prepareStatement(sqlItem);
                        psItem.setInt(1, genTransId);
                        psItem.setInt(2, catId);
                        psItem.setInt(3, Integer.parseInt(tId));
                        psItem.setInt(4, requestedQty);
                        psItem.setDouble(5, total);
                        psItem.executeUpdate();

                        String sqlUpdateStock = "UPDATE categories SET Stock = Stock - ? WHERE CategoryId = ?";
                        java.sql.PreparedStatement psUpdate = con.prepareStatement(sqlUpdateStock);
                        psUpdate.setInt(1, requestedQty);
                        psUpdate.setInt(2, catId);
                        psUpdate.executeUpdate();

                        con.commit();
                        JOptionPane.showMessageDialog(null, "Transaction successful! Tickets and Stock updated.");

                        loadTransactionTableData(); 
                        loadGarageData();           
                       
                    } catch (Exception ex) {
                        try { con.rollback(); } catch (Exception ignore) {}
                        JOptionPane.showMessageDialog(null, "Transaction Failed: " + ex.getMessage());
                        ex.printStackTrace();
                    } finally {
                        try { con.setAutoCommit(true); } catch (Exception ignore) {}
                    }
                }
            }
        });

        TransactionAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        TransactionAdd.setBounds(60, 508, 73, 23);
        garageContent.add(TransactionAdd);
        
        JButton btnDeleteTransaction = new JButton("Delete Selected");
        btnDeleteTransaction.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnDeleteTransaction.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row = TransactionTable.getSelectedRow();
                
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a transaction to remove.");
                    return;
                }

                String transId = TransactionTable.getValueAt(row, 0).toString(); 
                String studentId = TransactionTable.getValueAt(row, 1).toString(); 
                String tableId = TransactionTable.getValueAt(row, 2).toString();  
                String category = TransactionTable.getValueAt(row, 3).toString();

                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Delete Transaction #" + transId + " for Student " + studentId + "?", 
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteTransaction(studentId, tableId, category);
                }
        	}
        });
        btnDeleteTransaction.setBounds(630, 496, 158, 23);
        garageContent.add(btnDeleteTransaction);
        
        JButton btnGenerateGarageReport = new JButton("Generate Report");
        btnGenerateGarageReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                
                DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h-mm a");
                DateTimeFormatter contentFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h:mm a");
                
                String formattedFileDate = currentDateTime.format(fileFormatter);
                String formattedContentDate = currentDateTime.format(contentFormatter);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Sales Report");
                fileChooser.setSelectedFile(new File("GarageSalesReport(" + formattedFileDate + ").csv")); 

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();
                    
                    if (!filePath.toLowerCase().endsWith(".csv")) {
                        filePath += ".csv";
                    }

                    if (con == null) return;

                    String query = "SELECT c.TableId, c.CategoryName, g.FixedPrice, g.DiscountRate, " +
                                   "c.Stock, IFNULL(SUM(ti.Quantity), 0) AS QuantitySold, " +
                                   "IFNULL(SUM(ti.Total), 0) AS TotalSales " +
                                   "FROM categories c " +
                                   "JOIN garagetable g ON c.TableId = g.TableId " +
                                   "LEFT JOIN transactionitems ti ON c.CategoryId = ti.CategoryId " +
                                   "GROUP BY c.CategoryId, c.TableId, c.CategoryName, g.FixedPrice, g.DiscountRate, c.Stock " +
                                   "ORDER BY c.TableId ASC";

                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);
                         PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

                        writer.println("Date Generated:," + formattedContentDate);
                        writer.println(); 

                        writer.println("Table Number,Category Name,Fixed Price,Discount Rate,Quantity,Quantity Sold");

                        double grandTotalSales = 0.0;

                        while (rs.next()) {
                            int tableId = rs.getInt("TableId");
                            String categoryName = rs.getString("CategoryName").replace(",", " "); 
                            double price = rs.getDouble("FixedPrice");
                            double discountRate = rs.getDouble("DiscountRate");
                            int currentStock = rs.getInt("Stock");
                            int quantitySold = rs.getInt("QuantitySold");
                            double totalSales = rs.getDouble("TotalSales");

                            int originalQuantity = currentStock + quantitySold;
                            grandTotalSales += totalSales;

                            writer.printf("%d,%s,%.2f,%.2f,%d,%d\n", 
                                          tableId, categoryName, price, discountRate, originalQuantity, quantitySold);
                        }

                        writer.println(); 
                        writer.printf(",,,,Total Amount Sold:,%.2f\n", grandTotalSales);

                        JOptionPane.showMessageDialog(null, "Report successfully generated!\nSaved at: " + filePath);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error generating report: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnGenerateGarageReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnGenerateGarageReport.setBounds(498, 264, 130, 23);
        garageContent.add(btnGenerateGarageReport);
        
        JButton btnGenerateTransactionReport = new JButton("Generate Report");
        btnGenerateTransactionReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LocalDateTime currentDateTime = LocalDateTime.now();
                
                DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h-mm a");
                DateTimeFormatter contentFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h:mm a");
                
                String formattedFileDate = currentDateTime.format(fileFormatter);
                String formattedContentDate = currentDateTime.format(contentFormatter);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Transaction Report");
                fileChooser.setSelectedFile(new File("TransactionReport(" + formattedFileDate + ").csv")); 

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();
                    
                    if (!filePath.toLowerCase().endsWith(".csv")) {
                        filePath += ".csv";
                    }

                    if (con == null) return;

                    String query = "SELECT t.TransactionId, s.StudentId, s.FirstName, s.LastName, s.Email, " +
                                   "ti.TableId, c.CategoryName, ti.Quantity AS QuantityBought, ti.Total, " +
                                   "(SELECT COUNT(*) FROM tickets WHERE TransactionId = t.TransactionId) as TicketsGot " +
                                   "FROM transactions t " +
                                   "JOIN students s ON t.StudentId = s.StudentId " +
                                   "JOIN transactionitems ti ON t.TransactionId = ti.TransactionId " +
                                   "JOIN categories c ON ti.CategoryId = c.CategoryId " +
                                   "ORDER BY t.TransactionId ASC";

                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);
                         PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

                        writer.println("Date Generated:," + formattedContentDate);
                        writer.println(); 

                        writer.println("TransactionID,StudentID,First Name,Last Name,Email,TableID,Category Name,Quantity Bought,Quantity of Tickets Got,Total");

                        double grandTotalSales = 0.0;

                        while (rs.next()) {
                            int transId = rs.getInt("TransactionId");
                            String studentId = rs.getString("StudentId");
                            String fName = rs.getString("FirstName").replace(",", " ");
                            String lName = rs.getString("LastName").replace(",", " ");
                            String email = rs.getString("Email").replace(",", " ");
                            
                            int tableId = rs.getInt("TableId");
                            String categoryName = rs.getString("CategoryName").replace(",", " "); 
                            int quantityBought = rs.getInt("QuantityBought");
                            int ticketsGot = rs.getInt("TicketsGot");
                            double total = rs.getDouble("Total");

                            grandTotalSales += total;

                            writer.printf("%d,%s,%s,%s,%s,%d,%s,%d,%d,%.2f\n", 
                                          transId, studentId, fName, lName, email, tableId, categoryName, quantityBought, ticketsGot, total);
                        }

                        writer.println(); 
                        writer.printf(",,,,,,,,Grand Total:,%.2f\n", grandTotalSales);

                        JOptionPane.showMessageDialog(null, "Transaction Report successfully generated!\nSaved at: " + filePath);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error generating transaction report: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnGenerateTransactionReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnGenerateTransactionReport.setBounds(498, 496, 130, 23);
        garageContent.add(btnGenerateTransactionReport);
        
        loadTransactionTableData();
        

        // ===== TICKETSSSSSSS =====
        JPanel ticketsContent = new JPanel(null);
        JLabel ticketLabel = new JLabel("Ticketing System");
        ticketLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ticketLabel.setBounds(21, 11, 400, 40);
        ticketsContent.add(ticketLabel);
        contentPanel.add(ticketsContent, "TICKETS");
   
        lblTicketStatus = new JLabel("Tickets Availability Status");
        lblTicketStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTicketStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicketStatus.setBounds(232, 270, 200, 20); 
        ticketsContent.add(lblTicketStatus);

        ticketProgressBar = new JProgressBar();
        ticketProgressBar.setStringPainted(true); 
        ticketProgressBar.setForeground(new Color(0, 0, 200)); 
        ticketProgressBar.setBounds(232, 295, 200, 30);
        ticketsContent.add(ticketProgressBar);

        JLabel lblSetTickets = new JLabel("Set Tickets:");
        lblSetTickets.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSetTickets.setBounds(21, 119, 110, 14);
        ticketsContent.add(lblSetTickets);

        lblSoldStatus = new JLabel("Tickets Sold Status");
        lblSoldStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSoldStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoldStatus.setBounds(589, 270, 200, 20); 
        ticketsContent.add(lblSoldStatus);

        soldProgressBar = new JProgressBar();
        soldProgressBar.setStringPainted(true);
        soldProgressBar.setForeground(new Color(0, 128, 0)); 
        soldProgressBar.setBounds(587, 295, 200, 30);
        ticketsContent.add(soldProgressBar);

        updateTicketStatusLabel();
        
        SetTicketInput = new JTextField();
        SetTicketInput.setBounds(106, 119, 64, 20);
        ticketsContent.add(SetTicketInput);
        SetTicketInput.setColumns(10);
        
        JButton btnSet = new JButton("Set");
        btnSet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String input = SetTicketInput.getText().trim();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a number of tickets.");
                    return;
                }
                try {
                    int totalTickets = Integer.parseInt(input);
                    initializeTicketPool(totalTickets);
                    SetTicketInput.setText("");
                    loadTicketData();
                    updateTicketStatusLabel();
                
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a whole number.");
                }
            }
        });

        btnSet.setBounds(61, 150, 56, 23);
        ticketsContent.add(btnSet);
        
     // ===========TICKET TABLE============= 

     JScrollPane TicketScroll = new JScrollPane();
     TicketScroll.setBounds(233, 62, 555, 204); 
     ticketsContent.add(TicketScroll);
     
     TicketTable = new JTable();
     TicketTable.setDefaultEditor(Object.class, null); 
     TicketTable.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
     TicketScroll.setViewportView(TicketTable);
     TicketTable.setModel(new DefaultTableModel(
         new Object[][] {
         },
         new String[] {
             "Ticket ID", "Availability Status", "Transaction ID"
         }
     ));
     TicketTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
     
     JButton btnGenerateTicketReport = new JButton("Generate Report");
     btnGenerateTicketReport.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent e) {
     		LocalDateTime currentDateTime = LocalDateTime.now();
            
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h-mm a");
            DateTimeFormatter contentFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h:mm a");
            
            String formattedFileDate = currentDateTime.format(fileFormatter);
            String formattedContentDate = currentDateTime.format(contentFormatter);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Ticket Report");
            fileChooser.setSelectedFile(new File("TicketReport(" + formattedFileDate + ").csv")); 

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                
                if (!filePath.toLowerCase().endsWith(".csv")) {
                    filePath += ".csv";
                }
                if (con == null) return;
                String query = "SELECT t.TicketId, t.availability, t.TransactionId, tr.StudentId " +
                               "FROM tickets t " +
                               "LEFT JOIN transactions tr ON t.TransactionId = tr.TransactionId " +
                               "ORDER BY t.TicketId ASC";

                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(query);
                     PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

                    writer.println("Date Generated:," + formattedContentDate);
                    writer.println(); 
                    writer.println("TicketID,Availability,TransactionID,StudentID");
                    int availableTicketsCount = 0;
                    while (rs.next()) {
                        int ticketId = rs.getInt("TicketId");
                        String availability = rs.getString("Availability");
                        if ("1".equals(availability)) {
                            availableTicketsCount++;
                        }
                        int transId = rs.getInt("TransactionId");
                        String transIdStr = rs.wasNull() ? "" : String.valueOf(transId);
                        String studentId = rs.getString("StudentId");
                        if (studentId == null) {
                            studentId = "";
                        }
                        writer.printf("%d,%s,%s,%s\n", 
                                      ticketId, availability, transIdStr, studentId);
                    }
                    writer.println(); 
                    writer.printf("Available tickets:,%d,,\n", availableTicketsCount);
                    JOptionPane.showMessageDialog(null, "Ticket Report successfully generated!\nSaved at: " + filePath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error generating ticket report: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    });
     	
     btnGenerateTicketReport.setFont(new Font("Segoe UI", Font.PLAIN, 11));
     btnGenerateTicketReport.setBounds(444, 336, 130, 23);
     ticketsContent.add(btnGenerateTicketReport);
     loadTicketData();


        // ================= NAVIGATION =================
        btnDashboard.addActionListener(e -> {
        contentLayout.show(contentPanel, "DASHBOARD");
        updateActive(btnDashboard, btnGarage, btnTickets);
        loadDashboardData(incomeBar, lblIncomePercent, lblIncomeText,
                ticketBar, lblTicketPercent, lblTicketDetails);
        });
        
        btnGarage.addActionListener(e -> {
        contentLayout.show(contentPanel, "GARAGE");
        updateActive(btnGarage, btnDashboard, btnTickets);
        });

        btnTickets.addActionListener(e -> {
        contentLayout.show(contentPanel, "TICKETS");
        updateActive(btnTickets, btnDashboard, btnGarage);
        loadTicketData();
        updateTicketStatusLabel();
        });
        
        javax.swing.Timer timer = new javax.swing.Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshDashboard();
            }
        });

        timer.start();
    }
    

        //=============METHODSSSSSSSSSS====================
    private void initializeTicketPool(int targetTotalTickets) {
        Connection con = DBcon.getConnection();
        if (con == null) return;

        try {
            int currentMaxId = 0;
            String checkSql = "SELECT MAX(TicketId) FROM tickets";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                currentMaxId = rs.getInt(1);
            }
            rs.close();
            checkStmt.close();

            if (targetTotalTickets < currentMaxId) {
                int confirmDecrease = JOptionPane.showConfirmDialog(null,
                    "This will DECREASE the pool and delete the highest " + (currentMaxId - targetTotalTickets) + " tickets. Continue?",
                    "Decrease Ticket Pool", JOptionPane.YES_NO_OPTION);
                if (confirmDecrease == JOptionPane.YES_OPTION) {
                    String deleteSql = "DELETE FROM tickets WHERE TicketId > ?";
                    PreparedStatement delStmt = con.prepareStatement(deleteSql);
                    delStmt.setInt(1, targetTotalTickets);
                    
                    int rowsDeleted = delStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully removed " + rowsDeleted + " tickets.");
                    delStmt.close();
                }
                return; 
            } 
            else if (targetTotalTickets == currentMaxId) {
                 JOptionPane.showMessageDialog(null, "The pool is already exactly " + targetTotalTickets + " tickets.");
                 return;
            }
            int ticketsToAdd = targetTotalTickets - currentMaxId;
            String insertSql = "INSERT INTO tickets (TicketId, TransactionId, availability) VALUES (?, NULL, 1)";
            PreparedStatement pstmt = con.prepareStatement(insertSql);
            
            for (int i = currentMaxId + 1; i <= targetTotalTickets; i++) {
                pstmt.setInt(1, i);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            JOptionPane.showMessageDialog(null, "Successfully added " + ticketsToAdd + " new tickets! The total capacity is now " + targetTotalTickets + ".");
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: Cannot delete tickets that are already tied to a transaction.");
        }
    }
    
    private void updateTicketStatusLabel() {
        Connection con = DBcon.getConnection();
        if (con == null) return;

        try {
            int total = 0;
            int available = 0;
            
            String sqlTotal = "SELECT COUNT(*) FROM tickets";
            PreparedStatement pstmtTotal = con.prepareStatement(sqlTotal);
            ResultSet rsTotal = pstmtTotal.executeQuery();
            if (rsTotal.next()) total = rsTotal.getInt(1);

            String sqlAvail = "SELECT COUNT(*) FROM tickets WHERE availability = 1";
            PreparedStatement pstmtAvail = con.prepareStatement(sqlAvail);
            ResultSet rsAvail = pstmtAvail.executeQuery();
            if (rsAvail.next()) available = rsAvail.getInt(1);

            ticketProgressBar.setMinimum(0);
            ticketProgressBar.setMaximum(total);
            ticketProgressBar.setValue(available);
            ticketProgressBar.setString(available + " / " + total + " Available");
            
         int soldCount = total - available;

         soldProgressBar.setMinimum(0);
         soldProgressBar.setMaximum(total);
         soldProgressBar.setValue(soldCount);
         soldProgressBar.setString(soldCount + " / " + total + " Sold");
         
            rsTotal.close();
            pstmtTotal.close();
            rsAvail.close();
            pstmtAvail.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadTicketData() {
        // Get the model so we can add rows to it
        DefaultTableModel model = (DefaultTableModel) TicketTable.getModel();
        model.setRowCount(0); 
        Connection con = DBcon.getConnection();
        if (con == null) return;

        try {
            String sql = "SELECT TicketId, availability, TransactionId FROM tickets";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String ticketId = rs.getString("TicketId");
                int avail = rs.getInt("availability");
                String status = (avail == 1) ? "Available" : "Sold";
                String transId = rs.getString("TransactionId");
                if (rs.wasNull()) {
                    transId = "-"; 
                }
                model.addRow(new Object[]{ticketId, status, transId});
            }
            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading ticket data: " + e.getMessage());
        }
    }
    
    private void deleteTransaction(String studentId, String tableId, String categoryName) {
        Connection con = DBcon.getConnection();
        if (con == null) return;

        try {
            con.setAutoCommit(false); 

            String findDataQuery = "SELECT ti.TransactionId, ti.TransactionItemsId, ti.CategoryId, ti.Quantity " +
                                   "FROM transactionitems ti " +
                                   "JOIN transactions t ON ti.TransactionId = t.TransactionId " +
                                   "JOIN categories c ON ti.CategoryId = c.CategoryId " +
                                   "WHERE t.StudentId = ? AND ti.TableId = ? AND c.CategoryName = ? LIMIT 1";
            
            int transId = -1;
            int itemId = -1;
            int catId = -1;
            int qtyToReturn = 0;
            
            try (java.sql.PreparedStatement psFind = con.prepareStatement(findDataQuery)) {
                psFind.setString(1, studentId);
                psFind.setInt(2, Integer.parseInt(tableId));
                psFind.setString(3, categoryName);
                java.sql.ResultSet rs = psFind.executeQuery();
                if (rs.next()) {
                    transId = rs.getInt("TransactionId");
                    itemId = rs.getInt("TransactionItemsId");
                    catId = rs.getInt("CategoryId");
                    qtyToReturn = rs.getInt("Quantity");
                }
            }

            if (transId != -1) {
                String releaseTickets = "UPDATE tickets SET TransactionId = NULL, availability = 1 WHERE TransactionId = ?";
                try (java.sql.PreparedStatement psRelease = con.prepareStatement(releaseTickets)) {
                    psRelease.setInt(1, transId);
                    psRelease.executeUpdate();
                }

                String restoreStock = "UPDATE categories SET Stock = Stock + ? WHERE CategoryId = ?";
                try (java.sql.PreparedStatement psRestore = con.prepareStatement(restoreStock)) {
                    psRestore.setInt(1, qtyToReturn);
                    psRestore.setInt(2, catId);
                    psRestore.executeUpdate();
                }

                String delItems = "DELETE FROM transactionitems WHERE TransactionItemsId = ?";
                try (java.sql.PreparedStatement ps1 = con.prepareStatement(delItems)) {
                    ps1.setInt(1, itemId);
                    ps1.executeUpdate();
                }

                String delTrans = "DELETE FROM transactions WHERE TransactionId = ?";
                try (java.sql.PreparedStatement ps2 = con.prepareStatement(delTrans)) {
                    ps2.setInt(1, transId);
                    ps2.executeUpdate();
                }
                
                con.commit();
                JOptionPane.showMessageDialog(null, "Transaction deleted. Stock and Tickets restored.");
                
                loadTransactionTableData(); 
                loadGarageData(); 
            }

        } catch (Exception ex) {
            try { con.rollback(); } catch (Exception ignore) {}
            JOptionPane.showMessageDialog(null, "Error during deletion: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { con.setAutoCommit(true); } catch (Exception ignore) {}
        }
    }
    
    private void loadTransactionTableData() {
        DefaultTableModel model = (DefaultTableModel) TransactionTable.getModel();
        model.setRowCount(0);

        Connection con = DBcon.getConnection();
        if (con == null) return;

        String query = "SELECT t.TransactionId, s.StudentId, " +
                "ti.TableId, c.CategoryName, ti.Quantity, ti.Total, " +
                "(SELECT COUNT(*) FROM tickets WHERE TransactionId = t.TransactionId) as TicketCount " +
                "FROM transactionitems ti " +
                "JOIN transactions t ON ti.TransactionId = t.TransactionId " +
                "JOIN students s ON t.StudentId = s.StudentId " +
                "JOIN categories c ON ti.CategoryId = c.CategoryId";

        try (java.sql.Statement stmt = con.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(query)) {

        	while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("TransactionId"), 
                    rs.getString("StudentId"),   
                    rs.getInt("TableId"),        
                    rs.getString("CategoryName"),
                    rs.getInt("Quantity"),      
                    rs.getDouble("Total"),      
                    rs.getInt("TicketCount")     
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void removeSelectedRow() {
        int selectedRow = GarageTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table to remove.");
            return;
        }

        String tableId = GarageTable.getValueAt(selectedRow, 0).toString();
        String categoryName = GarageTable.getValueAt(selectedRow, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to remove '" + categoryName + "' from Table " + tableId + "?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Connection con = DBcon.getConnection();
            if (con != null) {
                String sql = "DELETE FROM categories WHERE TableId = ? AND CategoryName = ?";
                
                try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(tableId));
                    pstmt.setString(2, categoryName);
                    
                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(null, "Item removed successfully.");
                        loadGarageData();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting row: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
    

    private void updateTransactionCategories(String tableId, JComboBox categoryCombo) {
        categoryCombo.removeAllItems();
        
        Connection con = DBcon.getConnection();
        if (con == null) return;

        String query = "SELECT CategoryName FROM categories WHERE TableId = ?";
        
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(tableId));
            java.sql.ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                categoryCombo.addItem(rs.getString("CategoryName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadGarageData() {
        DefaultTableModel model = (DefaultTableModel) GarageTable.getModel();
        model.setRowCount(0); 

        Connection con = DBcon.getConnection();
        if (con == null) return;

        String query = "SELECT c.TableId, c.CategoryName, g.FixedPrice, g.DiscountRate, c.Stock " +
                       "FROM categories c " +
                       "INNER JOIN garagetable g ON c.TableId = g.TableId " +
                       "ORDER BY c.TableId ASC";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("TableId"),
                    rs.getString("CategoryName"),
                    rs.getInt("FixedPrice"),
                    (int)(rs.getDouble("DiscountRate") * 100) + "%", 
                    rs.getInt("Stock")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void styleNavButton(JButton btn, boolean active) {
        btn.setBackground(new Color(185, 11, 41));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", active ? Font.BOLD : Font.PLAIN, active ? 19 : 14));
    }

    private void updateActive(JButton active, JButton... others) {
        styleNavButton(active, true);
        for (JButton btn : others) {
            styleNavButton(btn, false);
        }
    }
    private void loadDashboardData(JProgressBar incomeBar, JLabel lblIncomePercent, JLabel lblIncomeText,
            JProgressBar ticketBar, JLabel lblTicketPercent, JLabel lblTicketDetails) {

    	Connection con = DBcon.getConnection();
    	if (con == null) return;

    	try {
    		// ===== INCOME =====
    		String incomeQuery = "SELECT IFNULL(SUM(Total),0) as total FROM transactionitems";
    		Statement st = con.createStatement();
    		ResultSet rs = st.executeQuery(incomeQuery);

    		double totalIncome = 0;
    		if (rs.next()) totalIncome = rs.getDouble("total");

    		ResultSet rsGoal = st.executeQuery("SELECT income_goal FROM settings WHERE id = 1");
    		if (rsGoal.next()) {
    		    incomeGoal = rsGoal.getInt("income_goal");
    		}
    		double goal = incomeGoal;

    		int percent = (int)((totalIncome / goal) * 100);
    		if (percent > 100) percent = 100;

    		incomeBar.setMaximum((int)goal);
    		incomeBar.setValue((int)Math.min(totalIncome, goal));
    		incomeBar.setString((int)totalIncome + " / " + (int)goal);
    		lblIncomePercent.setText(percent + "%");
    		lblIncomeText.setText((int)totalIncome + " / " + (int)goal);


    		// ===== TICKETS =====
    		String totalQ = "SELECT COUNT(*) FROM tickets";
    		String availQ = "SELECT COUNT(*) FROM tickets WHERE availability = 1";

    		ResultSet rsTotal = st.executeQuery(totalQ);
    		rsTotal.next();
    		int total = rsTotal.getInt(1);

    		ResultSet rsAvail = st.executeQuery(availQ);
    		rsAvail.next();
    		int remaining = rsAvail.getInt(1);

    		int sold = total - remaining;

    		int ticketPercent = total == 0 ? 0 : (sold * 100 / total);

    		ticketBar.setMaximum(total);
    		ticketBar.setValue(sold);
    		ticketBar.setString(sold + " / " + total);

    		lblTicketPercent.setText(ticketPercent + "%");
    		lblTicketDetails.setText("Sold: " + sold + "   Remaining: " + remaining + "   Total: " + total);
    		
    		// ===== 100 & 80 PESOS =====
    		Statement st2 = con.createStatement();
    		grid100.removeAll();
    		grid80.removeAll();

    		// Get all unique category names from database
    		ResultSet rsCatNames = st2.executeQuery(
    		    "SELECT DISTINCT CategoryName FROM categories");
    		List<String> categoryList = new ArrayList<>();
    		while (rsCatNames.next()) {
    		    categoryList.add(rsCatNames.getString("CategoryName"));
    		}

    		for (String cat : categoryList) {
    		    // 100 pesos - sold = sum of quantity, remaining = stock - sold
    			ResultSet rsCat100 = st2.executeQuery(
    					"SELECT c.TableId as tableId, IFNULL(SUM(ti.Quantity), 0) as sold, " +
    				    "SUM(c.Stock) as totalStock " +
    				    "FROM categories c " +
    				    "JOIN garagetable g ON c.TableId = g.TableId " +
    				    "LEFT JOIN transactionitems ti ON ti.CategoryId = c.CategoryId " +
    				    "WHERE g.FixedPrice = 100 AND c.CategoryName = '" + cat + "'");
    			int sold100 = 0, rem100 = 0, tableId100 = 0;
    			if (rsCat100.next()) {
    				tableId100 = rsCat100.getInt("tableId");
    			    sold100 = rsCat100.getInt("sold");
    			    rem100 = rsCat100.getInt("totalStock");
    			}
    		    JPanel cell100 = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
    		    cell100.setBackground(Color.WHITE);
    		    JLabel catLbl100 = new JLabel("Table " + tableId100 + " - " + cat);
    		    catLbl100.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		    JLabel soldLbl100 = new JLabel("● " + sold100);
    		    soldLbl100.setForeground(new Color(0, 102, 204));
    		    soldLbl100.setFont(new Font("Segoe UI", Font.BOLD, 11));
    		    JLabel remLbl100 = new JLabel("● " + rem100);
    		    remLbl100.setForeground(new Color(204, 0, 0));
    		    remLbl100.setFont(new Font("Segoe UI", Font.BOLD, 11));
    		    cell100.add(catLbl100);
    		    cell100.add(soldLbl100);
    		    cell100.add(remLbl100);
    		    grid100.add(cell100);
    		
    		// 80 pesos - sold = sum of quantity, remaining = stock - sold
    		    ResultSet rsCat80 = st2.executeQuery(
    		    		"SELECT c.TableId as tableId, IFNULL(SUM(ti.Quantity), 0) as sold, " +
    		    	    "SUM(c.Stock) as totalStock " +
    		    	    "FROM categories c " +
    		    	    "JOIN garagetable g ON c.TableId = g.TableId " +
    		    	    "LEFT JOIN transactionitems ti ON ti.CategoryId = c.CategoryId " +
    		    	    "WHERE g.FixedPrice = 80 AND c.CategoryName = '" + cat + "' GROUP BY c.TableId");

    		    int sold80 = 0, rem80 = 0, tableId80 = 0;
    		    if (rsCat80.next()) {
    		    	tableId80 = rsCat80.getInt("tableId");
    		        sold80 = rsCat80.getInt("sold");
    		        rem80 = rsCat80.getInt("totalStock");
    		    }
    		    	
    		  
    		    JPanel cell80 = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
    		    cell80.setBackground(Color.WHITE);
    		    JLabel catLbl80 = new JLabel("Table " + tableId80 + " - " + cat);
    		    catLbl80.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    		    JLabel soldLbl80 = new JLabel("● " + sold80);
    		    soldLbl80.setForeground(new Color(230, 130, 0));
    		    soldLbl80.setFont(new Font("Segoe UI", Font.BOLD, 11));
    		    JLabel remLbl80 = new JLabel("● " + rem80);
    		    remLbl80.setForeground(new Color(220, 180, 0));
    		    remLbl80.setFont(new Font("Segoe UI", Font.BOLD, 11));
    		    cell80.add(catLbl80);
    		    cell80.add(soldLbl80);
    		    cell80.add(remLbl80);
    		    grid80.add(cell80);
    		}

    		grid100.revalidate();
    		grid100.repaint();
    		grid80.revalidate();
    		grid80.repaint();
    		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    // ============ CAYARI ETO YUNG PART NG REAL TIME SA DASHBOARD OKAY? OKAY?=============
    private void refreshDashboard() {   
        loadGarageData();          
        loadTransactionTableData();
        loadTicketData();          
        updateTicketStatusLabel();
        
    }
}
