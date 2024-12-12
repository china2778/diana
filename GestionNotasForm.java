import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GestionNotasForm extends JFrame {
    private JTextField txtNombre;
    private JTextField txtEspanol;
    private JTextField txtMatematicas;
    private JTextField txtCienciasSociales;
    private JTextField txtCienciasNaturales;
    private JTextField txtFisica;
    private JButton btnIngresar;
    private JButton btnSeleccionar;
    private JButton btnEliminar;
    private JButton btnSalir;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblEstado;

    public GestionNotasForm() {
        setTitle("Gestion de Notas de Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel de ingreso de datos
        JPanel panelIngreso = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // componentes
        JLabel lblNombre = new JLabel("Nombre del Estudiante:");
        txtNombre = new JTextField(30);
        JLabel lblEspanol = new JLabel("Prof. Español:");
        txtEspanol = new JTextField(10);
        JLabel lblMatematicas = new JLabel("Prof. Matematicas:");
        txtMatematicas = new JTextField(10);
        JLabel lblCienciasSociales = new JLabel("Prof. Sociales:");
        txtCienciasSociales = new JTextField(10);
        JLabel lblCienciasNaturales = new JLabel("Prof. Naturales:");
        txtCienciasNaturales = new JTextField(10);
        JLabel lblFisica = new JLabel("Prof. Física:");
        txtFisica = new JTextField(10);

        // componentes al panel de ingreso usando GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelIngreso.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelIngreso.add(lblEspanol, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtEspanol, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelIngreso.add(lblMatematicas, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtMatematicas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelIngreso.add(lblCienciasSociales, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtCienciasSociales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelIngreso.add(lblCienciasNaturales, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtCienciasNaturales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelIngreso.add(lblFisica, gbc);
        gbc.gridx = 1;
        panelIngreso.add(txtFisica, gbc);

        //  boton de ingresar y agregarlo al panel de ingreso
        btnIngresar = new JButton("Ingresar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panelIngreso.add(btnIngresar, gbc);

        // Panel de acciones
        JPanel panelAcciones = new JPanel(new BorderLayout());

        //  panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // botones
        btnSeleccionar = new JButton("Seleccionar");
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");

        //  botones al panel de botones
        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        // Crear etiqueta para mostrar el estado
        lblEstado = new JLabel("Estado del estudiante aparecera aquí.");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

        // Configurar tabla
        String[] columnNames = {"Nombre", "Prof. Espanol", "Prof. Matematicas", "Prof. Sociales", "Prof. Naturales", "Prof. Física", "Promedio", "Estado"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30); // altura de las filas
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150); //   ancho de las columnas
        }
        JScrollPane scrollPane = new JScrollPane(table);

        //   panel de acciones
        panelAcciones.add(panelBotones, BorderLayout.NORTH);
        panelAcciones.add(scrollPane, BorderLayout.CENTER);
        panelAcciones.add(lblEstado, BorderLayout.SOUTH);

        //  paneles al panel de pestañas
        tabbedPane.addTab("Ingreso de Datos", panelIngreso);
        tabbedPane.addTab("Acciones", panelAcciones);

        //  panel de pestañas al formulario
        add(tabbedPane, BorderLayout.CENTER);

        // funcionalidad a los botones
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                double espanol = Double.parseDouble(txtEspanol.getText());
                double matematicas = Double.parseDouble(txtMatematicas.getText());
                double cienciasSociales = Double.parseDouble(txtCienciasSociales.getText());
                double cienciasNaturales = Double.parseDouble(txtCienciasNaturales.getText());
                double fisica = Double.parseDouble(txtFisica.getText());
                double promedio = (espanol + matematicas + cienciasSociales + cienciasNaturales + fisica) / 5;
                String estado = promedio >= 60 ? "Aprobado" : "Reprobado";
                model.addRow(new Object[]{nombre, espanol, matematicas, cienciasSociales, cienciasNaturales, fisica, promedio, estado});
                txtNombre.setText("");
                txtEspanol.setText("");
                txtMatematicas.setText("");
                txtCienciasSociales.setText("");
                txtCienciasNaturales.setText("");
                txtFisica.setText("");
            }
        });

        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtNombre.setText(model.getValueAt(selectedRow, 0).toString());
                    txtEspanol.setText(model.getValueAt(selectedRow, 1).toString());
                    txtMatematicas.setText(model.getValueAt(selectedRow, 2).toString());
                    txtCienciasSociales.setText(model.getValueAt(selectedRow, 3).toString());
                    txtCienciasNaturales.setText(model.getValueAt(selectedRow, 4).toString());
                    txtFisica.setText(model.getValueAt(selectedRow, 5).toString());
                    String estado = model.getValueAt(selectedRow, 7).toString();
                    lblEstado.setText(estado.equals("Aprobado") ? "¡Felicidades! Paso el año." : "No paso el ano.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    lblEstado.setText("Estado del estudiante aparecera aquí.");
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GestionNotasForm().setVisible(true);
            }
        });
    }
}
