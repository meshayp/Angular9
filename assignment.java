class style
{
	HashMap<String, boolean> attribs = new HashMap();   // make set
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

class Editor 
{
	String text = "";
	HashMap<int, style> styles = new HashMap();
	List<UndoRedoItem> undo = new ArrayList<>();   // can make one listand save position
	List<UndoRedoItem> redo = new ArrayList<>();

	public void operation(String operationName , List<String> data, boolean registerUndo = true)
	{
		if (registerUndo)
		{
			Operation item = new Operation();
			item.operation = operationName;
			item.data = data;
			undo.add(item);
			
			redo.clear();
		}
		
		switch(operationName)
		{
			case "add" :
			    if (data.length == 1)
					add(data.get(0));
				
				if (data.length == 2)
					add(data.get(0), data.get(1));
			break;
			
			case "remove" :
			    remove(data.get(0), data.get(1));
			break;
			
			case "bold" :
				bold(data.get(0), data.get(1));
			break;
			
			case "undeline" :
				undeline(data.get(0), data.get(1));
			break;
			
			case "italic" :
				italic(data.get(0), data.get(1));
			break;
			
			
		}
	}
	
	public void undo()
	{
		redo.add(undo.get(undo.length-1));    // check if no more undoes
		undo.remove(undo.length-1);
		
		text = "";
		styles.clear();
		for(int i=0; i < undo.length; i++)
		{
			operation(undo.get(i).operation, undo.get(i).data, false);
		}
	}
	
	public void redo()   
	{		
		operation(redo.get(redo.length-1).operation, redo.get(redo.length-1).data, false);  // check if no more redues
		
		undo.add(redo.get(redo.length-1));
		redo.remove(redo.length-1);
	}
	
	public void add(String newText)
	{
		text += newText;
	}
	
	public void add(String newText, int index)
	{		
		text = text.substring(0, index + 1) + newText + text.substring(index + 1);    // check if index is valid
		
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
	}
	
	public void remove(int start, int end)     // check if index is valid
	{	
		HashMap<int, style> newStyles = new HashMap();
		for(int i=start; i < end; i++)
		{
			if (styles.contains(i)) {
				styles.remove(i);
			}
		}
		
		for(int i=end; i < text.length; i++)
		{
			if (styles.contains(i)) {
				styles.put(i-(end-start), styles.get(i));
			}
		}
		
		text = text.substring(0, start + 1) + text.substring(end + 1); 
	}
	
	public void bold(int start, int end)   
	{		
		putStyle("bold", start, end)
	}
	
	public void italic(int start, int end)
	{		
		putStyle("italic", start, end)
	}
	
	public void undeline(int start, int end)
	{
		putStyle("undeline", start, end)
	}
	
	public putStyle(String style, int start, int end)   // check if index is valid
	{
		for(int i=start; i < end; i++)
		{
			if (!styles.contains(i))
			{
				styles.put(i, new style());
			}
			
			styles.get(i).put(style, true);
		}
	}
	
	public print()  
	{
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
		
		System.out.print(sb.toString());
	}
}



class EditorUI
{
     private Editor editor = new Editor();
}
		
		undo.add(undoitem);
