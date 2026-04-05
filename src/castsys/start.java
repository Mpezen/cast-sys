package castsys;
import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;

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
        
        JLabel lblProfitGoal = new JLabel("Profit Goal");
        lblProfitGoal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblProfitGoal.setBounds(20, 58, 136, 40);
        dashboardContent.add(lblProfitGoal);
        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(255, 0, 0));
        progressBar.setValue(67);
        progressBar.setBounds(20, 97, 263, 27);
        dashboardContent.add(progressBar);
        
        
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
        		"StudentID", "First Name", "Last Name", "Email", "TableID", "Category Name", "Quantity", "Total", "Tickets"
        	}
        ));
        TransactionTable.getColumnModel().getColumn(5).setPreferredWidth(93);
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
        btnDeleteTransaction.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row = TransactionTable.getSelectedRow();
                
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a transaction to remove.");
                    return;
                }

                String studentId = TransactionTable.getValueAt(row, 0).toString();
                String tableId = TransactionTable.getValueAt(row, 4).toString();
                String category = TransactionTable.getValueAt(row, 5).toString();

                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Delete this transaction for Student " + studentId + "?", 
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteTransaction(studentId, tableId, category);
                }
        	}
        });
        btnDeleteTransaction.setBounds(630, 496, 158, 23);
        garageContent.add(btnDeleteTransaction);
        
        loadTransactionTableData();
        

        // ===== TICKETSSSSSSS =====
        JPanel ticketsContent = new JPanel(null);
        JLabel ticketLabel = new JLabel("Ticketing System");
        ticketLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ticketLabel.setBounds(21, 11, 400, 40);
        ticketsContent.add(ticketLabel);
        contentPanel.add(ticketsContent, "TICKETS");
        
        JLabel lblSetTickets = new JLabel("Set Tickets:");
        lblSetTickets.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSetTickets.setBounds(21, 119, 110, 14);
        ticketsContent.add(lblSetTickets);
        
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
                    int confirm = JOptionPane.showConfirmDialog(null, 
                        "This will reset the ticket pool to " + totalTickets + ". Continue?", 
                        "Reset Ticket Pool", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        initializeTicketPool(totalTickets);
                        SetTicketInput.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a whole number.");
                }
            }
        });
        
        btnSet.setBounds(61, 150, 56, 23);
        ticketsContent.add(btnSet);
        

        // ================= NAVIGATION =================
        btnDashboard.addActionListener(e -> {
            contentLayout.show(contentPanel, "DASHBOARD");
            updateActive(btnDashboard, btnGarage, btnTickets);
        });

        btnGarage.addActionListener(e -> {
            contentLayout.show(contentPanel, "GARAGE");
            updateActive(btnGarage, btnDashboard, btnTickets);
        });

        btnTickets.addActionListener(e -> {
            contentLayout.show(contentPanel, "TICKETS");
            updateActive(btnTickets, btnDashboard, btnGarage);
        });
    }
    
    //=============METHODSSSSSSSSSS====================
    
    private void initializeTicketPool(int totalTickets) {
        Connection con = DBcon.getConnection();
        if (con == null) return;
        
        try {
            String sql = "INSERT IGNORE INTO tickets (TicketId, TransactionId, availability) VALUES (?, NULL, 1)";
            java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
            
            for (int i = 1; i <= totalTickets; i++) {
                pstmt.setInt(1, i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            JOptionPane.showMessageDialog(null, totalTickets + " Tickets initialized and available.");
        } catch (Exception e) {
            e.printStackTrace();
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

        String query = "SELECT s.StudentId, s.FirstName, s.LastName, s.Email, ti.TableId, c.CategoryName, ti.Quantity, ti.Total, " +
                       "(SELECT COUNT(*) FROM tickets WHERE TransactionId = t.TransactionId) as TicketCount " +
                       "FROM transactionitems ti " +
                       "JOIN transactions t ON ti.TransactionId = t.TransactionId " +
                       "JOIN students s ON t.StudentId = s.StudentId " +
                       "JOIN categories c ON ti.CategoryId = c.CategoryId";

        try (java.sql.Statement stmt = con.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("StudentId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
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
}