/*
 * 【时间原因没有实现的功能】
 * update类型的transaction（即，修改表格内容产生的记录）
 * 支持添加共享账户
 * 支持type+balance删除账户
 * 输入检查，如以ID为依据删除时只能输入int
 * 支持显示和添加利率、保护额度
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import banking.domain.*;
//import banking.reports.*;

import javafx.application.Application;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.KeyCode;
//import javafx.util.converter.IntegerStringConverter;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.input.KeyEvent;
//import java.net.URL;
//import java.util.ResourceBundle;


public class TestBanking extends Application{
	private TableView<Customer> CT = new TableView<Customer>();//customers table
	private TableView<Account> AT = new TableView<Account>();//a customer's own accounts table
	private TableView<ID> RT = new TableView<ID>();//search result table
	
	String fonclick,lonclick;
	
	final HBox hb = new HBox();
	final HBox hb2 = new HBox();
	final HBox hb3 = new HBox();//用户区三排按钮

	final HBox hb4 = new HBox();
	final HBox hb5 = new HBox();
	final HBox hb6 = new HBox();//账户区三排按钮
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {

		Bank bank=Bank.getBank();
		try {
			read();
		} catch (FileNotFoundException e1) {
			System.out.println("【can't find input file!】");
			e1.printStackTrace();
		}
		
		ObservableList<Customer> cdata = FXCollections.observableArrayList();
		for(int i=0;i<bank.getNumOfCustomers();i++) {cdata.add(bank.getCustomer(i));}//初始化用户表格
		ObservableList<Account> adata = FXCollections.observableArrayList();//声明各用户拥有账户，内容动态填充
		ObservableList<ID> rdata = FXCollections.observableArrayList();//声明查找结果的编号序列，内容动态填充
		
		Scene scene = new Scene(new Group());
		stage.setTitle("My Bank");
		stage.setWidth(1090);
		stage.setHeight(620);

		final Label customers = new Label("Customers (Click to show accounts)");
		customers.setFont(new Font("Arial", 20));
		final Label accounts = new Label("Own Accounts (Uneditable)");
		accounts.setFont(new Font("Arial", 20));
		final Label sresults = new Label("Search Results");
		sresults.setFont(new Font("Arial", 20));

		/********************************************用户区*********************************************/
		/********************************************用户表格*********************************************/
		CT.setEditable(true);
		Callback<TableColumn<Customer, String>, TableCell<Customer, String>> cellFactory = new Callback<TableColumn<Customer, String>, TableCell<Customer, String>>() {
			public TableCell<Customer, String> call(TableColumn<Customer, String> p) {
				return new EditingCell();
			}
		};

		
		TableColumn<Customer, Integer> CusIDCol = new TableColumn<Customer, Integer>("ID");
		CusIDCol.setMinWidth(120);
		CusIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
