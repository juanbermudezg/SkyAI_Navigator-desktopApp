package com.juanbermudez.desktopapp.Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends JFrame implements ActionListener {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private JTable table;
    private JButton addButton, editButton, deleteButton;
    private JScrollPane scrollPane;

    public AdminPanel() {
        // Establecer la configuración de la ventana
        setTitle("Panel de Administración");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Establecer el diseño del panel
        setLayout(new BorderLayout());

        // Botones de operaciones CRUD
        addButton = new JButton("Agregar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Eliminar");

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Tabla para mostrar los usuarios
        table = new JTable();
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Conectar a la base de datos
        connectDatabase();
        loadUsers();
    }

    // Método para conectar a la base de datos
    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skyai_db", "root", "esave2021");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los usuarios desde la base de datos
    private void loadUsers() {
        try {
            String query = "SELECT * FROM Users";
            rs = stmt.executeQuery(query);

            // Crear un modelo de tabla para mostrar los datos
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar nombres de columnas al modelo de tabla
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnName(columnIndex));
            }

            // Agregar filas al modelo de tabla
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }

            // Establecer el modelo de tabla en la tabla
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión a la base de datos
    private void closeDatabase() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Manejar acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Lógica para agregar un nuevo usuario
            // Puedes abrir un nuevo formulario de entrada de datos aquí
        } else if (e.getSource() == editButton) {
            // Lógica para editar un usuario seleccionado
            // Puedes abrir un nuevo formulario de edición de datos aquí
        } else if (e.getSource() == deleteButton) {
            // Lógica para eliminar un usuario seleccionado
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int response = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        String id = table.getValueAt(selectedRow, 0).toString();
                        String query = "DELETE FROM Users WHERE id = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, id);
                        pstmt.executeUpdate();
                        loadUsers(); // Recargar los usuarios después de la eliminación
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.setVisible(true);
        });
    }
}
