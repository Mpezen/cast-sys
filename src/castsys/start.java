package castsys;
import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

public class start extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel contentPanel;
    private CardLayout contentLayout;

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
        setResizable(false);
        setTitle("CAST-SYS-LI TAYO");
        setIconImage(new ImageIcon("logocast.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 835, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ================= SIDEBAR (added ONCE) =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(185, 11, 41));
        sidebar.setBounds(0, 0, 188, 461);
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
        contentPanel.setBounds(188, 0, 631, 461);
        contentLayout = new CardLayout();
        contentPanel.setLayout(contentLayout);
        contentPane.add(contentPanel);

        // ===== Dashboard Content =====
        JPanel dashboardContent = new JPanel(null);
        JLabel dashLabel = new JLabel("Welcome to the Dashboard");
        dashLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        dashLabel.setBounds(50, 50, 400, 40);
        dashboardContent.add(dashLabel);
        contentPanel.add(dashboardContent, "DASHBOARD");
        
        JLabel lblProfitGoal = new JLabel("Profit Goal");
        lblProfitGoal.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblProfitGoal.setBounds(50, 121, 136, 40);
        dashboardContent.add(lblProfitGoal);

        // ===== Garage Content =====
        JPanel garageContent = new JPanel(null);
        JLabel garageLabel = new JLabel("Garage Management");
        garageLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        garageLabel.setBounds(50, 50, 400, 40);
        garageContent.add(garageLabel);
        contentPanel.add(garageContent, "GARAGE");

        // ===== Tickets Content =====
        JPanel ticketsContent = new JPanel(null);
        JLabel ticketLabel = new JLabel("Ticketing System");
        ticketLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ticketLabel.setBounds(50, 50, 400, 40);
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