//package br.imd.visao;
//
//import br.imd.modelo.Aluno;
//import br.imd.modelo.Tree;
//
//public class Testes {
//
//	public static void main(String[] args)
//	{
//		Tree arvore = new Tree();
//
//		Aluno a1 = new Aluno(5, "Diogo");
//		Aluno a2 = new Aluno(2, "Myke");
//		Aluno a3 = new Aluno(3, "Wells");
//		Aluno a4 = new Aluno(11,"Grant");
//		Aluno a5 = new Aluno(6, "Julian");
//		Aluno a6 = new Aluno(8, "Cisco");
//		Aluno a7 = new Aluno(21,"Cait");
//		Aluno a8 = new Aluno(15,"Iris");
//
//		arvore.insereAluno(a1);
//		arvore.insereAluno(a2);
//		arvore.insereAluno(a3);
//		arvore.insereAluno(a4);
//		arvore.insereAluno(a5);
//		arvore.insereAluno(a6);
//		arvore.insereAluno(a7);
//		arvore.insereAluno(a8);
//		
//		// Teste OK
////		arvore.percorrerPreOrdem();
////		System.out.println();
//		arvore.percorrerInOrdem();
//		System.out.println();
////		arvore.percorrerPosOrdem();
//		
//		/*Aluno aux = arvore.Busca(21);
//		
//		if( aux != null )
//		{
//			System.out.println("Aluno Encontrado: " + aux.getNome());
//		}
//		else
//			System.out.println("Aluno nao encontrado");*/
//
//		arvore.remover(11);
//		arvore.remover(3);
//		arvore.remover(5);
//
//		arvore.percorrerInOrdem();
//	}
//
//}
