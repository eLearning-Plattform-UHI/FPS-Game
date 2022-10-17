package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import cbr_util.CBREngine;
import model.Request;

/**
 * 
 * Diese Klasse beinhaltet die Main-Methode und stellt somit den Einstiegspunkt
 * f&uuml;r das Programm dar. Innerhalb dieser Klasse wird ein neuer Thread
 * f&uuml;r die TCP/IP-Verbindung erstellt.
 * 
 * @author Jannis Hillmann
 *
 */
public class CBRSystem {

	/**
	 * Singleton f&uuml;r den Zugriff auf das CBR-System.
	 */
	private static CBREngine engine;

	/**
	 * Diese Methode stellt den zentralen Punkt des CBR-System dar. Hier wird
	 * die Verbindung vom C#-Projekt akzeptiert, die Daten in Empfang genommen,
	 * das Retrieval angesto&szlig;en und die Antwort zur&uuml;ck an das
	 * C#-Projekt gesendet.
	 * 
	 * @param port
	 *            Der Port, auf welchem das Programm Verbindungen akzeptieren
	 *            soll.
	 */
	public static void receive(int port) {
		while (true) {

			ServerSocket serverSocket = null;

			Socket socket = null;
			InputStream in = null;
			OutputStream out = null;

			try {

				serverSocket = new ServerSocket(port);

				socket = serverSocket.accept();

				in = socket.getInputStream();

				out = socket.getOutputStream();

				String clientSentence;

				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in));
				clientSentence = inFromClient.readLine();
				System.out.println("clientSentence: " + clientSentence);
				Gson gson = new Gson();

				Request request = gson.fromJson(clientSentence, Request.class);
				System.out.println("Received in java: " + request.toString());

				String player = request.getSituation().getPlayer();

				handlePlayerCaseBase(player);

				System.out.println("executing retrieval...");
				String toC = gson.toJson(engine.executeRetrieval(request));

				System.out.println("CB Size: " + engine.getCaseBaseForPlayer(player).getCases().size());
				
				

				writeToFile("Estimated Plan: " + toC);
				System.out.println("Status: " + toC);
				System.out.println("Next Case");

				out.write(toC.toString().concat("\r\n").getBytes());
				out.flush();
				System.out.println("toC sent!");

			} catch (IOException exc) {
				System.out.println(exc.getMessage());
				writeToFile(exc.getMessage());
			} finally {
				try {
					engine.getCbrProject().save();
					out.close();
					in.close();
					socket.close();
					serverSocket.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * Diese Methode schreibt einen gegebenen Text (String) in eine Log-Datei.
	 * Diese Methode dient ausschlie&szlig;ich f&uuml;r Log-Zwecke.
	 * 
	 * @param text
	 *   Der zu loggende Text.
	 */
	public static void writeToFile(String text) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(System.getProperty("user.dir"), "JavaLog.txt"), true);
			fw.write(text);
			fw.write("\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Diese Methode &uuml;berpr&uuml;ft, ob f&uuml;r einen &uuml;bergebenen
	 * Spieler bereits eine Fallbasis existiert. Falls keine existiert, wird
	 * eine angelegt und mit den Startf&auml;llen bef&uuml;llt.
	 * 
	 * @param name
	 *            Der Name des Spielers, f&uuml;r den eine Fallbasis angelegt
	 *            werden soll.
	 */
	private static void handlePlayerCaseBase(String name) {

		if (!engine.caseBaseForPlayerAlreadyExists(name)) {
			try {
				engine.createCaseBaseForPlayer(name);
				engine.addDefaultCases(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Singleton-Getter.
	 * 
	 * @return Die einzige Instanz der {@link CBREngine} Klasse.
	 */
	public static CBREngine getEngine() {
		return engine;
	}

	/**
	 * In der main-Methode wird die {@link CBREngine} initialisiert und es wird
	 * ein neuer Thread f&uuml;r die TCP/IP-Verbindung erzeugt.
	 * 
	 * @param args
	 *            Als Parameter wird der Port erwartet, auf dem die
	 *            Kommunikation stattfinden soll. Der Port wird vom C#-Projekt
	 *            festgelegt.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int port;

		PrintStream out = new PrintStream(new FileOutputStream("Java-Console_Output.txt"));
		System.setOut(out);
		if (args.length == 0) {
			port = 5555;
		} else {
			port = Integer.parseInt(args[0]);
		}
		System.out.println("Starting server on port " + port);

		engine = CBREngine.getInstance();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					receive(port);
				}
			}
		}).start();
	}
}