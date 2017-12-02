package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import GUI.FileChooserListener.FileType;

public class GUI {

	//Frame
	private JFrame frame;

	//JTexfield para os caminhos dos ficheiros
	private JTextField tf_rules;
	private JTextField tf_ham;
	private JTextField tf_spam;

	//Ficheiros
	private File rules;
	private File ham;
	private File spam;

	//JTextfields avaliadores
	private JTextField fp_manual;
	private JTextField fn_manual;
	private JTextField fp_automatico;
	private JTextField fn_automatico;

	//Butões
	private static ArrayList<JButton> lista_de_botoes;





	/*
	 * Listas lógicas para base de representação gráfica, estáticas para serem acedidas livremente pelas tabelas de expansão
	 */

	private static DefaultTableModel lista_regras_pesos_manual;

	private static DefaultTableModel lista_regras_pesos_automatico;



	public GUI(int x, int y) {

		frame = new JFrame("Configuração do serviço o de filtragem anti-spam");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Projeto_ES_2.0/src/GUI/ES.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 0));
		addFrameContent();
		frame.pack();
		frame.setSize(x, y);
		frame.setVisible(true);

	}

	private void addFrameContent() {
		lista_de_botoes	= new ArrayList<JButton>();

		addFirstPanel();
		addSecondPanel();
		addThirdPanel();
	}

	private void addFirstPanel() {
		//1. Criação de painel inicial dirigido aos ficheiros
		JPanel ficheiros = new JPanel();
		ficheiros.setLayout(new BorderLayout());
		frame.add(ficheiros);

		//1.1. Titulo do primeiro painel
		JLabel lblCaminhosParaFicheiro = new JLabel("Caminhos para ficheiros de configuração:");
		lblCaminhosParaFicheiro.setFont(new Font(" ", Font.BOLD, 15));
		lblCaminhosParaFicheiro.setHorizontalAlignment(JLabel.CENTER);
		ficheiros.add(lblCaminhosParaFicheiro, BorderLayout.NORTH);

		//Criação das Labels referentes a cada tipo de ficheiro
		JPanel TiTulosParaFicheiros = new JPanel();
		TiTulosParaFicheiros.setLayout(new GridLayout(3,0));
		JLabel rules= new JLabel(" Regras: ");
		rules.setFont(new Font("", Font.BOLD,13));
		rules.setHorizontalAlignment(JLabel.CENTER);
		JLabel ham= new JLabel(" Ham: ");
		ham.setFont(new Font("", Font.BOLD,13));
		ham.setHorizontalAlignment(JLabel.CENTER);
		JLabel spam= new JLabel(" Spam: ");
		spam.setFont(new Font("", Font.BOLD,13));
		spam.setHorizontalAlignment(JLabel.CENTER);

		TiTulosParaFicheiros.add(rules);
		TiTulosParaFicheiros.add(ham);
		TiTulosParaFicheiros.add(spam);

		ficheiros.add(TiTulosParaFicheiros,BorderLayout.WEST);

		//1.2. Criação de painel para a escolha de ficheiros rules.cf, ham.log e spam.log
		JPanel caminhos_ficheiros = new JPanel();
		caminhos_ficheiros.setLayout(new GridLayout(3, 0));
		ficheiros.add(caminhos_ficheiros, BorderLayout.CENTER);

		//1.2.1. Criação das JTextField com os caminhos para cada ficheiro
		tf_rules = new JTextField();
		tf_rules.setEnabled(false);
		caminhos_ficheiros.add(tf_rules);


		tf_rules.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				Leitor leitor_rules = new Leitor(tf_rules.getText());
				leitor_rules.start();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				//metodo somente necessário por ser import
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {	
				//metodo somente necessário por ser import
			}
		});


		tf_ham = new JTextField();
		tf_ham.setEnabled(false);
		caminhos_ficheiros.add(tf_ham);

		tf_spam = new JTextField();
		tf_spam.setEnabled(false);
		caminhos_ficheiros.add(tf_spam);

		//1.3. Criação de painel para o botoes de procura dos ficehiros rules.cf, ham.log e spam.log
		JPanel Botoes_ficheiros = new JPanel();
		ficheiros.add(Botoes_ficheiros, BorderLayout.EAST);
		Botoes_ficheiros.setLayout(new GridLayout(0, 1));

		//1.3.1. Criação dos botões para cada tipo de ficheiro
		JButton rules_cf = new JButton("Procurar Ficheiro de Regras");
		rules_cf.addActionListener(new FileChooserListener(tf_rules,this.rules,FileType.RULES));
		Botoes_ficheiros.add(rules_cf);


		JButton ham_log = new JButton("Procurar Ficheiro de Ham");
		ham_log.addActionListener(new FileChooserListener(tf_ham,this.ham,FileType.HAM));
		Botoes_ficheiros.add(ham_log);


		JButton spam_log = new JButton("Procurar Ficheiro de Spam");
		spam_log.addActionListener(new FileChooserListener(tf_spam,this.spam,FileType.SPAM));
		Botoes_ficheiros.add(spam_log);

		//1.4. Separador de paineis
		JSeparator separator = new JSeparator();
		ficheiros.add(separator, BorderLayout.SOUTH);

	}

	private void addSecondPanel() {
		//2. Criação de painel inicial dirigido à configuração manual
		JPanel manual = new JPanel();
		frame.add(manual);
		manual.setLayout(new BorderLayout());

		//2.1. Criação de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_manual = new JPanel();
		configuracao_manual.setLayout(new BorderLayout());

		//2.1.1. Construção da tabela

		String[] columnNames = {"Regras","Pesos"};
		lista_regras_pesos_manual = new DefaultTableModel(columnNames,0);

		JTable tabela_regras_manual = new JTable(lista_regras_pesos_manual);
		tabela_regras_manual.setGridColor(Color.black);

		JScrollPane scroll_tabela = new JScrollPane(tabela_regras_manual);
		configuracao_manual.add(scroll_tabela, BorderLayout.CENTER);

		//2.1.2 Botão de expansão
		JButton expansão_manual = new JButton("Expandir");
		//começar sem ser editável
		expansão_manual.setEnabled(false);
		lista_de_botoes.add(expansão_manual);
		expansão_manual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame_expandida = new JFrame("Tabela de configuração manual");
				frame_expandida.setLayout(new BorderLayout());
				frame_expandida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Apresentação da lista na nova janela
				JScrollPane scrool_tabela_expandida= new JScrollPane(new JTable(lista_regras_pesos_manual));
				frame_expandida.add(scrool_tabela_expandida,BorderLayout.CENTER);
				frame_expandida.pack();
				frame_expandida.setSize(1000,800);
				frame_expandida.setVisible(true);
			}
		});

		configuracao_manual.add(expansão_manual,BorderLayout.WEST);
		manual.add(configuracao_manual, BorderLayout.CENTER);

		//2.1.3. Criação de painel para falsos positivos e falsos negativos
		JPanel falsos_manual = new JPanel();
		configuracao_manual.add(falsos_manual, BorderLayout.SOUTH);

		//2.1.3.1. Adicionar os falsos ao painel
		JLabel lblFP_manual = new JLabel("Falsos Positivos:");
		lblFP_manual.setHorizontalAlignment(SwingConstants.CENTER);
		falsos_manual.add(lblFP_manual);

		fp_manual = new JTextField();
		fp_manual.setEnabled(false);
		fp_manual.setText("0");
		fp_manual.setHorizontalAlignment(SwingConstants.CENTER);
		fp_manual.setColumns(2);
		falsos_manual.add(fp_manual);

		JLabel lblFN_manual = new JLabel("Falsos Negativos:");
		falsos_manual.add(lblFN_manual);

		fn_manual = new JTextField();
		fn_manual.setEnabled(false);
		fn_manual.setText("0");
		fn_manual.setHorizontalAlignment(SwingConstants.CENTER);
		fn_manual.setColumns(2);
		falsos_manual.add(fn_manual);

		//2.2. Criação de painel para os botões da configuração manual
		JPanel buttons_configuracao_manual = new JPanel();
		buttons_configuracao_manual.setLayout(new GridLayout(3, 0));
		manual.add(buttons_configuracao_manual, BorderLayout.EAST);

		//2.2.1. Adicionar os botões ao painel
		JButton btnGerarConfigurcao = new JButton("Gerar Configuração");
		buttons_configuracao_manual.add(btnGerarConfigurcao);
		//começar sem ser editável
		btnGerarConfigurcao.setEnabled(false);
		lista_de_botoes.add(btnGerarConfigurcao);


		JButton btnAvaliarConfiguracao = new JButton("Avaliar Configuração");
		buttons_configuracao_manual.add(btnAvaliarConfiguracao);
		//começar sem ser editável
		btnAvaliarConfiguracao.setEnabled(false);
		lista_de_botoes.add(btnAvaliarConfiguracao);

		JButton btnGravarConfigurcaoRulescf_manual = new JButton("           Gravar Configuração          ");
		buttons_configuracao_manual.add(btnGravarConfigurcaoRulescf_manual);
		//começar sem ser editável
		btnGravarConfigurcaoRulescf_manual.setEnabled(false);
		lista_de_botoes.add(btnGravarConfigurcaoRulescf_manual);


	}

	private void addThirdPanel() {

		//3. Criação de painel inicial dirigido à configuração automática
		JPanel automatico = new JPanel();
		automatico.setLayout(new BorderLayout());
		frame.add(automatico);

		//3.1. Criação de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_automatica = new JPanel();
		configuracao_automatica.setLayout(new BorderLayout());
		automatico.add(configuracao_automatica, BorderLayout.CENTER);

		//2.1.1. Construção da tabela

		String[] columnNames = {"Regras","Pesos"};
		lista_regras_pesos_automatico= new DefaultTableModel(columnNames,0);


		JTable tabela_regras_automatico = new JTable(lista_regras_pesos_automatico);
		tabela_regras_automatico.setGridColor(Color.black);


		JScrollPane scroll_tabela_automatica = new JScrollPane(tabela_regras_automatico);
		configuracao_automatica.add(scroll_tabela_automatica, BorderLayout.CENTER);

		//2.1.2 Botão de expansão
		JButton expansão_automatico = new JButton("Expandir");
		//começar sem ser editável
		expansão_automatico.setEnabled(false);
		lista_de_botoes.add(expansão_automatico);

		expansão_automatico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame_expandida = new JFrame("Tabela de configuração Automática");
				frame_expandida.setLayout(new BorderLayout());
				frame_expandida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Apresentação da lista na nova janela
				JScrollPane scrool_tabela_expandida= new JScrollPane(new JTable(lista_regras_pesos_automatico));
				frame_expandida.add(scrool_tabela_expandida,BorderLayout.CENTER);
				frame_expandida.pack();
				frame_expandida.setSize(1000,800);
				frame_expandida.setVisible(true);
			}
		});

		configuracao_automatica.add(expansão_automatico,BorderLayout.WEST);
		automatico.add(configuracao_automatica, BorderLayout.CENTER);


		//3.1.2. Criação de painel para falsos positivos e falsos negativos
		JPanel falsos_automatico = new JPanel();
		configuracao_automatica.add(falsos_automatico, BorderLayout.SOUTH);

		//3.1.2.1. Adicionar os falsos ao painel
		JLabel lblFP_automatico = new JLabel("Falsos Positivos:");
		falsos_automatico.add(lblFP_automatico);

		fp_automatico = new JTextField();
		fp_automatico.setEnabled(false);
		fp_automatico.setText("0");
		fp_automatico.setHorizontalAlignment(SwingConstants.CENTER);
		fp_automatico.setColumns(2);
		falsos_automatico.add(fp_automatico);

		JLabel lblFN_automatico = new JLabel("Falsos Negativos:");
		falsos_automatico.add(lblFN_automatico);

		fn_automatico = new JTextField();
		fn_automatico.setEnabled(false);
		fn_automatico.setText("0");
		fn_automatico.setHorizontalAlignment(SwingConstants.CENTER);
		fn_automatico.setColumns(2);
		falsos_automatico.add(fn_automatico);

		//3.2. Criação de painel para os botões da configuração manual
		JPanel buttons_configuracao_automatica = new JPanel();
		buttons_configuracao_automatica.setLayout(new GridLayout(3, 0));
		automatico.add(buttons_configuracao_automatica, BorderLayout.EAST);


		//3.2.1. Adicionar os botões ao painel
		JButton btnGerarConfigurcaoAutomtica = new JButton("Gerar Conf. Automática c/ JMetal");
		//começar sem ser editável
		btnGerarConfigurcaoAutomtica.setEnabled(false);
		lista_de_botoes.add(btnGerarConfigurcaoAutomtica);
		btnGerarConfigurcaoAutomtica.setFont(new Font("", Font.BOLD, 11));
		buttons_configuracao_automatica.add(btnGerarConfigurcaoAutomtica);

		JButton btnGravarConfigurcaoRulescf_automatico = new JButton("Gravar Configuração");
		//começar sem ser editável
		btnGravarConfigurcaoRulescf_automatico.setEnabled(false);
		lista_de_botoes.add(btnGravarConfigurcaoRulescf_automatico);
		buttons_configuracao_automatica.add(btnGravarConfigurcaoRulescf_automatico);

		//3.3. Separador de paineis
		JSeparator separator_1 = new JSeparator();
		automatico.add(separator_1, BorderLayout.NORTH);

	}



	/*
	 * Acesso controlado das listas
	 */
	public static DefaultTableModel getLista_regras_pesos_manual() {
		return lista_regras_pesos_manual;
	}

	public static DefaultTableModel getLista_regras_pesos_automatico() {
		return lista_regras_pesos_automatico;
	}

	public static void ActivateButons() {
		for (int i = 0; i < lista_de_botoes.size(); i++) {
			lista_de_botoes.get(i).setEnabled(true);
		}

	}
}