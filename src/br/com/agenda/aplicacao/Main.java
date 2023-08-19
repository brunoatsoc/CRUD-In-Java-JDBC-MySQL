package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;

import br.com.agenda.model.Contato;
import java.util.Date;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		int option = 0;
		Scanner scn = new Scanner(System.in);
		
		System.out.printf("SISTEMA DE CADASTRO DE PESSOAS NO BANCO DE DADOS\n\n");
		
		while(option != -1){
			menu();
			option = scn.nextInt();
			chooseOption(option);
			System.out.printf("\n\n");
		}
		
		scn.close();
	}
	
	public static void menu(){
		System.out.printf("MENU\n\n");
		System.out.printf("DIGITE [1] PARA VER OS CADASTROS\n");
		System.out.printf("DIGITE [2] CADASTRAR UMA PESSOA\n");
		System.out.printf("DIGITE [3] PARA REMOVER UM CADASTRO PELO ID\n");
		System.out.printf("DIGITE [4] PARA ATUALIZAR UM CADASTRO\n");
		System.out.printf("DIGITE [-1] PARA SAIR\n");
		System.out.printf("DIGITE SUA OPÇÃO: ");
	}
	
	public static void chooseOption(int option){
		ContatoDAO contatoDao = new ContatoDAO();
		Contato contato = new Contato();
		Scanner scn = new Scanner(System.in);

		switch(option){
			case 1:
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				System.out.printf("\nCONTATOS\n");
				for(Contato c : contatoDao.getContatos()){
					System.out.printf("\n" + "ID: " + c.getId() + "\n" + "NOME: " + c.getNome() + "\n" + "IDADE: " + c.getIdade() + "\n" + "DATA CADASTRO: " + c.getDataCadastro() + "\n");
				}
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");

				break;
			case 2:
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				System.out.printf("\nDIGITE O NOME: ");
				contato.setNome(scn.nextLine());
				System.out.printf("DIGITE A IDADE: ");
				contato.setIdade(scn.nextInt());
				contato.setDataCadastro(new Date());
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				
				contatoDao.save(contato);
				
				break;
			case 3:
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				System.out.printf("\nDIGITE O ID DA PESSOA A SER REMOVIDA: ");
				contatoDao.delete(scn.nextInt());
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				
				break;
			case 4:
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				System.out.printf("\nDIGITE O ID: ");
				contato.setId(scn.nextInt());
				scn.nextLine();
				System.out.printf("DIGITE O NOME: ");
				contato.setNome(scn.nextLine());
				System.out.printf("DIGITE A IDADE: ");
				contato.setIdade(scn.nextInt());
				contato.setDataCadastro(new Date());
				System.out.printf("\n/////////////////////////////////////////////////////////////////////\n");
				
				contatoDao.update(contato);
				
				break;
			case -1:
				scn.close();

				break;
			default:
				System.out.printf("\nOPÇÃ INVALIDA!!!\n");
		}
	}
}