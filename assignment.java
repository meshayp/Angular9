import java.util.*; 
//import com.google.gson.Gson; 

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		EditorAPI edit = new EditorAPI();
		
		edit.print();
		edit.add("aaa");
		edit.print();
		edit.add("bbb");
		edit.print();
		edit.undo();
		edit.print();
		edit.redo();
		edit.print();
		edit.bold(1,2);
		edit.print();
		edit.italic(3,4);
		edit.print();
		
		
	}
}

class style
{
	Set<String> attribs = new HashSet<>();   // make set
}

class textPart 
{
	style style;
	String text;
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<div ");
		for(String key : style.attribs)
		{
			sb.append(key);
			sb.append(" = ");
			sb.append("true ");
		}
		sb.append(">");
		sb.append(text);
		sb.append("</div>");
		
		return sb.toString();
	}
}

class Operation
{
	String operation;
	List<String> data;
}

class TextSpan
{
	public TextSpan(int p_start, int p_end)
	{
		start = p_start;
		end = p_end;
	}
	
	int start;
	int end;
}

class Editor 
{
	String text = "";
	HashMap<Integer, style> styles = new HashMap<>();
	List<Operation> undo = new ArrayList<>();   // can make one list and save position
	List<Operation> redo = new ArrayList<>();
	
	private boolean fixSpan(TextSpan tSpan)
	{
		if (tSpan.start > tSpan.end ||
		    tSpan.start > text.length() ||
			tSpan.end < 0)
		{
			return false;    
		}
	
	    if (tSpan.start < 0)   // check if index is valid
		{
			tSpan.start = 0;
		}
		
		if (tSpan.end > text.length())   // check if index is valid
		{
			tSpan.end = text.length();
		}
		
		return true;
	}
	
	public Operation createOperation(Object ...args)
	{
		Operation oper = new Operation();
		oper.operation = args[0].toString();
		oper.data = new ArrayList<>();
		for(int i=1; i < args.length; i++)
		{
			oper.data.add(args[i].toString());
		}
		
		return oper;
	}
	
	public void operation(Operation item, boolean registerUndo)
	{
		boolean operationSucceeded = true;
		
		switch(item.operation)
		{
			case "add" :
			    if (item.data.size() == 1)
					add(item.data.get(0));
				else if (item.data.size() == 2)
					operationSucceeded = add(item.data.get(0), Integer.parseInt(item.data.get(1)));
				else operationSucceeded = false;
			break;
			
			case "remove" :
				if (item.data.size() == 2)
				{
					operationSucceeded = remove( Integer.parseInt(item.data.get(0)), Integer.parseInt(item.data.get(1)));
				}
				else
				{
					operationSucceeded = false;
				}
			break;
			
			case "bold" :
			case "undeline" :
			case "italic" :
			
				if (item.data.size() == 2)
				{
					operationSucceeded = putStyle(item.operation , Integer.parseInt(item.data.get(0)), Integer.parseInt(item.data.get(1)));
				}
				else
				{
					operationSucceeded = false;
				}
				
			break;
			
			case "undo" :
			   undo();
			   break;
			   
			case "redo" :
			   redo();
			   break;
		}
		
		if (operationSucceeded)
		{
				if (registerUndo && item.operation != "undo" && item.operation != "redo")
				{
					undo.add(item);
					redo.clear();
				}
		}
	}
	
	private void undo()
	{
		if (undo.size() > 0)  // check if no more undoes
		{
			redo.add(undo.get(undo.size()-1));    
			undo.remove(undo.size()-1);
			
			text = "";
			styles.clear();
			for(int i=0; i < undo.size(); i++)
			{
				operation(undo.get(i), false);
			}
		}
	}
	
	private void redo()   
	{		
	    if (redo.size() > 0)  // check if no more redues
		{
			operation(redo.get(redo.size()-1), false);  
			
			undo.add(redo.get(redo.size()-1));
			redo.remove(redo.size()-1);
		}
	}
	
	private void add(String newText)
	{
		text += newText;
	}
	
