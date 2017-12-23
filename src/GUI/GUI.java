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

import GUI.ButtonOptionListener.AutomaticOptions;
import GUI.ButtonOptionListener.ManualOptions;
import GUI.DefaultTableModelEdited.ListType;
import GUI.FileChooserListener.FileType;
import InputOutput.RulesReader;
/**
 * The GUI class is the Graphical User Interface for the configuration of the AntiSpamFilter.
 * It's a workspace of interaction with the representations of the imported rules, and provides
 * means of evaluation the current set weights associated with each rule.
 * Also runs an automated algorithm to optimize the weights of each rule in order to satisfy
 * the requirements of an ProfessionalMailBox.
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

public class GUI {

	//Frame
	private JFrame frame;

	//JTexfield para os caminhos dos ficheiros
	private static JTextField textFieldRules;
	private static JTextField textFieldHam;
	private static JTextField textFieldSpam;

	//Ficheiros
	private static File rulesFile;
	private static File hamFile;
	private static File spamFile;

	//JTextfields avaliadores
	private static JTextField textFieldManualFalsePositive;
	private static JTextField textFieldManualFalseNegative;
	private static JTextField textFieldAutomaticFalsePositive;
	private static JTextField textFieldAutomaticFalseNegative;

	//Butoes
	private static ArrayList<JButton> buttonList;


	/*
	 * Listas logicas para base de representacao grafica, estaticas para serem acedidas livremente pelas tabelas de expansao
	 */

	private static DefaultTableModelEdited manualRulesWeightList;

	private static DefaultTableModelEdited automaticRulesWeightList;

	private static Map<String,Double> rulesMap;
	private static Map<Integer, ArrayList<String>> spamMap;
	private static Map<Integer, ArrayList<String>> hamMap;



