import java.util.ArrayList;
import java.util.StringTokenizer;
public class AnnotationVariant {
	private String ann;
	
	public AnnotationVariant(){
		this.ann="";
	}
	
	public AnnotationVariant(String s){
		this.ann=s;
	}
	
	public String  getAnnotationVariant(){
		return ann;
		
	}
	
	public void setAnnotationVariant(String s){
		ann=s;
	}
	
	public Tag decodeAnnotationVariant(){
		Tag lingAnno=new Tag("");
		StringTokenizer	st=new StringTokenizer(ann,"/");
		int i=0;
		String s="";
		ArrayList<Attribut> atList=new ArrayList<Attribut>();
		while(st.hasMoreTokens()){
			s=st.nextToken();
			if (i==0) lingAnno.setName(s);
			else {
				Attribut a= new Attribut(s.substring(0,s.indexOf(':')),s.substring(s.indexOf(':')+1));
				atList.add(a);
			}
		}
		if (atList.size()>0) lingAnno.setAttrList(atList);
		
		return lingAnno;
	}
	
	public void encodeAnnotationVariant(Tag t){
		ann=t.getName();
		String s="";
		if (t.getAttrList()!=null){
			for(int i=0;i<t.getAttrList().size();i++){
				if (!t.getAttrList().get(i).getName().equalsIgnoreCase("Comment")){
					if (ann.equalsIgnoreCase("Common Noun")|| ann.equalsIgnoreCase("Proper Noun")){
						if(t.getAttrList().get(i).getName().equalsIgnoreCase("gender")){
							String gs=t.getAttrList().get(i).getValue();
							gs=gs.replaceAll("S","");
							s=s+"/"+t.getAttrList().get(i).getName()+":"+gs;
						}
						else if(t.getAttrList().get(i).getName().equalsIgnoreCase("number")){
							String gs=t.getAttrList().get(i).getValue();
							if(gs.indexOf(" ")>0)
							gs=gs.substring(0,gs.indexOf(" "));
							s=s+"/"+t.getAttrList().get(i).getName()+":"+gs;
						}
						else s=s+"/"+t.getAttrList().get(i).getName()+":"+t.getAttrList().get(i).getValue();
					}
					else s=s+"/"+t.getAttrList().get(i).getName()+":"+t.getAttrList().get(i).getValue();
				}
			}
		}
		ann=ann+s;
	}

}
