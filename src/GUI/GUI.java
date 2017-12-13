package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import GUI.ButtonOpListener.Option;
import GUI.FileChooserListener.FileType;
import InputOutput.Leitor;

public class GUI {

	//Frame
	private JFrame frame;

	//JTexfield para os caminhos dos ficheiros
	private static JTextField tf_rules;
	private static JTextField tf_ham;
	private static JTextField tf_spam;

	//Ficheiros
	private static File rules;
	private static File ham;
	private static File spam;

	//JTextfields avaliadores
	private static JTextField fp_manual;
	private static JTextField fn_manual;
	private static JTextField fp_automatico;
	private static JTextField fn_automatico;

	//But�es
	private static ArrayList<JButton> lista_de_botoes;


	/*
	 * Listas l�gicas para base de representa��o gr�fica, est�ticas para serem acedidas livremente pelas tabelas de expans�o
	 */

	private static DefTableModel lista_regras_pesos_manual;
	private static DefTableModel lista_regras_pesos_automatico;

	private static Map<String,Double> mapa;




	public GUI(int x, int y) {

		frame = new JFrame("Configura��o do servi�o de filtragem anti-spam");
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/ES1-2017/Images/2814.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 0));
		
		lista_de_botoes	= new ArrayList<JButton>();
		mapa = new HashMap<String, Double>();
		
		addFrameContent();
		frame.pack();
		frame.setSize(x, y);
		frame.setVisible(true);

	}

	private void addFrameContent() {

		addFirstPanel();
		addSecondPanel();
		addThirdPanel();
	}

	private void addFirstPanel() {
		//1. Cria��o de painel inicial dirigido aos ficheiros
		JPanel ficheiros = new JPanel();
		ficheiros.setLayout(new BorderLayout());
		frame.add(ficheiros);

		//1.1. Titulo do primeiro painel
		JLabel lblCaminhosParaFicheiro = new JLabel("Caminhos para ficheiros de configura��o:");
		lblCaminhosParaFicheiro.setFont(new Font(" ", Font.BOLD, 15));
		lblCaminhosParaFicheiro.setHorizontalAlignment(JLabel.CENTER);
		ficheiros.add(lblCaminhosParaFicheiro, BorderLayout.NORTH);

		//Cria��o das Labels referentes a cada tipo de ficheiro
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

		//1.2. Cria��o de painel para a escolha de ficheiros rules.cf, ham.log e spam.log
		JPanel caminhos_ficheiros = new JPanel();
		caminhos_ficheiros.setLayout(new GridLayout(3, 0));
		ficheiros.add(caminhos_ficheiros, BorderLayout.CENTER);

		//1.2.1. Cria��o das JTextField com os caminhos para cada ficheiro
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
				//metodo somente necess�rio por ser import
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				//metodo somente necess�rio por ser import

			}	
		});


		tf_ham = new JTextField();
		tf_ham.setEnabled(false);
		caminhos_ficheiros.add(tf_ham);

		tf_ham.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {

				// cole��o para ham
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}
		});

		tf_spam = new JTextField();
		tf_spam.setEnabled(false);
		caminhos_ficheiros.add(tf_spam);

		tf_spam.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// Tcole��o para spam
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}
		});

		//1.3. Cria��o de painel para o botoes de procura dos ficehiros rules.cf, ham.log e spam.log
		JPanel Botoes_ficheiros = new JPanel();
		ficheiros.add(Botoes_ficheiros, BorderLayout.EAST);
		Botoes_ficheiros.setLayout(new GridLayout(0, 1));

		//1.3.1. Cria��o dos bot�es para cada tipo de ficheiro
		JButton rules_cf = new JButton("Procurar Ficheiro de Regras");
		rules_cf.addActionListener(new FileChooserListener(tf_rules,GUI.rules,FileType.RULES));
		Botoes_ficheiros.add(rules_cf);
		//teste
		lista_de_botoes.add(rules_cf);


		JButton ham_log = new JButton("Procurar Ficheiro de Ham");
		ham_log.addActionListener(new FileChooserListener(tf_ham,GUI.ham,FileType.HAM));
		Botoes_ficheiros.add(ham_log);
		//teste
				lista_de_botoes.add(ham_log);


		JButton spam_log = new JButton("Procurar Ficheiro de Spam");
		spam_log.addActionListener(new FileChooserListener(tf_spam,GUI.spam,FileType.SPAM));
		Botoes_ficheiros.add(spam_log);
		//teste
				lista_de_botoes.add(spam_log);

		//1.4. Separador de paineis
		JSeparator separator = new JSeparator();
		ficheiros.add(separator, BorderLayout.SOUTH);

	}

	private void addSecondPanel() {
		//2. Cria��o de painel inicial dirigido � configura��o manual
		JPanel manual = new JPanel();
		frame.add(manual);
		manual.setLayout(new BorderLayout());

		//2.1. Cria��o de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_manual = new JPanel();
		configuracao_manual.setLayout(new BorderLayout());

		//2.1.1. Constru��o da tabela

		String[] columnNames = {"Regras","Pesos"};
		lista_regras_pesos_manual = new DefTableModel(columnNames,0);

		JTable tabela_regras_manual = new JTable(lista_regras_pesos_manual);
		tabela_regras_manual.setGridColor(Color.black);

		JScrollPane scroll_tabela = new JScrollPane(tabela_regras_manual);
		configuracao_manual.add(scroll_tabela, BorderLayout.CENTER);

		//2.1.2. Cria��o de painel para falsos positivos e falsos negativos
		JPanel falsos_manual = new JPanel();
		configuracao_manual.add(falsos_manual, BorderLayout.SOUTH);

		//2.1.2.1. Adicionar os falsos ao painel
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

		//2.2. Cria��o de painel para os bot�es da configura��o manual
		JPanel buttons_configuracao_manual = new JPanel();
		buttons_configuracao_manual.setLayout(new GridLayout(4, 0));
		manual.add(buttons_configuracao_manual, BorderLayout.EAST);

		//2.2.1. Adicionar os bot�es ao painel
		JButton btnGerarConfigurcao = new JButton("Gerar Configura��o");
		buttons_configuracao_manual.add(btnGerarConfigurcao);
		//come�ar sem ser edit�vel
		btnGerarConfigurcao.setEnabled(false);
		btnGerarConfigurcao.addActionListener(new ButtonOpListener(Option.GERAR));
		lista_de_botoes.add(btnGerarConfigurcao);

		JButton btnAvaliarConfiguracao = new JButton("Avaliar Configura��o");
		buttons_configuracao_manual.add(btnAvaliarConfiguracao);
		//come�ar sem ser edit�vel
		btnAvaliarConfiguracao.setEnabled(false);
		lista_de_botoes.add(btnAvaliarConfiguracao);
		btnAvaliarConfiguracao.addActionListener(new ButtonOpListener(Option.AVALIAR));

		JButton btnGravarConfigurcaoRulescf_manual = new JButton("           Gravar Configura��o          ");
		buttons_configuracao_manual.add(btnGravarConfigurcaoRulescf_manual);
		//come�ar sem ser edit�vel
		btnGravarConfigurcaoRulescf_manual.setEnabled(false);
		lista_de_botoes.add(btnGravarConfigurcaoRulescf_manual);
		btnGravarConfigurcaoRulescf_manual.addActionListener(new ButtonOpListener(Option.GRAVAR));

		JButton btnResetValores = new JButton("Inicializar Configura��o");
		buttons_configuracao_manual.add(btnResetValores);
		btnResetValores.setEnabled(false);
		lista_de_botoes.add(btnResetValores);
		btnResetValores.addActionListener(new ButtonOpListener(Option.INICIALIZAR));

		//2.3 Bot�o de expans�o
		JButton expans�o_manual = new JButton("Expandir");
		//come�ar sem ser edit�vel
		expans�o_manual.setEnabled(false);
		lista_de_botoes.add(expans�o_manual);

		expans�o_manual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame_expandida = new JFrame("Tabela de configura��o manual");
				frame_expandida.setLayout(new BorderLayout());
				frame_expandida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Apresenta��o da lista na nova janela
				JScrollPane scrool_tabela_expandida= new JScrollPane(new JTable(lista_regras_pesos_manual));
				frame_expandida.add(scrool_tabela_expandida,BorderLayout.CENTER);
				//Apresenta��o dos bot�es
				JPanel painel_bot�es_expandido= new JPanel(new GridLayout(1,4));
				/*
				 * Cria��o de bot�es replica, pois a pr�pria implementa��o JButton, n�o
				 * permite instancia��o m�ltipla, como a DefaultModelList.
				 * Ou seja, estes n�o podem existir em "2" lados ao emsmo tempo. 
				 */
				JButton btnGerarConfigurcao_expandido = new JButton("Gerar Configura��o");
				btnGerarConfigurcao_expandido.addActionListener(new ButtonOpListener(Option.GERAR));
				painel_bot�es_expandido.add(btnGerarConfigurcao_expandido);
				JButton btnAvaliarConfiguracao_expandido = new JButton("Avaliar Configura��o");
				btnAvaliarConfiguracao_expandido.addActionListener(new ButtonOpListener(Option.AVALIAR));
				painel_bot�es_expandido.add(btnAvaliarConfiguracao_expandido);
				JButton btnGravarConfigurcaoRulescf_manual_expandido = new JButton("Gravar Configura��o");
				btnGravarConfigurcaoRulescf_manual_expandido.addActionListener(new ButtonOpListener(Option.GRAVAR));
				painel_bot�es_expandido.add(btnGravarConfigurcaoRulescf_manual_expandido);
				JButton btnResetValores_expandido = new JButton("Inicializar Configura��o");
				btnResetValores_expandido.addActionListener(new ButtonOpListener(Option.INICIALIZAR));
				painel_bot�es_expandido.add(btnResetValores_expandido);

				frame_expandida.add(painel_bot�es_expandido, BorderLayout.SOUTH);
				frame_expandida.pack();
				frame_expandida.setSize(1000,600);
				frame_expandida.setVisible(true);
			}
		});

		configuracao_manual.add(expans�o_manual,BorderLayout.WEST);
		manual.add(configuracao_manual, BorderLayout.CENTER);

	}

	private void addThirdPanel() {

		//3. Cria��o de painel inicial dirigido � configura��o autom�tica
		JPanel automatico = new JPanel();
		automatico.setLayout(new BorderLayout());
		frame.add(automatico);

		//3.1. Cria��o de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_automatica = new JPanel();
		configuracao_automatica.setLayout(new BorderLayout());
		automatico.add(configuracao_automatica, BorderLayout.CENTER);

		//2.1.1. Constru��o da tabela

		String[] columnNames = {"Regras","Pesos"};
		lista_regras_pesos_automatico= new DefTableModel(columnNames,0);



		JTable tabela_regras_automatico = new JTable(lista_regras_pesos_automatico);
		tabela_regras_automatico.setGridColor(Color.black);


		JScrollPane scroll_tabela_automatica = new JScrollPane(tabela_regras_automatico);
		configuracao_automatica.add(scroll_tabela_automatica, BorderLayout.CENTER);

		//2.1.2 Bot�o de expans�o
		JButton expans�o_automatico = new JButton("Expandir");
		//come�ar sem ser edit�vel
		expans�o_automatico.setEnabled(false);
		lista_de_botoes.add(expans�o_automatico);

		expans�o_automatico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame_expandida = new JFrame("Tabela de configura��o Autom�tica");
				frame_expandida.setLayout(new BorderLayout());
				frame_expandida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Apresenta��o da lista na nova janela
				JScrollPane scrool_tabela_expandida= new JScrollPane(new JTable(lista_regras_pesos_automatico));
				frame_expandida.add(scrool_tabela_expandida,BorderLayout.CENTER);
				frame_expandida.pack();
				frame_expandida.setSize(1000,700);
				frame_expandida.setVisible(true);
			}
		});

		configuracao_automatica.add(expans�o_automatico,BorderLayout.WEST);
		automatico.add(configuracao_automatica, BorderLayout.CENTER);


		//3.1.2. Cria��o de painel para falsos positivos e falsos negativos
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

		//3.2. Cria��o de painel para os bot�es da configura��o manual
		JPanel buttons_configuracao_automatica = new JPanel();
		buttons_configuracao_automatica.setLayout(new GridLayout(3, 0));
		automatico.add(buttons_configuracao_automatica, BorderLayout.EAST);


		//3.2.1. Adicionar os bot�es ao painel
		JButton btnGerarConfigurcaoAutomtica = new JButton("Gerar Conf. Autom�tica c/ JMetal");
		//come�ar sem ser edit�vel
		btnGerarConfigurcaoAutomtica.setEnabled(false);
		lista_de_botoes.add(btnGerarConfigurcaoAutomtica);
		btnGerarConfigurcaoAutomtica.setFont(new Font("", Font.BOLD, 11));
		buttons_configuracao_automatica.add(btnGerarConfigurcaoAutomtica);

		JButton btnGravarConfigurcaoRulescf_automatico = new JButton("Gravar Configura��o");
		//come�ar sem ser edit�vel
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

	//---------------------------------------

	public static Map<String, Double> getMapa() {
		return mapa;
	}

	
	//---------------------------------------
	// Ativa��o dos but�es inactivos at� se escolher o ficheiro de regras
	public static void ActivateButons() {
		for (int i = 0; i < lista_de_botoes.size(); i++) {
			lista_de_botoes.get(i).setEnabled(true);
		}
	}

	
	
	
	
	//---------------------------------------
	
	public static  JTextField getFp_manual() {
		return fp_manual;
	}

	public static JTextField getFn_manual() {
		return fn_manual;
	}

	public static JTextField getFp_automatico() {
		return fp_automatico;
	}

	public static JTextField getFn_automatico() {
		return fn_automatico;
	}

	//---------------------------------------
	
	public static JTextField getRules() {
		return tf_rules;	
	}
	public static JTextField getHam() {
		return tf_ham;
	}

	public static JTextField getSpam() {
		return tf_spam;
	}

	//---------------------------------------
	public ArrayList<JButton> getLista_de_botoes() {
		return lista_de_botoes;
	}
	
	//---------------------------------------

	public static File GetRulesFile() {
		return rules;
	}
	public static File GetSpamFile() {
		return spam;
	}
	
	public static File GetHamFile() {
		return ham;
	}

	
	//---------------------------------------
	protected static void setRules(File rules) {
		GUI.rules = rules;
	}

	protected static void setHam(File ham) {
		GUI.ham = ham;
	}

	protected static void setSpam(File spam) {
		GUI.spam = spam;
	}



}