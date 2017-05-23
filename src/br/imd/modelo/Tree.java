package br.imd.modelo;

public class Tree {
	
	private No root;
	private Tree leftTree;
	private Tree rightTree;

	private int SubsEsquerda;
	private int SubsDireita;
	
	public Tree()
	{
		this.SubsEsquerda = 0;
		this.SubsDireita = 0;
	}
	
	public Tree getRightTree(){
		return rightTree;
	}
	
	public void setRightTree(Tree rightTree){
		this.rightTree = rightTree;
	}
	
	public Tree getLeftTree(){
		return leftTree;
	}
	
	public void setLeftTree(Tree leftTree){
		this.leftTree = leftTree;
	}
	
	 public No getRoot() {
	        return root;
	 }

	public void setRoot(No root) {
	        this.root = root;
	}
	
	public void insereAluno(int matricula, String nome)
	{
        Aluno aluno = new Aluno(matricula, nome);
        No no = new No(aluno);
        inserir(no);
    }
	
	public boolean insereAluno(Aluno a)
	{
		No no = new No(a);
		
		return inserir(no);
	}

	private boolean inserir(No no)
	{
		if(this.root == null)
		{
		   this.root = no;
		   return true;
		}
		else 
		{
			if (no.getAluno().getMatricula() > this.root.getAluno().getMatricula())
			{
				if (this.rightTree == null)
				{
					this.rightTree = new Tree();
				}
				
				if ( this.rightTree.inserir(no) )
				{
					this.incrementaSubsDireita();
					return true;
				}
			}
			else if (no.getAluno().getMatricula() < this.root.getAluno().getMatricula())
			{
				if (this.leftTree == null)
				{
					this.leftTree = new Tree();
				}
				
				if( this.leftTree.inserir(no) )
				{
					this.incrementaSubsEsquerda();
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Aluno Busca(int matricula)
	{
		if(this.root != null)
		{
			return Busca(matricula, this);
		}
		else
		{
			return null;
		}
	}
	
	private Aluno Busca(int matricula, Tree arvore)
	{		
		if( matricula == arvore.getRoot().getAluno().getMatricula() )
		{
			return arvore.getRoot().getAluno();
		}
		else if( matricula < arvore.getRoot().getAluno().getMatricula() )
		{
			if( arvore.getLeftTree() != null )
			{
				return Busca(matricula, getLeftTree() );
			}
		}
		else if( matricula > arvore.getRoot().getAluno().getMatricula() )
		{			
			if( arvore.getRightTree() != null )
			{
				return Busca(matricula, arvore.getRightTree() );
			}
		}
		
		return null;
	}
	
	public void percorrerInOrdem()
	{
		percorrerInOrdem(this);
	}
	
	public void percorrerPreOrdem()
	{
		percorrerPreOrdem(this);
	}
	
	public void percorrerPosOrdem()
	{
		percorrerPosOrdem(this);
	}
	
	private void percorrerInOrdem( Tree arvore )
	{
		Aluno aux;
		
		if( arvore.getRoot() != null)
		{
			if( arvore.getLeftTree() != null )
			{
				percorrerInOrdem( arvore.getLeftTree() );
			}

			aux = arvore.getRoot().getAluno();
			System.out.println( "Aluno: " + aux.getNome() + " Matricula: " + aux.getMatricula() );

			if( arvore.getRightTree() != null )
			{
				percorrerInOrdem( arvore.getRightTree() );
			}
		}
	}
	
	private void percorrerPreOrdem( Tree arvore )
	{		
		Aluno aux;

		aux = arvore.getRoot().getAluno();
		System.out.println( "Aluno: " + aux.getNome() + " Matricula: " + aux.getMatricula() );
		
		if( arvore.getRoot() != null)
		{
			if( arvore.getLeftTree() != null )
			{
				percorrerPreOrdem( arvore.getLeftTree() );
			}

			if( arvore.getRightTree() != null )
			{
				percorrerPreOrdem( arvore.getRightTree() );
			}
		}
	}
	
	private void percorrerPosOrdem( Tree arvore )
	{
		Aluno aux;
		
		if( arvore.getRoot() != null)
		{
			if( arvore.getLeftTree() != null )
			{
				percorrerPosOrdem( arvore.getLeftTree() );
			}

			if( arvore.getRightTree() != null )
			{
				percorrerPosOrdem( arvore.getRightTree() );
			}

			aux = arvore.getRoot().getAluno();
			System.out.println( "Aluno: " + aux.getNome() + " Matricula: " + aux.getMatricula() );
		}
	}
	
	public boolean remover(int matricula)
	{
		System.out.println("QTD NOS: " + this.qtdNos() );
		
		if(this.root != null)
		{	
			int qtdAnterior = this.qtdNos();
			
			Tree m = remover(this, matricula);
			
			if( m != null )
			{
				this.setRoot( m.getRoot() );
				
				if( m.getLeftTree() != null )
					this.setLeftTree(m.getLeftTree());
				else
					this.setLeftTree(null);

				if( m.getRightTree() != null )
					this.setRightTree(m.getRightTree());
				else
					this.setRightTree(null);
				
			}
				

			/*System.out.println("QTD Anterior: " + qtdAnterior);
			System.out.println("QTD Atual: " + this.qtdNos());*/
			
			if( qtdAnterior > this.qtdNos() )
				return true;
		}
		
		return false;
	}
	
	private Tree remover(Tree arvore, int k)
	{		
		int anterior;
		
		if( k < arvore.getRoot().getAluno().getMatricula() )
		{
			if( arvore.getLeftTree() != null )
			{
				anterior = arvore.getLeftTree().qtdNos();
				
				arvore.setLeftTree( remover(arvore.getLeftTree(), k) );
				
				if( arvore.getLeftTree() == null )
				{
					arvore.decrementaSubsEsquerda();
				}
				else if( arvore.getLeftTree().qtdNos() != anterior )
				{
					arvore.decrementaSubsEsquerda();
				}				
			}
			else 
				return arvore;
		}
		else if( k > arvore.getRoot().getAluno().getMatricula() )
		{
			if( arvore.getRightTree() != null )
			{
				anterior = arvore.getRightTree().qtdNos();
				
				arvore.setRightTree( remover(arvore.getRightTree(), k) );
				
				if( arvore.getRightTree() == null )
				{
					arvore.decrementaSubsDireita();
				}
				else if( arvore.getRightTree().qtdNos() != anterior )
				{
					arvore.decrementaSubsDireita();
				}
				
			}
			else 
				return arvore;
		}
		else if( arvore.getLeftTree() != null && arvore.getRightTree() != null )
		{		
			Aluno menor =  arvore.encontraMenor().getRoot().getAluno();
			arvore.getRoot().setAluno(menor);
			arvore.decrementaSubsDireita();
			
			arvore.setRightTree( remover( arvore.getRightTree(), menor.getMatricula() ) );
		}
		else if( arvore.getLeftTree() == null && arvore.getRightTree() == null)
		{
			arvore.setRoot( null );
			return null;
		}
		else if( arvore.getLeftTree() != null )
		{
			arvore.setRoot(null);
			arvore.decrementaSubsEsquerda();
			return arvore.getLeftTree();
		}
		else if( arvore.getRightTree() != null )
		{
			arvore.setRoot(null);
			arvore.decrementaSubsDireita();
			return arvore.getRightTree();
		}
		
		return arvore;
	}
	
	public Tree encontraMenor()
	{
		if( this.root  != null)
		{
			return encontraMenor(this.rightTree);
		}
		else
			return null;
	}
	
	public Tree encontraMenor( Tree arvore )
	{
		if( arvore.getLeftTree() != null )
		{
			return arvore.getLeftTree();
		}
		else
			return arvore;
	}
	
	public int qtdNos()
	{
		if( this.root != null )
			return this.SubsDireita + this.SubsEsquerda + 1;
		else
			return 0;
	}
	
	public String toString(int opcao)
	{
		if( this.root != null )
		{
			String result = toString(this, opcao, "");
			
			switch(opcao)
			{
				case 0:
					result = "Pre Ordem: \n" + result;
				break;

				case 1:
					result = "In Ordem: \n" + result;
				break;

				case 2:
					result = "Pos Ordem: \n" + result;
				break;

				default:
					result = "In Ordem: \n" + result;
				break;
			}
			
			return result;
		}
		else
			return "";
	}
	
	public String toString(Tree arvore, int opcao, String s)
	{
		if(opcao == 0)
			s += arvore.getRoot().getAluno().getNome() + " - " + arvore.getRoot().getAluno().getMatricula() + "\n";
			
		if( arvore.getLeftTree() != null )
		{
			s = toString(arvore.getLeftTree(), opcao, s);
		}

		if( opcao == 1 || (opcao != 0 && opcao != 2) )
			s += arvore.getRoot().getAluno().getNome() + " - " + arvore.getRoot().getAluno().getMatricula() + "\n";
		
		if( arvore.getRightTree() != null )
		{
			s = toString(arvore.getRightTree(), opcao, s);
		}

		if(opcao == 2)
			s += arvore.getRoot().getAluno().getNome() + " - " + arvore.getRoot().getAluno().getMatricula() + "\n";
		
		return s;
	}
	
	public int alturaRaiz()
    {
            Tree aux = this;
            int altura = 1;
            
            while (aux != null)
            {
                    if ( aux.getLeftTree() != null && aux.getRightTree() != null )
                    {
                    	if( aux.getSubsEsquerda() >= aux.getSubsDireita() )
                    	{
                    		aux = aux.getLeftTree();
                    	}
                    	else
                    	{
                    		aux = aux.getRightTree();
                    	}
                    } 
                    else if( aux.getLeftTree() != null )
                    {
                    	aux = aux.getLeftTree();
                    }
                    else if( aux.getRightTree() != null)
                    {
                    	aux = aux.getRightTree();
                    }
                    else
                    {
                    	break;
                    }
                    
                    altura++;
            }
            
            return altura;
    }
	
	public void incrementaSubsEsquerda()
	{
		SubsEsquerda += 1;
	}

	public void incrementaSubsDireita()
	{
		SubsDireita += 1;
	}
	
	public void decrementaSubsEsquerda()
	{
		SubsEsquerda -= 1;
	}

	public void decrementaSubsDireita()
	{
		SubsDireita -= 1;
	}
	
	public int getSubsEsquerda()
	{
		return SubsEsquerda;
	}
	
	public int getSubsDireita()
	{
		return SubsDireita;
	}
}