/**
 * This method constructs a User Interface with the specified width and height value
 * and instantiates all of it's visual and logical components
 * @param width width value of the GUI window
 * @param height height value of the GUI window
 */
	public GUI(int width, int height) {

		frame = new JFrame("Configuracao do servico de filtragem anti-spam");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 0));

		buttonList = new ArrayList<JButton>();
		rulesMap = new HashMap<String, Double>();
		spamMap = new HashMap<Integer,ArrayList<String>>();
		hamMap = new HashMap<Integer, ArrayList<String>>();

		addFrameContent();
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);


	}

	/**
	 * Facade method that calls all of the constructors for the different parts of the GUI.
	 */
	private void addFrameContent() {
		addFirstPanel();
		addSecondPanel();
		addThirdPanel();

	}
	/**
	 * Creates the visual and logic components associated with the first panel of the GUI
	 * which contemplates the acess of the Files used in the program.
	 */
	private void addFirstPanel() {
		//1. Criacao de painel inicial dirigido aos ficheiros
		JPanel filesPanel = new JPanel();
		filesPanel.setLayout(new BorderLayout());
		frame.add(filesPanel);

		//1.1. Titulo do primeiro painel
		JLabel filesPathLabel = new JLabel("Caminhos para ficheiros de configuracao:");
		filesPathLabel.setFont(new Font(" ", Font.BOLD, 15));
		filesPathLabel.setHorizontalAlignment(JLabel.CENTER);
		filesPanel.add(filesPathLabel, BorderLayout.NORTH);

		//Criacao das Labels referentes a cada tipo de ficheiro
		JPanel fileTitlesPanel = new JPanel();
		fileTitlesPanel.setLayout(new GridLayout(3,0));
		JLabel rulesLabel= new JLabel(" Regras: ");
		rulesLabel.setFont(new Font("", Font.BOLD,13));
		rulesLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel hamLabel= new JLabel(" Ham: ");
		hamLabel.setFont(new Font("", Font.BOLD,13));
		hamLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel spamLabel= new JLabel(" Spam: ");
		spamLabel.setFont(new Font("", Font.BOLD,13));
		spamLabel.setHorizontalAlignment(JLabel.CENTER);

		fileTitlesPanel.add(rulesLabel);
		fileTitlesPanel.add(hamLabel);
		fileTitlesPanel.add(spamLabel);

		filesPanel.add(fileTitlesPanel,BorderLayout.WEST);

		//1.2. Criacao de painel para a escolha de ficheiros rules.cf, ham.log e spam.log
		JPanel filesPathPanel = new JPanel();
		filesPathPanel.setLayout(new GridLayout(3, 0));
		filesPanel.add(filesPathPanel, BorderLayout.CENTER);

		//1.2.1. Criacao das JTextField com os caminhos para cada ficheiro
		textFieldRules = new JTextField();
		textFieldRules.setEnabled(false);
		filesPathPanel.add(textFieldRules);


		textFieldRules.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				GUI.setRulesFile(new File(textFieldRules.getText()));
				RulesReader rulesReader = new RulesReader(textFieldRules.getText());
				rulesReader.start();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				//metodo somente necessario por ser import
				}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				//metodo somente necessario por ser import

			}	
		});


		textFieldHam = new JTextField();
		textFieldHam.setEnabled(false);
		filesPathPanel.add(textFieldHam);


		textFieldSpam = new JTextField();
		textFieldSpam.setEnabled(false);
		filesPathPanel.add(textFieldSpam);

		//1.3. Criacao de painel para o botoes de procura dos ficehiros rules.cf, ham.log e spam.log
		JPanel fileButtonsPanel = new JPanel();
		filesPanel.add(fileButtonsPanel, BorderLayout.EAST);
		fileButtonsPanel.setLayout(new GridLayout(0, 1));

		//1.3.1. Criacao dos botoes para cada tipo de ficheiro
		JButton rulesSearchButton = new JButton("Procurar Ficheiro de Regras");
		rulesSearchButton.addActionListener(new FileChooserListener(textFieldRules,GUI.rulesFile,FileType.RULES));
		fileButtonsPanel.add(rulesSearchButton);
		//teste
		buttonList.add(rulesSearchButton);


		JButton hamSearchButton = new JButton("Procurar Ficheiro de Ham");
		hamSearchButton.addActionListener(new FileChooserListener(textFieldHam,GUI.hamFile,FileType.HAM));
		fileButtonsPanel.add(hamSearchButton);
		//teste
		buttonList.add(hamSearchButton);


		JButton spamSearchButton = new JButton("Procurar Ficheiro de Spam");
		spamSearchButton.addActionListener(new FileChooserListener(textFieldSpam,GUI.spamFile,FileType.SPAM));
		fileButtonsPanel.add(spamSearchButton);
		//teste
		buttonList.add(spamSearchButton);

		//1.4. Separador de paineis
		JSeparator separator = new JSeparator();
		filesPanel.add(separator, BorderLayout.SOUTH);

	}

	/**
	 * Creates the visual and logic components associated with the second panel of the GUI
	 * which contemplates the manual configurations on the AntiSpamFilter.
	 */
	private void addSecondPanel() {
		//2. Criacao de painel inicial dirigido a configuracao manual
		JPanel manualPanel = new JPanel();
		frame.add(manualPanel);
		manualPanel.setLayout(new BorderLayout());

		//2.1. Criacao de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel manualConfigurationPanel = new JPanel();
		manualConfigurationPanel.setLayout(new BorderLayout());

		//2.1.1. Construcao da tabela

	
		String[] columnNamesVector = {"Regras","Pesos"};
		manualRulesWeightList = new DefaultTableModelEdited(columnNamesVector,0, ListType.MANUAL);

		JTable manualRulesTable = new JTable(manualRulesWeightList);
		manualRulesTable.setGridColor(Color.black);

		JScrollPane scroll = new JScrollPane(manualRulesTable);
		manualConfigurationPanel.add(scroll, BorderLayout.CENTER);
		
		//2.1.2. Criacao de painel para falsos positivos e falsos negativos
		JPanel manualFalseResultsPanel = new JPanel();
		manualConfigurationPanel.add(manualFalseResultsPanel, BorderLayout.SOUTH);

		//2.1.2.1. Adicionar os falsos ao painel
		JLabel manualFalsePositiveLabel = new JLabel("Falsos Positivos:");
		manualFalsePositiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manualFalseResultsPanel.add(manualFalsePositiveLabel);

		textFieldManualFalsePositive = new JTextField();
		textFieldManualFalsePositive.setEnabled(false);
		textFieldManualFalsePositive.setText("0");
		textFieldManualFalsePositive.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldManualFalsePositive.setColumns(2);
		manualFalseResultsPanel.add(textFieldManualFalsePositive);

		JLabel manualFalseNegativeLabel = new JLabel("Falsos Negativos:");
		manualFalseResultsPanel.add(manualFalseNegativeLabel);

		textFieldManualFalseNegative = new JTextField();
		textFieldManualFalseNegative.setEnabled(false);
		textFieldManualFalseNegative.setText("0");
		textFieldManualFalseNegative.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldManualFalseNegative.setColumns(2);
		manualFalseResultsPanel.add(textFieldManualFalseNegative);

		//2.2. Criacao de painel para os botoes da configuracao manual
		JPanel manualConfigurationButtons = new JPanel();
		manualConfigurationButtons.setLayout(new GridLayout(4, 0));
		manualPanel.add(manualConfigurationButtons, BorderLayout.EAST);

		//2.2.1. Adicionar os botoes ao painel
		JButton generateConfigurationButton = new JButton("Gerar Configuracao");
		manualConfigurationButtons.add(generateConfigurationButton);
		//comeaar sem ser editavel
		generateConfigurationButton.setEnabled(false);
		generateConfigurationButton.addActionListener(new ButtonOptionListener(ManualOptions.GENERATEMANUAL,null));
		buttonList.add(generateConfigurationButton);

		JButton evaluateConfigurationButton = new JButton("Avaliar Configuracao");
		manualConfigurationButtons.add(evaluateConfigurationButton);
		//comecar sem ser editavel
		evaluateConfigurationButton.setEnabled(false);
		buttonList.add(evaluateConfigurationButton);
		evaluateConfigurationButton.addActionListener(new ButtonOptionListener(ManualOptions.EVALUATE,null));

		JButton manualRulesConfigurationSaveButton = new JButton("           Gravar Configuracao          ");
		manualConfigurationButtons.add(manualRulesConfigurationSaveButton);
		//comecar sem ser editavel
		manualRulesConfigurationSaveButton.setEnabled(false);
		buttonList.add(manualRulesConfigurationSaveButton);
		manualRulesConfigurationSaveButton.addActionListener(new ButtonOptionListener(ManualOptions.SAVEMANUAL,null));

		JButton resetValuesButton = new JButton("Inicializar Configuracao");
		manualConfigurationButtons.add(resetValuesButton);
		resetValuesButton.setEnabled(false);
		buttonList.add(resetValuesButton);
		resetValuesButton.addActionListener(new ButtonOptionListener(ManualOptions.INITIALIZE,null));

		//2.3 Botao de expansao
		JButton expansionButtonOnManualPanel = new JButton("Expandir");
		//comecar sem ser editavel
		expansionButtonOnManualPanel.setEnabled(false);
		buttonList.add(expansionButtonOnManualPanel);

		expansionButtonOnManualPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame expandedManualFrame = new JFrame("Tabela de configuracao manual");
				expandedManualFrame.setLayout(new BorderLayout());
				expandedManualFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//Apresentacao da lista na nova janela
				JScrollPane expandedManualTableScroll = new JScrollPane(new JTable(manualRulesWeightList));
				expandedManualFrame.add(expandedManualTableScroll,BorderLayout.CENTER);
				//Apresentacao dos botoes
				JPanel expandedManualButtonsPanel = new JPanel(new GridLayout(1,4));
				/*
				 * Criacao de botoes replicados, pois a propria implementacao JButton, nao
				 * permite instanciacao multipla, como a DefaultModelList.
				 * Ou seja, estes nao podem existir em "2" lados ao mesmo tempo. 
				 */
				JButton expandedGenerateConfigurationButton = new JButton("Gerar Configuracao");
				expandedGenerateConfigurationButton.addActionListener(new ButtonOptionListener(ManualOptions.GENERATEMANUAL,null));
				expandedManualButtonsPanel.add(expandedGenerateConfigurationButton);
				JButton expandedEvaluateConfigurationButton = new JButton("Avaliar Configuracao");
				expandedEvaluateConfigurationButton.addActionListener(new ButtonOptionListener(ManualOptions.EVALUATE,null));
				expandedManualButtonsPanel.add(expandedEvaluateConfigurationButton);
				JButton expandedSaveManualRulesConfigurationButton = new JButton("Gravar Configuracao");
				expandedSaveManualRulesConfigurationButton.addActionListener(new ButtonOptionListener(ManualOptions.SAVEMANUAL,null));
				expandedManualButtonsPanel.add(expandedSaveManualRulesConfigurationButton);
				JButton expandedResetValuesButton = new JButton("Inicializar Configuracao");
				expandedResetValuesButton.addActionListener(new ButtonOptionListener(ManualOptions.INITIALIZE,null));
				expandedManualButtonsPanel.add(expandedResetValuesButton);

				expandedManualFrame.add(expandedManualButtonsPanel, BorderLayout.SOUTH);
				expandedManualFrame.pack();
				expandedManualFrame.setSize(1000,600);
				expandedManualFrame.setVisible(true);
			}
		});

		manualConfigurationPanel.add(expansionButtonOnManualPanel,BorderLayout.WEST);
		manualPanel.add(manualConfigurationPanel, BorderLayout.CENTER);

	}

	/**
	 * Creates the visual and logic components associated with the third panel of the GUI
	 * which contemplates the automatic configurations on the AntiSpamFilter.
	 */
	private void addThirdPanel() {

		//3. Criacao de painel inicial dirigido a configuracao automatica
		JPanel automaticPanel = new JPanel();
		automaticPanel.setLayout(new BorderLayout());
		frame.add(automaticPanel);

		//3.1. Criacao de painel para tabela de regras e pesos, e falsos positivos e negativos
		JPanel automaticConfigurationPanel = new JPanel();
		automaticConfigurationPanel.setLayout(new BorderLayout());
		automaticPanel.add(automaticConfigurationPanel, BorderLayout.CENTER);

		//2.1.1. Construcao da tabela

		String[] columnNames = {"Regras","Pesos"};
		automaticRulesWeightList= new DefaultTableModelEdited(columnNames,0, ListType.AUTOMATIC);



		JTable automaticRulesTable = new JTable(automaticRulesWeightList);
		automaticRulesTable.setGridColor(Color.black);


		JScrollPane automaticTableScroll = new JScrollPane(automaticRulesTable);
		automaticConfigurationPanel.add(automaticTableScroll, BorderLayout.CENTER);

		//2.1.2 Botao de expansao
		JButton automaticExpansionButton = new JButton("Expandir");
		//comecar sem ser editavel
		automaticExpansionButton.setEnabled(false);
		buttonList.add(automaticExpansionButton);

		automaticExpansionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame expandedAutomaticFrame = new JFrame("Tabela de configuracao Automatica");
				expandedAutomaticFrame.setLayout(new BorderLayout());
				expandedAutomaticFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel expandedAutomaticButtonsPanel= new JPanel(new GridLayout(1,2));

				JButton expandedGenerateAutomaticConfigurationButton = new JButton("Gerar Conf. Automatica c/ JMetal");
				expandedGenerateAutomaticConfigurationButton.addActionListener(new ButtonOptionListener(null,AutomaticOptions.GENERATEAUTOMATIC));
				expandedAutomaticButtonsPanel.add(expandedGenerateAutomaticConfigurationButton);

				JButton expandedSaveAutomaticConfigurationButton = new JButton("Gravar Conf. Automatica");
				expandedGenerateAutomaticConfigurationButton.addActionListener(new ButtonOptionListener(null,AutomaticOptions.SAVEAUTOMATIC));
				expandedAutomaticButtonsPanel.add(expandedSaveAutomaticConfigurationButton);


				//Apresentacao da lista na nova janela
				JScrollPane expandedAutomaticTableScroll= new JScrollPane(new JTable(automaticRulesWeightList));
				expandedAutomaticFrame.add(expandedAutomaticTableScroll,BorderLayout.CENTER);
				expandedAutomaticFrame.add(expandedAutomaticButtonsPanel,BorderLayout.SOUTH);
				expandedAutomaticFrame.pack();
				expandedAutomaticFrame.setSize(1000,700);
				expandedAutomaticFrame.setVisible(true);
			}
		});

		automaticConfigurationPanel.add(automaticExpansionButton,BorderLayout.WEST);
		automaticPanel.add(automaticConfigurationPanel, BorderLayout.CENTER);


		//3.1.2. Criacao de painel para falsos positivos e falsos negativos
		JPanel automaticFalseResultsPanel = new JPanel();
		automaticConfigurationPanel.add(automaticFalseResultsPanel, BorderLayout.SOUTH);

		//3.1.2.1. Adicionar os falsos ao painel
		JLabel automaticFalsePositiveLabel = new JLabel("Falsos Positivos:");
		automaticFalseResultsPanel.add(automaticFalsePositiveLabel);

		textFieldAutomaticFalsePositive = new JTextField();
		textFieldAutomaticFalsePositive.setEnabled(false);
		textFieldAutomaticFalsePositive.setText("0");
		textFieldAutomaticFalsePositive.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAutomaticFalsePositive.setColumns(2);
		automaticFalseResultsPanel.add(textFieldAutomaticFalsePositive);

		JLabel automaticFalseNegativesLabel = new JLabel("Falsos Negativos:");
		automaticFalseResultsPanel.add(automaticFalseNegativesLabel);

		textFieldAutomaticFalseNegative = new JTextField();
		textFieldAutomaticFalseNegative.setEnabled(false);
		textFieldAutomaticFalseNegative.setText("0");
		textFieldAutomaticFalseNegative.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAutomaticFalseNegative.setColumns(2);
		automaticFalseResultsPanel.add(textFieldAutomaticFalseNegative);

		//3.2. Criacao de painel para os botoes da configuracao manual
		JPanel automaticConfigurationButtonsPanel = new JPanel();
		automaticConfigurationButtonsPanel.setLayout(new GridLayout(3, 0));
		automaticPanel.add(automaticConfigurationButtonsPanel, BorderLayout.EAST);

		//3.2.1. Adicionar os botoes ao painel
		JButton generateAutomaticConfigurationButton = new JButton("Gerar Conf. Automatica c/ JMetal");
		//comecar sem ser editavel
		generateAutomaticConfigurationButton.setEnabled(false);
		generateAutomaticConfigurationButton.addActionListener(new ButtonOptionListener(null, AutomaticOptions.GENERATEAUTOMATIC));
		buttonList.add(generateAutomaticConfigurationButton);
		generateAutomaticConfigurationButton.setFont(new Font("", Font.BOLD, 11));
		automaticConfigurationButtonsPanel.add(generateAutomaticConfigurationButton);

		JButton saveAutomaticRulesConfigurationButton = new JButton("Gravar Configuracao");
		//comecar sem ser editavel
		saveAutomaticRulesConfigurationButton.setEnabled(false);
		saveAutomaticRulesConfigurationButton.addActionListener(new ButtonOptionListener(null, AutomaticOptions.SAVEAUTOMATIC));
		buttonList.add(saveAutomaticRulesConfigurationButton);
		automaticConfigurationButtonsPanel.add(saveAutomaticRulesConfigurationButton);

		//3.3. Separador de paineis
		JSeparator panelSeparator = new JSeparator();
		automaticPanel.add(panelSeparator, BorderLayout.NORTH);

	}

	

