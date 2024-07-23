package com.projects.dadosldr.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.projects.dadosldr.dao.EscolaDao;
import com.projects.dadosldr.entity.Escola;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main implements NativeKeyListener {

	private static JFrame frame = new JFrame();
	private JTextField txtEscola;
	private JTextField txtInep;
	private JTextField txtUf;
	private JTextField txtMunicipio;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtPorte;
	private JTextField txtEmeo;
	private JTextField txtCnpj;
	private JTextField txtEmail;
	private JTextField txtTelefone2;
	private JTextField txtTelefone3;
	private JTextPane txtAnotacoes;
	private JTextField txtSite;
	private JTextField txtInstagram;
	private JTextField txtSistemaDeEnsino;
	private JTextField txtAgendaDigital;
	private JTextField txtTelefone4;
	private JTextField txtId;
	private EscolaDao escolaDao = new EscolaDao();
	private Escola escolaSelecionada;
	private String ultimaListaAdicionada;
	JButton btnAtualizar = new JButton();
	JButton btnAnterior = new JButton();
	JButton btnPro = new JButton();
	String listaSelecionada;
	JComboBox<String> comboBox = new JComboBox<>();
	String[] listas;
	boolean ctrlPressed = false;
	boolean shiftPressed = false;
	String[] valoresColados = new String[17];

	// Mapeamento de chaves para JTextField
	private Map<Integer, JTextField> keysList = new HashMap<Integer, JTextField>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{

			put(NativeKeyEvent.VC_1, new JTextField());
			put(NativeKeyEvent.VC_2, new JTextField());
			put(NativeKeyEvent.VC_3, new JTextField());
			put(NativeKeyEvent.VC_4, new JTextField());
			put(NativeKeyEvent.VC_5, new JTextField());
			put(NativeKeyEvent.VC_6, new JTextField());
			put(NativeKeyEvent.VC_7, new JTextField());
			put(NativeKeyEvent.VC_8, new JTextField());
			put(NativeKeyEvent.VC_9, new JTextField());
			put(NativeKeyEvent.VC_Q, new JTextField());
			put(NativeKeyEvent.VC_W, new JTextField());
			put(NativeKeyEvent.VC_E, new JTextField());
			put(NativeKeyEvent.VC_R, new JTextField());
			put(NativeKeyEvent.VC_T, new JTextField());
			put(NativeKeyEvent.VC_Y, new JTextField());
			put(NativeKeyEvent.VC_U, new JTextField());
			put(NativeKeyEvent.VC_I, new JTextField());

		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new Main());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setResizable(false);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				atualizaCampos();
			}
		});
		frame.setBounds(100, 100, 217, 638);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // Centraliza tela
		frame.setExtendedState(JFrame.MAXIMIZED_VERT);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 201, 599);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 201, 24);
		panel.add(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmSalvar = new JMenuItem("Salvar (CTRL + S)");
		mntmSalvar.setEnabled(false);
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (salvarEscola() == 1) {
					JOptionPane.showMessageDialog(null, "Escola salva com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar escola");
				}

			}
		});
		mnMenu.add(mntmSalvar);

		JMenuItem mntmConfiguracoes = new JMenuItem("Configurações");
		mntmConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Settings settings = new Settings();
				settings.setLocationRelativeTo(null); // Centraliza tela
				settings.setVisible(true);
				settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Não deixa encerrar o programa ao fechar a
																			// tela de cadastro, apenas a tela de
																			// cadastro é fechada

			}
		});
		mnMenu.add(mntmConfiguracoes);
		
		JMenu mnNewMenu = new JMenu("Atalhos");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Copiar: ALT + Nº Campo");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Colar: CTRL + Shift + Nº Campo");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Salvar: CTRL + S");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Atualizar: F5");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Anterior: CTRL + CIMA");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Próximo: CTRL + BAIXO");
		mnNewMenu.add(mntmNewMenuItem_5);

		txtEscola = new JTextField();
		txtEscola.setHorizontalAlignment(SwingConstants.LEFT);
		txtEscola.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtEscola.setBounds(10, 41, 181, 17);
		panel.add(txtEscola);
		txtEscola.setColumns(10);

		txtInep = new JTextField();
		txtInep.setHorizontalAlignment(SwingConstants.LEFT);
		txtInep.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtInep.setColumns(10);
		txtInep.setBounds(62, 59, 129, 17);
		panel.add(txtInep);

		txtUf = new JTextField();
		txtUf.setHorizontalAlignment(SwingConstants.LEFT);
		txtUf.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtUf.setColumns(10);
		txtUf.setBounds(62, 85, 129, 17);
		panel.add(txtUf);

		txtMunicipio = new JTextField();
		txtMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		txtMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(62, 111, 129, 17);
		panel.add(txtMunicipio);

		txtEndereco = new JTextField();
		txtEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(62, 137, 129, 17);
		panel.add(txtEndereco);

		txtTelefone = new JTextField();
		txtTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(62, 163, 129, 17);
		panel.add(txtTelefone);

		txtPorte = new JTextField();
		txtPorte.setHorizontalAlignment(SwingConstants.LEFT);
		txtPorte.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPorte.setColumns(10);
		txtPorte.setBounds(62, 189, 129, 17);
		panel.add(txtPorte);

		txtEmeo = new JTextField();
		txtEmeo.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmeo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtEmeo.setColumns(10);
		txtEmeo.setBounds(62, 215, 129, 17);
		panel.add(txtEmeo);

		txtCnpj = new JTextField();
		txtCnpj.setHorizontalAlignment(SwingConstants.LEFT);
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(62, 241, 129, 17);
		panel.add(txtCnpj);

		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtEmail.setColumns(10);
		txtEmail.setBounds(62, 267, 129, 17);
		panel.add(txtEmail);

		txtTelefone2 = new JTextField();
		txtTelefone2.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTelefone2.setColumns(10);
		txtTelefone2.setBounds(62, 293, 129, 17);
		panel.add(txtTelefone2);

		txtTelefone3 = new JTextField();
		txtTelefone3.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTelefone3.setColumns(10);
		txtTelefone3.setBounds(62, 319, 129, 17);
		panel.add(txtTelefone3);

		txtAnotacoes = new JTextPane();
		txtAnotacoes.setText("Anotações");
		txtAnotacoes.setFont(new Font("Tahoma", Font.PLAIN, 8));
		txtAnotacoes.setBounds(62, 477, 129, 41);
		panel.add(txtAnotacoes);

		txtSite = new JTextField();
		txtSite.setHorizontalAlignment(SwingConstants.LEFT);
		txtSite.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtSite.setColumns(10);
		txtSite.setBounds(62, 449, 129, 17);
		panel.add(txtSite);

		txtInstagram = new JTextField();
		txtInstagram.setHorizontalAlignment(SwingConstants.LEFT);
		txtInstagram.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtInstagram.setColumns(10);
		txtInstagram.setBounds(62, 423, 129, 17);
		panel.add(txtInstagram);

		txtSistemaDeEnsino = new JTextField();
		txtSistemaDeEnsino.setHorizontalAlignment(SwingConstants.LEFT);
		txtSistemaDeEnsino.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtSistemaDeEnsino.setColumns(10);
		txtSistemaDeEnsino.setBounds(62, 397, 129, 17);
		panel.add(txtSistemaDeEnsino);

		txtAgendaDigital = new JTextField();
		txtAgendaDigital.setHorizontalAlignment(SwingConstants.LEFT);
		txtAgendaDigital.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtAgendaDigital.setColumns(10);
		txtAgendaDigital.setBounds(62, 371, 129, 17);
		panel.add(txtAgendaDigital);

		txtTelefone4 = new JTextField();
		txtTelefone4.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefone4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTelefone4.setColumns(10);
		txtTelefone4.setBounds(62, 345, 129, 17);
		panel.add(txtTelefone4);

		JLabel lbl1 = new JLabel("1 Escola");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl1.setBounds(10, 21, 181, 24);
		panel.add(lbl1);

		JLabel lbl2 = new JLabel("2 INEP");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl2.setBounds(10, 62, 42, 14);
		panel.add(lbl2);

		JLabel lbl3 = new JLabel("3 UF");
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl3.setBounds(10, 88, 42, 14);
		panel.add(lbl3);

		JLabel lbl4 = new JLabel("4 Cidade");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl4.setBounds(10, 114, 42, 14);
		panel.add(lbl4);

		JLabel lbl5 = new JLabel("5 Endereço");
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl5.setBounds(10, 140, 42, 14);
		panel.add(lbl5);

		JLabel lbl6 = new JLabel("6 Telefone");
		lbl6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl6.setBounds(10, 166, 42, 14);
		panel.add(lbl6);

		JLabel lbl7 = new JLabel("7 Porte");
		lbl7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl7.setBounds(10, 192, 42, 14);
		panel.add(lbl7);

		JLabel lbl8 = new JLabel("8 Emeo");
		lbl8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl8.setBounds(10, 218, 42, 14);
		panel.add(lbl8);

		JLabel lbl9 = new JLabel("9 CNPJ");
		lbl9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl9.setBounds(10, 244, 42, 14);
		panel.add(lbl9);

		JLabel lbl10 = new JLabel("Q Email");
		lbl10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl10.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl10.setBounds(0, 270, 52, 14);
		panel.add(lbl10);

		JLabel lbl11 = new JLabel("W Telefone 2");
		lbl11.setHorizontalAlignment(SwingConstants.CENTER);
		lbl11.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl11.setBounds(0, 296, 52, 14);
		panel.add(lbl11);

		JLabel lbl12 = new JLabel("E Telefone 3");
		lbl12.setHorizontalAlignment(SwingConstants.CENTER);
		lbl12.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl12.setBounds(0, 322, 52, 14);
		panel.add(lbl12);

		JLabel lbl13 = new JLabel("R Telefone 4");
		lbl13.setHorizontalAlignment(SwingConstants.CENTER);
		lbl13.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl13.setBounds(0, 348, 52, 14);
		panel.add(lbl13);

		JLabel lbl14 = new JLabel("T Agenda");
		lbl14.setHorizontalAlignment(SwingConstants.CENTER);
		lbl14.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl14.setBounds(0, 373, 52, 14);
		panel.add(lbl14);

		JLabel lbl15 = new JLabel("Y Sistema");
		lbl15.setHorizontalAlignment(SwingConstants.CENTER);
		lbl15.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl15.setBounds(0, 399, 52, 14);
		panel.add(lbl15);

		JLabel lbl16 = new JLabel("U Instagram");
		lbl16.setHorizontalAlignment(SwingConstants.CENTER);
		lbl16.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl16.setBounds(0, 425, 52, 14);
		panel.add(lbl16);

		JLabel lbl17 = new JLabel("I Site");
		lbl17.setHorizontalAlignment(SwingConstants.CENTER);
		lbl17.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl17.setBounds(0, 451, 52, 14);
		panel.add(lbl17);

		JLabel lbl18 = new JLabel("Anotações");
		lbl18.setHorizontalAlignment(SwingConstants.CENTER);
		lbl18.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl18.setBounds(0, 478, 52, 14);
		panel.add(lbl18);

		txtId = new JTextField();
		txtId.setText("ID");
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtId.setColumns(10);
		txtId.setBounds(10, 499, 42, 17);
		panel.add(txtId);

		btnAnterior.setText("Ant ^");
		btnAnterior.setEnabled(false);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				salvarEscola();

				int anteriorId = escolaSelecionada.getId() - 1;
				escolaSelecionada = escolaDao.acessaEscolaById(anteriorId, listaSelecionada);

				if (escolaSelecionada.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Esta é a primeira escola da lista");
					escolaSelecionada = escolaDao.acessaEscolaById(anteriorId + 1, listaSelecionada);
				}
				mostraEscola(escolaSelecionada);

			}
		});
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnAnterior.setBounds(131, 527, 60, 31);
		panel.add(btnAnterior);

		btnPro.setText("Pro");
		btnPro.setEnabled(false);
		btnPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				salvarEscola();

				int proximoId = escolaSelecionada.getId() + 1;
				escolaSelecionada = escolaDao.acessaEscolaById(proximoId, listaSelecionada);

				if (escolaSelecionada.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Esta é a última escola da lista");
					escolaSelecionada = escolaDao.acessaEscolaById(proximoId - 1, listaSelecionada);
				}
				mostraEscola(escolaSelecionada);

			}
		});
		btnPro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPro.setBounds(131, 557, 60, 31);
		panel.add(btnPro);

		listas = escolaDao.getListas();

		comboBox = new JComboBox<>(listas);
		comboBox.setBounds(10, 557, 111, 31);
		panel.add(comboBox);

		comboBox.addActionListener(e -> {
			
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			String selected = (String) cb.getSelectedItem();

			escolaSelecionada = escolaDao.acessaLista(selected);
			mostraEscola(escolaSelecionada);
			listaSelecionada = escolaSelecionada.getLista();

			btnAnterior.setEnabled(true);
			btnPro.setEnabled(true);
			btnAtualizar.setEnabled(true);
			mntmSalvar.setEnabled(true);

		});

		btnAtualizar.setText("Atualizar (F5)");
		btnAtualizar.setEnabled(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				atualizar();

			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAtualizar.setBounds(10, 527, 111, 31);
		panel.add(btnAtualizar);

	}

	public void mostraEscola(Escola escola) {

		txtId.setText(String.valueOf(escola.getId()));
		txtEscola.setText(escola.getNome());
		txtInep.setText(String.valueOf(escola.getInep()));
		txtUf.setText(escola.getUf());
		txtMunicipio.setText(escola.getMunicipio());
		txtEndereco.setText(escola.getEndereco());
		txtTelefone.setText(escola.getTelefone());
		txtPorte.setText(escola.getPorte());
		txtEmeo.setText(escola.getEmeo());
		txtCnpj.setText(String.valueOf(escola.getCnpj()));
		txtEmail.setText(escola.getEmail());
		txtTelefone2.setText(escola.getTelefone2());
		txtTelefone3.setText(escola.getTelefone3());
		txtAnotacoes.setText(escola.getAnotacoes());
		txtTelefone4.setText(escola.getTelefone4());
		txtAgendaDigital.setText(escola.getAgendaDigital());
		txtSistemaDeEnsino.setText(escola.getSistemaDeEnsino());
		txtInstagram.setText(escola.getInstagram());
		txtSite.setText(escola.getSite());

	}

	public void setUltimaLista(String ultimaLista) {

		ultimaListaAdicionada = ultimaLista;
		System.out.println(ultimaListaAdicionada);

	}

	public int salvarEscola() {

		escolaSelecionada = new Escola(escolaSelecionada.getId(), listaSelecionada, txtEscola.getText(),
				Integer.valueOf(txtInep.getText()), txtUf.getText(), txtMunicipio.getText(), txtEndereco.getText(),
				txtTelefone.getText(), txtPorte.getText(), txtEmeo.getText(), Integer.valueOf(txtCnpj.getText()),
				txtEmail.getText(), txtTelefone2.getText(), txtTelefone3.getText(), txtTelefone4.getText(),
				txtSite.getText(), txtInstagram.getText(), txtSistemaDeEnsino.getText(), txtAgendaDigital.getText(),
				txtAnotacoes.getText());

		if (escolaDao.atualizaEscola(escolaSelecionada) != 0) {
			return 1;
		} else {
			return 0;
		}

	}

	public void atualizar() {

		listas = escolaDao.getListas();

		comboBox.removeAllItems();
		for (String lista : listas) {
			comboBox.addItem(lista);
		}

		escolaDao.acessaLista(listaSelecionada);
		comboBox.setSelectedItem(listas[listas.length - 1]);

	}

	/**
	 * Obtendo cliques nas teclas
	 */

	public void nativeKeyPressed(NativeKeyEvent e) {

		keysList.put(NativeKeyEvent.VC_1, txtEscola);
		keysList.put(NativeKeyEvent.VC_2, txtInep);
		keysList.put(NativeKeyEvent.VC_3, txtUf);
		keysList.put(NativeKeyEvent.VC_4, txtMunicipio);
		keysList.put(NativeKeyEvent.VC_5, txtEndereco);
		keysList.put(NativeKeyEvent.VC_6, txtTelefone);
		keysList.put(NativeKeyEvent.VC_7, txtPorte);
		keysList.put(NativeKeyEvent.VC_8, txtEmeo);
		keysList.put(NativeKeyEvent.VC_9, txtCnpj);
		keysList.put(NativeKeyEvent.VC_Q, txtEmail);
		keysList.put(NativeKeyEvent.VC_W, txtTelefone2);
		keysList.put(NativeKeyEvent.VC_E, txtTelefone3);
		keysList.put(NativeKeyEvent.VC_R, txtTelefone4);
		keysList.put(NativeKeyEvent.VC_T, txtAgendaDigital);
		keysList.put(NativeKeyEvent.VC_Y, txtSistemaDeEnsino);
		keysList.put(NativeKeyEvent.VC_U, txtInstagram);
		keysList.put(NativeKeyEvent.VC_I, txtSite);

			
		for (Map.Entry<Integer, JTextField> entry : keysList.entrySet()) {

			int chave = entry.getKey();
			JTextField textField = entry.getValue();

			if (e.getModifiers() == (NativeKeyEvent.CTRL_L_MASK | NativeKeyEvent.SHIFT_L_MASK) && e.getKeyCode() == chave) {
				// O operador | combina os bits dos dois valores, o que permite verificar se ambos estão presentes nos modificadores.
				frame.setExtendedState(JFrame.ICONIFIED);
				frame.setExtendedState(JFrame.NORMAL);
				textField.setText(colaClipboard());
			}

			if (e.getModifiers() == NativeKeyEvent.ALT_L_MASK && e.getKeyCode() == chave) {
				copiaParaClipboard(textField.getText());
			}

		}

		if (e.getKeyCode() == NativeKeyEvent.VC_F5) {

			atualizar();
			System.out.println("F5");

		}

		if (e.getModifiers() == NativeKeyEvent.CTRL_L_MASK && e.getKeyCode() == NativeKeyEvent.VC_DOWN) {

			if (btnPro.isEnabled()) {

				salvarEscola();

				int proximoId = escolaSelecionada.getId() + 1;
				escolaSelecionada = escolaDao.acessaEscolaById(proximoId, listaSelecionada);

				if (escolaSelecionada.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Esta é a última escola da lista");
					escolaSelecionada = escolaDao.acessaEscolaById(proximoId - 1, listaSelecionada);
				}
				mostraEscola(escolaSelecionada);

			}

		}

		if (e.getModifiers() == NativeKeyEvent.CTRL_L_MASK && e.getKeyCode() == NativeKeyEvent.VC_UP) {

			if (btnAnterior.isEnabled()) {

				salvarEscola();

				int anteriorId = escolaSelecionada.getId() - 1;
				escolaSelecionada = escolaDao.acessaEscolaById(anteriorId, listaSelecionada);

				if (escolaSelecionada.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Esta é a primeira escola da lista");
					escolaSelecionada = escolaDao.acessaEscolaById(anteriorId + 1, listaSelecionada);
				}
				mostraEscola(escolaSelecionada);

			}

		}

		if (e.getModifiers() == NativeKeyEvent.CTRL_L_MASK && e.getKeyCode() == NativeKeyEvent.VC_S) {

			if (escolaSelecionada != null) {
				if (salvarEscola() == 1) {
					JOptionPane.showMessageDialog(null, "Escola salva com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar escola");
				}
			}

		}

	}

	public void nativeKeyReleased(NativeKeyEvent e) {

		if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
			ctrlPressed = false;
		}

	}

	public void atualizaCampos() {

		txtEscola.setText(txtEscola.getText());
		txtInep.setText(txtInep.getText());
		txtUf.setText(txtUf.getText());
		txtMunicipio.setText(txtMunicipio.getText());
		txtEndereco.setText(txtEndereco.getText());
		txtTelefone.setText(txtTelefone.getText());
		txtPorte.setText(txtPorte.getText());
		txtEmeo.setText(txtEmeo.getText());
		txtCnpj.setText(txtCnpj.getText());
		txtEmail.setText(txtEmail.getText());
		txtTelefone2.setText(txtTelefone2.getText());
		txtTelefone3.setText(txtTelefone3.getText());
		txtTelefone4.setText(txtTelefone4.getText());
		txtAgendaDigital.setText(txtAgendaDigital.getText());
		txtSistemaDeEnsino.setText(txtSistemaDeEnsino.getText());
		txtInstagram.setText(txtInstagram.getText());
		txtSite.setText(txtSite.getText());

	}

	public String colaClipboard() {

		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			Transferable transferable = clipboard.getContents(null);

			if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String clipboardText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
				return clipboardText;
			} else {
				return "";
			}
		} catch (UnsupportedFlavorException | IOException ex) {
			ex.printStackTrace();
			return "";
		}

	}
	
	public void copiaParaClipboard(String valorDoCampo) {

        // Obtém o toolkit padrão do sistema
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Obtém a área de transferência do sistema
        Clipboard clipboard = toolkit.getSystemClipboard();

        // Cria um conteúdo transferível com o texto desejado
        Transferable transferableText = new StringSelection(valorDoCampo);

        // Define o conteúdo na área de transferência
        clipboard.setContents(transferableText, null);

		
	}
}
