import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import java.util.List;
import java.util.UUID;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.StringReader;

public class ReadNE {
	TagGUI interf;
	public ReadNE(TagGUI interf){
		this.interf=interf;
	}
	private final String USER_AGENT = "Mozilla/5.0";
	//String urlServer="https://betamasaheft.aai.uni-hamburg.de/";
	String urlServer="https://betamasaheft.eu/api/";
	String url="";
	//public static void main(String[] args) {
		public ArrayList<NamedEntity> readNEFile(String fileName){
		ArrayList<NamedEntity> nelist =new ArrayList<NamedEntity>();
		try{
		InputStreamReader r = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
		System.out.println("read");
		String ind=IOUtils.toString(r);
		ind=ind.replace("\uFEFF", "");
		InputStream stream = new ByteArrayInputStream(ind.getBytes("UTF-8"));
		org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
		saxBuilder.setIgnoringBoundaryWhitespace(true);
		saxBuilder.setIgnoringElementContentWhitespace(true);
		org.jdom2.Document doc = saxBuilder.build(stream);
		List listNEs=doc.getRootElement().getChildren("ne");
		System.out.println("Detected NEs " +listNEs.size());
		 ArrayList<Attribut> alist=new ArrayList<Attribut>();
		 
		for (int i=0;i<listNEs.size();i++){
			System.out.println(" NE no. "+i);
			String ne="no id";
			String neTyp="";
			if (((Element)listNEs.get(i)).hasAttributes()) {
				neTyp=((Element)listNEs.get(i)).getAttributeValue("typ");
				alist=new ArrayList<Attribut>();
			}
			if (((Element)listNEs.get(i)).getChild("id")!=null){
				ne=((Element)listNEs.get(i)).getChild("id").getTextNormalize();
				url=urlServer+ne+"/tei";
				System.out.println("URL "+url);
				String infoNE=sendGet(url);
				
				//infoNE=infoNE.trim();
              // System.out.println(infoNE);
             alist=new ArrayList<Attribut>();
             
				if (!infoNE.equals("Error")){
					stream = new ByteArrayInputStream(infoNE.getBytes("UTF-8"));
					doc =saxBuilder.build(stream) ;
					Namespace ns=doc.getRootElement().getNamespace();
					/*Namespace ns = Namespace.getNamespace("",
					         "https://www.tei-c.org/ns/1.0");*/
					System.out.println("Space "+ns.toString());
				if (neTyp.equals("date")){
						alist=new ArrayList<Attribut>();
						ne="";
					}
					else {
						if(ne.substring(0,3).equals("PRS")){
						
						String persN=doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("title",ns).getTextNormalize();
						System.out.println("Name "+persN);
						alist.add(new Attribut("Name",persN));
						String persS="";
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns)!=null){
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).hasAttributes())
							{ 
							persS=doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).getAttributeValue("sex");
							alist.add(new Attribut("Sex",persS));
							}
						}
						System.out.println("Sex "+persS);
						String persO="";
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns)!=null){
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).getChild("occupation",ns)!=null)
							{
							persO=doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).getChild("occupation",ns).getTextNormalize();
							alist.add(new Attribut("Occupation",persO));
							//System.out.println("Ocup"+persO);
							}
					}
						System.out.println("Occup "+persO);
					/* String persR="";
					 if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns)!=null)
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns)!=null){
							if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).getChild("persName",ns)!=null) {
						List<Element> pN=doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPerson",ns).getChild("person",ns).getChildren("persName",ns);			
						if(pN!=null){
							boolean found=false;
							int j=0;
							while((j<pN.size())&&(!found)){
								if((!pN.get(i).hasAttributes())&&(pN.get(i).getChild("roleName",ns)!=null)){
									persR=pN.get(i).getChild("roleName",ns).getTextNormalize();
									alist.add(new Attribut("Role",persR));
									found=true;
								}
								else j=j+1;
							}
						}
						}
						}*/
						neTyp="person";
						//System.out.println("Role "+persR);
						
						System.out.println(persN+" "+persS+" "+persO);
					}
					else if(ne.substring(0,3).equals("LOC")){
					
						System.out.println("READ");
						Element el=doc.getRootElement();
						System.out.println("No. children "+el.getChildren().size());
						for (int k=0;k<el.getChildren().size();k++) {
							System.out.println(el.getChildren().get(k).getName());
							//System.out.println(el.getChildren().get(k).getChildren().get(0).getName());
						}
						 el=el.getChild("teiHeader",ns);
						System.out.println("No. children "+el.getChildren().size());
						el=el.getChild("fileDesc",ns);
						System.out.println("No. children "+el.getChildren().size());
						el=el.getChild("titleStmt",ns);
						System.out.println("No. children "+el.getChildren().size());
						String locN=doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("title",ns).getValue();
						//String locN=doc.getRootElement().getChild("teiHeader").getChild("fileDesc").getChild("titleStmt").getChild("title").getValue();
						System.out.println("Name "+locN);
						alist.add(new Attribut("Name",locN));
						String locT="";
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPlace",ns)!=null)
						if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPlace",ns).getChild("place",ns)!=null)
								if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPlace",ns).getChild("place",ns).hasAttributes())
							{ 
							if(doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPlace",ns).getChild("place",ns).getAttribute("type",ns)!=null) {
							locT=doc.getRootElement().getChild("text",ns).getChild("body",ns).getChild("listPlace",ns).getChild("place",ns).getAttributeValue("type");
							System.out.println("loc type "+locT);
							alist.add(new Attribut("Type",locT));
							}
							}
						//System.out.println("place type "+locT);
						System.out.println(locN);
						neTyp="place";
					}
					else if(ne.substring(0,3).equals("LIT")){
						
						String t=doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("title",ns).getTextNormalize();
						System.out.println("Name "+t);
						alist.add(new Attribut("Name",t));
						String auth="";
						if(doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("author",ns)!=null)
							{ 
							auth=doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("author",ns).getTextNormalize();
							alist.add(new Attribut("Author",auth));
							}
						System.out.println("Author "+auth);
						System.out.println(t+" "+auth);
						neTyp="work";
					}
				}	
				}
			}
			ArrayList<RefWord> refw=new ArrayList<RefWord>();
			List<Element> listW= ((Element)listNEs.get(i)).getChildren("w");
			String name="";String wid="";
			for(int j=0;j<listW.size();j++){
				name=name+listW.get(j).getAttributeValue("tr")+ " ";
				wid=listW.get(j).getAttributeValue("id");
				System.out.println("WID="+wid);
				System.out.println("TID="+interf.getWords().get(interf.getIndexIdWords().get(wid).intValue()).getTokenIds());
				refw.add(new RefWord(wid,interf.getWords().get(interf.getIndexIdWords().get(wid).intValue()).getTokenIds()));
			}
			name=name.substring(0, name.length()-1);
			NamedEntity n=new NamedEntity("N"+UUID.randomUUID(), ne,neTyp,refw,alist,true,false);
			nelist.add(n);
			System.out.println(ne+" "+ name);
		}
		}
		catch (JDOMException e) {
			System.out.println("Error JDOM");
		} 
		catch(Exception e){System.out.println("Error file");}
		return nelist;
	}
		
		private String sendGet(String url){

			try{	

				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				// optional default is GET
				con.setRequestMethod("GET");

				//add request header
				con.setRequestProperty("User-Agent",USER_AGENT);
				con.setRequestProperty("Accept", "*/*");
				int responseCode = con.getResponseCode();
				//if (responseCode!=200)
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);
		if (responseCode==200){
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream(),"UTF-8"));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					//response.append("\n");
				}
				in.close();
			
				return response.toString();
		}
		else return "Error";
			}
		catch(Exception e){ return "Error";}
				//print result
				//System.out.println(response.toString());

			}

}