	private boolean add(String newText, int index)
	{		
	    if (index >= text.length() || index < 0)  // check if index is valid
		{
			return false;
		}
	
		text = text.substring(0, index + 1) + newText + text.substring(index + 1);    
		
		HashMap<Integer, style> newStyles = new HashMap<>();
		for(int i=index; i < index+newText.length(); i++)
		{
			if (styles.containsKey(i)) {
				newStyles.put(i+newText.length(), styles.get(i));
				styles.remove(i);
			}
		}
		
		for(int key : newStyles.keySet())
		{
			styles.put(key, newStyles.get(key));
		}
		
		return true;
	}
	
	private boolean remove(int start, int end)     
	{	 
		TextSpan tspan = new TextSpan(start, end);
	
		if (!fixSpan(tspan)) return false;
	
	
		HashMap<Integer, style> newStyles = new HashMap<>();
		for(int i=tspan.start; i < tspan.end; i++)
		{
			if (styles.containsKey(i)) {
				styles.remove(i);
			}
		}
		
		for(int i=tspan.end; i < text.length(); i++)
		{
			if (styles.containsKey(i)) {
				styles.put(i-(tspan.end-tspan.start+1), styles.get(i));
			}
		}
		
		text = text.substring(0, tspan.start + 1) + text.substring(tspan.end + 1); 
		
		return true;
	}
	
	
	
	private boolean putStyle(String style, int start, int end)   // check if index is valid
	{
		TextSpan tspan = new TextSpan(start, end);
	
		if (!fixSpan(tspan)) return false;
		
		for(int i=tspan.start; i < tspan.end; i++)
		{
			if (!styles.containsKey(i))
			{
				styles.put(i, new style());
			}
			
			styles.get(i).attribs.add(style);
		}
		
		return true;
	}
	
	public String generate()
	{
		if (text.length() == 0) return "";
		
		List<textPart> parts = new ArrayList<>();
		parts.add(new textPart());
		//parts.get(0).text += text.charAt(0);
		parts.get(0).text = "";
		parts.get(0).style = styles.get(0);
		if (parts.get(0).style == null) parts.get(0).style = new style();
		textPart currentPart = parts.get(0);
		for(int i = 0; i < text.length(); i++)
		{
			style currentStyle = new style();
			if (styles.containsKey(i))
			{
				currentStyle = styles.get(i);
			}
			
			if (!currentPart.style.attribs.equals(currentStyle.attribs))
			{
				currentPart = new textPart();
				currentPart.style = currentStyle;
				parts.add(currentPart);
			}
			
			currentPart.text += text.charAt(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < parts.size(); i++)
		{
			sb.append(parts.get(i).toString());
		}
		
		return sb.toString();
	}

}

class EditorAPI
{
     private Editor editor = new Editor();
	 
	 
	 public void bold(int start, int end)   
	{		
	    editor.operation ( editor.createOperation("bold", start, end), true );
	}
	
	public void italic(int start, int end)
	{		
		editor.operation ( editor.createOperation("italic", start, end), true );
	}
	
	public void undeline(int start, int end)
	{
		editor.operation ( editor.createOperation("undeline", start, end), true ); 
	}
	
	public void undo()
	{
		editor.operation ( editor.createOperation("undo"), false ); 
	}
	
	public void redo()
	{
		editor.operation ( editor.createOperation("redo"), false ); 
	}
	
	public void print()  
	{
		System.out.println(editor.generate());
	}
	
	public void add(String newText)
	{
		editor.operation ( editor.createOperation("add", newText), true ); 
	}
	
	public void add(String newText, int position)
	{
		editor.operation ( editor.createOperation("add", newText, position), true ); 
	}
	 
	public void remove(int start, int end)
	{
		editor.operation ( editor.createOperation("remove", start, end), true ); 
	}
}



//class EditorRestApi
//{
//	class outJson
//	{
//		Editor editor;
//		String result;
//	}
//
//	class inJson
//	{
//		Editor editor;
//		Operation commnad;
//	}
//	
//	public String command(String data)
//	{
//		Type type = new TypeToken<inJson>(){}.getType();
//        inJson inObj = gson.fromJson(data, type);
//		
//		ibObj.editor.operation(ibObj.commnad, true);
//		
//		outJson outObj = new outJson();
//		outObj.editor = inObj.editor;
//		outObj.result = outObj.editor.generate();
//		
//		Gson gson = new Gson();
//		String jsonString = gson.toJson(outObj);
//		
//		return jsonString;
//	}
//
//}
		
		
