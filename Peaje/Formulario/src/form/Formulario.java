package form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Formulario extends JFrame implements ActionListener{

	private JTextField tplaca, tMonto, tImpu, tTotal;
	private JButton procesar, limpiar, salir;
	private Validacion val = new Validacion();
	private JRadioButton rb1, rb2;
	private JComboBox<String> cvehiculos;

	public Formulario() {
		super("Peaje");
		this.setSize(350,400);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		Container contenedor = getContentPane();
		contenedor.setBackground(Color.WHITE);
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowHeights = new int[]{60,40,100,30,20,20,90};
		contenedor.setLayout(gbl);


		GridBagConstraints reglas = new GridBagConstraints();


		JLabel titulo = new JLabel("SERVICIO DE PEAJE");
		titulo.setFont(new Font("Arial", Font.BOLD+Font.ITALIC,16));
		reglas.gridwidth= GridBagConstraints.REMAINDER;
		reglas.anchor = GridBagConstraints.CENTER;
		reglas.gridx=0;
		reglas.gridy=0;
		contenedor.add(titulo,reglas);

		JLabel placa = new JLabel("PLACA: ");
		placa.setHorizontalAlignment(SwingConstants.RIGHT);
		reglas.anchor = GridBagConstraints.CENTER;
		reglas.gridy=1;
		reglas.gridwidth=1;
		reglas.weighty = 0;
		reglas.fill = GridBagConstraints.HORIZONTAL;
		contenedor.add(placa,reglas);

		tplaca = new JTextField(6);
		reglas.gridwidth=2;
		reglas.gridx=1;
		contenedor.add(tplaca,reglas);
		val.ValidarCantidad(tplaca, 6);

		JLabel vehiculo = new JLabel("VEHICULO: ");
		reglas.gridwidth =1;
		reglas.gridx=0;
		reglas.gridy=2;
		reglas.anchor = GridBagConstraints.NORTH;
		contenedor.add(vehiculo, reglas);

		cvehiculos = new JComboBox<String>();
		cvehiculos.addItem("Automovil");
		cvehiculos.addItem("Camion");
		cvehiculos.addItem("Gandola");
		cvehiculos.addItem("Transporte");
		reglas.gridx=1;
		contenedor.add(cvehiculos, reglas);

		ButtonGroup botones = new ButtonGroup();		
		rb1 = new JRadioButton("IVA");
		rb2 = new JRadioButton("EXONERADO");
		rb1.setBackground(Color.WHITE);
		rb2.setBackground(Color.WHITE);
		botones.add(rb1);
		botones.add(rb2);
		rb1.setSelected(true);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1), "IMPUESTO:"));
		panel1.setBackground(Color.WHITE);
		panel1.add(rb1);
		panel1.add(rb2);
		reglas.gridy=2;
		reglas.gridx=2;
		reglas.anchor = GridBagConstraints.NORTH;
		reglas.fill = GridBagConstraints.VERTICAL;
		contenedor.add(panel1,reglas);


		JLabel monto = new JLabel("MONTO: ");
		monto.setHorizontalAlignment(SwingConstants.RIGHT);
		reglas.gridx=0;
		reglas.gridy=3;
		reglas.gridheight=1;
		reglas.anchor = GridBagConstraints.SOUTH;
		reglas.fill = GridBagConstraints.HORIZONTAL;
		contenedor.add(monto,reglas);

		reglas.gridy++;

		JLabel impuesto = new JLabel("IMPUESTO: ");
		impuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		reglas.anchor = GridBagConstraints.CENTER;
		contenedor.add(impuesto,reglas);

		reglas.gridy++;

		JLabel total = new JLabel("TOTAL: ");
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		contenedor.add(total,reglas);

		tMonto = new JTextField(20);
		tMonto.setBackground(Color.LIGHT_GRAY);
		tMonto.setEditable(false);
		reglas.gridy=3;
		reglas.gridwidth=2;
		reglas.gridx=1;
		reglas.anchor = GridBagConstraints.SOUTH;
		contenedor.add(tMonto,reglas);

		reglas.gridy++;

		tImpu = new JTextField(20);
		tImpu.setBackground(Color.LIGHT_GRAY);
		tImpu.setEditable(false);
		reglas.anchor = GridBagConstraints.CENTER;
		contenedor.add(tImpu,reglas);

		reglas.gridy++;

		tTotal = new JTextField(20);
		tTotal.setBackground(Color.LIGHT_GRAY);
		tTotal.setEditable(false);
		contenedor.add(tTotal,reglas);

		reglas.gridy++;
		reglas.gridx=0;
		reglas.gridwidth=3;
		reglas.anchor = GridBagConstraints.CENTER;

		procesar = new JButton("Procesar");
		limpiar = new JButton("LIMPIAR");
		salir = new JButton("SALIR");
		procesar.addActionListener(this);
		limpiar.addActionListener(this);
		salir.addActionListener(this);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(new BorderLayout(20, 0));
		panel2.add(procesar, BorderLayout.WEST);
		panel2.add(limpiar, BorderLayout.CENTER);
		panel2.add(salir, BorderLayout.EAST);

		contenedor.add(panel2, reglas);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		float costo=0, impuesto=0, total=0;
		// TODO Auto-generated method stub
		JButton bt= (JButton) e.getSource();

		if(bt==limpiar) {

			tTotal.setText("");
			tMonto.setText("");
			tImpu.setText("");
			tplaca.setText("");
			rb1.setSelected(true);
			cvehiculos.setSelectedItem("Automovil");

		}

		if (bt==procesar) {
			if(tplaca.getText().length()==0) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, 
				"Debe ingresar la placa del vehiculo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (cvehiculos.getSelectedItem().equals("Automovil")){
					costo = 3000;
				}
				if (cvehiculos.getSelectedItem().equals("Camion")) {
					costo = 15000;
				}
				if (cvehiculos.getSelectedItem().equals("Gandola")) {
					costo = 80000;
				}
				if (cvehiculos.getSelectedItem().equals("Transporte")) {
					costo = 10000;
				}


				if (rb1.isSelected()) {
					impuesto = (float) (costo*0.16);
					total = impuesto + costo;
				}
				if(rb2.isSelected()) {
					impuesto = 0;
					total = costo;
				}

				tMonto.setText(costo+" Bs");
				tImpu.setText(impuesto+" Bs");
				tTotal.setText(total+" Bs");
			}

		}

		if (bt == salir) {
			close();
		}

	}



	private void close(){
		Toolkit.getDefaultToolkit().beep();
		if(JOptionPane.showConfirmDialog(null, "¿Desea salir del sistema?","SALIDA DEL SISTEMA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
			System.exit(0);
		}  
	}
}

