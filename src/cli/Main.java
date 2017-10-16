package cli;

import java.util.List;
import hashmap.FixedSizeHashMap;

public final class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	private CommandReader cr = new CommandReader();
	private FixedSizeHashMap hm = null;
	
	public Main() {
	}
	
	private void run() {
		while (true) {
			final List<String> args = cr.readCommand();
			if (args == null) {
				System.exit(0);
			} else if (args.size() == 0) {
				continue;
			}
			
			switch (args.get(0)) {
			case "construct":
				if (args.size() != 2) {
					System.out.println("Error: Invalid usage of command [constructor <size>]");
				}
				
				int capacity;
				try {
					capacity = Integer.parseInt(args.get(1));
				} catch (NumberFormatException e) {
					System.out.println("Error: Given size must be an integer.");
					break;
				}
				
				if (capacity < 1) {
					System.out.println("Error: Given size must be greater than 1.");
					break;
				}
				
				hm = new FixedSizeHashMap(capacity);
				System.out.println("FixedSizeHashMap with size " + Integer.toString(capacity) + " constructed.");
				break;
			case "set":
				if (hm == null) {
					System.out.println("Error: FixedSizeHashMap not yet instantiated.");
					break;
				} else if (args.size() != 3) {
					System.out.println("Error: Invalid usage of command [set <key> <value>]");
					break;
				}
				
				String key = args.get(1);
				Object value = args.get(2);
				if (hm.set(key, value)) {
					System.out.println("Object " + value.toString() + " added to FixedSizeHashMap with key " + "\"" + key + "\".");
				} else {
					System.out.println("Error: Hashmap has reached capacity.");
				}
				break;
			case "get":
				if (hm == null) {
					System.out.println("Error: FixedSizeHashMap not yet instantiated.");
					break;
				} else if (args.size() != 2) {
					System.out.println("Error: Invalid usage of command [get <key>]");
					break;
				}
				
				Object got = hm.get(args.get(1));
				if (got == null) {
					System.out.println("No existing object in FixedSizeHashMap with given key.");
				} else {
					System.out.println("Object " + got.toString() + " retrieved.");
				}
				break;
			case "delete":
				if (hm == null) {
					System.out.println("Error: FixedSizeHashMap not yet instantiated.");
					break;
				} else if (args.size() != 2) {
					System.out.println("Error: Invalid usage of command [delete <key>]");
					break;
				}
				Object deleted = hm.delete(args.get(1));
				if (deleted == null) {
					System.out.println("No existing object in FixedSizeHashMap with given key.");
				} else {
					System.out.println("Object " + deleted.toString() + " removed.");
				}
				break;
			case "load":
				if (hm == null) {
					System.out.println("Error: FixedSizeHashMap not yet instantiated.");
					break;
				} else if (args.size() != 1) {
					System.out.println("Error: Invalid usage of command [load]");
					break;
				}
				System.out.println("Load is now " + Float.toString(hm.load()) + ".");
				break;
			case "quit":
				if (args.size() != 1) {
					System.out.println("Error: Invalid usage of command [quit]");
					break;
				}
				System.out.println("Exiting...");
				System.exit(0);
				break;
			}
		}
	}
	
}
