package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import co.proyecto.controller.metodoPagosController;
import co.proyecto.controller.reservaController;
import co.proyectoAlura.Modelo.MetodoPago;
import co.proyectoAlura.Modelo.ReservasLogic;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Reservas extends JFrame {

	private JPanel contentPane;
	private JTextField txtValor;
	private metodoPagosController metodoPago = new metodoPagosController();
	private reservaController reservase = new reservaController();
	private ReservasLogic logic = new ReservasLogic();
	RegistroHuesped huesped = new RegistroHuesped();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Reservas frame = new Reservas();
					
					frame.setVisible(true);
		            
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
	}
	
	

	
	

	/**
	 * Create the frame.
	 */
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		JDateChooser txtFechaE = new JDateChooser();
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		JDateChooser txtFechaS = new JDateChooser();
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);

		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		JComboBox<MetodoPago> txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.addItem(new MetodoPago(0, "Escoger metodo"));
		panel.add(txtFormaPago);

		var metodo = this.metodoPago.lista();
		metodo.forEach(listado -> txtFormaPago.addItem(listado));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de ReservasLogic");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				
				guardar();

			}

			private void guardar() {
				if (txtFechaS.getDate() == null || txtFechaE.getDate() == null) {
					JOptionPane.showMessageDialog(null, " no se ha ingresado correctamente");
					
					
					huesped.setVisible(false);

					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "se ha ingresado correctamente");
					
					
					huesped.setVisible(true);

					dispose();
				java.util.Date fechaSalida = txtFechaS.getDate();
				java.util.Date fechaEntrada = txtFechaE.getDate();

				Date converFechaEntrada = new Date(fechaEntrada.getYear(), fechaEntrada.getMonth(),
						fechaEntrada.getDay());

				Date converFechaSalida = new Date(fechaSalida.getYear(), fechaSalida.getMonth(), fechaSalida.getDay());
				SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
				String dateone = formato.format(fechaSalida);
				String dateTwo = formato.format(fechaEntrada);
				LocalDate dataFechaEn = LocalDate.parse(dateone);
				LocalDate dataFechaSa = LocalDate.parse(dateTwo);
				long dataDate = ChronoUnit.DAYS.between(dataFechaSa, dataFechaEn);
				logic.setValor(4500);
				float valores = logic.getValor() * (int) dataDate;
				txtValor.setText(String.valueOf(valores));
				var reserva = new ReservasLogic(converFechaSalida, converFechaEntrada, valores);
				var metodoPago = (MetodoPago) txtFormaPago.getSelectedItem();
				reservaController crear = new reservaController();
				reserva.setMetodoPago(metodoPago.getId());
				crear.guardarReserva(reserva, metodoPago.getId());
			

			}
			}
		});
		
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(183, 436, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
