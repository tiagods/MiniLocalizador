package controller;

import static view.Menu.table;
import static view.Menu.textArea;
import static view.Menu.txDestino;
import static view.Menu.txExtensao;
import static view.Menu.txLocalizacao;
import static view.Menu.txSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Model;

public class Controller implements ActionListener{
	static Controller instance;
	
	List<File> arquivosLocal;
	List<Model> filtroProcurado;
	String destino = "";
	String backup= "";
	
	public static Controller getInstance(){
		if(instance==null){
			instance = new Controller();
		}
		return instance;
	}
	public void iniciar(){
		txSeparator.setText(",");
		txExtensao.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Localizacao":
			txLocalizacao.setText(carregarArquivo());
			break;
		case "Destino":
			txDestino.setText(carregarArquivo());
			break;
		case "Iniciar":
			if(txSeparator.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null,"Delimitador invalido");
				//separador invalido
			}
			else{
				if(textArea.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"Campo texto vazio");
				}
				else{
					if(txLocalizacao.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Localização não informada");
					}
					else{
						if(txDestino.getText().equals("")){
							JOptionPane.showMessageDialog(null,"Destino dos arquivos não informados");
						}
						else{
							destino = txDestino.getText();
							
							arquivosLocal = new ArrayList<File>();
							filtroProcurado= new ArrayList<Model>();
							
							String[] filesNames = textArea.getText().split(txSeparator.getText().toUpperCase());//nome de arquivos enviados para o textArea
							
							listAndAddFile(new File(txLocalizacao.getText()));
							
//							Iterator<File> iterator = arquivosLocal.iterator();
//							while(iterator.hasNext()){
//								for(String arquivo : filesNames){
//									if(arquivo.trim().equals("")) continue;
//									
//									if(arquivo.lastIndexOf(".")==arquivo.length()-1)
//										arquivo = arquivo+txExtensao.getText().trim().replace(".", "");
//									else if(arquivo.lastIndexOf(".")==-1 && !txExtensao.getText().trim().replace(".", "").equals("")){
//										arquivo = arquivo+"."+txExtensao.getText().trim().replace(".", "");
//									}
//									if(iterator.next().getName().contains(arquivo)){
//										addStatus(arquivo, iterator.next(), true);
//										break;
//									}
//								}
//							}
							for(String arquivo : filesNames){
								if(arquivo.trim().equals("")) continue;

								if(arquivo.lastIndexOf(".")==arquivo.length()-1)
									arquivo = arquivo+txExtensao.getText().trim().replace(".", "");
								else if(arquivo.lastIndexOf(".")==-1 && !txExtensao.getText().trim().replace(".", "").equals("")){
									arquivo = arquivo+"."+txExtensao.getText().trim().replace(".", "");
								}
								boolean encontrado = false;
								for(int i=0;i<arquivosLocal.size();i++){
									if(arquivosLocal.get(i).getName().contains(arquivo.trim().toUpperCase())){
										addStatus(arquivo, arquivosLocal.get(i), true);
										encontrado=true;
										break;
									}
								}
								//se não encontrar criara uma lista informando
								if(!encontrado){
									addStatus(arquivo, null, false);
								}
							}
							copiar();
							imprimirEmTela(filtroProcurado);
						}
					}
				}
			}
			break;
		}
		
	}
	private void imprimirEmTela(List<Model> lista){
		DefaultTableModel tbm = (DefaultTableModel)table.getModel();
		for(int i=tbm.getRowCount()-1; i>=0; i--){
	        tbm.removeRow(i);
	    }
		for(int i=0; i<lista.size();i++){
			tbm.addRow(new Object[1]);
			tbm.setValueAt(lista.get(i).getName(), i, 0);
			if(lista.get(i).isStatus()==true)
				tbm.setValueAt("Encontrado", i, 1);
			else
				tbm.setValueAt("Não Encontrado", i, 1);
			if(lista.get(i).isCopiado()==true)
				tbm.setValueAt("Copiado", i, 2);
			else
				tbm.setValueAt("Não copiado", i, 2);
			if(lista.get(i).getOrigem()==null)
				tbm.setValueAt("", i, 3);
			else
				tbm.setValueAt(lista.get(i).getOrigem().getAbsolutePath(), i, 3);
			tbm.setValueAt(lista.get(i).getDestino(), i, 4);
		}
	}
	private void copiar(){
		filtroProcurado.forEach(c->{
			if(c.isStatus()){
				File fileFinal = new File(destino+"/"+c.getOrigem().getName());
		   	 	Path pathI = Paths.get(c.getOrigem().getAbsolutePath());
		        Path pathO = Paths.get(fileFinal.getAbsolutePath());
		        try {
					Files.copy(pathI, pathO, StandardCopyOption.REPLACE_EXISTING);
					c.setDestino(fileFinal.getAbsolutePath());
					c.setCopiado(true);
				} catch (IOException e) {
					c.setDestino("Não foi possivel copiar o arquivo "+e.getMessage());
					c.setCopiado(false);
					e.printStackTrace();
				}
			}
			else{
				c.setCopiado(false);
			}
		});
	}
	
	public void addStatus(String nome, File file, boolean encontrado){
		Model model = new Model();
		model.setName(nome.trim());
		model.setOrigem(file);
		model.setStatus(encontrado);
		model.setCopiado(false);
		filtroProcurado.add(model);
	}
	
	private String carregarArquivo(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("Escolha de Diretorio");
        String local = "";
        if(!backup.equals(""))
        	chooser.setCurrentDirectory(new File(backup));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retorno = chooser.showOpenDialog(null);
        if(retorno==JFileChooser.APPROVE_OPTION){
        	local = chooser.getSelectedFile().getAbsolutePath();//
        	backup=local;
        }
        return local;
    }
	//listar pasta e adicionar arquivos no conteudo
	public void listAndAddFile(File file){
		File[] files = file.listFiles();
		for(File f : files){
			if(f.isFile()){
				arquivosLocal.add(f);
			}
			else
				listAndAddFile(f);
		}
	}

}
