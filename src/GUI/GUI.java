package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import javax.swing.table.DefaultTableModel;

import GUI.FileChooserListener.FileType;

public class GUI {

<<<<<<< HEAD
	//NESTEDCLASS OFF
	
=======
	//NESTEDCLASS

>>>>>>> branch 'master' of https://github.com/mambc-iscteiul/ES1-2017-IC1-70.git

	private JFrame frame;

	//JTEXTFIELDS para os caminhos dos ficheiros
	private JTextField tf_rules;
	private JTextField tf_ham;
	private JTextField tf_spam;
	//FICHEIROS
	private File rules;
	private File ham;
	private File spam;

	//JTEXT avaliadores
	private JTextField fp_manual;
	private JTextField fn_manual;
	private JTextField fp_automatico;
	private JTextField fn_automatico;


	//static para ser acedida de uma forma paralela nas janelas de espansıes
	static DefaultTableModel lista_regras_pesos_manual;
	
	static DefaultTableModel lista_regras_pesos_automatico;


	public GUI(int x, int y) {

		frame = new JFrame("ConfiguraÁ„o do serviÁo o de filtragem anti-spam");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Projeto_ES_2.0/src/GUI/ES.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 0));
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

	private void addThirdPanel() {
		//3. Cria√ß√£o de painel inicial dirigido √† configura√ß√£o autom√°tica
		JPanel automatico = new JPanel();
		automatico.setLayout(new BorderLayout());
		frame.add(automatico);

		//3.1. Cria√ß√£o de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_automatica = new JPanel();
		configuracao_automatica.setLayout(new BorderLayout());
		automatico.add(configuracao_automatica, BorderLayout.CENTER);

	
		//3.1.2. Cria√ß√£o de painel para falsos positivos e falsos negativos
		JPanel falsos_automatico = new JPanel();
		configuracao_automatica.add(falsos_automatico, BorderLayout.SOUTH);

		//3.1.2.1. Adicionar os falsos ao painel
		JLabel lblFP_automatico = new JLabel("Falsos Positivos:");
		falsos_automatico.add(lblFP_automatico);

		fp_automatico = new JTextField();	
		fp_automatico.setEnabled(false);
		fp_automatico.setText("2");
		fp_automatico.setHorizontalAlignment(SwingConstants.CENTER);
		fp_automatico.setColumns(2);
		falsos_automatico.add(fp_automatico);

		JLabel lblFN_automatico = new JLabel("Falsos Negativos:");
		falsos_automatico.add(lblFN_automatico);

		fn_automatico = new JTextField();
		fn_automatico.setEnabled(false);
		fn_automatico.setText("3");
		fn_automatico.setHorizontalAlignment(SwingConstants.CENTER);
		fn_automatico.setColumns(2);
		falsos_automatico.add(fn_automatico);

		//3.2. Cria√ß√£o de painel para os bot√µes da configura√ß√£o manual
		JPanel buttons_configuracao_automatica = new JPanel();
		buttons_configuracao_automatica.setLayout(new GridLayout(3, 0));
		automatico.add(buttons_configuracao_automatica, BorderLayout.EAST);


		//3.2.1. Adicionar os bot√µes ao painel
		JButton btnGerarConfigurcaoAutomtica = new JButton("Gerar Conf. Autom·tica c/ JMetal");
		btnGerarConfigurcaoAutomtica.setFont(new Font("", Font.PLAIN, 11));
		buttons_configuracao_automatica.add(btnGerarConfigurcaoAutomtica);

		JButton btnGravarConfigurcaoRulescf_automatico = new JButton("Gravar ConfiguraÁ„o");
		buttons_configuracao_automatica.add(btnGravarConfigurcaoRulescf_automatico);

		//3.3. Separador de paineis
		JSeparator separator_1 = new JSeparator();
		automatico.add(separator_1, BorderLayout.NORTH);

	}
	private void addSecondPanel() {
		//2. Cria√ß√£o de painel inicial dirigido √† configura√ß√£o manual
		JPanel manual = new JPanel();
		frame.add(manual);
		manual.setLayout(new BorderLayout());

		//2.1. Cria√ß√£o de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_manual = new JPanel();
		configuracao_manual.setLayout(new BorderLayout());

		
	
	
		
		//2.1.1ConstruÁ„o da tabela
		String[] columnNames = {"Regras","Pesos"};
		String[] columnNames1 = {"BAYES_00","0.2"};
		String[] columnNames2 = {"FREEMAIL_FROM","0.3"};
		String[] columnNames3 = {"RDNS_NONE","1.3"};
		String[] columnNames4 = {"FREEMAIL_REPLYTO_END_DIGIT","1.2"};
		String[] columnNames5 = {"MSOE_MID_WRONG_CASE","3.2"};
		String[] columnNames6 = {"DATE_IN_PAST_24_48","3.1"};
		String[] columnNames7 = {"T_LOTS_OF_MONEY","0.7"};
		String[] columnNames8 = {"ALL_TRUSTED","3.1"};
		String[] columnNames9 = {"SPF_HELO_FAIL","4.1"};

		lista_regras_pesos_manual = new DefaultTableModel(columnNames,0);
		lista_regras_pesos_manual.addRow(columnNames1);
		lista_regras_pesos_manual.addRow(columnNames2);
		lista_regras_pesos_manual.addRow(columnNames3);
		lista_regras_pesos_manual.addRow(columnNames4);
		lista_regras_pesos_manual.addRow(columnNames5);
		lista_regras_pesos_manual.addRow(columnNames6);
		lista_regras_pesos_manual.addRow(columnNames7);
		lista_regras_pesos_manual.addRow(columnNames8);
		lista_regras_pesos_manual.addRow(columnNames9);

<<<<<<< HEAD
		JTable tabela_regras_manual = new JTable(lista_regras_pesos_manual);
=======
//		for (int i = 0; i < 300; i++) {
//			lista_regras_pesos.addRow(columnNames);
//		}

		JTable tabela_regras_manual = new JTable(lista_regras_pesos);
>>>>>>> branch 'master' of https://github.com/mambc-iscteiul/ES1-2017-IC1-70.git
		tabela_regras_manual.setGridColor(Color.black);

		JScrollPane scroll_tabela = new JScrollPane(tabela_regras_manual);
		configuracao_manual.add(scroll_tabela, BorderLayout.CENTER);

		//2.1.2 Bot„o de expans„o
		JButton expans„o = new JButton("Expandir");
		expans„o.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame_expandida = new JFrame("Tabela de configuraÁ„o manual");
				frame_expandida.setLayout(new BorderLayout());
				frame_expandida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JScrollPane scrool_tabela_expandida= new JScrollPane(new JTable(lista_regras_pesos_manual));
				frame_expandida.add(scrool_tabela_expandida,BorderLayout.CENTER);
				frame_expandida.pack();
				frame_expandida.setSize(1000,800);
				frame_expandida.setVisible(true);
			}
		});
		configuracao_manual.add(expans„o,BorderLayout.WEST);
		manual.add(configuracao_manual, BorderLayout.CENTER);



		//2.1.3. Cria√ß√£o de painel para falsos positivos e falsos negativos
		JPanel falsos_manual = new JPanel();
		configuracao_manual.add(falsos_manual, BorderLayout.SOUTH);

		//2.1.3.1. Adicionar os falsos ao painel
		JLabel lblFP_manual = new JLabel("Falsos Positivos:");
		lblFP_manual.setHorizontalAlignment(SwingConstants.CENTER);
		falsos_manual.add(lblFP_manual);

		fp_manual = new JTextField();
		fp_manual.setEnabled(false);
		fp_manual.setText("4");
		fp_manual.setHorizontalAlignment(SwingConstants.CENTER);
		fp_manual.setColumns(2);
		falsos_manual.add(fp_manual);

		JLabel lblFN_manual = new JLabel("Falsos Negativos:");
		falsos_manual.add(lblFN_manual);

		fn_manual = new JTextField();
		fn_manual.setEnabled(false);
		fn_manual.setText("1");
		fn_manual.setHorizontalAlignment(SwingConstants.CENTER);
		fn_manual.setColumns(2);
		falsos_manual.add(fn_manual);

		//2.2. Cria√ß√£o de painel para os bot√µes da configura√ß√£o manual
		JPanel buttons_configuracao_manual = new JPanel();
		buttons_configuracao_manual.setLayout(new GridLayout(3, 0));
		manual.add(buttons_configuracao_manual, BorderLayout.EAST);

		//2.2.1. Adicionar os bot√µes ao painel
		JButton btnGerarConfigurcao = new JButton("Gerar ConfiguraÁ„o");
		buttons_configuracao_manual.add(btnGerarConfigurcao);

		JButton btnAvaliarConfiguracao = new JButton("Avaliar ConfiguraÁ„o");
		buttons_configuracao_manual.add(btnAvaliarConfiguracao);

		JButton btnGravarConfigurcaoRulescf_manual = new JButton("Gravar ConfiguraÁ„o rules.cf");
		buttons_configuracao_manual.add(btnGravarConfigurcaoRulescf_manual);


	}
	private void addFirstPanel() {
		//1. Cria√ß√£o de painel inicial dirigido aos ficheiros
		JPanel ficheiros = new JPanel();
		ficheiros.setLayout(new BorderLayout());
		frame.add(ficheiros);


		//1.1. T√≠tulo do primeiro painel
		JLabel lblCaminhosParaFicheiro = new JLabel("Caminhos para ficheiros de configuraÁ„o:");
		lblCaminhosParaFicheiro.setFont(new Font(" ", Font.BOLD, 15));
		lblCaminhosParaFicheiro.setHorizontalAlignment(JLabel.CENTER);
		ficheiros.add(lblCaminhosParaFicheiro, BorderLayout.NORTH);

		//CriaÁ„o das Labels referentes a cada tipo de ficheiro
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

		//1.2. Cria√ß√£o de painel para a escolha de ficheiros rules.cf, ham.log e spam.log
		JPanel caminhos_ficheiros = new JPanel();
		caminhos_ficheiros.setLayout(new GridLayout(3, 0));
		ficheiros.add(caminhos_ficheiros, BorderLayout.CENTER);

		//1.2.1. Cria√ß√£o das JTextField com os caminhos para cada ficheiro
		tf_rules = new JTextField();
		tf_rules.setEnabled(false);
		caminhos_ficheiros.add(tf_rules);



		tf_rules.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Leitor leitor_rules = new Leitor(tf_rules.getText());
				leitor_rules.start();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});


		tf_ham = new JTextField();
		tf_ham.setEnabled(false);
		caminhos_ficheiros.add(tf_ham);

		tf_spam = new JTextField();
		tf_spam.setEnabled(false);
		caminhos_ficheiros.add(tf_spam);

		//1.3. Cria√ß√£o de painel para o botoes de procura dos ficehiros rules.cf, ham.log e spam.log
		JPanel Botoes_ficheiros = new JPanel();
		ficheiros.add(Botoes_ficheiros, BorderLayout.EAST);
		Botoes_ficheiros.setLayout(new GridLayout(0, 1));

		//1.3.1. Cria√ß√£o dos botoes para cada tipo de ficheiro
		JButton rules_cf = new JButton("Procurar Ficheiro de Regras");
		rules_cf.addActionListener(new FileChooserListener(tf_rules,this.rules,FileType.RULES));
		Botoes_ficheiros.add(rules_cf);


		/*
		 * Falta implementar uma thread que, quando se selecionar o ficheiro
		 * esta rode e v· meter as regras todas no sitio em ambos os paineis
		 * 
		 */


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

}