/**
 * This method returns the displayed manual weight list 
 * 
 * @return a DefaultTableModelEdited associated with the visualization of the GUI manual Table
 */
	public static DefaultTableModelEdited getManualRulesWeightList() {
		return manualRulesWeightList;
	}

	/**
	 * This method returns the displayed automatic weight list 
	 * 
	 * @return a DefaultTableModelEdited associated with the visualization of the GUI automatic Table
	 */
	public static DefaultTableModelEdited getAutomaticRulesWeightList() {
		return automaticRulesWeightList;
	}
	/**
	 * This method returns the mapping of the rules and their weights for efficient logical computation 
	 * 
	 * @return a HashMap associated with the desired mapping of rules-weights
	 */
	public static Map<String, Double> getRulesMap() {
		return rulesMap;
	}
	/**
	 * This method returns the mapping of the rules present in each e-mail saved in the Spam file. 
	 * 
	 * @return a HashMap associated with the rules present in each Spam e-mail
	 */

	public static Map<Integer, ArrayList<String>> getSpamMap() {
		return spamMap;
	}
	/**
	 * This method returns the mapping of the rules present in each e-mail saved in the Ham file. 
	 * 
	 * @return a HashMap associated with the rules present in each Ham e-mail
	 */

	public static Map<Integer, ArrayList<String>> getHamMap(){
		return hamMap;
	}




	//---------------------------------------
