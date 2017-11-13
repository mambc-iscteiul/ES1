package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;

public class GUI {
	private JFrame frame;
	private JTextField tf_rules;
	private JTextField tf_ham;
	private JTextField tf_spam;

	private File rules = new File("rules");
	private File ham = new File("ham");
	private File spam = new File("spam");

	private JTextField fp_manual;
	private JTextField fn_manual;
	private JTextField fp_automatico;
	private JTextField fn_automatico;

	public GUI(int x, int y) {


		frame = new JFrame("Configuração automática do serviço de filtragem anti-spam");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Projeto_ES_2.0/src/GUI/ES.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 0));
		addFrameContent();
		frame.pack();
		frame.setSize(x, y);
		frame.setVisible(true);
	}

	private void addFrameContent() {
		//1. Criação de painel inicial dirigido aos ficheiros
		JPanel ficheiros = new JPanel();
		ficheiros.setLayout(new BorderLayout());
		frame.add(ficheiros);


		//1.1. Título do primeiro painel
		JLabel lblCaminhosParaFicheiro = new JLabel("Caminhos para ficheiro de configuração:");
		lblCaminhosParaFicheiro.setFont(new Font(" ", Font.BOLD, 13));
		ficheiros.add(lblCaminhosParaFicheiro, BorderLayout.WEST);

		//1.2. Criação de painel para a escolha de ficheiros rules.cf, ham.log e spam.log
		JPanel caminhos_ficheiros = new JPanel();
		caminhos_ficheiros.setLayout(new GridLayout(3, 0));
		ficheiros.add(caminhos_ficheiros, BorderLayout.CENTER);

		//1.2.1. Criação das JTextField com os caminhos para cada ficheiro
		tf_rules = new JTextField(rules.getAbsolutePath());
		tf_rules.setEnabled(false);
		caminhos_ficheiros.add(tf_rules);

		tf_ham = new JTextField(ham.getAbsolutePath());
		tf_ham.setEnabled(false);
		caminhos_ficheiros.add(tf_ham);

		tf_spam = new JTextField(spam.getAbsolutePath());
		tf_spam.setEnabled(false);
		caminhos_ficheiros.add(tf_spam);

		//1.3. Criação de painel para as JCheckBox'es dos ficheiros rules.cf, ham.log e spam.log
		JPanel checkbox_ficheiros = new JPanel();
		ficheiros.add(checkbox_ficheiros, BorderLayout.EAST);
		checkbox_ficheiros.setLayout(new GridLayout(0, 1));

		//1.3.1. Criação das JCheckBox'es para cada tipo de ficheiro
		JCheckBox chckbxRulescf = new JCheckBox("rules.cf");
		checkbox_ficheiros.add(chckbxRulescf);

		JCheckBox chckbxHamlog = new JCheckBox("ham.log");
		checkbox_ficheiros.add(chckbxHamlog);

		JCheckBox chckbxSpamlog = new JCheckBox("spam.log");
		checkbox_ficheiros.add(chckbxSpamlog);

		//1.4. Separador de paineis
		JSeparator separator = new JSeparator();
		ficheiros.add(separator, BorderLayout.SOUTH);

		//2. Criação de painel inicial dirigido à configuração manual
		JPanel manual = new JPanel();
		frame.add(manual);
		manual.setLayout(new BorderLayout());

		//2.1. Criação de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_manual = new JPanel();
		manual.add(configuracao_manual, BorderLayout.CENTER);
		configuracao_manual.setLayout(new BorderLayout());

		//Retirou-se 2.1.1 e 2.1.1.1 para serem alterados

		//2.1.2. Criação de painel para falsos positivos e falsos negativos
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

		//2.2. Criação de painel para os botões da configuração manual
		JPanel buttons_configuracao_manual = new JPanel();
		buttons_configuracao_manual.setLayout(new GridLayout(3, 0));
		manual.add(buttons_configuracao_manual, BorderLayout.EAST);

		//2.2.1. Adicionar os botões ao painel
		JButton btnGerarConfigurcao = new JButton("Gerar Configuração");
		buttons_configuracao_manual.add(btnGerarConfigurcao);

		JButton btnAvaliarConfiguracao = new JButton("Avaliar Configuração");
		buttons_configuracao_manual.add(btnAvaliarConfiguracao);

		JButton btnGravarConfigurcaoRulescf_manual = new JButton("Gravar Configuração rules.cf");
		buttons_configuracao_manual.add(btnGravarConfigurcaoRulescf_manual);

		//3. Criação de painel inicial dirigido à configuração automática
		JPanel automatico = new JPanel();
		automatico.setLayout(new BorderLayout());
		frame.add(automatico);

		//3.1. Criação de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel configuracao_automatica = new JPanel();
		configuracao_automatica.setLayout(new BorderLayout());
		automatico.add(configuracao_automatica, BorderLayout.CENTER);

		//Retirou-se 3.1.1 e 3.1.1.1 para serem alterados

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
		btnGerarConfigurcaoAutomtica.setFont(new Font("", Font.PLAIN, 11));
		buttons_configuracao_automatica.add(btnGerarConfigurcaoAutomtica);

		JButton btnGravarConfigurcaoRulescf_automatico = new JButton("Gravar Configuração rules.cf");
		buttons_configuracao_automatica.add(btnGravarConfigurcaoRulescf_automatico);

		//3.3. Separador de paineis
		JSeparator separator_1 = new JSeparator();
		automatico.add(separator_1, BorderLayout.NORTH);
	}
}