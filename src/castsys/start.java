package castsys;
import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
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
    private JTable table;
    private JTextField FixedPriceInput;
    private JTextField LastNameInput;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField FirstNameInput;

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
        setBounds(100, 100, 986, 575);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ================= SIDEBAR (added ONCE) =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(185, 11, 41));
        sidebar.setBounds(0, 0, 188, 536);
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

        // ================= CONTENT AREA =================
        contentPanel = new JPanel();
        contentPanel.setBounds(188, 0, 782, 536);
        contentLayout = new CardLayout();
        contentPanel.setLayout(contentLayout);
        contentPane.add(contentPanel);

        // ===== Dashboard Content =====
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

        // ===== Garage Content =====
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
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(251, 62, 425, 187);
        garageContent.add(scrollPane);
        
        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        table.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Table Number", "Category Name", "Fixed Price", "Discount Rate", "Stock"
        	}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(96);
        table.getColumnModel().getColumn(1).setPreferredWidth(91);
        table.getColumnModel().getColumn(3).setPreferredWidth(91);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        loadGarageData();
        
        JComboBox comboBoxTableNumber = new JComboBox();
        comboBoxTableNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
        comboBoxTableNumber.setEditable(true);
        comboBoxTableNumber.setBounds(114, 95, 55, 22);
        garageContent.add(comboBoxTableNumber);
        
        JLabel lblall = new JLabel("(all)");
        lblall.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblall.setBounds(171, 190, 20, 31);
        garageContent.add(lblall);
        
        //BUTTONNNNNNN
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
        
        JLabel lblTransactions = new JLabel("Transactions\r\n");
        lblTransactions.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTransactions.setBounds(21, 291, 185, 40);
        garageContent.add(lblTransactions);
        
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFirstName.setBounds(21, 321, 83, 31);
        garageContent.add(lblFirstName);
        
        LastNameInput = new JTextField();
        LastNameInput.setColumns(10);
        LastNameInput.setBounds(114, 351, 55, 20);
        garageContent.add(LastNameInput);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblLastName.setBounds(21, 346, 90, 31);
        garageContent.add(lblLastName);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(114, 376, 55, 20);
        garageContent.add(textField_1);
        
        JLabel lblFixedPrice_1 = new JLabel("Table Fixed Price:");
        lblFixedPrice_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFixedPrice_1.setBounds(21, 371, 90, 31);
        garageContent.add(lblFixedPrice_1);
        
        JLabel lblStock_1 = new JLabel("Stock:");
        lblStock_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStock_1.setBounds(21, 396, 38, 31);
        garageContent.add(lblStock_1);
        
        JLabel lblDiscountRate_1 = new JLabel("Table Discount:");
        lblDiscountRate_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDiscountRate_1.setBounds(21, 421, 90, 31);
        garageContent.add(lblDiscountRate_1);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setEditable(true);
        comboBox_1.setBounds(114, 426, 55, 22);
        garageContent.add(comboBox_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(114, 401, 54, 20);
        garageContent.add(textField_2);
        
        JButton TransactionAdd = new JButton("Add\r\n");
        TransactionAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        TransactionAdd.setBounds(60, 457, 73, 23);
        garageContent.add(TransactionAdd);
        
        FirstNameInput = new JTextField();
        FirstNameInput.setColumns(10);
        FirstNameInput.setBounds(114, 327, 55, 20);
        garageContent.add(FirstNameInput);

        // ===== Tickets Content =====
        JPanel ticketsContent = new JPanel(null);
        JLabel ticketLabel = new JLabel("Ticketing System");
        ticketLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ticketLabel.setBounds(21, 11, 400, 40);
        ticketsContent.add(ticketLabel);
        contentPanel.add(ticketsContent, "TICKETS");

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

    // ================= Helpers =================
    private void loadGarageData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
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