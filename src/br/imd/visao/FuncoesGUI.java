package br.imd.visao;

import com.mxgraph.view.mxGraph;

import br.imd.modelo.Aluno;
import br.imd.modelo.Tree;

public class FuncoesGUI
{
	public static void desenhaArvore(mxGraph graph, Tree arvore)
	{		
		
		if(arvore.getRoot()!= null)
		{				
			desenhaArvore(graph, arvore, null, 450, 10, 200);
		}
	}
	
	public static void atualizaArvore(mxGraph graph, Tree arvore)
	{
		graph.getModel().beginUpdate();
		Object parent = graph.getDefaultParent();
		
	    try
	    {
	        Object[] cells = graph.getChildCells(parent, true, false);
	        graph.removeCells(cells, true);
	        desenhaArvore(graph, arvore);

	    } 
	    finally
	    {
	        graph.getModel().endUpdate();
	    }
	}
	
	public static void desenhaArvore(mxGraph graph, Tree arvore, Object pai, int xPai, int yPai, int distancia)
	{		
		// Posições dos nós ainda não estão adequadas.
		
		if( arvore.getRoot() != null )
		{
			Aluno a = new Aluno( arvore.getRoot().getAluno() );
			String no =  a.getMatricula() + "-" + a.getNome();
			//String no = arvore.getSubsEsquerda() + "-" + a.getMatricula() + "-" + arvore.getSubsDireita();
			
			
			Object parent = graph.getDefaultParent();
			graph.getModel().beginUpdate();
			
			Object v1 = graph.insertVertex(parent, null, no , xPai, yPai, 50, 20);		// Insere o desenho do nó
			
			if(pai == null)
				pai = v1;
			else
			{
				Object filho = v1;
				graph.insertEdge(parent, null, "", pai, filho);		// Insere Linha que liga os nós
			}
	
			graph.getModel().endUpdate();
			
			int novaDistancia = arvore.alturaRaiz() * 25;
			
			if( arvore.getLeftTree() != null )
			{
				desenhaArvore(graph, arvore.getLeftTree(), v1, xPai - novaDistancia, yPai + 80, novaDistancia);
			}
			
			if( arvore.getRightTree() != null )
			{
				desenhaArvore(graph, arvore.getRightTree(), v1, xPai + novaDistancia, yPai + 80, novaDistancia);
			}	
		}
	}
	
	public static boolean validaCampo(String texto)
	{
		char Ctexto[] = texto.toCharArray();
		
		if( Ctexto.length == 0 )
		{
			return false;
		}
		else
		{			
			for(int i = 0; i < Ctexto.length; i++)
			{
				if( ! ( Character.isDigit( Ctexto[i] ) ) )
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
}
