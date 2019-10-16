import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GeezFrame extends JInternalFrame{
	Font etiopic1=new Font("TITUS Cyberbit Basic",Font.PLAIN,14);
	static JTextArea  variant;
	 JTextArea variant1;
	 String inhaltAlt;
	 String inhaltNew; 
	public GeezFrame() {
		super ("Ge\'ez Keyboad");
		setIconifiable(true);
		setMaximizable(true);
		this.setResizable(true);
		 
		this.setClosable(true);
		this.setFont(etiopic1);
		setBackground(Color.lightGray);
		Container p = this.getContentPane();
		 p.setFont(etiopic1);
		 
		 
		p.setLayout(new GridLayout(7,6));
		
		ActionListener insertEtiopicChar = new ActionListener(){
			
			
			@Override public void actionPerformed(ActionEvent e){
				JComboBox currentCombo = (JComboBox)e.getSource();
				String inhaltAlt= variant.getText();
			    inhaltNew = currentCombo.getSelectedItem().toString();
				//System.out.println("to insert "+ inhaltAlt+inhaltNew);
				variant.setText(inhaltAlt+inhaltNew);
			}
			
			
			};
	      JComboBox c1 = new JComboBox();
	      c1.setFont(etiopic1);
		 c1.addItem("\u1200"); 
		 c1.addItem("\u1201");
		 c1.addItem("\u1202");
		 c1.addItem("\u1203");
		 c1.addItem("\u1204");
		 c1.addItem("\u1205");
		 c1.addItem("\u1206");
		 c1.addActionListener(insertEtiopicChar);
		 p.add(c1);
		 
		JComboBox c2 = new JComboBox();
		c2.setFont(etiopic1);
		 c2.addItem("\u1208"); 
		 c2.addItem("\u1209");
		 c2.addItem("\u120A");
		 c2.addItem("\u120B");
		 c2.addItem("\u120C");
		 c2.addItem("\u120D");
		 c2.addItem("\u120E");
		 c2.addActionListener(insertEtiopicChar);
		 p.add(c2);
		 
		 JComboBox c3 = new JComboBox();
		 c3.setFont(etiopic1);
		 c3.addItem("\u1210"); 
		 c3.addItem("\u1211");
		 c3.addItem("\u1212");
		 c3.addItem("\u1213");
		 c3.addItem("\u1214");
		 c3.addItem("\u1215");
		 c3.addItem("\u1216");
		 c3.addActionListener(insertEtiopicChar);
		 p.add(c3);
		 
		 JComboBox c4 = new JComboBox();
		 c4.setFont(etiopic1);
		 c4.addItem("\u1218"); 
		 c4.addItem("\u1219");
		 c4.addItem("\u121A");
		 c4.addItem("\u121B");
		 c4.addItem("\u121C");
		 c4.addItem("\u121D");
		 c4.addItem("\u121E");
		 c4.addActionListener(insertEtiopicChar);
		 p.add(c4);
		 
		 JComboBox c5 = new JComboBox();
		 c5.setFont(etiopic1);
		 c5.addItem("\u1220"); 
		 c5.addItem("\u1221");
		 c5.addItem("\u1222");
		 c5.addItem("\u1223");
		 c5.addItem("\u1224");
		 c5.addItem("\u1225");
		 c5.addItem("\u1226");
		 c5.addActionListener(insertEtiopicChar);
		 p.add(c5);
		 
		 JComboBox c6 = new JComboBox();
		 c6.setFont(etiopic1);
		 c6.addItem("\u1228"); 
		 c6.addItem("\u1229");
		 c6.addItem("\u122A");
		 c6.addItem("\u122B");
		 c6.addItem("\u122C");
		 c6.addItem("\u122D");
		 c6.addItem("\u122E");
		 c6.addActionListener(insertEtiopicChar);
		 p.add(c6);
		 
		 JComboBox c7 = new JComboBox();
		 c7.setFont(etiopic1);
		 c7.addItem("\u1230"); 
		 c7.addItem("\u1231");
		 c7.addItem("\u1232");
		 c7.addItem("\u1233");
		 c7.addItem("\u1234");
		 c7.addItem("\u1235");
		 c7.addItem("\u1236");
		 c7.addActionListener(insertEtiopicChar);
		 p.add(c7);
		 
		 JComboBox c8 = new JComboBox();
		 c8.setFont(etiopic1);
		 c8.addItem("\u1240"); 
		 c8.addItem("\u1241");
		 c8.addItem("\u1242");
		 c8.addItem("\u1243");
		 c8.addItem("\u1244");
		 c8.addItem("\u1245");
		 c8.addItem("\u1246");
		 c8.addActionListener(insertEtiopicChar);
		 p.add(c8);
		 
		 JComboBox c9 = new JComboBox();
		 c9.setFont(etiopic1);
		 c9.addItem("\u1260"); 
		 c9.addItem("\u1261");
		 c9.addItem("\u1262");
		 c9.addItem("\u1263");
		 c9.addItem("\u1264");
		 c9.addItem("\u1265");
		 c9.addItem("\u1266");
		 c9.addActionListener(insertEtiopicChar);
		 p.add(c9);
		 
		 JComboBox c10 = new JComboBox();
		 c10.setFont(etiopic1);
		 c10.addItem("\u1270"); 
		 c10.addItem("\u1271");
		 c10.addItem("\u1272");
		 c10.addItem("\u1273");
		 c10.addItem("\u1274");
		 c10.addItem("\u1275");
		 c10.addItem("\u1276");
		 c10.addActionListener(insertEtiopicChar);
		 p.add(c10);
		 
		 JComboBox c11 = new JComboBox();
		 c11.setFont(etiopic1);
		 c11.addItem("\u1280"); 
		 c11.addItem("\u1281");
		 c11.addItem("\u1282");
		 c11.addItem("\u1283");
		 c11.addItem("\u1284");
		 c11.addItem("\u1285");
		 c11.addItem("\u1286");
		 c11.addActionListener(insertEtiopicChar);
		 p.add(c11);
		 
		 JComboBox c12 = new JComboBox();
		 c12.setFont(etiopic1);
		 c12.addItem("\u1290"); 
		 c12.addItem("\u1291");
		 c12.addItem("\u1292");
		 c12.addItem("\u1293");
		 c12.addItem("\u1294");
		 c12.addItem("\u1295");
		 c12.addItem("\u1296");
		 c12.addActionListener(insertEtiopicChar);
		 p.add(c12);
		 
		 JComboBox c13 = new JComboBox();
		 c13.setFont(etiopic1);
		 c13.addItem("\u12A0"); 
		 c13.addItem("\u12A1");
		 c13.addItem("\u12A2");
		 c13.addItem("\u12A3");
		 c13.addItem("\u12A4");
		 c13.addItem("\u12A5");
		 c13.addItem("\u12A6");
		 c13.addActionListener(insertEtiopicChar);
		 p.add(c13);
		 
		 JComboBox c14 = new JComboBox();
		 c14.setFont(etiopic1);
		 c14.addItem("\u12A8"); 
		 c14.addItem("\u12A9");
		 c14.addItem("\u12AA");
		 c14.addItem("\u12AB");
		 c14.addItem("\u12AC");
		 c14.addItem("\u12AD");
		 c14.addItem("\u12AE");
		 c14.addActionListener(insertEtiopicChar);
		 p.add(c14);
		 
		 JComboBox c15 = new JComboBox();
		 c15.setFont(etiopic1);
		 c15.addItem("\u12C8"); 
		 c15.addItem("\u12C9");
		 c15.addItem("\u12CA");
		 c15.addItem("\u12CB");
		 c15.addItem("\u12CC");
		 c15.addItem("\u12CD");
		 c15.addItem("\u12CE");
		 c15.addActionListener(insertEtiopicChar);
		 p.add(c15);
		 

		 JComboBox c16 = new JComboBox();
		 c16.setFont(etiopic1);
		 c16.addItem("\u12D0"); 
		 c16.addItem("\u12D1");
		 c16.addItem("\u12D2");
		 c16.addItem("\u12D3");
		 c16.addItem("\u12D4");
		 c16.addItem("\u12D5");
		 c16.addItem("\u12D6");
		 c16.addActionListener(insertEtiopicChar);
		 p.add(c16);
		 
		 JComboBox c17 = new JComboBox();
		 c17.setFont(etiopic1);
		 c17.addItem("\u12D8"); 
		 c17.addItem("\u12D9");
		 c17.addItem("\u12DA");
		 c17.addItem("\u12DB");
		 c17.addItem("\u12DC");
		 c17.addItem("\u12DD");
		 c17.addItem("\u12DE");
		 c17.addActionListener(insertEtiopicChar);
		 p.add(c17);
		 
		 JComboBox c18 = new JComboBox();
		 c18.setFont(etiopic1);
		 c18.addItem("\u12E8"); 
		 c18.addItem("\u12E9");
		 c18.addItem("\u12EA");
		 c18.addItem("\u12EB");
		 c18.addItem("\u12EC");
		 c18.addItem("\u12ED");
		 c18.addItem("\u12EE");
		 c18.addActionListener(insertEtiopicChar);
		 p.add(c18);
		 
		 JComboBox c19 = new JComboBox();
		 c19.setFont(etiopic1);
		 c19.addItem("\u12F0"); 
		 c19.addItem("\u12F1");
		 c19.addItem("\u12F2");
		 c19.addItem("\u12F3");
		 c19.addItem("\u12F4");
		 c19.addItem("\u12F5");
		 c19.addItem("\u12F6");
		 c19.addActionListener(insertEtiopicChar);
		 p.add(c19);
		 
		 JComboBox c20 = new JComboBox();
		 c20.setFont(etiopic1);
		 c20.addItem("\u1308"); 
		 c20.addItem("\u1309");
		 c20.addItem("\u130A");
		 c20.addItem("\u130B");
		 c20.addItem("\u130C");
		 c20.addItem("\u130D");
		 c20.addItem("\u130E");
		 c20.addActionListener(insertEtiopicChar);
		 p.add(c20);
		 
		 JComboBox c21 = new JComboBox();
		 c21.setFont(etiopic1);
		 c21.addItem("\u1320"); 
		 c21.addItem("\u1321");
		 c21.addItem("\u1322");
		 c21.addItem("\u1323");
		 c21.addItem("\u1324");
		 c21.addItem("\u1325");
		 c21.addItem("\u1326");
		 c21.addActionListener(insertEtiopicChar);
		 p.add(c21);
		 
		 JComboBox c22 = new JComboBox();
		 c22.setFont(etiopic1);
		 c22.addItem("\u1330"); 
		 c22.addItem("\u1331");
		 c22.addItem("\u1332");
		 c22.addItem("\u1333");
		 c22.addItem("\u1334");
		 c22.addItem("\u1335");
		 c22.addItem("\u1336");
		 c22.addActionListener(insertEtiopicChar);
		 p.add(c22);
		 
		 JComboBox c23 = new JComboBox();
		 c23.setFont(etiopic1);
		 c23.addItem("\u1338"); 
		 c23.addItem("\u1339");
		 c23.addItem("\u133A");
		 c23.addItem("\u133B");
		 c23.addItem("\u133C");
		 c23.addItem("\u133D");
		 c23.addItem("\u133E");
		 c23.addActionListener(insertEtiopicChar);
		 p.add(c23);
		 
		 JComboBox c24 = new JComboBox();
		 c24.setFont(etiopic1);
		 c24.addItem("\u1340"); 
		 c24.addItem("\u1341");
		 c24.addItem("\u1342");
		 c24.addItem("\u1343");
		 c24.addItem("\u1344");
		 c24.addItem("\u1345");
		 c24.addItem("\u1346");
		 c24.addActionListener(insertEtiopicChar);
		 p.add(c24);
		 
		 JComboBox c25 = new JComboBox();
		 c25.setFont(etiopic1);
		 c25.addItem("\u1348"); 
		 c25.addItem("\u1349");
		 c25.addItem("\u134A");
		 c25.addItem("\u134B");
		 c25.addItem("\u134C");
		 c25.addItem("\u134D");
		 c25.addItem("\u134E");
		 c25.addActionListener(insertEtiopicChar);
		 p.add(c25);
		 
		 JComboBox c26 = new JComboBox();
		 c26.setFont(etiopic1);
		 c26.addItem("\u1350"); 
		 c26.addItem("\u1351");
		 c26.addItem("\u1352");
		 c26.addItem("\u1353");
		 c26.addItem("\u1354");
		 c26.addItem("\u1355");
		 c26.addItem("\u1356");
		 c26.addActionListener(insertEtiopicChar);
		 p.add(c26);
		 
		 JComboBox c27 = new JComboBox();
		 c27.setFont(etiopic1);
		 c27.addItem("\u1248"); 
		 c27.addItem("\u1249");
		 c27.addItem("\u124A");
		 c27.addItem("\u124B");
		 c27.addItem("\u124C");
		 c27.addItem("\u124D");
		 c27.addActionListener(insertEtiopicChar);
		 p.add(c27);
		 
		 JComboBox c28 = new JComboBox();
		 c28.setFont(etiopic1);
		 c28.addItem("\u1288"); 
		 c28.addItem("\u1289");
		 c28.addItem("\u128A");
		 c28.addItem("\u128B");
		 c28.addItem("\u128C");
		 c28.addItem("\u128D");
		 c28.addActionListener(insertEtiopicChar);
		 p.add(c28);
		 
		 JComboBox c29 = new JComboBox();
		 c29.setFont(etiopic1);
		 c29.addItem("\u13B0"); 
		 c29.addItem("\u13B1");
		 c29.addItem("\u13B2");
		 c29.addItem("\u13B3");
		 c29.addItem("\u13B4");
		 c29.addItem("\u13B5");
		 c29.addActionListener(insertEtiopicChar);
		 p.add(c29);
		 
		 JComboBox c30 = new JComboBox();
		 c30.setFont(etiopic1);
		 c30.addItem("\u1310"); 
		 c30.addItem("\u1311");
		 c30.addItem("\u1312");
		 c30.addItem("\u1313");
		 c30.addItem("\u1314");
		 c30.addItem("\u1315");
		 c30.addActionListener(insertEtiopicChar);
		 p.add(c30);
		 
		 JComboBox c31 = new JComboBox();
		 c31.setFont(etiopic1);
		 c31.addItem("\u1238"); 
		 c31.addItem("\u1239");
		 c31.addItem("\u123A");
		 c31.addItem("\u123B");
		 c31.addItem("\u123C");
		 c31.addItem("\u123D");
		 c31.addItem("\u123E");
		 c31.addActionListener(insertEtiopicChar);
		 p.add(c31);
		 
		 JComboBox c32 = new JComboBox();
		 c32.setFont(etiopic1);
		 c32.addItem("\u1268"); 
		 c32.addItem("\u1269");
		 c32.addItem("\u126A");
		 c32.addItem("\u126B");
		 c32.addItem("\u126C");
		 c32.addItem("\u126D");
		 c32.addItem("\u126E");
		 c32.addActionListener(insertEtiopicChar);
		 p.add(c32);
		 
		 JComboBox c33 = new JComboBox();
		 c33.setFont(etiopic1);
		 c33.addItem("\u1278"); 
		 c33.addItem("\u1279");
		 c33.addItem("\u127A");
		 c33.addItem("\u127B");
		 c33.addItem("\u127C");
		 c33.addItem("\u127D");
		 c33.addItem("\u127E");
		 c33.addActionListener(insertEtiopicChar);
		 p.add(c33);
		 
		 JComboBox c34 = new JComboBox();
		 c34.setFont(etiopic1);
		 c34.addItem("\u1298"); 
		 c34.addItem("\u1299");
		 c34.addItem("\u129A");
		 c34.addItem("\u129B");
		 c34.addItem("\u129C");
		 c34.addItem("\u129D");
		 c34.addItem("\u129E");
		 c34.addActionListener(insertEtiopicChar);
		 p.add(c34);
		 
		 JComboBox c35 = new JComboBox();
		 c35.setFont(etiopic1);
		 c35.addItem("\u12B8"); 
		 c35.addItem("\u12B9");
		 c35.addItem("\u12BA");
		 c35.addItem("\u12BB");
		 c35.addItem("\u12BC");
		 c35.addItem("\u12BD");
		 c35.addItem("\u12BE");
		 c35.addActionListener(insertEtiopicChar);
		 p.add(c35);
		 
		 JComboBox c36 = new JComboBox();
		 c36.setFont(etiopic1);
		 c36.addItem("\u12C0"); 
		 c36.addItem("\u12C1");
		 c36.addItem("\u12C2");
		 c36.addItem("\u12C3");
		 c36.addItem("\u12C4");
		 c36.addItem("\u12C5");
		 c36.addActionListener(insertEtiopicChar);
		 p.add(c36);
		 
		 JComboBox c37 = new JComboBox();
		 c37.setFont(etiopic1);
		 c37.addItem("\u12E0"); 
		 c37.addItem("\u12E1");
		 c37.addItem("\u12E2");
		 c37.addItem("\u12E3");
		 c37.addItem("\u12E4");
		 c37.addItem("\u12E5");
		 c37.addItem("\u12E6");
		 c37.addActionListener(insertEtiopicChar);
		 p.add(c37);
		 
		 JComboBox c38 = new JComboBox();
		 c38.setFont(etiopic1);
		 c38.addItem("\u1300"); 
		 c38.addItem("\u1301");
		 c38.addItem("\u1302");
		 c38.addItem("\u1303");
		 c38.addItem("\u1304");
		 c38.addItem("\u1305");
		 c38.addItem("\u1306");
		 c38.addActionListener(insertEtiopicChar);
		 p.add(c38);
		 
		 JComboBox c39 = new JComboBox();
		 c39.setFont(etiopic1);
		 c39.addItem("\u1328"); 
		 c39.addItem("\u1329");
		 c39.addItem("\u132A");
		 c39.addItem("\u132B");
		 c39.addItem("\u132C");
		 c39.addItem("\u132D");
		 c39.addItem("\u132E");
		 c39.addActionListener(insertEtiopicChar);
		 p.add(c39);
		 
		 JComboBox c40 = new JComboBox();
		 c40.setFont(etiopic1);
		 c40.addItem("\u1361");
		 c40.addItem("\u1362");
		 c40.addItem("\u1360"); 
		 c40.addItem("\u1363");
		 c40.addItem("\u1364");
		 c40.addItem("\u1365");
		 c40.addItem("\u1367");
		 c40.addItem("\u1368");
		 c40.addActionListener(insertEtiopicChar);
		 p.add(c40);
		 
		 JComboBox c41 = new JComboBox();
		 c41.setFont(etiopic1);
		 c41.addItem("\u1369"); 
		 c41.addItem("\u136A");
		 c41.addItem("\u136A");
		 c41.addItem("\u136C");
		 c41.addItem("\u136D");
		 c41.addItem("\u136E");
		 c41.addItem("\u136F");
		 c41.addItem("\u1370"); 
		 c41.addItem("\u1371");
		 c41.addItem("\u1372");
		 c41.addItem("\u1373");
		 c41.addItem("\u1374");
		 c41.addItem("\u1375");
		 c41.addItem("\u1377");
		 c41.addItem("\u1378");
		 c41.addItem("\u1379");
		 c41.addItem("\u137A");
		 c41.addItem("\u137B");
		 c41.addItem("\u137C");
		 c41.addActionListener(insertEtiopicChar);
		 p.add(c41);
	}
	public static void setInputField(JTextArea v){
		variant=v;
	}

}