/**
 * This method enables all the buttons from the moment that the rules file is selected
 */
	public static void ActivateButons() {
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setEnabled(true);
		}
	}





	//---------------------------------------
	
	/**
	 * This method returns the TextField object associated with the manual evaluation
	 * count of False Positives detected 
	 * 
	 * @return a JTextField with the value of detected false positives
	 */
	public static JTextField getTextFieldManualFalsePositive() {
		return textFieldManualFalsePositive;
	}


	/**
	 * This method returns the TextField object associated with the manual evaluation
	 * count of False Negatives detected 
	 * 
	 * @return a JTextField with the value of detected false negatives
	 */
	
	public static JTextField getTextFieldManualFalseNegative() {
		return textFieldManualFalseNegative;
	}
//___________________________________________________________
	
	/**
	 * This method returns the TextField object associated with the automatic evaluation
	 * count of False Positives detected 
	 * 
	 * @return a JTextField with the value of detected false positives
	 */
	public static JTextField getTextFieldAutomaticFalsePositive() {
		return textFieldAutomaticFalsePositive;
	}

	/**
	 * This method returns the TextField object associated with the automatic evaluation
	 * count of False Negatives detected 
	 * 
	 * @return a JTextField with the value of detected false negatives
	 */
	
	public static JTextField getTextFieldAutomaticFalseNegative() {
		return textFieldAutomaticFalseNegative;
	}

	//---------------------------------------
	
	/**
	 * This method returns the TextField object associated with the File path 
	 * of the desired Rules file
	 * 
	 * @return a JTextField with an absolute path for a File
	 */

	public static JTextField getTextFieldRules() {
		return textFieldRules;	
	}
	/**
	 * This method returns the TextField object associated with the File path 
	 * of the desired Ham file
	 * 
	 * @return a JTextField with an absolute path for a File
	 */
	public static JTextField getTextFieldHam() {
		return textFieldHam;
	}

	/**
	 * This method returns the TextField object associated with the File path 
	 * of the desired Spam file
	 * 
	 * @return a JTextField with an absolute path for a File
	 */
	public static JTextField getTextFieldSpam() {
		return textFieldSpam;
	}

	//---------------------------------------
	/**
	 * This method returns a list of all the buttons in the GUI
	 * 
	 * @return an ArrayList with all the buttons of the GUI
	 */
	public static ArrayList<JButton> getButtonList() {
		return buttonList;
	}

	//---------------------------------------
	/**
	 * This method returns the selected Rules File
	 * 
	 * @return a File of the desired rules
	 */

	public static File getRulesFile() {
		return rulesFile;
	}
	/**
	 * This method returns the selected Spam File
	 * 
	 * @return a File of the Spam e-mails
	 */
	public static File getSpamFile() {
		return spamFile;
	}
	/**
	 * This method returns the selected Ham File
	 * 
	 * @return a File of the Ham e-mails
	 */

	public static File getHamFile() {
		return hamFile;
	}

	//---------------------------------------
	
	/**
	 * This method sets the desired File to be the designated Rules File
	 * 
	 * @param rules File to set as the designated Rules File
	 */
	public static void setRulesFile(File rules) {
		GUI.rulesFile = rules;
	}
	
	/**
	 * This method sets the desired File to be the designated Ham File
	 * 
	 * @param ham File to set as the designated Ham File
	 */

	public static void setHamFile(File ham) {
		GUI.hamFile = ham;
	}

	/**
	 * This method sets the desired File to be the designated Spam File
	 * 
	 * @param spam File to set as the designated Spam File
	 */
	
	public  static void setSpamFile(File spam) {
		GUI.spamFile = spam;
	}
}