//		//【不行，不能提供更改ID的权限】
//		CusIDCol.setCellFactory(TextFieldTableCell .forTableColumn(new IntegerStringConverter()));//只有int类型的输入被认可
//		CusIDCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, Integer>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, Integer> t) {
//				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCustomerID(t.getNewValue());
//			}
//		});
		
		TableColumn<Customer, String> firstNameCol = new TableColumn<Customer, String>("First Name");
		firstNameCol.setMinWidth(120);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		firstNameCol.setCellFactory(cellFactory);
		firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				int id=t.getTableView().getItems().get(t.getTablePosition().getRow()).getCustomerID();
				String f=t.getNewValue();
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(f);
				bank.getCustomer(id).setFirstName(f);
				bank.transactions.add(new Transaction("Customer",id,"update",0,true,"change first name"));
			}
		});

		TableColumn<Customer, String> lastNameCol = new TableColumn<Customer, String>("Last Name");
		lastNameCol.setMinWidth(120);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		lastNameCol.setCellFactory(cellFactory);
		lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				int id=t.getTableView().getItems().get(t.getTablePosition().getRow()).getCustomerID();
				String l=t.getNewValue();
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(l);
				bank.getCustomer(id).setLastName(l);
				bank.transactions.add(new Transaction("Customer",id,"update",0,true,"change last name"));
			}
		});
		
		CT.setOnMouseClicked(value-> {//鼠标点击人名时展示其账户
            int now=CT.getSelectionModel().getSelectedIndex();
			if(cdata.get(now).getFirstName()!=null) {
	            String f=cdata.get(now).getFirstName(),l=cdata.get(now).getLastName();
	            fonclick=f;lonclick=l;
	            adata.remove(0,adata.size());//清空账户表
	            for(Account a:bank.getCustomer(f, l).getAccounts()){adata.add(a);}
        }});
		
		CT.setItems(cdata);
		CT.getColumns().addAll(CusIDCol,firstNameCol, lastNameCol);

		/********************************************用户按钮*********************************************/
		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		addFirstName.setMaxWidth(firstNameCol.getPrefWidth()+15);
		final TextField addLastName = new TextField();
		addLastName.setMaxWidth(lastNameCol.getPrefWidth()+15);
		addLastName.setPromptText("Last Name");

		final Button addButton = new Button("  Add  ");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String f=addFirstName.getText(),l=addLastName.getText();
				if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);cdata.add(bank.getCustomer(f, l));}//查重
				addFirstName.clear();
				addLastName.clear();
			}
		});
		
		final Button deleteButton = new Button("  Delete  ");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String f=addFirstName.getText(),l=addLastName.getText();
				if(bank.hasCustomer(f,l)) {cdata.remove(bank.getCustomer(f, l));bank.deleteCustomer(f,l);}
				//有趣的是，add先加bank实际数据，而delete后删bank实际数据
				addFirstName.clear();
				addLastName.clear();
			}
		});

		hb.getChildren().addAll(addFirstName, addLastName, addButton,deleteButton);
		hb.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列
		
		
		final TextField delTargetID = new TextField();
		delTargetID.setPromptText("Target ID");
		delTargetID.setMaxWidth(CusIDCol.getPrefWidth()+15);
		
		final Button deleteButton2 = new Button("   Delete   ");
		deleteButton2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id=Integer.parseInt(delTargetID.getText());
				if(bank.getCustomer(id)!=null) {
					cdata.remove(bank.getCustomer(id));
					bank.deleteCustomer(id);
				}
				delTargetID.clear();
			}
		});

		hb2.getChildren().addAll(delTargetID,deleteButton2);
		hb2.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列
		
		
		final TextField searchKeyword = new TextField();
		searchKeyword.setPromptText("Keyword");
		searchKeyword.setMaxWidth(CusIDCol.getPrefWidth()+15);
		
		final Button searchButton = new Button("   Search   ");
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String keyword=searchKeyword.getText();
				rdata.remove(0,rdata.size());//清空查询结果表
				sresults.setText("Search Results (keyword: "+keyword+")");
	            for(int a:bank.searchResult(keyword)){rdata.add(new ID(a));}
				searchKeyword.clear();
			}
		});

		hb3.getChildren().addAll(searchKeyword,searchButton);
		hb3.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列

		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);//设置各children的纵间隔
		vbox.setPadding(new Insets(10, 0, 0, 10));//第一项是下偏移量，最后一项是右偏移量，中间？？
		vbox.getChildren().addAll(customers, CT, hb,hb2,hb3);//把一组表格标题+表格+（输入+按钮）组合
		/******************************************用户区完***********************************************/
		
	
		/******************************************账户区***********************************************/
		/******************************************账户表格***********************************************/

		
		TableColumn<Account, Integer> AcIDCol = new TableColumn<Account, Integer>("ID");
		AcIDCol.setMinWidth(120);
		AcIDCol.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountID"));
		//【不可编辑】
		
		TableColumn<Account, String> typeCol = new TableColumn<Account, String>("Account Type");
		typeCol.setMinWidth(180);
		typeCol.setCellValueFactory(new PropertyValueFactory<Account, String>("accountType"));
		//【不可编辑】

		TableColumn<Account, String> blcCol = new TableColumn<Account, String>("Balance");
		blcCol.setMinWidth(120);
		blcCol.setCellValueFactory(new PropertyValueFactory<Account, String>("balance"));
		//【不可编辑】
		
