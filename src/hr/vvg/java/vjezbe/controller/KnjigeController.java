package hr.vvg.java.vjezbe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.vvg.java.vjezbe.entitet.Datoteke;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class KnjigeController {
	
	
	
	public KnjigeController() { 
		
	} 
	@FXML 
	private TextField nazivKnjige; 
	
	@FXML 
	private TableView<Knjiga> knjigaTable;
	
	@FXML 
	private TableColumn<Knjiga, String> nazivKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, VrstaPublikacije> vrstaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> godinaIzdanjaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> brojStranicaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Jezik> jezikKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, String> nazivIzdavacaKnjigeColumn;
	
	
	@FXML 
	public void initialize() { 
		nazivKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("naziv")); 
		vrstaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, VrstaPublikacije>("vrstaPublikacije")); 
		godinaIzdanjaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("godinaIzdanja")); 
		brojStranicaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("brojStranica")); 
		jezikKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Jezik>("jezikKnjige")); 
		nazivIzdavacaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("nazivIzdavaca")); 
		
	}
	
	public void prikaziSveKnjige() {
		
		Datoteke datoteka = new Datoteke();
		
		List<Knjiga> pomocnaListaKnjiga = new ArrayList<>();
		
		File fileZaCitanje = new File("knjige.txt");
		
		try {
			
			datoteka.ucitajKnjigu(pomocnaListaKnjiga, fileZaCitanje);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (DuplikatPublikacijeException e) {
			e.printStackTrace();
		}
		
		
	List<Knjiga> filtriraneKnjige = new ArrayList<Knjiga>();
	
	if (nazivKnjige.getText().isEmpty() == false) { 
		filtriraneKnjige = pomocnaListaKnjiga.stream().filter(p -> p.getNaziv() .contains(nazivKnjige.getText())) 
				.collect(Collectors.toList()); 
		} else { 
			
			filtriraneKnjige = pomocnaListaKnjiga;
			
				} 
	ObservableList<Knjiga> listaKnjiga = FXCollections.observableArrayList(filtriraneKnjige); 
	knjigaTable.setItems(listaKnjiga); 
	
	}
	

}
