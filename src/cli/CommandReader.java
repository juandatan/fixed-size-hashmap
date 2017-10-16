package cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class CommandReader {

	private BufferedReader reader;
	static final List<String> commandList = new ArrayList<>();
	
	protected CommandReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		// add available commands
		commandList.add("construct");
		commandList.add("set");
		commandList.add("get");
		commandList.add("delete");
		commandList.add("load");
		commandList.add("quit");
	}
	
	protected List<String> readCommand() {
		String newCommand;
		List<String> tokens = new ArrayList<>();
		StringTokenizer st;
		
		try {
			if ((newCommand = reader.readLine()) != null) {
				st = new StringTokenizer(newCommand.trim());
				while (st.hasMoreTokens()) {
					tokens.add(st.nextToken());
				}
				if (tokens.size() == 0) {
					return new ArrayList<String>();
				} else if (!commandList.contains(tokens.get(0))) {
					System.out.println("Error: Available commands are [construct <size>], [set <object>], [get <object>}, [delete <object>], [load], and [quit].");
				}
			}
			
			return tokens;
		} catch (IOException e) {
			System.out.println("Error: I/O exception.");
		}
		
		return null;
	}
	
}
