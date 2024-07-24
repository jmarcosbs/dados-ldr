package com.projects.dadosldr.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.projects.dadosldr.dao.EscolaDao;
import com.projects.dadosldr.entity.Escola;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDigiteUmNome = new JTextField();
	JButton btnSelecionaLista = new JButton();
	JButton btnAdicionarLista = new JButton();
	JFileChooser chooser =  new JFileChooser();
	JLabel lblNomeArquivo = new JLabel("Selecione a lista");
	EscolaDao escolaDao = new EscolaDao();
	Escola escola = null;
	File arquivoCSV = null;
	String listaAdicionada;
	Main main = new Main();
	JComboBox<String> comboBox = new JComboBox<>();
	String[] listas;
	String listaParaExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
					SwingUtilities.invokeLater(() -> new Settings());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 396, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione o arquivo CSV", "csv");
		chooser.setFileFilter(filter);
		
		btnSelecionaLista = new JButton("Selecionar Lista");
		btnSelecionaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNomeArquivo.setText("Carregando...");
				btnSelecionaLista.setEnabled(false);
				txtDigiteUmNome.setEnabled(false);
				btnAdicionarLista.setEnabled(false);
				
				arquivoCSV = getArquivoLista();
				
				if (arquivoCSV != null) {
					
					lblNomeArquivo.setText(arquivoCSV.getName());
					btnSelecionaLista.setEnabled(true);
					txtDigiteUmNome.setEnabled(true);
					btnAdicionarLista.setEnabled(true);
					
				} else {
					
					lblNomeArquivo.setText("Selecione a lista");
					btnSelecionaLista.setEnabled(true);
					txtDigiteUmNome.setEnabled(true);
					btnAdicionarLista.setEnabled(false);
					
				}
				
				
				
				
			}
		});
		
		btnSelecionaLista.setBounds(10, 92, 177, 29);
		panel.add(btnSelecionaLista);
		
		JLabel lblidentificador = new JLabel("Identificador da lista");
		lblidentificador.setHorizontalAlignment(SwingConstants.CENTER);
		lblidentificador.setBounds(10, 11, 177, 14);
		panel.add(lblidentificador);
		txtDigiteUmNome.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if(!txtDigiteUmNome.getText().equals("") && !lblNomeArquivo.getText().equals("Selecione a lista")) {
					btnAdicionarLista.setEnabled(true);
				} else {
					btnAdicionarLista.setEnabled(false);
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if(!txtDigiteUmNome.getText().equals("") && !lblNomeArquivo.getText().equals("Selecione a lista")) {
					btnAdicionarLista.setEnabled(true);
				} else {
					btnAdicionarLista.setEnabled(false);
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		txtDigiteUmNome.setText("Digite um nome");
		txtDigiteUmNome.setBounds(10, 36, 177, 20);
		panel.add(txtDigiteUmNome);
		txtDigiteUmNome.setColumns(10);
		
		lblNomeArquivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeArquivo.setBounds(10, 67, 177, 14);
		panel.add(lblNomeArquivo);
		
		btnAdicionarLista = new JButton("Adicionar Lista");
		btnAdicionarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (arquivoCSV != null) {
					
					adicionaListaBruta(txtDigiteUmNome.getText(), arquivoCSV);
					
				}
				
			}

		});
		btnAdicionarLista.setEnabled(false);
		btnAdicionarLista.setBounds(10, 143, 177, 55);
		panel.add(btnAdicionarLista);
		
		JLabel lblExcluirLista = new JLabel("Excluir lista");
		lblExcluirLista.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluirLista.setBounds(248, 56, 115, 14);
		panel.add(lblExcluirLista);
		
		listas = escolaDao.getListas();
		
		comboBox = new JComboBox<>(listas);
		comboBox.setBounds(248, 81, 115, 22);
		panel.add(comboBox);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				escolaDao.excluiLista(listaParaExcluir);
				
				listas = escolaDao.getListas();
				comboBox.removeAllItems();
				for (String lista: listas) {
					comboBox.addItem(lista);
				}
				btnExcluir.setEnabled(false);

				
			}
		});
		btnExcluir.setBounds(248, 114, 115, 23);
		panel.add(btnExcluir);
	
		comboBox.addActionListener(e -> {
		
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
	            String selected = (String) cb.getSelectedItem();

	            listaParaExcluir = selected;
	            btnExcluir.setEnabled(true);
	            
	            
	   });

	
	}
	
	public File getArquivoLista() {
		
		int retorno = chooser.showOpenDialog(null);
		
		if (retorno == JFileChooser.APPROVE_OPTION) {
			
			File arquivo = chooser.getSelectedFile();
			
			return arquivo;

		} else {
			
			JOptionPane.showMessageDialog(null, "Arquivo inválido");
			return null;
			
		}
		
	}
	
	public int adicionaListaBruta(String nomeLista, File arquivoCSV) {
		
		Scanner sc = null;

		Escola escola = new Escola();
		
		
		try {
			
			sc = new Scanner(arquivoCSV, "UTF-8");
			
			sc.nextLine(); // Ignora cabeçalho
			
			
			while (sc.hasNextLine()) {
				
				String line2 = sc.nextLine();
				
				// Expressão regular para dividir a linha por vírgulas, ignorando as vírgulas dentro de aspas
		        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

		        // Dividir a linha usando a expressão regular
		        String[] escolaCSV = line2.split(regex, -1);

		        // Remover as aspas duplas (") dos valores
		        for (int i = 0; i < escolaCSV.length; i++) {
		        	escolaCSV[i] = escolaCSV[i].replaceAll("^\"|\"$", "");
		        }

				
				escola.setLista(nomeLista);
				escola.setNome(escolaCSV[1]);
				escola.setInep(Integer.valueOf(escolaCSV[2]));
				escola.setUf(escolaCSV[3]);
				escola.setMunicipio(escolaCSV[4]);
				escola.setMunicipio(escolaCSV[4]);
				escola.setEndereco(escolaCSV[8]);
				escola.setTelefone(escolaCSV[9]);
				escola.setPorte(escolaCSV[14]);
				escola.setEmeo(escolaCSV[15]);
				
				escolaDao.insereEscolaBruta(escola);
				
				}
				
				JOptionPane.showMessageDialog(null, "Lista Adicionada com sucesso!");
				listaAdicionada = txtDigiteUmNome.getText();
				main.setUltimaLista(listaAdicionada);
				main.atualizar();
				
				return 1;
			
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao ler arquivo");
			System.out.println(e.getMessage());
			
			return 0;
			
		} finally {
			
			if (sc != null) {
				sc.close();
			}
			
			dispose();
		}
			
		
	}
	
	public int contaLinhas(File arquivoCSV) {
		
		try {
			
			Scanner sc = new Scanner(arquivoCSV, "UTF-8");
			sc.nextLine(); // Ignora cabeçalho
			
			int tamanhoLista = 0;
	
			while(sc.hasNextLine()) {
				
				sc.nextLine();
				tamanhoLista++;
				
			}
			
			sc.close();

			System.out.println("Tamanho da lista: " + tamanhoLista);
			
			return tamanhoLista;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
			
		}
		
    }
}