//		TableColumn<Account, String> oiCol = new TableColumn<Account, String>("Interest Rate/ Overdraft Protection");
//		oiCol.setMinWidth(220);
//		if(oiCol.getRow()) {oiCol.setCellValueFactory(new PropertyValueFactory<Account, String>("balance"));}
//		else if() {}
//		//【不可编辑】

		AT.setItems(adata);
		AT.getColumns().addAll(AcIDCol,typeCol, blcCol);
		/********************************************账户按钮*********************************************/
		
		final TextField addType = new TextField();
		addType.setPromptText("Type (input:s/c)");
		addType.setMaxWidth(typeCol.getPrefWidth()+60);
		final TextField addBalance = new TextField();
		addBalance.setMaxWidth(blcCol.getPrefWidth()+15);
		addBalance.setPromptText("Balance");

		final Button addButton2 = new Button("  Add  ");
		addButton2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String t=addType.getText();
				double blc=Double.parseDouble(addBalance.getText());
				if(t.equalsIgnoreCase("s")) {bank.getCustomer(fonclick,lonclick).setSavingsAccount(new SavingsAccount(blc,0.03));
				 	adata.remove(0,adata.size());//清空账户表
		            for(Account a:bank.getCustomer(fonclick, lonclick).getAccounts()){adata.add(a);}}
				else if(t.equalsIgnoreCase("c")) {bank.getCustomer(fonclick,lonclick).setCheckingAccount(new CheckingAccount(blc,0));
			 		adata.remove(0,adata.size());//清空账户表
			 		for(Account a:bank.getCustomer(fonclick, lonclick).getAccounts()){adata.add(a);}}
				addType.clear();
				addBalance.clear();
			}
		});

		hb4.getChildren().addAll(addType, addBalance, addButton2);
		hb4.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列
		
		
		final TextField delTargetID2 = new TextField();
		delTargetID2.setPromptText("Target ID");
		delTargetID2.setMaxWidth(AcIDCol.getPrefWidth()+15);
		
		final Button deleteButton3 = new Button("   Delete   ");
		deleteButton3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id=Integer.parseInt(delTargetID2.getText());
				if(bank.getCustomer(fonclick,lonclick).getAccount(id)!=null) {
					adata.remove(bank.getCustomer(fonclick,lonclick).getAccount(id));
					bank.getCustomer(fonclick,lonclick).deleteAccount(id);
				}
				delTargetID2.clear();
			}
		});

		hb5.getChildren().addAll(delTargetID2,deleteButton3);
		hb5.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列
		
		
		final TextField searchBoolean = new TextField();
		searchBoolean.setPromptText("input:<(double)/>(double)");
		searchBoolean.setMaxWidth(210);
		
		final Button searchButton2 = new Button("   Search   ");
		searchButton2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String booleanE=searchBoolean.getText();
				char type=booleanE.charAt(0);
				double limit=Double.parseDouble(booleanE.substring(1));
				if(type=='<') {
					rdata.remove(0,rdata.size());//清空查询结果表
					sresults.setText("Search Results (condition: balance"+booleanE+")");
		            for(int a:bank.getCustomer(fonclick,lonclick).searchBelowResult(limit)){rdata.add(new ID(a));}
		            }
				else if(type=='>') {
					rdata.remove(0,rdata.size());//清空查询结果表
					sresults.setText("Search Results (condition: balance"+booleanE+")");
		            for(int a:bank.getCustomer(fonclick,lonclick).searchAboveResult(limit)){rdata.add(new ID(a));}
		            }
				searchBoolean.clear();
			}
		});

		hb6.getChildren().addAll(searchBoolean,searchButton2);
		hb6.setSpacing(6);//把一组输入+按钮组合，此句设置横间隔，不要太宽，会导致表格多一列

		
		final VBox vbox2 = new VBox();
		vbox2.setSpacing(5);//设置各children的纵间隔
		vbox2.setPadding(new Insets(10, 0, 0, 10));//第一项是下偏移量，最后一项是右偏移量，中间？？
		vbox2.getChildren().addAll(accounts, AT, hb4,hb5,hb6);//把一组表格标题+表格+（输入+按钮）组合
		/******************************************账户区完***********************************************/
		
		
		/******************************************结果区***********************************************/
		/******************************************结果表格***********************************************/
		TableColumn<ID, String> IDCol = new TableColumn<ID, String>("Result ID");
		IDCol.setMinWidth(120);
		IDCol.setCellValueFactory(new PropertyValueFactory<ID, String>("id"));
		//【不可编辑】

		RT.setItems(rdata);
		RT.getColumns().add(IDCol);
		
		final VBox vbox3 = new VBox();
		vbox3.setSpacing(5);//设置各children的纵间隔
		vbox3.setPadding(new Insets(10, 0, 0, 10));//第一项是下偏移量，最后一项是右偏移量，中间？？
		vbox3.getChildren().addAll(sresults,RT);//把一组表格标题+表格+（输入+按钮）组合
		/******************************************结果区完***********************************************/
		
        final HBox sum= new HBox();
        sum.getChildren().addAll(vbox,vbox2,vbox3);
		((Group) scene.getRoot()).getChildren().addAll(sum);

		
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void read() throws FileNotFoundException {
	    Bank bank = Bank.getBank();
	
	    File inputf=new File("step12input.txt");
	    if(inputf.exists()) {
	    	try(
	    			Scanner in=new Scanner(inputf);
	        		)
	    	{
	    	    // 任务1：Create bank customers and their accounts from account section of step12input.txt
	        	ArrayList<String> ac=new ArrayList<String>();
	        	int acnum=0;boolean acZone=false;
	        	ArrayList<String> tr=new ArrayList<String>();
	        	int trnum=0;boolean trZone=false;
	        	in.useDelimiter("\n|;");
	        	while(in.hasNextLine()) {
	        		String line=in.nextLine();
	        		if(line.contentEquals("")) {acZone=false;}
	        		else if(line.contentEquals("account")) {acZone=true;acnum=-1;}
	        		else if(line.contentEquals("transaction")) {trZone=true;trnum=-1;}
	        		if(acZone) {ac.add(line);acnum++;}
	        		else if(trZone) {tr.add(line);trnum++;}
	        		}
	        	for(int i=1;i<=acnum;i++) {//第一行跳过
	        		Scanner si=new Scanner(ac.get(i));
	        		si.useDelimiter(" |,|;");
	        		String f=si.next();String l=si.next();
	        		if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
	        		si.useDelimiter(",|;");
	        		double blc=0;double irate=0;double prt=0;String f0="",l0="";char sharetype=' ';
	        		int type=0;//type 1 saveAccount 2 check(withdraw)Account 3 share account
	        		while(si.hasNext()) {
	        			String t=si.next();
	        			String[] tokens=t.split(" ");
	        			switch(tokens[0]) {
	        			case "s":type=1;if(tokens[2].contentEquals("i")) {
	        				blc=Double.parseDouble(tokens[1]);irate=Double.parseDouble(tokens[3]);}
	        			    else{type=3;f0=tokens[2];l0=tokens[3];sharetype='s';}break;//share account
	        			case "c":type=2;
	        				if(tokens.length>2) {
	        					if(tokens[2].contentEquals("o")) {
		        				blc=Double.parseDouble(tokens[1]);prt=Double.parseDouble(tokens[3]);}
		        			    else{type=3;f0=tokens[2];l0=tokens[3];sharetype='c';}}
	        			    else blc=Double.parseDouble(tokens[1]);break;//share account
	        			default:;}
	    			    if(type==1) {bank.getCustomer(f,l).setSavingsAccount(new SavingsAccount(blc,irate));}
	    				else if(type==2) {
	    					if(prt==0) {bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc));}
	    					else{bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc,prt));}}
	    				else if(type==3) {
	    					if(!bank.hasCustomer(f0,l0)) {bank.addCustomer(f0,l0);}
	    				    bank.getCustomer(f, l).shareType=sharetype;
	    				    bank.getCustomer(f, l).shareFromFN=f0;
				            bank.getCustomer(f, l).shareFromLN=l0;
	    				    if(sharetype=='s') {
	    				    	if(bank.getCustomer(f0, l0).getSavingsAccount()==null){bank.getCustomer(f0, l0).setSavingsAccount(new SavingsAccount(0,0));}
	    				    	bank.getCustomer(f, l).setSavingsAccount(bank.getCustomer(f0, l0).getSavingsAccount());
	    				        System.out.println(f+"shares her/his Savings Account with "+f0+" "+l0);}
	    				    else {if(bank.getCustomer(f0, l0).getCheckingAccount()==null){bank.getCustomer(f0, l0).setCheckingAccount(new CheckingAccount());}
	    				    	bank.getCustomer(f, l).setCheckingAccount(bank.getCustomer(f0, l0).getCheckingAccount());
	    				    	System.out.println(f+" shares her/his Checking Account with "+f0+" "+l0);}
	    				    }
	    			    }
	        		    si.close();
	        	}
	        	//任务2：Demonstrate behavior of various account types according to transactions section of step12input.txt 
	        	for(int i=1;i<=trnum;i++) {//i=1略过transactions句
	        		Scanner si=new Scanner(tr.get(i));
	        		si.useDelimiter(" |,|;");
	        		String f=si.next();
	    		    String l=si.next();
	    		    if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
	    		    System.out.println("");
	    		    Account act=bank.getCustomer(f, l).getMaxAccount();//实际上测试用例取的都是max账户存取款
			    	if(act instanceof SavingsAccount)
			    		{System.out.println("Customer ["+l+", "+f+"] has a savings balance of "+act.getBalance()
			    		+" with a "+((SavingsAccount) act).getInterestRate()*100+"% interest rate.");
			    		}
			    	else {System.out.println("Customer ["+l+", "+f+"] has a checking balance of "+act.getBalance()
			    	+" with a "+((CheckingAccount) act).getOverdraftProtection()+" overdraft protection.");
		    		}
	    		    double amt=0;
	    		    int type=0;//type 1 deposit 2 withdraw
	    		    while(si.hasNext()) {
	    		    	switch(si.next()) {
	    		    	case "d":amt=si.nextDouble();type=1;break;//deposit
	    		    	case "w":amt=si.nextDouble();type=2;break;//withdraw
	    		    	default:;}
	    		    	if(bank.getCustomer(f, l).shareType!=' ') {
	    		    		String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
	    		    		if(type==1) {
	    		    			if(bank.getCustomer(f, l).shareType=='s')
	    		    			{
	        		    			System.out.print("Savings Acct ["+f0+" "+l0+"] : ");bank.getCustomer(f0,l0).getSavingsAccount().deposit(amt);}
	    		    			else 
	        		    			System.out.print("Checking Acct ["+f0+" "+l0+"] : ");bank.getCustomer(f0, l0).getCheckingAccount().deposit(amt);}
	    		    		else if(type==2) {
	    		    			if(bank.getCustomer(f, l).shareType=='s')
	    		    			{
	        		    			System.out.print("Savings Acct ["+f0+" "+l0+"] : ");try {
										bank.getCustomer(f0,l0).getSavingsAccount().withdraw(amt);
									} catch (OverdraftException e) {
										System.out.println("\n"+e.getMessage()+e.getDeficit());
									}}
	    		    			else 
	        		    			System.out.print("Checking Acct ["+f0+" "+l0+"] : ");try {
										bank.getCustomer(f0,l0).getCheckingAccount().withdraw(amt);
									} catch (OverdraftException e) {
										System.out.println("\n"+e.getMessage()+e.getDeficit());
									}}
	    		    	}
	    		    	else {
	    		    		if(type==1) {
	    		    			if(act instanceof SavingsAccount)
	    		    			{
	        		    			System.out.print("Savings Acct ["+f+" "+l+"] : ");
	        		    			bank.getCustomer(f,l).getSavingsAccount().deposit(amt);continue;
	        		    			}
	    		    			else 
	        		    			System.out.print("Checking Acct ["+f+" "+l+"] : ");bank.getCustomer(f, l).getCheckingAccount().deposit(amt);}
	    		    		else if(type==2) {
	    		    			if(act instanceof SavingsAccount)
	    		    			{
	        		    			System.out.print("Savings Acct ["+f+" "+l+"] : ");
	        		    			try {
										bank.getCustomer(f,l).getSavingsAccount().withdraw(amt);
									} catch (OverdraftException e) {
										System.out.println("\n"+e.getMessage()+e.getDeficit());
									}continue;
	        		    			}
	    		    			else 
	        		    			System.out.print("Checking Acct ["+f+" "+l+"] : ");try {
										bank.getCustomer(f,l).getCheckingAccount().withdraw(amt);
									} catch (OverdraftException e) {
										System.out.println("\n"+e.getMessage()+e.getDeficit());
									}}
	    		    	}
	        		}
	    		    if(bank.getCustomer(f, l).shareType!=' ') {
	    				String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
	    				if(bank.getCustomer(f, l).shareType=='s')
	    				{System.out.println("Cusotmer ["+l+", "+f+"] has a savings balance of "
	    				+bank.getCustomer(f0,l0).getSavingsAccount().getBalance());
	    				}
	    				else System.out.println("Cusotmer ["+l+", "+f+"] has a checking balance of "
	    				+bank.getCustomer(f0,l0).getCheckingAccount().getBalance());
	    				}
	    			else {
	    				if(act instanceof SavingsAccount)
	    				{System.out.println("Cusotmer ["+l+", "+f+"] has a savings balance of "
	    				+bank.getCustomer(f, l).getSavingsAccount().getBalance());
	    				}
	    				else System.out.println("Cusotmer ["+l+", "+f+"] has a checking balance of "
	    				+bank.getCustomer(f, l).getCheckingAccount().getBalance());
	    				}
	    		    si.close();
	    		}
	    	}
	    }
	    else {throw new FileNotFoundException();}
	    //CustomerReport cr=new CustomerReport();
	    //cr.generateReport();;
	  }
}

class EditingCell extends TableCell<Customer, String> {

    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }

        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(textField);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem();
    }
}