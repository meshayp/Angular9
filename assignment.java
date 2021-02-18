class style
{
	Set<String> attribs = new HashSet();   // make set
}

class textPart 
{
	style style;
	String text;
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.add("<div ");
		for(String key : style.attribs)
		{
			sb.add(key);
			sb.add(" = ");
			sb.add("true ");
		}
		sb.add(">");
		sb.add(text);
		sb.add("</div>");
		
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
	HashMap<int, style> styles = new HashMap();
	List<UndoRedoItem> undo = new ArrayList<>();   // can make one list and save position
	List<UndoRedoItem> redo = new ArrayList<>();
	
	private boolean fixSpan(TextSpan tSpan)
	{
		if (tSpan.start > tSpan.end ||
		    tSpan.start > text.length ||
			tSpan.end < 0)
		{
			return false;    
		}
	
	    if (tSpan.start < 0)   // check if index is valid
		{
			tSpan.start = 0;
		}
		
		if (tSpan.end > text.length)   // check if index is valid
		{
			tSpan.end = text.length;
		}
		
		return true;
	}
	
	public Operation createOperation(Object ...args)
	{
		Operation oper = new Operation();
		oper.operation = args.get(0).toString;
		oper.data = new ArrayList<>();
		for(int i=1; i < args.length; i++)
		{
			oper.data.add(args.get(i).toString());
		}
		
		return oper;
	}
	
	public void operation(Operation item, boolean registerUndo = true)
	{
		boolean operationSucceeded = true;
		
		switch(item.operation)
		{
			case "add" :
			    if (item.data.length == 1)
					add(data.get(0));
				else if (item.data.length == 2)
					operationSucceeded = add(data.get(0), data.get(1));
				else operationSucceeded = false;
			break;
			
			case "remove" :
				if (item.data.length == 2)
				{
					operationSucceeded = remove(item.data.get(0), item.data.get(1));
				}
				else
				{
					operationSucceeded = false;
				}
			break;
			
			case "bold" :
			case "undeline" :
			case "italic" :
			
				if (item.data.length == 2)
				{
					operationSucceeded = putStyle(item.operation , item.data.get(0), item.data.get(1));
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
				if (registerUndo && item.operation !== "undo" && item.operation !== "redo")
				{
					undo.add(item);
					redo.clear();
				}
		}
	}
	
	private void undo()
	{
		if (undo.length > 0)  // check if no more undoes
		{
			redo.add(undo.get(undo.length-1));    
			undo.remove(undo.length-1);
			
			text = "";
			styles.clear();
			for(int i=0; i < undo.length; i++)
			{
				operation(undo.get(i), false);
			}
		}
	}
	
	private void redo()   
	{		
	    if (redo.length > 0)  // check if no more redues
		{
			operation(redo.get(redo.length-1), false);  
			
			undo.add(redo.get(redo.length-1));
			redo.remove(redo.length-1);
		}
	}
	
	private void add(String newText)
	{
		text += newText;
	}
	
	private boolean add(String newText, int index)
	{		
	    if (index >= text.length || index < 0)  // check if index is valid
		{
			return false;
		}
	
		text = text.substring(0, index + 1) + newText + text.substring(index + 1);    
		
		HashMap<int, style> newStyles = new HashMap();
		for(int i=index; i < index+newText.length)
		{
			if (styles.contains(i)) {
				newStyles.put(i+newText.length, styles.get(i));
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
		TextSpan tspan = new (start, end);
	
		if (!fixSpan(tspan)) return false;
	
	
		HashMap<int, style> newStyles = new HashMap();
		for(int i=tspan.start; i < tspan.end; i++)
		{
			if (styles.contains(i)) {
				styles.remove(i);
			}
		}
		
		for(int i=tspan.end; i < text.length; i++)
		{
			if (styles.contains(i)) {
				styles.put(i-(tspan.end-tspan.start+1), styles.get(i));
			}
		}
		
		text = text.substring(0, tspan.start + 1) + text.substring(tspan.end + 1); 
		
		return true;
	}
	
	
	
	private boolean putStyle(String style, int start, int end)   // check if index is valid
	{
		TextSpan tspan = new (start, end);
	
		if (!fixSpan(tspan)) return false;
		
		for(int i=tspan.start; i < tspan.end; i++)
		{
			if (!styles.contains(i))
			{
				styles.put(i, new style());
			}
			
			styles.get(i).put(style, true);
		}
		
		return true;
	}
	
	public String generate()
	{
		if (text.length == 0) return "";
		
		List<textPart> parts = new ArrayList<>();
		parts.add(new textPart());
		parts.get(0).text += text.get(0);
		parts.get(0).style = styles.get(0);
		textPart currentPart = parts.get(0);
		for(int i = 1; i < text.length; i++)
		{
			style currentStyle = new Style();
			if (styles.contains(i))
			{
				currentStyle = styles.get(i);
			}
			
			if (!currentPart.style.attribs.equals(currentStyle.attribs))
			{
				currentPart = new textPart();
				currentPart.style = currentStyle;
				parts.add(currentPart);
			}
			
			currentPart.text += text.get(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < parts.length; i++)
		{
			sb.add(parts.get(i).toString);
		}
		
		return sb.toString();
	}

}

class EditorAPI
{
     private Editor editor = new Editor();
	 
	 
	 public void bold(int start, int end)   
	{		
	    editor.operation ( editor.createOperation("bold", start, end) );
	}
	
	public void italic(int start, int end)
	{		
		editor.operation ( editor.createOperation("italic", start, end) );
	}
	
	public void undeline(int start, int end)
	{
		editor.operation ( editor.createOperation("undeline", start, end) ); 
	}
	
	public void undo()
	{
		editor.operation ( editor.createOperation("undo") ); 
	}
	
	public void redo()
	{
		editor.operation ( editor.createOperation("redo") ); 
	}
	
	public String print()  
	{
		System.out.print(editor.generate());
	}
	
	public void add(String newText)
	{
		editor.operation ( editor.createOperation("add", newText) ); 
	}
	
	public void add(String newText, int position)
	{
		editor.operation ( editor.createOperation("add", newText, position) ); 
	}
	 
	public void remove(int start, int end)
	{
		editor.operation ( editor.createOperation("remove", start, end) ); 
	}
}



class EditorRestApi
{
	class outJson
	{
		Editor editor;
		String result;
	}

	class inJson
	{
		Editor editor;
		Operation commnad;
	}
	
	public String command(String data)
	{
		Type type = new TypeToken<inJson>(){}.getType();
        inJson inObj = gson.fromJson(data, type);
		
		ibObj.editor.operation(ibObj.commnad);
		
		outJson outObj = new outJson();
		outObj.editor = inObj.editor;
		outObj.result = outObj.editor.generate();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(outObj);
		
		return jsonString;
	}

}
		
		
