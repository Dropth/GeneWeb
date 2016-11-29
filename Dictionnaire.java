import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Dictionnaire
{
	private ArrayList<String> listeMotsFr;
	
	public Dictionnaire()
	{
		listeMotsFr = new ArrayList<String>();
		
		lireFichier();
	}
	
	private void lireFichier()
	{
		String nomFichier = System.getProperty("user.dir");

		nomFichier = nomFichier + "/donnees.txt";

		Scanner fichier = null;

		try
		{
			fichier = new Scanner(new File(nomFichier));

			while (fichier.hasNext())
			{
				listeMotsFr.add(fichier.next());
			}

			fichier.close();

		} catch (Exception exc)
		{
			System.out.println("Erreur fichier" + exc);
		}
	}
}
