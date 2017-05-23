package br.imd.visao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.imd.modelo.Aluno;
import br.imd.modelo.Tree;

public class JanelaPrincipal extends JFrame implements ActionListener
{	
	static Tree arvore;
	
	// Botões
	JButton bInserir  = new JButton("Inserir");
	JButton bRemover  = new JButton("Remover");
	JButton bImprimir = new JButton("Imprimir");
	
	// Campos
	JTextField tInome  = new JTextField();
	JTextField tImatr  = new JTextField();
	JTextArea  percu   = new JTextArea();
	
	// Radio Buttons
	JRadioButton rPre = new JRadioButton("Pre Ordem");
	JRadioButton rIn  = new JRadioButton("In Ordem");
	JRadioButton rPos = new JRadioButton("Pos Ordem");
	
	// rótulos
	JLabel lnome;
	JLabel lmatricula;
	JLabel lpercursso;
	
	// Graph
	static mxGraph graph = new mxGraph();
	mxGraphComponent graphComponent;
	
	public JanelaPrincipal()
	{
		Container ct = this.getContentPane();
		ct.setLayout( new BorderLayout() );
				
		JPanel controle = new JPanel();
		controle.setLayout(new GridLayout(3,2) );
		
		JPanel radios = new JPanel();
		radios.setLayout(new GridLayout(5,1) );
		
		JPanel ajcontrol = new JPanel();
		ajcontrol.setLayout(new GridLayout(2,1) );

		JPanel ajuste = new JPanel();
		ajuste.setLayout(new GridLayout(2,1) );
		
		// Controles
		lnome = new JLabel("Nome:");
		lmatricula = new JLabel("Matricula:");
		
		controle.add(lnome);
		controle.add(tInome);
		controle.add(lmatricula);
		controle.add(tImatr);
		controle.add(bInserir);
		controle.add(bRemover);
				
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    rIn.setSelected(true);
	    group.add(rPre);
	    group.add(rIn);
	    group.add(rPos);

		lpercursso = new JLabel("Realizar Percursso:");
		
	    radios.add(lpercursso);
	    radios.add(rPre);
	    radios.add(rIn);
	    radios.add(rPos);
	    radios.add(bImprimir);
	    
	    percu.setLineWrap(true);
	    percu.setWrapStyleWord(true);
	    percu.setSize(100, 100);
	    
		JScrollPane text = new JScrollPane(percu);
		
		text.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text.setSize(500, 500);
	    //----------------------------------

		ajcontrol.add(controle);
		ajcontrol.add(radios);
		ajuste.add(ajcontrol);
	    ajuste.add(text);
	    
		ct.add(ajuste, BorderLayout.WEST);
		
		// ----------- Graph Component ----------------
		graphComponent = new mxGraphComponent(graph);
		//graphComponent.setPreferredSize();
	    
		ct.add(graphComponent,BorderLayout.CENTER);		

		// ---------------------------------------------
		
		bInserir.addActionListener(this);
		bRemover.addActionListener(this);
		bImprimir.addActionListener(this);

		setTitle("Arvore Binaria de Busca - GUI");
		setSize(1000,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		JanelaPrincipal janela = new JanelaPrincipal();
		janela.setVisible(true);
		
		arvore = new Tree();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == bInserir )
		{
			if( FuncoesGUI.validaCampo( tImatr.getText()  ) )
			{
				Aluno a = new Aluno( Integer.parseInt( tImatr.getText() ), tInome.getText() );
				
				if( arvore.insereAluno(a) )
					FuncoesGUI.atualizaArvore(graph, arvore);
				
				tImatr.setText("");
				tInome.setText("");
			}
		}
		else if( e.getSource() == bRemover)
		{
			if( FuncoesGUI.validaCampo( tImatr.getText()  ) )
			{
				if( arvore.remover( Integer.parseInt( tImatr.getText() ) ) )
					FuncoesGUI.atualizaArvore(graph, arvore);
	
				tImatr.setText("");
				tInome.setText("");
			}
		}
		else if(e.getSource() == bImprimir)
		{
			if( rPre.isSelected() )
			{
				percu.setText( arvore.toString(0) );
			}
			else if( rIn.isSelected() )
			{
				percu.setText( arvore.toString(1) );
			}
			else if( rPos.isSelected() )
			{
				percu.setText( arvore.toString(2) );
			}
		}
	}
}
